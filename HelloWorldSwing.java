package display;

import javax.swing.*;

public class HelloWorldSwing {
	protected static void createAndShowGUI(){
		
		JFrame window = new JFrame("HelloWorldSwing");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JLabel label = new JLabel("HelloWorld");
		window.getContentPane().add(label);
		
		window.pack();
		window.setVisible(true);
	}
	
	public static void main(String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				createAndShowGUI();
			}
		});
	}
}
