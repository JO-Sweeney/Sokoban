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
		//mMapImporter = new MapImporter();
		
		mCharMap = new ArrayList<ArrayList<String>>();
		ArrayList<String> lineArray = new ArrayList<String>();
		
		//mCharMap = mMapImporter.getMap(1);
		
		lineArray.add("@");
		lineArray.add(" ");
		lineArray.add(" ");
		lineArray.add(" ");
		lineArray.add(" ");
		
		mCharMap.add(lineArray);
		
		for(int i = 0; i < mCharMap.size(); i++) {
			String line = "";
			for(int j = 0; j < mCharMap.get(i).size(); j++) {
				line = line + mCharMap.get(i).get(j);
			}
			System.out.println(line);
		}
	}
	
	public void setObjectList() {
		mObjectMap = new ArrayList<ArrayList<MoveableObject>>();
		ArrayList<MoveableObject> lineArray = new ArrayList<MoveableObject>();
		
		for(int i = 0; i < mCharMap.size(); i++) {
			
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
		for(int i = 0; i < mObjectMap.size(); i++) {
			
			for(int j = 0; j < mObjectMap.get(i).size(); j++) {
				ImageView imgPlaceholder = mObjectMap.get(i).get(j).getImageView();
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
		
		int playerNewX = 0;
		int playerNewY = 0;
		
		for(int i = 0; i < mObjectMap.size(); i++) {
			
			for(int j = 0; j < mObjectMap.get(i).size(); j++) {
				
				if(mObjectMap.get(i).get(j).getClass().toString().equals("class GridTest.Player")) {
					
					playerX = j;
					playerY = i;
				}
			}
		}
		
		switch(direction) {
		
		case "up":
			playerNewX = playerX;
			playerNewY = playerY - 1;
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
		placeholder = mObjectMap.get(playerNewY).get(playerNewX);
		
		mObjectMap.get(playerNewY).set(playerNewX, mObjectMap.get(playerY).get(playerX));			//place found player at new index location
		
		mObjectMap.get(playerY).set(playerX, placeholder);											//place placeholder object at player's old location
		
	}
	
}
