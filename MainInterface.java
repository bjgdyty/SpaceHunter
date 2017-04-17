package display;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


public class MainInterface extends JFrame implements Runnable,KeyListener{
	
	private BufferedImage bim = new BufferedImage(640,480,BufferedImage.TYPE_INT_ARGB);
	private Ribbon bg;
	private FrameRate frameRate;
	private volatile boolean running;
	private Canvas canvas;
	private Thread gameThread;
	private Image im;
	private Character player;
	private int picD;
	
	
	protected void createAndShowGUI() {
		
		canvas = new Canvas();
		canvas.setSize(640, 480);
		
		canvas.setIgnoreRepaint(true);
		
		getContentPane().add(canvas);
		setTitle("Space Hunter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIgnoreRepaint(true);
		
		
		pack();
		

		canvas.addKeyListener(this);
		
		
		
		setVisible(true);
		gameThread = new Thread(this);
		gameThread.start();	
	}
	
	private void gameRender(){
		
		im = createImage(getWidth(),getHeight());
		Graphics g = im.getGraphics();
		bg.draw(g);
		player.draw(g);
		g.setFont(new Font("Courier New",Font.PLAIN,12));
		g.setColor(Color.BLACK);
		frameRate.calculate();
		g.drawString(frameRate.getFrameRate(), 20, 20);
		
		System.out.println("rendering");
		
	}
	
	private void gamePaint(){
		Graphics g = canvas.getGraphics();
		
		g.drawImage(im, 0, 0, null);
		
		g.dispose();
		System.out.println("painting");
	}
	
	private void gameUpdate(){
		bg.updatePic(picD);
		player.moveUpdate();
		

	}
	
	public void run(){
		initialize();
		running = true;
		while(running){
			gameLoop();
		}
	}
	
	private void gameLoop(){
		gameUpdate();
		gameRender();
		gamePaint();
		
		sleep(9L);
		
		
	}
	
	private void sleep(long sleep){
		try{
			Thread.sleep(sleep);
		}catch(InterruptedException ex){}
	}
	
	
	public void keyPressed(KeyEvent e){
		int keycode = e.getKeyCode();
		if(keycode == e.VK_RIGHT){
			player.isLooping = true;
			picD = 1;
			bg.canMove = true;
			
		}else if(keycode == e.VK_LEFT){
			player.isLooping = true;
			picD = -1;
			bg.canMove = true;
		}
	}
	
	public void keyReleased(KeyEvent e){
		int keycode = e.getKeyCode();
		if(keycode == e.VK_RIGHT){
			bg.canMove = false;
			player.isLooping = false;
		}else if(keycode == e.VK_LEFT){
			bg.canMove = false;
			player.isLooping = false;
		}
	}
	
	public void keyTyped(KeyEvent e){
		
	}
	
	private void initialize(){
		
		picD = 0;
		
		frameRate = new FrameRate();
		frameRate.initialize();

		try {
			bim = ImageIO.read(new BufferedInputStream(
					new FileInputStream("resource/ribbontest.png")));
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		bg = new Ribbon(5,bim,canvas.getWidth(),canvas.getHeight());
		
		player = new Character("resource/move.png",4);
		
	}
	
	
	public static void main(String[] args) {
		final MainInterface app = new MainInterface();
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				
				app.createAndShowGUI();
				
				
			}
		});

	}

}
