import java.io.BufferedReader;
import java.io.FileReader;

interface Shape{
    double Perimeter();
    double Area();
}

class Circle implements Shape{
    private double radius;

    public Circle(double radius) throws Exception {
        if (radius <= 0) {
            throw new Exception("Радиус не может быть меньше или равен нулю");
        }

        this.radius = radius;
    }

    @Override
    public double Perimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public double Area() {
        return Math.PI * radius * radius;
    }
}

class Square implements Shape{
    private double side;

    public Square(double side) throws Exception {
        if (side <= 0) {
            throw new Exception("Сторона квадрата не может быть меньше или равна нулю");
        }

        this.side = side;
    }

    @Override
    public double Perimeter() {
        return 4 * side;
    }

    @Override
    public double Area() {
        return side * side;
    }
}

class Rectangle implements Shape{
    private double length;
    private double width;

    public Rectangle(double length, double width) throws Exception{
        if (length <= 0 | width <= 0) {
            throw new Exception("Сторона прямоугольника не может быть меньше или равна нулю");
        }

        this.length = length;
        this.width = width;
    }

    @Override
    public double Perimeter() {
        return (2 * length) + (2 * width);
    }

    @Override
    public double Area() {
        return length * width;
    }
}

public class Main {
    private static String[] values;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("shapes.txt"));

        String line;
        while ((line = br.readLine()) != null) {
            values = line.split(",");
        }

        try {
            double radius = Double.parseDouble(values[0]);
            double side = Double.parseDouble(values[1]);
            double length = Double.parseDouble(values[2]);
            double width = Double.parseDouble(values[3]);

            Circle circle = new Circle(radius);
            Square square = new Square(side);
            Rectangle rectangle = new Rectangle(length, width);

            System.out.println("Круг:");
            System.out.println("Периметр: " + circle.Perimeter() + ", площадь: " + circle.Area());

            System.out.println("Квадрат:");
            System.out.println("Периметр: " + square.Perimeter() + ", площадь: " + square.Area());

            System.out.println("Прямоугольник:");
            System.out.println("Периметр: " + rectangle.Perimeter() + ", площадь: " + rectangle.Area());
        } catch (Exception e) {
            System.out.println("Что-то пошло не так :(");
        } finally{
            br.close();
        }
    }
}