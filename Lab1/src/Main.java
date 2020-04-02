class Animal {
    public String toString()
    {
       return("I'm an animal");
    }
}

class Dog extends Animal {
    public String toString()
    {
        return(super.toString() + "\nI'm also a dog");
    }
}

class Person {
    public static String person_name = "";
    public static void getName(String name) {
        person_name = name;
    }

    String person_name_instance = "";
    public void getNameInstance(String name) {
        this.person_name_instance = name;
    }
}

public class Main {
    static int divideByZero(int a, int b)
    {
        int i = a/b;
        return i;
    }

    static int computeDivision(int a, int b) {
        int res =0;

        try
        {
            res = divideByZero(a,b);
        }
        catch(NumberFormatException ex)
        {
            System.out.println("NumberFormatException is occured");
        }
        return res;
    }

    public static void main(String args[])
    {
        Dog dog1 = new Dog();
        System.out.println(dog1.toString());

        // Accessing the static method getName() and field by class name itself.
        Person.getName("Teodora");
        System.out.println(Person.person_name);

        // Accessing the instance method getNameInstance()
        Person person1 = new Person();
        person1.getNameInstance("Alexandra");
        System.out.println(person1.person_name_instance);

        //EXCEPTION
        int a = 1;
        int b = 0;
        try {
            int i = computeDivision(a, b);
        }
        catch (ArithmeticException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
