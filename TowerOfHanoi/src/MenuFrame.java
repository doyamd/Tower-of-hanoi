
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class MenuFrame extends JFrame{
	ImagePanel panel = new ImagePanel();
	JButton newGame = new JButton(panel.imageNewGame);
	JButton loadGame = new JButton(panel.imageLoadGame);
	JButton howToPlay = new JButton(panel.imageOption);
	JButton back= new JButton(panel.imageBack);

	public MenuFrame() {
		init();
		addMenuFrameActionListener();
	}
	
	public void init(){ 
		 newGame.setBounds(520,250, 200, 45);
		 newGame.setBorder(null);
         loadGame.setBounds( 520, 300, 200, 45);
         loadGame.setBorder(null);
         howToPlay.setBounds(520, 350, 200, 45);
         howToPlay.setBorder(null);
         back.setBounds(520, 400, 200, 45);
         back.setBorder(null);
      
         this.add(newGame);
         this.add(loadGame);
         this.add(howToPlay);
         this.add(back);
         this.add(panel);
	}
	
	public void addMenuFrameActionListener(){
        loadGame.addActionListener(new newGameAction1());
        loadGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
			}
		});
      
       newGame.addActionListener(new newGameAction());
       newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
			}
		});
        
       howToPlay.addActionListener(new simGameAction());
       howToPlay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
			}
		});
       
       //back.addActionListener(new backPage());
       
       back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}
	
	public class ImagePanel extends JPanel{
		
		private ImageIcon imageNewGame = new ImageIcon("images/newgame.jpg");
		private ImageIcon imageLoadGame = new ImageIcon("images/loadgame.jpg");
		private ImageIcon imageOption = new ImageIcon("images/option.jpg");
		private ImageIcon imageBack = new ImageIcon("images/back.jpg");
		
			
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
		    
		        
		    }
		 }
	}

	class newGameAction implements ActionListener { 
		public void actionPerformed(ActionEvent e){
			GameHold mDraw =new GameHold("Tower Of Hanoi",1);
		} 
	}
	
	class simGameAction implements ActionListener { 
		public void actionPerformed(ActionEvent e){
			Main sim =new Main();
		} 
	}
	
	class newGameAction1 implements ActionListener { 
		public void actionPerformed(ActionEvent e){
			try{
				DataInputStream input =new DataInputStream(new FileInputStream("images/input.txt"));
				int x=input.readInt();
				input.close();
				System.out.println("here is XX: " + x);
				GameHold hold =new GameHold("Tower Of Hanoi",x);	
			}
			catch(IOException ex){
				System.out.println("Problem with Input Output FIle");		
			}
		} 
	}

   