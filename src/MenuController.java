import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;


// Controller class to manage the menu
class MenuController extends JPanel implements ActionListener {
    private DrawingView drawPanel;
    private JLabel statusLabel; // Reference to the JLabel

    public MenuController(DrawingView drawPanel, JLabel statusLabel) {
        this.drawPanel = drawPanel;
        this.statusLabel = statusLabel; // Initialize the JLabel reference
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand(); // Listens to messages from user.
        switch (str) {
            case "black":
                System.out.println("Black!");
                drawPanel.setColor(Color.BLACK);
                statusLabel.setText("Mode: " + drawPanel.getModeString() + " using color " + drawPanel.getColorString()); // Update the JLabel
                break;
            case "red":
                System.out.println("Red!");
                drawPanel.setColor(Color.RED);
                statusLabel.setText("Mode: " + drawPanel.getModeString() + " using color " + drawPanel.getColorString()); // Update the JLabel
                break;
            case "green":
                System.out.println("Green!");
                drawPanel.setColor(Color.GREEN);
                statusLabel.setText("Mode: " + drawPanel.getModeString() + " using color " + drawPanel.getColorString()); // Update the JLabel
                break;
            case "dot":
                System.out.println("Dot Mode!");
                drawPanel.changeMode(0);
                statusLabel.setText("Mode: Dot using color " + drawPanel.getColorString()); // Update the JLabel
                break;
            case "oval":
                System.out.println("Oval Mode!");
                drawPanel.changeMode(1);
                statusLabel.setText("Mode: Oval using color " + drawPanel.getColorString()); // Update the JLabel
                break;
            case "rect":
                System.out.println("Rect Mode!");
                drawPanel.changeMode(2);
                statusLabel.setText("Mode: Rect using color " + drawPanel.getColorString()); // Update the JLabel
                break;
            case "undo":
                System.out.println("Undo!");
                drawPanel.undo();
                statusLabel.setText("Last action undone"); // Update the JLabel
                break;
            case "save":
                System.out.println("Save!");
                drawPanel.save();
                statusLabel.setText("Drawing saved"); // Update the JLabel
            }
        }
    }
