package GridTest;

import java.util.ArrayList;

public class Collision {
	
	public Collision() {
		
	}
	
	//This doesn't move anything, simply detects that the immediate tile the player is trying to swap with is not a wall
	public boolean collisionDetect(String direction, int playerX, int playerY, ArrayList<ArrayList<MapObject>> gameMap) {
		
		boolean safeToMove = false;
		
		//Gets all objects around player
		String objectAboveClass = gameMap.get(playerY - 1).get(playerX).getClass().toString();
		String objectBelowClass = gameMap.get(playerY + 1).get(playerX).getClass().toString();
		String objectLeftClass = gameMap.get(playerY).get(playerX - 1).getClass().toString();
		String objectRightClass = gameMap.get(playerY).get(playerX + 1).getClass().toString();
		
		switch(direction) {
		
			case "up":
				if(playerY > 0 && (!"class GridTest.Wall".equals(objectAboveClass))){
					safeToMove = true;
				}
				break;
				
			case "down":
				if(playerY + 1 < gameMap.size() && (!"class GridTest.Wall".equals(objectBelowClass))){		//playerY + 1 because starting at 0 but .size starts count at 1
					safeToMove = true;
				}
				break;
				
			case "left":
				if(playerX > 0 && (!"class GridTest.Wall".equals(objectLeftClass))) {
					safeToMove = true;
				}
				break;
				
			case "right":
				if(playerX + 1 < gameMap.get(playerY).size() && (!"class GridTest.Wall".equals(objectRightClass))) {
					safeToMove = true;
				}
				break;
			}
		
		return safeToMove;
	}
	
	//Checks that a crate can be moved
	public boolean crateCollisionDetect(String direction, int crateX, int crateY, ArrayList<ArrayList<MapObject>> gameMap ) {
		
		boolean safeToMove = false;
		
		String objectAboveClass = gameMap.get(crateY - 1).get(crateX).getClass().toString();
		String objectBelowClass = gameMap.get(crateY + 1).get(crateX).getClass().toString();
		String objectLeftClass = gameMap.get(crateY).get(crateX - 1).getClass().toString();
		String objectRightClass = gameMap.get(crateY).get(crateX + 1).getClass().toString();
		
		switch(direction) {
		
		case "up":
	
			//(applies to all in switch - if crateY is > 0 and the class in the specified direction is NEITHER a wall nor another crate)
			if(crateY > 0) { 
				
				if(!"class GridTest.Wall".equals(objectAboveClass)){
							
					if(!"class GridTest.Crate".equals(objectAboveClass)){
						
						safeToMove = true;
							
					}
				}
			}
			break;
			
		case "down":
			if(crateY + 1 < gameMap.size()) {
				
				if(!"class GridTest.Wall".equals(objectBelowClass)){
					
					if(!"class GridTest.Crate".equals(objectBelowClass)){

						safeToMove = true;		
						
					}
				}
			}
			break;
			
		case "left":
			if(crateX > 0){
				
				if (!"class GridTest.Wall".equals(objectLeftClass)){

					if(!"class GridTest.Crate".equals(objectLeftClass)){
						
						safeToMove = true;
						
					}
				}
			}
			break;
			
		case "right":
			
			//Longest because crate can only move if its less than the length of the row, and neither wall nor crate is to the right of it
			if((crateX + 1) < gameMap.get(crateY).size()) {
				
				if(!"class GridTest.Wall".equals(objectRightClass)) {
						
					if(!"class GridTest.Crate".equals(objectRightClass)) {
							
							safeToMove = true;
					}
				}
			}
			break;
			
		}
		
		return safeToMove;
	}

}
