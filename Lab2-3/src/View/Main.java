package View;
import Model.ITransport;
import Model.Bike;
import Model.Car;
import Model.Motor;
import Repository.Repository;
import Repository.MyRepo;
import Controller.Controller;
import Exception.MyException;

//Intr-o parcare exista masini, motociclete
//si biciclete. Sa se afiseze toate vehiculele
//de culoare rosie.


public class Main {
    public static final String COLOR_CONSTANT = "Red";

    public static void main(String[] args) {
        Repository r = new MyRepo(10);
        Controller c = new Controller(r);

        //  ADD ENTITIES
        try {
            c.add(new Bike("FirstBike", "Green"));
            c.add(new Bike("SecondBike", "Red"));
            c.add(new Motor("FirstMotor", "Red"));
            c.add(new Motor("SecondMotor", "Blue"));
            c.add(new Car("FirstCar", "Red"));
            c.add(new Car("SecondCar", "Red"));
            System.out.println("\n");
        } catch (MyException ex) {
            System.out.println(ex.getMessage());
        }

        //  DELETE ENTITIES
        try {
            c.delete(2);    //  DELETE FIRST MOTOR
            c.delete(4);    //  DELETE SECOND CAR
            System.out.println("\n");
        } catch(MyException ex) {
            System.out.println(ex.getMessage());
        }

        //  FILTER AFTER GIVEN COLOR
        System.out.println("Show transport of red color:\n");

        //  AFTER DELETE AND FILTER WE SHOULD HAVE:
        //      SECOND BIKE
        //      FIRST CAR
        System.out.println(c.filterColor(COLOR_CONSTANT));
    }
}
