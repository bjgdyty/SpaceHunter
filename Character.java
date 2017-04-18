package display;

import java.awt.Graphics;
import java.awt.image.*;

public class Character {
	
	protected int locX;
	protected int locY;
	protected int x;
	protected int y;
	protected int dx;
	protected int dy;
	protected Animation animPlayer;
	private BufferedImage image;
	private int numImages;
	private String imFileName;
	private int width;
	private int height;
	public boolean isMoving;
	public boolean letMove;
	private int CHAR_SIZE = 80;

	
	public Character(String fnm,int num){
		locX = 300;
		locY = 350;
		x = 0;
		y = 0;
		dx = 5;
		dy = 0;
		imFileName = fnm;
		numImages = num;
		animPlayer = new Animation(imFileName,numImages,600);
		image = animPlayer.loadImage(fnm);
		
		width = image.getWidth();
		height = image.getHeight();
		isMoving = false;
		letMove = false;

		
	}
	
	
	public void moveUpdate(int moveD){

		if((x ==0 && moveD <0 )|| (x == 2240 && moveD > 0)){
			isMoving = false;
		}else{

			if(letMove && !isMoving){
				isMoving = true;
			}
			
			if(isMoving){
				if(moveD > 0){
					x += dx;
				}else if(moveD < 0){
					x -= dx;
				}	
			}

			
			
			if(letMove && isMoving){
				animPlayer.loopIms();
			}else if(!letMove && !isMoving){
				System.out.println("STAND");
			}else if(!letMove && isMoving){
				if(animPlayer.loopIms() == 0){
					isMoving = false;
				}
			}
			
		}



		
	}
	
	public int tilesToPixels(int numTiles){
		int pixelSize = numTiles * CHAR_SIZE;
		return pixelSize;
	}
	
	public int pixelsToTiles(int pixelCoord){
		int numTiles = pixelCoord / CHAR_SIZE;
		return numTiles;
	}
	
	public void draw(Graphics g){
		
		if(letMove && isMoving){
			animPlayer.draw(g, locX, locY);
			
			System.out.println("CHARACTER  " + pixelsToTiles(x) + "    " + y);
		}else if(!letMove && !isMoving){
			g.drawImage(animPlayer.ims[0], locX, locY, null);
		}else if(letMove && !isMoving){
			System.out.println("CANNOT MOVE");
			g.drawImage(animPlayer.ims[0], locX, locY, null);
		}else if(!letMove && isMoving){
			animPlayer.draw(g, locX, locY);
		}

		
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setDX(int dx){
		this.dx = dx;
	};
	
	public void setDY(int dy){
		this.dy = dy;
	}
	
	
	
	
}
