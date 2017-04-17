package display;

import java.awt.Graphics;
import java.awt.image.*;

public class Character {
	
	protected int locX;
	protected int locY;
	protected int dx;
	protected int dy;
	protected Animation animPlayer;
	private BufferedImage image;
	private int numImages;
	private String imFileName;
	private int width;
	private int height;
	public boolean isLooping;

	
	public Character(String fnm,int num){
		locX = 300;
		locY = 350;
		dx = 0;
		dy = 0;
		imFileName = fnm;
		numImages = num;
		animPlayer = new Animation(imFileName,numImages,800);
		image = animPlayer.loadImage(fnm);
		
		width = image.getWidth();
		height = image.getHeight();
		isLooping = false;

		
	}
	
	
	public void moveUpdate(){
		
		if(isLooping){
			locX += dx;
			locY += dy;
			animPlayer.updateImage();
		}
		
		
		
	}
	
	public void draw(Graphics g){
		if(isLooping){
			animPlayer.draw(g, locX, locY);
			System.out.println("ACTIVE");
		}else{
			g.drawImage(animPlayer.ims[0], locX, locY, null);
			System.out.println("NOT ACTIVE");
		}
			
		
	}
	
	public void setX(int x){
		locX = x;
	}
	
	public void setY(int y){
		locY = y;
	}
	
	public int getX(){
		return locX;
	}
	
	public int getY(){
		return locY;
	}
	
	public void setDX(int dx){
		this.dx = dx;
	};
	
	public void setDY(int dy){
		this.dy = dy;
	}
	
	
	
	
}
