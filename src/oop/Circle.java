package oop;

public class Circle extends Shape implements Drawable {

    private double radius;
    
    public Circle(){
         super();
        this.radius = 1;
    }

    public Circle(double radius) {
        super();
        this.radius =  Math.max(radius, 1);
    }

    public Circle(String color, double radius) {
        super(color);
        this.radius =  Math.max(radius, 1.0);
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius =Math.max(radius, 1.0) ;
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
    public String howToDraw() {
        return "Draw a Circle for radius " + radius;
    }

    @Override
    public String toString() {
        return "Circle{" + "radius = " + radius + "  Perimeter = "+getPerimeter()+"  Area = "+getArea()+"  color = "+getColor()+"}";
    }
   
}
