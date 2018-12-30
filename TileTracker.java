package GridTest;

import java.util.ArrayList;

public class TileTracker {
	
	private ArrayList<ArrayList<Integer>> diamondLocations;
	private ArrayList<ArrayList<Integer>> crateLocations;
	private int diamondsToWin;
	
	public TileTracker(ArrayList<ArrayList<MoveableObject>> gameMap) {
		diamondLocations = new ArrayList<ArrayList<Integer>>();
		crateLocations = new ArrayList<ArrayList<Integer>>();
		countTrackables(gameMap);
		diamondsToWin = diamondLocations.size(); 
	}
	
	public void countTrackables(ArrayList<ArrayList<MoveableObject>> gameMap) {
		for(int i = 0; i < gameMap.size(); i++) {
			
			for(int j = 0; j < gameMap.get(i).size(); j++) {
				
				if(gameMap.get(i).get(j).getClass().toString().equals("class GridTest.Diamond")){
					
					
					ArrayList<Integer> location = new ArrayList<Integer>();
					
					location.add(j);	//add X coordinate of diamond
					location.add(i);	//add Y coordinate of diamond
					
					diamondLocations.add(location);
					
				} if(gameMap.get(i).get(j).getClass().toString().equals("class GridTest.Crate")) {
					ArrayList<Integer> location = new ArrayList<Integer>();
					
					location.add(j);	//add X coordinate of diamond
					location.add(i);	//add Y coordinate of diamond
					
					crateLocations.add(location);
				} 
			}
		}
	}
	
	public ArrayList<ArrayList<Integer>> diamondLocations() {
		return diamondLocations;
	}
	
	
	public void ChangeCratePos(int x, int y, int newX, int newY) {
		for(int i = 0; i < crateLocations.size(); i++){
			
			if((x == crateLocations.get(i).get(0)) && (y == crateLocations.get(i).get(1))) {
				
				crateLocations.get(i).set(0, newX);
				crateLocations.get(i).set(1, newY);
			}
		}
	}
	
	//returns the crate locations
	public ArrayList<ArrayList<Integer>> crateLocations() {
		return crateLocations;
	}
	
	//returns the number of crates on diamonds required to win
	public int getDiamondsToWinNum() {
		return diamondsToWin;
	}
	
	//Checks whether the crate image needs to change based on a crate being on a diamond pos
	public	boolean checkHasCrate(int x, int y) {
		
		boolean hasCrate = false;
		
		for(int i = 0; i < diamondLocations.size(); i++) {
			
			if(diamondLocations.get(i).get(0) == x && diamondLocations.get(i).get(1) == y) {
				hasCrate = true;
			}else {
				hasCrate = false;
			}
			
		}
		
		return hasCrate;
	}

	public boolean hasWon() {
		
		int matches = 0;
		boolean win = false;
		
		for(int i = 0; i < diamondLocations.size(); i++) {
			
			if(diamondLocations.get(i).get(0) == crateLocations.get(i).get(0)) {			//if x matches
				
				if(diamondLocations.get(i).get(1) == crateLocations.get(i).get(1)) {		//and if y matches (easier to read on 2 lines)
					
					matches++;
				}
			}
		}
		
		if(matches == diamondsToWin) {
			win = true;
			System.out.println("you win!");
		}
		System.out.println("diamonds = " + diamondLocations.size());
		System.out.println("Matches = " + matches);
		return win;
	}

}
