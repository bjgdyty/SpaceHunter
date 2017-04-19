package display;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Animation {

	private long beginning;
	private long delta;
	private long lastTime;
	private long showTime;
	private long animTotalTime;
	private int imPosition;
	private boolean isRepeating;
	private boolean justBegin;
	private int numImages;
	public BufferedImage[] ims;
	
	public Animation(String fnm,int num,int animTotalTime){
		
		this.showTime = animTotalTime / num;
		this.animTotalTime = animTotalTime;
		
		isRepeating = false;
		beginning = 0;
		justBegin = false;
		
		setAnimImagesFromOneFile(fnm,num);
		numImages = num;
	}
	
	
	public BufferedImage loadImage(String fnm){
		BufferedImage bim;
		try {
			bim = ImageIO.read(new BufferedInputStream(
					new FileInputStream(fnm)));
			
			return bim;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	private void setAnimImagesFromOneFile(String fnm,int num){
		
		BufferedImage bim = loadImage(fnm);
		
		int imWidth = (int) bim.getWidth()/num;
		int imHeight = (int) bim.getHeight();
		
		ims = new BufferedImage[num];
		
		Graphics g;
		
		for(int i =0;i< num;i++){
			ims[i] = new BufferedImage(imWidth,imHeight,BufferedImage.TYPE_INT_ARGB);
			g = ims[i].getGraphics();
			g.drawImage(bim, 0, 0, imWidth,imHeight,
					i*imWidth,0,(i*imWidth)+imWidth,imHeight,null);
			g.dispose();
		}	
	}
	
	public int loopIms(){

		lastTime = System.currentTimeMillis();
		delta = lastTime - beginning;
			
		if((int) (delta / animTotalTime) > 1){
			beginning = System.currentTimeMillis();
		}
		imPosition = (int) ((delta % animTotalTime) / showTime);

		
		return imPosition;
	}
	

	
	
	public void draw(Graphics g,int x,int y){
		
		BufferedImage im = ims[imPosition];
		g.drawImage(im, x, y, null);
	}
	
	
	public boolean isRepeating(){
		return isRepeating;
	}
}
