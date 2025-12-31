package oop;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ShapeGUI extends JPanel {

    private final List<Drawable> shapes;

    private static final int GAP = 60;
    private static final int BASE_Y = 250;
    private static final int DEPTH = 30;

    private static final int CIRCLE_SIZE = 200;
    private static final int CUBE_SIZE   = 160;

    public ShapeGUI(List<Drawable> shapes) {
        this.shapes = shapes;
        setBackground(Color.WHITE);

        int panelWidth =
                shapes.size() * (Math.max(CIRCLE_SIZE, CUBE_SIZE) + GAP);

        setPreferredSize(new Dimension(panelWidth, 400));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));

        int x = 50;

        for (Drawable d : shapes) {

            if (d instanceof Circle) {
                Circle c = (Circle) d;
                Color color = getAWTColor(c.getColor());
                drawCircle(g2, x, color);
                x += CIRCLE_SIZE + GAP;
            }

            else if (d instanceof Cube) {
                Cube c = (Cube) d;
                Color color = getAWTColor(c.getColor());
                drawCube(g2, x, color);
                x += CUBE_SIZE + GAP;
            }
        }
    }

    /* ================= Drawing ================= */

    private void drawCircle(Graphics2D g2, int x, Color color) {

        int y = BASE_Y - CIRCLE_SIZE;

        g2.setColor(color);
        g2.fillOval(x, y, CIRCLE_SIZE, CIRCLE_SIZE);

        g2.setColor(Color.BLACK);
        g2.drawOval(x, y, CIRCLE_SIZE, CIRCLE_SIZE);
    }

    private void drawCube(Graphics2D g2, int x, Color color) {

        int y = BASE_Y - CUBE_SIZE;
        int d = DEPTH;

        // الوجه الأمامي
        g2.setColor(color);
        g2.fillRect(x, y, CUBE_SIZE, CUBE_SIZE);

        // الوجه العلوي
        Polygon top = new Polygon(
                new int[]{x, x + d, x + CUBE_SIZE + d, x + CUBE_SIZE},
                new int[]{y, y - d, y - d, y},
                4
        );
        g2.setColor(color.brighter());
        g2.fillPolygon(top);

        // الوجه الجانبي
        Polygon side = new Polygon(
                new int[]{x + CUBE_SIZE, x + CUBE_SIZE + d,
                          x + CUBE_SIZE + d, x + CUBE_SIZE},
                new int[]{y, y - d, y + CUBE_SIZE - d, y + CUBE_SIZE},
                4
        );
        g2.setColor(color.darker());
        g2.fillPolygon(side);

        // الحواف
        g2.setColor(Color.BLACK);
        g2.drawRect(x, y, CUBE_SIZE, CUBE_SIZE);
        g2.drawPolygon(top);
        g2.drawPolygon(side);

        // الحواف الخلفية
        g2.drawLine(x + d, y - d,
                    x + CUBE_SIZE + d, y - d);

        g2.drawLine(x + CUBE_SIZE + d, y - d,
                    x + CUBE_SIZE + d, y + CUBE_SIZE - d);

        g2.drawLine(x + d, y - d,
                    x + d, y + CUBE_SIZE - d);

        g2.drawLine(x + d, y + CUBE_SIZE - d,
                    x + CUBE_SIZE + d, y + CUBE_SIZE - d);

        // الربط بين الأمام والخلف
        g2.drawLine(x, y, x + d, y - d);
        g2.drawLine(x + CUBE_SIZE, y,
                    x + CUBE_SIZE + d, y - d);
        g2.drawLine(x, y + CUBE_SIZE,
                    x + d, y + CUBE_SIZE - d);
        g2.drawLine(x + CUBE_SIZE, y + CUBE_SIZE,
                    x + CUBE_SIZE + d, y + CUBE_SIZE - d);
    }

    /* ================= Color Mapping ================= */

    private Color getAWTColor(String color) {

        if (color == null) return Color.BLUE;

        switch (color.toLowerCase()) {
            case "red":    return Color.RED;
            case "green":  return Color.GREEN;
            case "blue":   return Color.BLUE;
            case "yellow": return Color.YELLOW;
            case "orange": return Color.ORANGE;
            case "black":  return Color.BLACK;
            case "pink":   return Color.PINK;
            case "gray":   return Color.GRAY;
            default:       return Color.BLUE;
        }
    }

    /* ================= Frame ================= */

    public static void showGUI(List<Drawable> shapes) {

        if (shapes.isEmpty()) return;

        JFrame frame = new JFrame("Shapes Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);

        ShapeGUI panel = new ShapeGUI(shapes);

        JScrollPane scroll = new JScrollPane(
                panel,
                JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );

        frame.add(scroll);
        frame.setVisible(true);
    }
}
