import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.*;

// Modelklass
abstract class Shape implements Serializable{
    int x, y, width, height;
    Color color;

    public Shape(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public abstract void draw(Graphics g); // Abstract method for drawing the shape, should exist in every subclass of "mainclass shape".
}

// Dot shape class
class DotShape extends Shape {
    public DotShape(int x, int y, Color color) {
        super(x, y, 10, 10, color); // Dots are always 10x10
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x - 5, y - 5, 10, 10); // Draw dot
    }
}

// Oval shape class
class OvalShape extends Shape {
    public OvalShape(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, width, height); // Draw oval
    }
}

// Rectangle shape class
class RectShape extends Shape {
    public RectShape(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height); // Draw rectangle
    }
}