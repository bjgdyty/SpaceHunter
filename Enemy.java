package gamecore;


import java.awt.Graphics;
import java.awt.image.BufferedImage;

import display.Animation;

public class Enemy   {
	protected int locX;
	protected int locY;
	protected int Px;
	protected int Py;
	protected int dx;
	protected int dy;
	private int width;
	private int height;
	private BufferedImage image;
	protected Animation animEnemy;
	private final static int GRAVITY = 1;
	public boolean runningRight;
	public boolean runningLeft;
	private Animation animRight;
	private Animation animLeft;

	public Enemy(int x,int y) {
		
		runningRight =false;
		runningLeft =true;
		dx=-5;
		dy=0;
		
	}
	public void update(){
		if (dx>0) {
			runningRight = true;
			runningLeft = false;
		}
		else if (dx<0){
			runningRight = false;
			runningLeft = true;
		}
		dy += GRAVITY;
		locX =  Px - Character.Px + 400 + dx - Character.dx ;
		locY =  Py ;
		if (runningRight)
			animRight.loopIms();	
		
		else if (runningLeft)
		    animLeft.loopIms();	
		if (GameWorldManager.EnemyCollisionHorizontal=true)
			dx = -dx;
		
	}

	public void draw(Graphics g){

		if(runningRight == true)
			animRight.draw(g, locX, locY);
	    else if(runningLeft == true)
	    	animLeft.draw(g, locX, locY);
	}

	public void setPX(int Px) {
		this.Px = Px;
	}

	public void setPY(int Py) {
		this.locY = Py;
	}

	public int getPX() {
		return Px;
	}

	public int getPY() {
		return locY;
	}

	public int getDY() {
		return dy;
	}

	public int getDX() {
		return dx;
	}

	public void setPDX(int dx) {
		this.dx = dx;
	};

	public void setPDY(int dy) {
		this.dy = dy;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}

