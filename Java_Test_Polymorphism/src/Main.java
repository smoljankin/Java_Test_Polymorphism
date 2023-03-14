import java.util.*;
import java.util.stream.Collectors;
import java.math.BigDecimal;

abstract class Shape {
    private String name;

    public Shape(String name) {
        this.name = name;
    }
    public Shape() {
        name= null;
    }

    public String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public String toString() {
        return "[" + this.name + "], Properties [" + this.getProperties() + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shape s = (Shape) o;
        return this.getUniqueKey().equals(s.getUniqueKey());
    }

    @Override
    public int hashCode() {
        if (name == null) {
            return (int)getArea();
        }
        return (int)getArea() +  getName().hashCode();
    }

    abstract public double getArea();
    abstract protected String getProperties();
    abstract protected String getUniqueKey();
}

class Circle extends Shape {
    private double radius;

    public Circle(double r) {
        super("Circle");
        radius = r;
    }
    public Circle(String name , double r) {
        super(name);
        radius = r;
    }

    @Override
    public double getArea() {
        return Math.PI * radius*radius;
    }

    public double getRadius() {
        return this.radius;
    }

    protected String getUniqueKey() {
        return this.getName() + this.getRadius();
    }

    protected String getProperties() {
        return "Radius: " + this.getRadius() + ", Area: " + this.getArea();
    }
}

class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double h, double w) {
        super("Rectangle");
        width = w;
        height = h;
    }
    public Rectangle(String name, double h, double w) {
        super(name);
        width = w;
        height = h;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    protected String getUniqueKey() {
        return this.getName() + this.width + "" + this.height;
    }

    protected String getProperties() {
        return "Width: " + this.getWidth() + ", Height: " + this.getHeight() + ", Area: " + this.getArea();
    }
}




class MyUtils {

    public  List<Shape> maxAreas(List<Shape> shapes) {
        return shapes.stream()
                .sorted(Comparator.comparing(Shape::getArea, Comparator.reverseOrder()))
                .limit(3).collect(Collectors.toList());
    }

    public void printList(List<Shape> shapes) {
        for (Shape s: shapes) {
            System.out.println(s);
        }
    }


}

public class Main {


    public static void main(String ... args)
    {
        ArrayList<Shape> al = new ArrayList<>();
        al.add(new Circle(2.0));
        al.add(new Rectangle(2.0, 3.0));
        al.add(new Circle(1.0));
        al.add(new Rectangle(3.0, 2.0));
        al.add(new Circle(0.5));
        al.add(new Rectangle(1.0, 2.0));
        al.add(new Circle(2.0));
        al.add(new Rectangle(1.0, 6.0));
        MyUtils myUtils = new MyUtils();
        List<Shape> newList = myUtils.maxAreas(al);
        myUtils.printList(newList);


    }



}
