package Firestorm;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Firestorm {

public static void main(String[] args){
		
		Game game = new Game();
		JFrame frame = new JFrame(Game.TITLE);
		frame.add(game);
		frame.setSize(Game.WIDTH, Game.HEIGHT);
		frame.setResizable(false);
		frame.setFocusable(true);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e) {
				System.err.println("Exiting Game");
				game.stop();
			}
		});
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.requestFocus();
		game.start();
	}
	
}
