package gamecore;

import java.awt.Point;

public class GameWorldManager {
	private TileMap tMap;
	
	public GameWorldManager(TileMap tMap){
		this.tMap = tMap;
	}
	
	public Point checkTileCollision(Character player){
		Point pointCache = new Point(0,0);
		int oldX = player.getPX();
		int oldY = player.getPY();
		int newX = player.getPX() + player.getDX();
		int newY = player.getPY() + player.getDY();
		int fromX = Math.min(oldX, newX);
		int fromY = Math.min(oldY, newY);
		int toX = Math.max(oldX, newX);
		int toY = Math.max(oldY, newY);
		int fromTileX = tMap.pixelsToTiles(fromX);
		int fromTileY = tMap.pixelsToTiles(fromY);
		int toTileX = tMap.pixelsToTiles(toX + player.getWidth()/6);//SOMETHING WRONG IF USE "+ player.getWidth() AND CAN'T FIND THE REASON"
		int toTileY = tMap.pixelsToTiles(toY + player.getHeight());
		
		
		for(int x = fromTileX;x <= toTileX; x++ ){
			for(int y = fromTileY;y <= toTileY; y++){
				if(y >= 0 && tMap.getTile(x, y) != null ){
					System.out.println(x + "  " +y);
					pointCache.setLocation(x, y);
					return pointCache;
					
				}
			}
		}
		return null;
	}
	
/*	public boolean worldCollisionHorizontal(Character player){
		int offSetX = tMap.offsetX();
		int dx = player.getDX();
		int oldX = player.getPX();
		int oldY = player.getPY();
		int newX = player.getPX() + player.getDX();
		int newY = player.getPY() + player.getDY();
		
		Point tile = checkTileCollision(player);
		if(tile == null){
			return false;
		}else{
			if(dx > 0){
				//player.setPX(tMap.tilesToPixels(tile.x) - offSetX -player.getWidth());
				
			}else if(dx < 0){
				//player.setPX(tMap.tilesToPixels(tile.x + 1 ) - offSetX);
			}
			System.out.println("BOOMM");

			return true;
		}
	}*/

	public boolean worldCollisionVertical(Character player){
		int offSetY = tMap.offsetY();
		int dy = player.getDY();
		
		Point tile = checkTileCollision(player);
		
		if(tile == null){
			if(player.onGround == 2){
				player.doJump = true;
				
				
			}else if(player.onGround == 0){
				
			}
			return false;
		}else{
			if(dy >= 0){
				System.out.println("BOOMMMM");
				System.out.println(player.getDY());
				player.doJump = false;
				player.onGround = 2;
				player.dy =0;
				//player.setPY(tMap.tilesToPixels(tile.y) - offSetY - player.getHeight());
			}else if(dy < 0){
				
				//player.setPY(tMap.tilesToPixels(tile.y + 1) - offSetY);
				//player.setPDY(0);
			}
			return true;
		}
	}
	
}
