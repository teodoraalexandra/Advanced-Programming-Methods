package Model;

public class Motor implements ITransport {
    private String name;
    private String color;

    public Motor(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Motor " + name + " " + color;
    }
}
