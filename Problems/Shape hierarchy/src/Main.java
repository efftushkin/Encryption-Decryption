abstract class Shape {
    abstract double getPerimeter();
    abstract double getArea();
}

class Triangle extends Shape {
    double firstSide;
    double secondSide;
    double thirdSide;

    public Triangle(double firstSide, double secondSide, double thirdSide) {
        this.firstSide = firstSide;
        this.secondSide = secondSide;
        this.thirdSide = thirdSide;
    }

    @Override
    double getPerimeter() {
        return firstSide + secondSide + thirdSide;
    }

    @Override
    double getArea() {
        double s = (firstSide + secondSide + thirdSide) / 2;
        return Math.sqrt(s * (s - firstSide) * (s - secondSide) * (s - thirdSide));
    }
}

class Rectangle extends Shape {
    double firstSide;
    double secondSide;

    public Rectangle(double firstSide, double secondSide) {
        this.firstSide = firstSide;
        this.secondSide = secondSide;
    }

    @Override
    double getPerimeter() {
        return 2 * (firstSide + secondSide);
    }

    @Override
    double getArea() {
        return firstSide * secondSide;
    }
}

class Circle extends Shape {
    double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    double getArea() {
        return Math.PI * radius * radius;
    }
}
