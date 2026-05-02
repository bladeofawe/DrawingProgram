import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

class DrawingView extends JPanel implements MouseListener, MouseMotionListener, Serializable {
    private int mode = 0; // 0 = dot, 1 = oval, 2 = rect
    private List<Shape> shapes = new ArrayList<>(); // Store all shapes in arraylist
    private Color currentColor = Color.BLACK; //color used for drawing

    private int startX, startY;
    private int currentX, currentY;
    private boolean dragging = false;

    public DrawingView() { //Det här gör så att hela jpanel fönstret är klickbart med mushändelser.
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    

    @Override
protected void paintComponent(Graphics g) { //paintcomponent är det som ritar upp grafik på jpanel.
        super.paintComponent(g);

        // Det är här som allting som sparats i "shape lista" som ex (rect, oval, dot, rect), och ritar upp allting. Draw finns därför i funktionen.
        for (Shape shape : shapes) {
            shape.draw(g);
        }

        // Draw the current shape during dragging
        if (dragging && (mode == 1 || mode == 2)) { //If its in another mode than dot, because u cannot draw a shape with the dot mode.
            g.setColor(currentColor); //set the "paintbrush" to variable currentcolor
            int x = Math.min(startX, currentX); 
            int y = Math.min(startY, currentY);
            int width = Math.abs(currentX - startX);
            int height = Math.abs(currentY - startY);

            if (mode == 1) { //om oval är aktiverat
                g.fillOval(x, y, width, height); // Draw oval while dragging
            } else if (mode == 2) {
                g.fillRect(x, y, width, height); // Draw rectangle while dragging
            }
        }
    }
    @Override
    public void mousePressed(MouseEvent e) { //när musen är tryckt, ska vi börja ta startpunkten där musen först trycktes, och därefter aktiveras dragging boolean som gör att vi kan använda oss av mousedragged.
        startX = e.getX();
        startY = e.getY();
        dragging = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        currentX = e.getX();
        currentY = e.getY();
        dragging = false;

        // Add the finalized shape to the list
        int x = Math.min(startX, currentX);
        int y = Math.min(startY, currentY);
        int width = Math.abs(currentX - startX);
        int height = Math.abs(currentY - startY);
        
        if(mode == 1){
            shapes.add(new OvalShape(x, y, width, height, currentColor)); //Denna lägger till ovalshape som sparar där x, y av ovalshape ska skapas, och width/height beror på vart vi releasar musen, och sparas också tillsammans med färg. Detta används sen för att rita upp  med informationen.
        }
        if(mode == 2){
            shapes.add(new RectShape(x, y, width, height, currentColor)); //Samma koncept men lägger istället till rectshape och inuti shape klassen hanterar man hur rect ska ritas, och man behöver bara anropa draw isåfall från paintcomponent.,
        }
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) { //Detta är en händelse som hanterar när musen drar, och då uppdateras X och y ständigt med musens rörelse, vilket är varför man kan rita om oval och rektangel.
        currentX = e.getX();
        currentY = e.getY();
        repaint(); //repaint är det som uppdaterar hela canla, det basically kallar på paintcomponent
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (mode == 0) {
            int x = e.getX();
            int y = e.getY();
            shapes.add(new DotShape(x, y, currentColor));  // Lägger till en "dotshape" subklass med nuvarande koordinat, och färg, sen inuti dotshape anropas draw som då ritar upp dot-
            repaint();
        }
    }

    public void setColor(Color color) { //Denna bytar färg på "färgbrush". 
        currentColor = color;
    }
    
    public Color getColor(Color color) { //Denna bytar färg på "färgbrush". 
        return currentColor;
    }

    public void changeMode(int mode) { //Byter mode så man kan shuffla mellan oval, rect, dot.
        this.mode = mode;
    }

    public void undo() { //Hur funkar undo? Vi kollar först så att shapes arraylistan inte är tom, sen tar vi bort senaste elementet som lagts till, shapes,size tar hela size på arrayen och tar bort senaste shape.
        if (!shapes.isEmpty()) {
            shapes.remove(shapes.size() - 1); // Remove last shape
            repaint(); //uppdatera ritningscanva
        }
    }
    
    
    //interface måste implementera vad varje body gör, och abstrakt tvingar metoder att finnas i subklasser, abstrakt tar skelettet och gör vad man vill, varför interface? abstrakt klass och mer, interface: du kan lägga till flera interfaces men bara ärva en klass. abstrakt(arvklass);
    public void save(){
        try{
        String save = JOptionPane.showInputDialog("Enter the file name to save:") + ".txt";
        FileOutputStream save1 = new FileOutputStream(save);
        ObjectOutputStream save2 = new ObjectOutputStream(save1);
        save2.writeObject(shapes);
        save2.flush();
        save2.close();
        }
        catch(IOException e){
            System.out.println("Save failed");
        }
    }
    
    public void load(){
        try{
        String load = JOptionPane.showInputDialog("Enter the file name to load:") + ".txt";
        FileInputStream load1 = new FileInputStream(load);
        ObjectInputStream load2 = new ObjectInputStream(load1);
        shapes = (List<Shape>) load2.readObject();
        load2.close();
        repaint(); 
    }
    catch (IOException | ClassNotFoundException e) {
        System.out.println("Load failed");
    }
    }
    
        public String getModeString() {
        switch (mode) {
            case 0: return "Dot";
            case 1: return "Oval";
            case 2: return "Rect";
            default: return "Unknown";
        }
    }

    // Method to return the current color as a string
    public String getColorString() {
        return currentColor.toString();
    }

    @Override //detta existerar bara för att det är abstrakt eller "interface".
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseMoved(MouseEvent e) {}
}