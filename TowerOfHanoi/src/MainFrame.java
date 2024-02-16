
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

public class MainFrame extends JFrame {    
	//ImagePanel imagePanel= new ImagePanel();
	//Constants
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 500;
    private static final int RODS_WIDTH = 550;
    private static final int RODS_HEIGHT = 400;
    private static final int INITIAL_NUMBER_OF_DISKS = 4;
    private static final int INITIAL_ROD = 0;
    private static final String TITLE = "Towers of Hanoi by Group 5";
    private static final String DISK_NUMBER_LABEL = "Select the number of disks:";
    private static final int DELAY = 800;
    
    //Instance Variables
    private JPanel panel;
    private JButton nextButton;
    private JButton animateButton;
    private JButton back;
    private JLabel diskNumberLabel;
    private JComboBox<Integer> diskNumberSelection;
    private JComponent mainComponent;
    private Rods rods;
    private Timer timer;
    private LinkedList<Integer> movesToSolve;
     
    public MainFrame() {
        rods = new Rods(INITIAL_NUMBER_OF_DISKS, INITIAL_ROD);
        createComponents();
        setTitle(TITLE);
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        
		
    }
    
    private void createComponents() {
        timer = new Timer(DELAY, new AnimationListener());
        
        nextButton = new JButton("NEXT MOVE");
        animateButton = new JButton("ANIMATE");
        back= new JButton("BACK");
        
        nextButton.addActionListener(new NextButtonListener());
        animateButton.addActionListener(new AnimateButtonListener());
        back.addActionListener(new BackListener());
        
        panel = new JPanel();
        
        mainComponent = new MainComponent(rods);
        mainComponent.setPreferredSize(new Dimension(RODS_WIDTH, RODS_HEIGHT));
        
        panel.add(mainComponent);
        
        diskNumberLabel = new JLabel(DISK_NUMBER_LABEL);
        
        diskNumberSelection = new JComboBox<Integer>(new Integer[] {1, 2, 3, 4, 5, 6});
        diskNumberSelection.setSelectedItem(4);
        diskNumberSelection.addActionListener(new DiskNumberChoiceListener());
        
        rods.solveTowersOfHanoi(rods.getRodArray()[INITIAL_ROD].getDisksOnTop().size(), INITIAL_ROD, 1, 2);
        rods.initializeRods((int) diskNumberSelection.getSelectedItem(), INITIAL_ROD);
        
        panel.add(diskNumberLabel);
        panel.add(diskNumberSelection);
        panel.add(nextButton);
        panel.add(animateButton); 
        panel.add(back);                
        add(panel);
    }
    
    private class NextButtonListener implements ActionListener {
       
        public void actionPerformed(ActionEvent event) {
            timer.stop();
            movesToSolve = rods.getMovesToSolve();
            if (!movesToSolve.isEmpty()) {
                rods.moveDisk(movesToSolve.removeFirst(), movesToSolve.removeFirst());
                ((MainComponent) mainComponent).update();
            }
        }
    }
    
    private class AnimateButtonListener implements ActionListener {
        
        public void actionPerformed(ActionEvent event) {
            timer.start();
        }
    }
    
    private class AnimationListener implements ActionListener {
        
        public void actionPerformed(ActionEvent event) {
            movesToSolve = rods.getMovesToSolve();
            if (!movesToSolve.isEmpty()) {
                rods.moveDisk(movesToSolve.removeFirst(), movesToSolve.removeFirst());
                ((MainComponent) mainComponent).update();
            } else {
                timer.stop();
            }
        }
    } 
    
    private class DiskNumberChoiceListener implements ActionListener {
        
        public void actionPerformed(ActionEvent event) {
            timer.stop();
            timer = new Timer(DELAY, new AnimationListener());
            rods = new Rods((int) diskNumberSelection.getSelectedItem(), INITIAL_ROD);
            ((MainComponent) mainComponent).updateRods(rods);
            rods.solveTowersOfHanoi(rods.getRodArray()[INITIAL_ROD].getDisksOnTop().size(), INITIAL_ROD, 1, 2);
            rods.initializeRods((int) diskNumberSelection.getSelectedItem(), INITIAL_ROD);
        }
    }
    
    private class BackListener implements ActionListener{
    
		public void actionPerformed(ActionEvent e) {
			MenuFrame menu=new MenuFrame();
			menu.addMenuFrameActionListener();
		}
    }
}
