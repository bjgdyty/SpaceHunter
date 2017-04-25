package gamecore;

import java.awt.Graphics;
import java.awt.image.*;

import display.Animation;

public class Character {
	
	protected int locX;
	protected int locY;
	protected int Px;
	protected int dx;
	protected int dy;
	protected Animation animPlayer;
	private BufferedImage image;
	private int numImages;
	private String imFileName;
	private int width;
	private int height;
	private final static int GRAVITY = 1;
	private final static int INISPEED = 16;
	private int jumpSpeed;
	public boolean doJump;
	public boolean isJumpingUP;
	public boolean isMoving;
	public boolean canMove;
	public int onGround;
	private int CHAR_SIZE = 80;

	
	public Character(String fnm,int num){
		width = 80;
		height = 80;
		locX = 400;
		locY = 198;
		Px = locX;
		dx = 4;
		dy = 0;
		imFileName = fnm;
		numImages = num;
		animPlayer = new Animation(imFileName,numImages,600);
		image = animPlayer.loadImage(fnm);
		jumpSpeed = -INISPEED;
		width = image.getWidth();
		height = image.getHeight();
		isMoving = false;
		canMove = true;
		isJumpingUP = false;
		doJump = false;
		onGround = 2;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	
/*	public void moveUpdate(int moveD){

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



		
	}*/
	public void updateJump(){
		if(doJump){
			if(onGround == 2){
				onGround = 0;

			}
			if(!isJumpingUP){
				dy += GRAVITY;
			}else if(isJumpingUP){
				
				jumpSpeed += GRAVITY;
				setPDY(jumpSpeed);
				if(jumpSpeed >= 0){
					System.out.println("AT THE TOP");
					isJumpingUP =false;
					jumpSpeed = -INISPEED;
				}
			}
			locY += dy;

		}else if(!doJump){
			
		}

	}
	
	
	
	public void moveAhead(int moveD){
		
		if((Px <= 380 && moveD < 0) || (Px >= 2400 && moveD > 0) ){
			System.out.println("OUT OF BOUNDARY");
			canMove = false;
			isMoving = false;
		}else{
			canMove = true;
			if(moveD == 1){
				if(!isMoving){
					isMoving =true;
				}
				Px += dx;
				animPlayer.loopIms();
			}else if(moveD == -1){
				if(!isMoving){
					isMoving =true;
				}
				Px -= dx;
				animPlayer.loopIms();
			}else if(moveD == 2){
				Px += dx;
				if(animPlayer.loopIms() == 0){
					isMoving = false;
				}
			}else if(moveD == -2){
				Px -= dx;
				if(animPlayer.loopIms() == 0){
					isMoving = false;
				}
			}
		}
		

		
	}
	
/*	public int tilesToPixels(int numTiles){
		int pixelSize = numTiles * 48;
		return pixelSize;
	}
	
	public int pixelsToTiles(int pixelCoord){
		int numTiles = (pixelCoord / 48);
		return numTiles;
	}*/
	
	public void draw(Graphics g){
		if(canMove){
			if(isMoving){
				animPlayer.draw(g, locX, locY);
				
				System.out.println("CHARACTER  " +(Px) + "    " + locY);
			}else if(!isMoving){
				g.drawImage(animPlayer.ims[0], locX, locY, null);
			}
		}else if(!canMove){
			g.drawImage(animPlayer.ims[0], locX, locY, null);
		}

		/*else if(!letMove && !isMoving){
			g.drawImage(animPlayer.ims[0], locX, locY, null);
		}else if(letMove && !isMoving){
			System.out.println("CANNOT MOVE");
			g.drawImage(animPlayer.ims[0], locX, locY, null);
		}else if(!letMove && isMoving){
			animPlayer.draw(g, locX, locY);
		}*/

		
	}
	
	public void setPX(int Px){
		this.Px = Px;
	}
	
	public void setPY(int Py){
		this.locY = Py;
	}
	
	public int getPX(){
		return Px;
	}
	
	public int getPY(){
		return locY;
	}
	
	public int getDY(){
		return dy;
	}
	
	public int getDX(){
		return dx;
	}
	
	public void setPDX(int dx){
		this.dx = dx;
	};
	
	public void setPDY(int dy){
		this.dy = dy;
	}
	
	
	
	
}
