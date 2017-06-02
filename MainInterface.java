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

import gamecore.Character;
import gamecore.FrameRate;
import gamecore.GameWorldManager;
import gamecore.Ribbon;
import gamecore.TileMap;


@SuppressWarnings("serial")
public class MainInterface extends JFrame implements Runnable{
	
	private BufferedImage bim = new BufferedImage(640,480,BufferedImage.TYPE_INT_ARGB);
	private Ribbon bg;
	private FrameRate frameRate;
	private volatile boolean running;
	private Canvas canvas;
	private Thread gameThread;
	private Image im;
	private Character player;
	private int picD;
	private TileMap tileMap;
	private KeyboardInput keyboard;
	private GameWorldManager manager;
	private Menu menu;
	private firstMenu firstMenu;
	private volatile boolean start;
	protected void createAndShowGUI() {
		
		canvas = new Canvas();
		canvas.setSize(640, 480);
		
		canvas.setIgnoreRepaint(true);
		
		getContentPane().add(canvas);
		setTitle("Space Hunter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIgnoreRepaint(true);
		
		
		pack();
		
		keyboard = new KeyboardInput();
		canvas.addKeyListener(keyboard);
		
		firstMenu = new firstMenu(this);
		menu = new Menu(this);
		
		this.setLayout(null);
		this.add(menu);
		menu.setVisible(false);
		
		
		this.add(firstMenu);
		firstMenu.setVisible(false);
		
		setVisible(true);
		gameThread = new Thread(this);
		gameThread.start();	
	}
	
	private void gameRender(){
		
		im = createImage(getWidth(),getHeight());
		Graphics g = im.getGraphics();
		bg.draw(g);
		player.draw(g);
		tileMap.draw(g);
		g.setFont(new Font("Courier New",Font.PLAIN,12));
		g.setColor(Color.WHITE);
		frameRate.calculate();
		g.drawString(frameRate.getFrameRate(), 20, 20);
		

		
	}
	
	private void gamePaint(){
		Graphics g = canvas.getGraphics();
		
		g.drawImage(im, 0, 0, null);
		
		g.dispose();

	}
	
	private void gameUpdate(){
		player.moveAhead(picD);
		player.updateJump();
		System.out.println(player.onGround);
		manager.worldCollisionVertical(player);		

		if(!player.isMoving || !player.canMove){
			picD = 0;
		}
		bg.updatePic(picD);
		tileMap.updateTile(picD);
		System.out.println(player.getPX() + "   " +player.getPY());
	}
	
	public void run(){
		
		initialize();
		
		while(true){
			if(start == false){
				canvas.setVisible(false);
				firstMenu.setVisible(true);
			}else if(start == true){
				firstMenu.setVisible(false);
					while(running){
						processInput();
						menu.setVisible(false);
						canvas.setVisible(true);
						gameLoop();
					}
					while(!running){
						processInput();
						canvas.setVisible(false);
						menu.setVisible(true);
					}
							
			}
		}
	}
	
	private void processInput(){
		keyboard.poll();
		if(keyboard.keyDown(KeyEvent.VK_RIGHT)){
			picD = 1;
			
		}
		if(keyboard.keyDown(KeyEvent.VK_LEFT)){
			picD = -1;
			
		}
		if(!keyboard.keyDown(KeyEvent.VK_RIGHT)
				&& !keyboard.keyDown(KeyEvent.VK_LEFT) && picD != 0 ){
			
			if(picD == 1){
				picD = 2;
			}else if(picD == -1){
				picD = -2;
			}
		}
		
		if(keyboard.keyDownOnce(KeyEvent.VK_SPACE)){
			if(player.onGround != 0 && !player.doJump){
				player.isJumpingUP = true;
				player.doJump = true;
				
			}
			
		}
		
		if(keyboard.keyDownOnce(KeyEvent.VK_ESCAPE)){
			running = !running;
			
		}
		
		
	}
	
	private void gameLoop(){
		
		gameUpdate();
		gameRender();
		gamePaint();
		
		sleep(7L);
		
		
	}
	
	private void sleep(long sleep){
		try{
			Thread.sleep(sleep);
		}catch(InterruptedException ex){}
	}
	
	
/*	public void keyPressed(KeyEvent e){
		int keycode = e.getKeyCode();
		if(keycode == KeyEvent.VK_RIGHT){
			
			player.letMove = true;
			picD = 1;

			
		}else if(keycode == KeyEvent.VK_LEFT){
			
			player.letMove = true;
			picD = -1;

		}
	}
	
	public void keyReleased(KeyEvent e){
		int keycode = e.getKeyCode();
		if(keycode == KeyEvent.VK_RIGHT){
			player.letMove = false;

		}else if(keycode == KeyEvent.VK_LEFT){
			player.letMove = false;

		}
	}
	
	public void keyTyped(KeyEvent e){
		
	}
	*/
	
	private void initialize(){
		
		picD = 0;
		
		frameRate = new FrameRate();
		frameRate.initialize();

		try {
			bim = ImageIO.read(new BufferedInputStream(
					new FileInputStream("resource/ribbonLong.png")));
			
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		bg = new Ribbon(4,bim,canvas.getWidth(),canvas.getHeight());
		
		player = new Character("resource/move.png",4);
		tileMap = new TileMap(0,0,canvas).loadTileMap("tilemap.txt",canvas);
		manager = new GameWorldManager(tileMap);
		running = false;
		start = false;
	}
	
	public void setRunning(){
		if(start == false){
			start = true;
		}
		running = true;
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
