package GridTest;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {
	
	private GridPane grid;
	private ArrayList<ArrayList<String>> mCharMap;
	private ArrayList<ArrayList<MapObject>> mObjectMap;
	private TileTracker mTracker;
	private Text displayMoveNo;
	private int mNumOfMoves;
	private boolean haveWon;
	private Collision collision;
	
	public Game(ArrayList<ArrayList<String>> charMap) {
		haveWon = false;
		setStringMap(charMap);
	
		setObjectList();
		mTracker = new TileTracker(mObjectMap);
		collision = new Collision();
		
		setGrid();
		displayMoveNo = new Text();
		displayMoveNo.setText("" + mNumOfMoves);
	}
	
	public void setStringMap(ArrayList<ArrayList<String>> charMap) {
		
		mCharMap = new ArrayList<ArrayList<String>>();
		mCharMap = charMap;
	
	}
	
	public void setObjectList() {
		mObjectMap = new ArrayList<ArrayList<MapObject>>();
		ArrayList<MapObject> lineArray = null;
		
		for(int i = 0; i < mCharMap.size(); i++) {
			
			lineArray = new ArrayList<MapObject>();
			
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
						lineArray.add(new Diamond())	;				//set as floor - diamond = true
						break;
					case "*":
						lineArray.add(new Crate());
						break;
				}
			}
			
			mObjectMap.add(lineArray);
		}
		
	}
	
	public ArrayList<ArrayList<MapObject>> getObjectList(){
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
	
	public void updateMoveDisplay() {
		displayMoveNo.setText("" + mNumOfMoves);
	}
	
	public Text getMoveDisplay() {
		return displayMoveNo;
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
		
		if(collision.collisionDetect(direction, playerX, playerY, mObjectMap)) {	//If collision says that movement is acceptable, then make changes
			
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
			
			MapObject placeholder = new MapObject();
			placeholder = mObjectMap.get(playerNewY).get(playerNewX);									//Get object we want to swap with player
			
			if(placeholder.getClass().toString().equals("class GridTest.Diamond")){					//If the object we're swapping with player is a diamond
				placeholder = new Floor();																						//Make that object floor instead
			}
			 
			//if the object we're trying to swap with is a crate, and it can be moved (check the crate can be moved first)
			if(placeholder.getClass().toString().equals("class GridTest.Crate")) {
			
				if(collision.crateCollisionDetect(direction, playerNewX, playerNewY, mObjectMap)) {
					
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
				
					MapObject placeholder2 = new MapObject();
					placeholder2 = mObjectMap.get(crateNewY).get(crateNewX);								//Gets the object to swap the crate with
					
					if(placeholder2.getClass().toString().equals("class GridTest.Diamond")){					//If the object we're swapping with player is a diamond
						placeholder2 = new Floor();																						//Make that object floor instead
					}
					
					mObjectMap.get(playerNewY).set(playerNewX, mObjectMap.get(playerY).get(playerX));		//set the new location = to player (not new identical object, but existing player)
					
					mObjectMap.get(crateNewY).set(crateNewX, placeholder);
					
					mObjectMap.get(playerY).set(playerX, placeholder2);
					
					mTracker.ChangeCratePos(crateX, crateY, crateNewX, crateNewY);
					
					mNumOfMoves++;					//move was successful so add 1 move
					
					if(mTracker.checkPlacedCrate(crateNewX, crateNewY)){
						mObjectMap.get(crateNewY).get(crateNewX).setPath("CrateInPlace.png");
						mObjectMap.get(crateNewY).get(crateNewX).setImageView();
					}else {
						mObjectMap.get(crateNewY).get(crateNewX).setPath("Crate.png");				//just in case - potential of crate moving off diamonds
						mObjectMap.get(crateNewY).get(crateNewX).setImageView();
					}
				}else {
					//Do not move
				}
				
			}else {
				
				mObjectMap.get(playerNewY).set(playerNewX, mObjectMap.get(playerY).get(playerX));			//place found player at new index location
				
				mObjectMap.get(playerY).set(playerX, placeholder);											//place placeholder object at player's old location 
				
				mNumOfMoves++;						//move was successful so add 1 more
			}
			

		
		}//End of if, if conditions aren't met then don't do anything
		resetDiamonds();
		if(mTracker.hasWon()) {
			haveWon = true;
		}
	}
	
	//if diamond indexes have floor on them, set them to be diamonds
	public void resetDiamonds() {
		for(int i = 0 ; i < mTracker.diamondLocations().size();  i++) {
			
			int x = mTracker.diamondLocations().get(i).get(0);
			int y = mTracker.diamondLocations().get(i).get(1);
			
			if(mObjectMap.get(y).get(x).getClass().toString().equals("class GridTest.Floor")) {
				mObjectMap.get(y).set(x, new Diamond());
			}
		}
	}
	
	
public boolean getWin() {
	return haveWon;
}

	
	
	
}
