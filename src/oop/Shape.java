package oop;

import java.util.Date;

public abstract class Shape implements Drawable {
    
    private final Date dateCreated;
    private String color;

    public Shape(){
        
        dateCreated = new Date();
        color="Blue";
        
    }
    
 public Shape(String color) {
     
    dateCreated = new Date(); 
    setColor(color);
    
 }

   public Date getDateCreated() {
        
        return new Date(dateCreated.getTime());       
    }
 
    public  String getColor() {
        
        return color;
        
    }

    public void setColor(String color) {
        
         if (color == null || color.trim().isEmpty()) {
            this.color = "Blue";
         }
        
         else {
            this.color = color;
        }
        
    }
    
    public abstract double getArea();
    public abstract double getPerimeter();

    @Override
    public String toString() {
        
    return "Shape [color=" + color + ", dateCreated=" + dateCreated + "]";
    
    }
    
}
