package lecture04.Item23;

abstract class AdvancedFigure {
    abstract double area();
}

class Circle extends AdvancedFigure {
    final double radius;

    Circle(double radius) { this.radius = radius; }

    @Override double area() { return Math.PI * (radius * radius); }
}

class Rectangle extends AdvancedFigure {
    final double length;
    final double width;

    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override double area() { return length * width; }
}