import javax.swing.JComponent;
import java.util.Stack;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

public class MainComponent extends JComponent {
   
    private static final Color[] COLOR_ARRAY = {Color.BLUE, Color.CYAN, Color.YELLOW, Color.GREEN, Color.ORANGE, Color.RED};
    private static final int X_START = 50;
    private static final int X_END = 500;
    private static final int Y_END = 300;
    private static final int ROD_LENGTH = 200;
    private static final int ROD_GAP = 150;
    private static final int SIDE_GAP = 75;
    private static final int THICKNESS = 3;
    private static final int STRING_ADJUST = 30;
    
    private Rods rods;
    
    public MainComponent(Rods rods) {
        this.rods = rods;
    }
    
    public void update() {
        removeAll();
        revalidate();
        repaint();
    }
    
    public void updateRods(Rods newRods) {
        rods = newRods;
        removeAll();
        revalidate();
        repaint();
    }

    public void paintComponent(Graphics g) {
        paintRods(g);
        
        Rod[] rodArray =  rods.getRodArray();
        for (int i = 0; i < rodArray.length; i++) {
            Stack<Disk> disksOnTop  = rodArray[i].getDisksOnTop();
            for (int j = 0; j < disksOnTop.size(); j++) {
                paintDisk(disksOnTop.get(j), i, 1 + j, g);
            }
        } 
        
        g.setColor(Color.GRAY);
        g.drawString("LEFT", X_START + SIDE_GAP - STRING_ADJUST / 2, Y_END + STRING_ADJUST);
        g.drawString("MIDDLE", X_START + SIDE_GAP + ROD_GAP - STRING_ADJUST / 2, Y_END + STRING_ADJUST);
        g.drawString("RIGHT", X_END - SIDE_GAP - STRING_ADJUST, Y_END + STRING_ADJUST);
    }
    
    private void paintRods(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(THICKNESS));
        g.setColor(Color.DARK_GRAY);
        
        g.drawLine(X_START, Y_END, X_END, Y_END);
        
        g.drawLine(X_START + SIDE_GAP, Y_END - ROD_LENGTH, X_START + SIDE_GAP, Y_END);
        g.drawLine(X_START + SIDE_GAP + ROD_GAP, Y_END - ROD_LENGTH, X_START + SIDE_GAP + ROD_GAP, Y_END);
        g.drawLine(X_END - SIDE_GAP, Y_END - ROD_LENGTH, X_END - SIDE_GAP, Y_END);
    }

    private void paintDisk(Disk toDraw, int rodNumber, int order, Graphics g) {
        int diskSize = toDraw.getSize();
        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(THICKNESS - 1));
        g.setColor(Color.BLACK);
        g.drawOval(X_START + SIDE_GAP + ROD_GAP * rodNumber - (diskSize * Disk.THICKNESS / 2), 
        Y_END - Disk.THICKNESS * order, diskSize * Disk.THICKNESS, Disk.THICKNESS);
        g.setColor(COLOR_ARRAY[COLOR_ARRAY.length - diskSize]);
        g.fillOval(X_START + SIDE_GAP + ROD_GAP * rodNumber - (diskSize * Disk.THICKNESS / 2), 
        Y_END - Disk.THICKNESS * order, diskSize * Disk.THICKNESS, Disk.THICKNESS);
    }
}