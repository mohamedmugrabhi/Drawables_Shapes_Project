package oop;

public class Cube extends ThreeDShape implements Drawable {

    private double side;
    
    public Cube(){
        super();
        this.side= 1;
    }

    public Cube(double side) {
        super();
        this.side = Math.max(side, 1.0);
    }

    public Cube(String color, double side) {
        super(color);
        this.side = Math.max(side, 1.0);
    }

    public double getSide() {
        return side;
    }

    @Override
    public double getArea() {
        return 6 * side * side;
    }

    @Override
    public double getPerimeter() {
        return 0;
    }

    @Override
    public double getVolume() {
        return side * side * side;
    }

    @Override
    public String howToDraw() {
        return "Draw a Cube for side " + side;
    }

    @Override
    public String toString() {
        return "Cube{" + " side = " + side +"  Volume = "+ getVolume()+" Area = "+getArea()+" color = "+getColor()+"}";
    }
  
}
