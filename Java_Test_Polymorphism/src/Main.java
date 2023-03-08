import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

abstract class Shape {
    private String name;

    public Shape(String name) {
        this.name = name;
    }
    public Shape() {
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
    public void printList(List<Shape> shapes) {
        for (Shape s: shapes) {
            System.out.println(s);
        }
    }

    public  List<Shape> maxAreas(List<Shape> shapes) {
        return shapes.stream()
                .sorted(Comparator.comparing(Shape::getArea, Comparator.reverseOrder()))
                .limit(3).collect(Collectors.toList());
    }


/*
    public List<Shape> maxAreas(List<Shape> shapes) {
        Map<String, Double> maxAreas = new HashMap<>();

        for (Shape s: shapes) {
            if (!maxAreas.containsKey(s.getName())) {
                // As we calculate area, it can't be negetive.
                maxAreas.put(s.getName(), 0.);
            }
            if (s.getArea() > maxAreas.get(s.getName())) {
                maxAreas.put(s.getName(), s.getArea());
            }
        }

        // check what we have in maximum areas for each type of shapes
        // maxAreas.forEach((k, v) -> System.out.println(k + " " + v));

        List<Shape> filteredShapes = new ArrayList<>();
        for (Shape s: shapes) {
            if (s.getArea() == maxAreas.get(s.getName()) && !filteredShapes.contains(s)) {
                filteredShapes.add(s);
            }
        }

        return filteredShapes;
    }

    */
}



public class Main {
    public static void checkTwoEqualCircles() {
        System.out.println("[checkTwoEqualCircles]\n");
        Circle c = new Circle(0.5);
        ArrayList<Shape> al = new ArrayList<>();
        al.add(c);
        al.add(c);
        MyUtils myUtils = new MyUtils();
        List<Shape> newList = myUtils.maxAreas(al);
        myUtils.printList(newList);
        System.out.println("*****************************");
    }

    public static void checkTwoEqualRectangles() {
        System.out.println("[checkTwoEqualRectangles]\n");
        Rectangle r = new Rectangle(1.5, 12.0);
        ArrayList<Shape> al = new ArrayList<>();
        al.add(r);
        al.add(new Rectangle(1.5, 12.0));
        MyUtils myUtils = new MyUtils();
        List<Shape> newList = myUtils.maxAreas(al);
        myUtils.printList(newList);
        System.out.println("*****************************");
    }

    public static void checkCirclesAndRectangles() {
        System.out.println("[checkCirclesAndRectangles]\n");
        ArrayList<Shape> al = new ArrayList<>();
        al.add(new Circle(0.5));
        al.add(new Rectangle(2.0, 3.0));
        al.add(new Circle(1.0));
        al.add(new Rectangle(3.0, 2.0));
        al.add(new Circle(2.0));
        al.add(new Rectangle(2.0, 1.0));
        al.add(new Circle(2.0));
        MyUtils myUtils = new MyUtils();
        List<Shape> newList = myUtils.maxAreas(al);
        myUtils.printList(newList);
        System.out.println("*****************************");
    }

    public static void checkUniqueAll() {
        System.out.println("[checkUniqueAll]\n");
        ArrayList<Shape> al = new ArrayList<>();
        al.add(new Circle(0.5));
        al.add(new Rectangle(2.0, 3.0));
        al.add(new Circle(15.35));
        al.add(new Rectangle(12.0, 35.0));
        MyUtils myUtils = new MyUtils();
        List<Shape> newList = myUtils.maxAreas(al);
        myUtils.printList(newList);
        System.out.println("*****************************");
    }

    public static void checkOneRectangle() {
        System.out.println("[checkOneRectangle]\n");
        ArrayList<Shape> al = new ArrayList<>();
        al.add(new Rectangle(2.0, 3.0));
        MyUtils myUtils = new MyUtils();
        List<Shape> newList = myUtils.maxAreas(al);
        myUtils.printList(newList);
        System.out.println("*****************************");
    }

    public static void main(String ... args) {
        checkTwoEqualCircles();
        checkTwoEqualRectangles();
        checkCirclesAndRectangles();
        checkUniqueAll();
        checkOneRectangle();
    }
}
