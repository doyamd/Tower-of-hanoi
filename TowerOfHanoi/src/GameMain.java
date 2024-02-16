import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;


public class GameMain {
	static AudioInputStream audioInputStream;
	static Clip clip;
	public static void main(String[] args) {
		JFrame frame = new FirstPage();
	    frame.setTitle("Tower Of Hanoi");
	    frame.setSize(800,600);
	    frame.setLocationRelativeTo(null);
	    
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);	
	    frame.setResizable(false);
		
	}
}