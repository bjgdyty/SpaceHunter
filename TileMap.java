package display;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class TileMap {
	private BufferedImage[][] tiles;
	private int TILE_SIZE = 48;
	private int xMapHead;
	private int pWidth;
	private int pHeight;
	private int mapWidth;
	private int mapHeight;
	private int mapMoveSize;
	private int offsetX;
	private int offsetY;
	public boolean tMapMoveRight = false;
	public boolean tMapMoveLeft = false;
	
	public TileMap(int width,int height,Canvas screen){
		tiles = new BufferedImage[width][height];
		xMapHead = 0;
		pWidth = screen.getWidth();
		pHeight = screen.getHeight();
		mapMoveSize = 5;
		mapWidth = tilesToPixels(width);
		mapHeight = tilesToPixels(height);
		offsetX = 0;
		offsetY = pHeight - mapHeight;
		System.out.println(mapWidth);
		System.out.println(width);
	}
	
	public int tilesToPixels(int numTiles){
		int pixelSize = numTiles * TILE_SIZE;
		return pixelSize;
	}
	
	public int pixelsToTiles(int pixelCoord){
		int numTiles = pixelCoord / TILE_SIZE;
		return numTiles;
	}
	
	public void updateTile(int d){
		if(d > 0){
			xMapHead = (xMapHead - mapMoveSize);
		}else if(d < 0){
			xMapHead = (xMapHead + mapMoveSize);
		}
	}
	
	public void draw(Graphics g){
		offsetX = xMapHead;
		int firstTileX = pixelsToTiles(-xMapHead);
		int lastTileX = pixelsToTiles(-xMapHead + pWidth);
		for(int y = 0;y < pixelsToTiles(mapHeight); y++){
			for(int x = firstTileX; x <= lastTileX; x++){
				//System.out.println(x + " " + y );
				BufferedImage image = getTile(x,y);
				if(image != null){
					g.drawImage(image, tilesToPixels(x) + offsetX, tilesToPixels(y) + offsetY, null);
				}
			}
		}
	}
	
	private BufferedImage getTile(int x,int y){
		return tiles[x][y];
	}
	
	private void setTile(int x,int y,BufferedImage image){
		tiles[x][y] = image;
	}
	
	public TileMap loadTileMap(String filename,Canvas canvas){
		ArrayList<String> lines = new ArrayList<>();
		int width = 0;
		int height = 10;
		
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
			for(int i=0;i<height;i++){
				String line = reader.readLine();
				System.out.println(line);
				
				if(width == 0){
					width = line.length();	
				}

				lines.add(line);
			}
		}catch(IOException e){
			
		}
		height = lines.size();
		TileMap newMap = new TileMap(width,height,canvas);
		for(int y=0;y<height;y++){
			String line = (String) lines.get(y);
			for(int x=0;x < line.length();x++){
				char ch = line.charAt(x);
				if(ch != 32){
					try {
						
						newMap.setTile(x,y,ImageIO.read(new BufferedInputStream(
								new FileInputStream(ch + ".png"))));
					} catch (FileNotFoundException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}	
				}


			}

		}
		return newMap;

	}

}
