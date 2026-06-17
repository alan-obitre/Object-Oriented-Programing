abstract class Shape {
    protected String color;
    protected boolean filled;

    public Shape(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }

    public abstract double getArea();

    public abstract double getPerimeter();

    public abstract void resize(double factor) throws InvalidShapeException;

    @Override
    public String toString() {
        return "Color=" + color + ", Filled=" + filled;
    }
}

class InvalidShapeException extends Exception {
    public InvalidShapeException(String message) {
        super(message);
    }
}

class Circle extends Shape {
    private double radius;

    public Circle(String color, boolean filled, double radius)
            throws InvalidShapeException {
        super(color, filled);

        if (radius <= 0)
            throw new InvalidShapeException("Radius must be positive.");

        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public void resize(double factor) throws InvalidShapeException {
        if (factor <= 0)
            throw new InvalidShapeException("Resize factor must be positive.");

        radius *= factor;
    }

    @Override
    public String toString() {
        return "Circle [radius=" + radius + "] " + super.toString();
    }
}

class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(String color, boolean filled,
                     double width, double height)
            throws InvalidShapeException {
        super(color, filled);

        if (width <= 0 || height <= 0)
            throw new InvalidShapeException(
                    "Width and height must be positive.");

        this.width = width;
        this.height = height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public void resize(double factor) throws InvalidShapeException {
        if (factor <= 0)
            throw new InvalidShapeException("Resize factor must be positive.");

        width *= factor;
        height *= factor;
    }

    @Override
    public String toString() {
        return "Rectangle [width=" + width +
                ", height=" + height + "] " + super.toString();
    }
}

class Triangle extends Shape {
    private double side1;
    private double side2;
    private double side3;

    public Triangle(String color, boolean filled,
                    double side1, double side2, double side3)
            throws InvalidShapeException {
        super(color, filled);

        if (side1 <= 0 || side2 <= 0 || side3 <= 0)
            throw new InvalidShapeException(
                    "All sides must be positive.");

        if (side1 + side2 <= side3 ||
                side1 + side3 <= side2 ||
                side2 + side3 <= side1)
            throw new InvalidShapeException(
                    "Triangle inequality violated.");

        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    @Override
    public double getArea() {
        double s = getPerimeter() / 2;
        return Math.sqrt(
                s * (s - side1) * (s - side2) * (s - side3));
    }

    @Override
    public double getPerimeter() {
        return side1 + side2 + side3;
    }

    @Override
    public void resize(double factor) throws InvalidShapeException {
        if (factor <= 0)
            throw new InvalidShapeException("Resize factor must be positive.");

        side1 *= factor;
        side2 *= factor;
        side3 *= factor;
    }

    @Override
    public String toString() {
        return "Triangle [side1=" + side1 +
                ", side2=" + side2 +
                ", side3=" + side3 + "] " + super.toString();
    }
}

public class Shapes {

    public static void printAreas(Shape[] shapes) {
        System.out.println("Areas of Shapes:");
        for (Shape s : shapes) {
            System.out.printf("%s%nArea = %.2f%n%n",
                    s, s.getArea());
        }
    }

    public static Shape largest(Shape[] shapes) {
        Shape largest = shapes[0];

        for (int i = 1; i < shapes.length; i++) {
            if (shapes[i].getArea() > largest.getArea()) {
                largest = shapes[i];
            }
        }
        return largest;
    }

    public static void main(String[] args) {

        try {

            Shape[] shapes = {
                    new Circle("Red", true, 5),
                    new Rectangle("Blue", false, 4, 6),
                    new Triangle("Green", true, 3, 4, 5)
            };

            printAreas(shapes);

            Shape largestShape = largest(shapes);

            System.out.println("Largest Shape:");
            System.out.println(largestShape);
            System.out.printf("Area = %.2f%n%n",
                    largestShape.getArea());

            shapes[0].resize(2);

            System.out.println("After resizing the circle by factor 2:");
            System.out.printf("New Area = %.2f%n%n",
                    shapes[0].getArea());

            // Demonstrate exception handling
            Triangle invalidTriangle =
                    new Triangle("Yellow", true, 1, 2, 10);

        } catch (InvalidShapeException e) {
            System.out.println("Exception Caught: "
                    + e.getMessage());
        }
    }
}