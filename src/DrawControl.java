import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


public class DrawControl extends JFrame {
    public DrawControl() {
        setTitle("Drawing Program");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        DrawingView drawingPanel = new DrawingView();
        JLabel statusLabel = new JLabel("Mode: DOT using color Black"); // Create the JLabel

        MenuController menu = new MenuController(drawingPanel, statusLabel); // Pass the JLabel to the MenuController

        menu.setLayout(new GridLayout(1, 0, 5, 5));
        menu.setBackground(Color.LIGHT_GRAY);

        String[] buttonNames = {"black", "red", "green", "dot", "oval", "rect", "undo", "save", "load"};
        for (String name : buttonNames) {
            JButton button = new JButton(name);
            button.addActionListener(menu);
            button.setActionCommand(name); // Set button command
            menu.add(button);
        }

        setLayout(new BorderLayout());
        add(menu, BorderLayout.NORTH);
        add(drawingPanel, BorderLayout.CENTER);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(statusLabel, BorderLayout.SOUTH); // Add JLabel to the frame

        setVisible(true);
    }

    public static void main(String[] args) {
        new DrawControl(); // Start the application
    }
}
