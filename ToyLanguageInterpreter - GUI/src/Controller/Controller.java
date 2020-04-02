package Controller;
import Domain.adt.MyIStack;
import Domain.stmt.IStmt;
import Domain.values.RefValue;
import Domain.values.Value;
import Repository.IRepository;
import Domain.PrgState;
import Exception.MyException;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.prefs.PreferencesFactory;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Map;

public class Controller {
    private IRepository repo;
    private ExecutorService executor;

    public Controller(IRepository r) {this.repo = r;}

    //START GARBAGE COLLECTOR
    private Map<Integer, Value> garbageCollector(List<Integer> symTableAddr, List<Integer> heapAddr, Map<Integer, Value> heap) {
        Map<Integer, Value> symMap = (Map<Integer, Value>)heap.entrySet().stream().filter((e) -> {
            return symTableAddr.contains(e.getKey());
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        Map<Integer, Value> heapMap = (Map<Integer, Value>)heap.entrySet().stream().filter((e) -> {
            return heapAddr.contains(e.getKey()) && !symTableAddr.contains(e.getKey());
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        symMap.putAll(heapMap);
        return symMap;
    }

    private List<Integer> getAddrFromSymTable(Collection<Value> symTableValues) {
        return (List<Integer>)symTableValues.stream().filter((v) -> {
            return v instanceof RefValue;
        }).map((v) -> {
            RefValue v1 = (RefValue)v;
            return v1.getAddr();
        }).collect(Collectors.toList());
    }

    private List<Integer> getAddrFromHeap(Collection<Integer> heap) {
        return new ArrayList<>(heap);
    }

    private PrgState oneStep(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getExeStack1();
        if (stk.isEmpty()) {
            throw new MyException("Prg state stack is empty");
        } else {
            IStmt crtStmt = (IStmt)stk.pop();
            System.out.println(crtStmt.toString());
            return crtStmt.execute(state);
        }
    }
    //END GARBAGE COLLECTOR

    private List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }

    private void oneStepForAllPrg(List<PrgState> prgList, List<PrgState> copy) throws InterruptedException {
        //before the execution, print the PrgState List into the log file
        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //RUN concurrently one step for each of the existing PrgStates
        // -----------------------------------------------------------------------
        // prepare the list of callables
        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>)(p::oneStep))
                .collect(Collectors.toList());

        //start the execution of the callables
        //it returns the list of new created PrgStates (namely threads)
        List<PrgState> newPrgList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        try {
                            return future.get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }).filter(p -> p != null)
                .collect(Collectors.toList());

        //add the new created threads to the list of existing threads
        prgList.addAll(newPrgList);
        copy.addAll(newPrgList);
        //after the execution, print the PrgState List into the log file
        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        //Save the current programs in the repository
        repo.setPrgList(prgList);
        repo.setCopy(prgList);
    }

    public List<PrgState> getPrgList() {
        return repo.getPrgList();
    }

    public int getPrgListSize() {
        List<PrgState> prgList = removeCompletedPrg(repo.getPrgList());
        return prgList.size();
    }

    public void allStep() throws InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        //remove the completed programs
        List<PrgState> prgList = removeCompletedPrg(repo.getPrgList());
        List<PrgState> copy = repo.getCopy();
        //while (prgList.size() > 0) {
            prgList.forEach(prgState -> {
                prgState.getHeap1().setContent(
                        (HashMap<Integer, Value>) this.garbageCollector
                                (this.getAddrFromSymTable
                                                (prgState.getSymTable1().getContent().values()),

                                        this.getAddrFromHeap
                                                (prgState.getHeap1().getContent().keySet()),
                                        prgState.getHeap1().getContent()));
            });
            oneStepForAllPrg(prgList, copy);
            //remove the completed programs
            prgList = removeCompletedPrg(repo.getPrgList());
        //}

        executor.shutdownNow();

        //HERE the repository still contains at least one Completed Prg
        // and its List<PrgState> is not empty. Note that oneStepForAllPrg calls the method
        // setPrgList of repository in order to change the repository
        // update the repository state

        repo.setPrgList(prgList);
        repo.setCopy(copy);
    }


    public void executeOneStep()
    {
        executor = Executors.newFixedThreadPool(8);
        removeCompletedPrg(repo.getPrgList());
        List<PrgState> programStates = repo.getPrgList();
        if(programStates.size() > 0)
        {
            try {
                oneStepForAllPrg(repo.getPrgList(), repo.getCopy());
            } catch (InterruptedException e) {
                System.out.println();
            }
            programStates.forEach(e -> {
                try {
                    repo.logPrgStateExec(e);
                } catch (IOException e1) {
                    System.out.println();
                }
            });
            removeCompletedPrg(repo.getPrgList());
            executor.shutdownNow();
        }
    }

    public IRepository getRepository() {
        return repo;
    }
}
