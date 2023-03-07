import org.omg.CORBA.Object;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class Shape {
    private String name;
    Shape(String name){
        this.name = name;
    }


    abstract public  double  getArea();
    public  String  getName()
    {
        return name;
    }
}
class Circle extends Shape {
    double radius;
    public Circle(double r, String name)
    {
        super(name);
        radius = r;
    }
    @Override
    public double  getArea()
    {
        return Math.PI * radius*radius;
    }

}
class Rectangle extends Shape {

    double width, height;
    public Rectangle(double h, double w, String name)
    {
        super(name);
        width = w;
        height = h;
    }
    @Override
    public double  getArea()
    {
        return width * height;
    }

}
class MyUtils {
    public List<Shape> maxAreas(List<Shape> shapes) {
        List<Shape> circle = new ArrayList<Shape>();
        List<Shape> rectangle = new ArrayList<Shape>();
        for(int i = 0; i < shapes.size(); i++)
        {
            if(shapes.get(i).getName() == "circle")
            {

                circle.add(shapes.get(i));
            }
            if(shapes.get(i).getName() == "rectangle")
            {
                rectangle.add(shapes.get(i));
            }
        }

        double max = circle.get(0).getArea();
        int index = 0;
        ArrayList<Integer> index1 = new ArrayList<>();
        for(int i = 0; i < circle.size(); i++)
        {
            if(max <= circle.get(i).getArea())
            {
                max = circle.get(i).getArea();
                index = i;
                index1.add(i);
            }

        }

        List<Shape> shapes_2 = new ArrayList<Shape>();
        for(int i = 0; i <index1.size(); i++)
        {
            shapes_2.add(circle.get(index1.get(i)));
        }


        max = rectangle.get(0).getArea();
        index = 0;
        ArrayList<Integer> index2 = new ArrayList<>();
        for(int i = 0; i < rectangle.size(); i++)
        {
            if(max <= rectangle.get(i).getArea())
            {
                max = rectangle.get(i).getArea();
                index = i;
                index2.add(i);
            }

        }
        for(int i = 0; i < index2.size(); i++)
        {
            shapes_2.add(rectangle.get(index2.get(i)));
        }
        //shapes_2.add(rectangle.get(index));
        return shapes_2;
    }
}



public class Main {


    public static void main(String ... args)
    {

        Circle c = new Circle(2.0,"circle");
        Circle c2 = new Circle(1.0,"circle");
        Circle c3 = new Circle(0.5,"circle");

        Rectangle r = new Rectangle(2.0, 3.0, "rectangle");
        Rectangle r2 = new Rectangle(3.0, 2.0, "rectangle");
        Rectangle r3 = new Rectangle(1.0, 2.0, "rectangle");

        ArrayList<Shape> al = new ArrayList<Shape>();
        al.add(c);
        al.add(r);
        al.add(c2);
        al.add(r2);
        al.add(c3);
        al.add(r3);

        MyUtils myUtils = new MyUtils();
        List<Shape> l =  myUtils.maxAreas(al);

        for(int i = 0; i < l.size(); i++)
        {
            System.out.println(l.get(i).getName() + " area = " + l.get(i).getArea());
        }


    }

}
