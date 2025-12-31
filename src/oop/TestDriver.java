package oop;

import java.io.*;
import java.util.*;
import java.util.Locale;

public class TestDriver {

    public static void main(String[] args) {

        List<Drawable> shapes;

        try {
            shapes = readShapes();
        } 
        catch (IllegalArgumentException e) {
            System.out.println("ERROR: " + e.getMessage());
            return;
        } 
        catch (Exception e) {
            return;
        }

        printShapes(shapes);

        double sumAreas = calculateSumAreas(shapes);

        writeSumToFile(sumAreas);

        showGUI(shapes);
    }

    private static List<Drawable> readShapes() {

        List<Drawable> shapes = new ArrayList<>();
        File inputFile = new File("input.txt");

        if (!inputFile.exists()) {
            System.out.println("input.txt not found – program will continue safely.");
            return shapes;
        }

        try (Scanner in = new Scanner(inputFile)) {

            int n = 0;

            if (in.hasNextLine()) {
                try {
                    n = Integer.parseInt(in.nextLine().trim());
                } 
                catch (NumberFormatException e) {
                    System.out.println("Invalid number of shapes.");
                    return shapes;
                }
            }

            if (n < 2) {
                throw new IllegalArgumentException("Number of shapes must be >=2");
            }

            int count = 0;

            while (in.hasNextLine() && count < n) {

                String line = in.nextLine().trim();
                if (line.isEmpty()) continue;

                Scanner lineScanner = new Scanner(line).useLocale(Locale.US);

                if (!lineScanner.hasNext()) continue;

                String type = lineScanner.next().toLowerCase();

                if (!lineScanner.hasNextDouble()) {
                    System.out.println("Invalid value skipped for shape: " + type);
                    continue;
                }

                double value = lineScanner.nextDouble();

                if (value <= 0) {
                    System.out.println(
                        "Invalid size ignored for shape: " + type + " (" + value + ")"
                    );
                    continue;
                }

             
                String color = null;
                if (lineScanner.hasNext()) {
                    color = lineScanner.next();
                }

                switch (type) {

                    case "circle":
                        if (color != null) {
                            shapes.add(new Circle(color, value));
                        } else {
                            shapes.add(new Circle(value));
                        }
                        count++;
                        break;

                    case "cube":
                        if (color != null) {
                            shapes.add(new Cube(color, value));
                        } else {
                            shapes.add(new Cube(value));
                        }
                        count++;
                        break;

                    default:
                        System.out.println("Unknown shape ignored: " + type);
                }
            }

            if (count == 0) {
                throw new IllegalArgumentException("No valid shapes were found.");
            }

        } 
        catch (IllegalArgumentException e) {
            throw e;
        } 
        catch (Exception e) {
            System.out.println("File read error handled safely.");
        }

        return shapes;
    }

    private static void printShapes(List<Drawable> shapes) {

        for (Drawable d : shapes) {
            System.out.println(d.toString());
        }
    }

    private static double calculateSumAreas(List<Drawable> shapes) {

        double sum = 0;

        for (Drawable d : shapes) {
            if (d instanceof Shape) {
                sum += ((Shape) d).getArea();
            }
        }

        return sum;
    }

    private static void writeSumToFile(double sum) {

        try (PrintWriter out = new PrintWriter("sumAreas.txt")) {
            out.println("Sum of Areas = " + sum);
        } 
        catch (IOException e) {
            System.out.println("Output file error handled safely.");
        }
    }

    private static void showGUI(List<Drawable> shapes) {
        ShapeGUI.showGUI(shapes);
    }
}
