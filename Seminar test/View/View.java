package View;
import Controller.Controller;
import Domain.exp.*;
import Repository.IRepository;
import Repository.Repository;

public class View {
        public static void main(String[] args) throws Exception {
                /*EXAMPLE WITH ADD*/
                Exp example1 = new AND(new ValueExp(1), new ValueExp(0)); //return 0
                example1.eval();
                IRepository repoEx1 = new Repository(example1);
                Controller myControllerEx1 = new Controller(repoEx1);
                System.out.println(myControllerEx1.run());

                /*EXAMPLE WITH OR*/
                Exp example2 = new OR(new ValueExp(0), new ValueExp(1)); //return 1
                example1.eval();
                IRepository repoEx2 = new Repository(example2);
                Controller myControllerEx2 = new Controller(repoEx2);
                System.out.println(myControllerEx2.run());

                /*EXAMPLE WITH NOT*/
                Exp example3 = new NOT(new ValueExp(0)); //return 1
                example3.eval();
                IRepository repoEx3 = new Repository(example3);
                Controller myControllerEx3 = new Controller(repoEx3);
                System.out.println(myControllerEx3.run());
        }
}
