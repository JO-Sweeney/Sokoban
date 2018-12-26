package GridTest;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import java.util.Arrays;

public class Game {
	
	private GridPane grid;
	private ArrayList<ArrayList<String>> mCharMap;
	private ArrayList<ArrayList<MoveableObject>> mObjectMap;
	private MapImporter mMapImporter;
	
	
	public Game() {
		setStringMap();
		setObjectList();

		setGrid();
	}
	
	public void setStringMap() {
		mMapImporter = new MapImporter();
		
		mCharMap = new ArrayList<ArrayList<String>>();
		mCharMap = mMapImporter.getMap(1);
	
	}
	
	public void setObjectList() {
		mObjectMap = new ArrayList<ArrayList<MoveableObject>>();
		ArrayList<MoveableObject> lineArray = null;
		
		for(int i = 0; i < mCharMap.size(); i++) {
			
			lineArray = new ArrayList<MoveableObject>();
			
			for(int j = 0; j < mCharMap.get(i).size(); j++) {
				
				switch(mCharMap.get(i).get(j)) {
					
					case "@": 
						lineArray.add(new Player());
						break;
					case " ":
						lineArray.add(new Floor());
						break;	
					case "X":
						lineArray.add(new Wall());
						break;
					case ".":
						lineArray.add(new Diamond());
						break;
					case "*":
						lineArray.add(new Crate());
						break;
				}
			}
			
			mObjectMap.add(lineArray);
		}
		
	}
	
	public ArrayList<ArrayList<MoveableObject>> getObjectList(){
		return mObjectMap;
	}
	
	public void checkContents() {
		for(int i = 0; i < mObjectMap.size(); i++) {
			
			for(int j = 0; j < mObjectMap.get(i).size(); j++) {
				
				System.out.println(j + ". toString = " + mObjectMap.get(i).get(j).getClass().toString());
			}
		}
	}
	
	public void setGrid() {
		grid = new GridPane();
		ImageView imgPlaceholder = null;
		for(int i = 0; i < mObjectMap.size(); i++) {
			
			for(int j = 0; j < mObjectMap.get(i).size(); j++) {
				imgPlaceholder = mObjectMap.get(i).get(j).getImageView();
				int X = j;
				int Y = i;
				grid.add(imgPlaceholder, X, Y);
			}
		}

	}
	
	public void updateGrid() {
		grid.getChildren().clear();
		for(int i = 0; i < mObjectMap.size(); i++) {
			
			for(int j = 0; j < mObjectMap.get(i).size(); j++) {
				ImageView imgPlaceholder = mObjectMap.get(i).get(j).getImageView();
				int X = j;
				int Y = i;
				grid.add(imgPlaceholder, X, Y);
			}
		}

	}
	
	public GridPane getGrid() {
		return grid;
	}
	
	
	public void moveObject(String direction) {
		int playerX = 0;
		int playerY = 0;
		
		for(int i = 0; i < mObjectMap.size(); i++) {
			
			for(int j = 0; j < mObjectMap.get(i).size(); j++) {
				
				if(mObjectMap.get(i).get(j).getClass().toString().equals("class GridTest.Player")) {
					
					playerX = j;
					playerY = i;
				}
			}
		}
		
		int playerNewX = playerX;		//Initialise these as equal so that nothing happens if the movement shouldn't happen
		int playerNewY = playerY;		//same as above
		
		if(collisionDetect(direction, playerX, playerY)) {	//If collision says that movement is acceptable, then make changes
		
			switch(direction) {
			
			case "up":				
				playerNewX = playerX;											
				playerNewY = playerY - 1;				//Nothing has moved, but this sets up the potential coordinates should movement be okay					
				break;
				
			case "down":
				playerNewX = playerX;
				playerNewY = playerY + 1;
				break;
				
			case "left":
				playerNewX = playerX - 1;
				playerNewY = playerY;
				break;
				
			case "right":
				playerNewX = playerX + 1;
				playerNewY = playerY;
				break;
			}
		
			MoveableObject placeholder = new MoveableObject();
			placeholder = mObjectMap.get(playerNewY).get(playerNewX);									//Get object we want to swap with player
			
			//if the object we're trying to swap with is a crate, and it can be moved (check the crate can be moved first)
			if(placeholder.getClass().toString().equals("class GridTest.Crate")) {
			
				if(crateCollisionDetect(direction, playerNewX, playerNewY)) {
					
					int crateX = playerNewX;			//Crate X for now is still the potential location for the player
					int crateY = playerNewY;			//Same as above
					
					int crateNewX = crateX;				//Why not crateNewX = playerNewX - would be the same thing but crateNewX = crate keeps symantic association (easier to understand)
					int crateNewY = crateY;			
					
					switch(direction) {
					
					case "up":															
						crateNewY--;					//Y position of crate should be - 1							
						break;
					case "down":
						crateNewY++;					//Y position of crate should be + 1
						break;
					case "left":
						crateNewX--;					//X position of crate should be - 1
						break;
					case "right":
						crateNewX++;					//X position of crate should be + 1
						break;
					}
				
					MoveableObject placeholder2 = new MoveableObject();
					placeholder2 = mObjectMap.get(crateNewY).get(crateNewX);								//Gets the object to swap the crate with
					
					
					mObjectMap.get(playerNewY).set(playerNewX, mObjectMap.get(playerY).get(playerX));		//set the new location = to player (not new identical object, but existing player)
					
					mObjectMap.get(crateNewY).set(crateNewX, placeholder);
					
					mObjectMap.get(playerY).set(playerX, placeholder2);
					
				}else {
					//Do not move
				}
				
			}else {
				
				mObjectMap.get(playerNewY).set(playerNewX, mObjectMap.get(playerY).get(playerX));			//place found player at new index location
				
				mObjectMap.get(playerY).set(playerX, placeholder);											//place placeholder object at player's old location
				
			}
			

		
		}//End of if, if conditions aren't met then don't do anything
		System.out.println(playerNewX + "," + playerNewY);
	}
	
	
	//This doesn't move anything, simply detects that the immediate tile the player is trying to swap with is not a wall
	public boolean collisionDetect(String direction, int playerX, int playerY) {
		
		boolean safeToMove = false;
		
		//Gets all objects around player
		String objectAboveClass = mObjectMap.get(playerY - 1).get(playerX).getClass().toString();
		String objectBelowClass = mObjectMap.get(playerY + 1).get(playerX).getClass().toString();
		String objectLeftClass = mObjectMap.get(playerY).get(playerX - 1).getClass().toString();
		String objectRightClass = mObjectMap.get(playerY).get(playerX + 1).getClass().toString();
		
		switch(direction) {
		
			case "up":
				if(playerY > 0 && (!"class GridTest.Wall".equals(objectAboveClass))){
					safeToMove = true;
				}
				break;
				
			case "down":
				if(playerY + 1 < mObjectMap.size() && (!"class GridTest.Wall".equals(objectBelowClass))){		//playerY + 1 because starting at 0 but .size starts count at 1
					safeToMove = true;
				}
				break;
				
			case "left":
				if(playerX > 0 && (!"class GridTest.Wall".equals(objectLeftClass))) {
					safeToMove = true;
				}
				break;
				
			case "right":
				if(playerX + 1 < mObjectMap.get(playerY).size() && (!"class GridTest.Wall".equals(objectRightClass))) {
					safeToMove = true;
				}
				break;
			}
		
		return safeToMove;
	}
	
	//Checks that a crate can be moved
	public boolean crateCollisionDetect(String direction, int crateX, int crateY) {
		
		boolean safeToMove = false;
		
		String objectAboveClass = mObjectMap.get(crateY - 1).get(crateX).getClass().toString();
		String objectBelowClass = mObjectMap.get(crateY + 1).get(crateX).getClass().toString();
		String objectLeftClass = mObjectMap.get(crateY).get(crateX - 1).getClass().toString();
		String objectRightClass = mObjectMap.get(crateY).get(crateX + 1).getClass().toString();
		
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
			if(crateY + 1 < mObjectMap.size()) {
				
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
			if((crateX + 1) < mObjectMap.get(crateY).size()) {
				
				if(!"class GridTest.Wall".equals(objectRightClass)) {
						
					if(!"class GridTest.Crate".equals(objectRightClass)) {
							
							safeToMove = true;
							System.out.println("right condition met");
					}
				}
			}
			break;
			
		}
		
		return safeToMove;
	}
	
	
}
