package window;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;

import Main.Game;

public class GamePanel {
	
	private static GraphicsDevice device;
	private static JFrame frame;
	public static boolean fullscreen;
	
	public GamePanel(int width, int height, String title, Game game) {
	
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		device = ge.getDefaultScreenDevice();
		frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(width, height));
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}
	
	public static void mainScreenTurnOn() {
		frame.dispose(); 
		frame.setUndecorated(true);
		device.setFullScreenWindow(frame);
		fullscreen = true;
	}
	
	public static void mainScreenTurnOff() {
		fullscreen = false;
		device.setFullScreenWindow(null);
		frame.dispose();
		frame.setUndecorated(false);
		frame.setVisible(true);
	}

}