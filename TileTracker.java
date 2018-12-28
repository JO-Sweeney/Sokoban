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
				
				if(gameMap.get(i).get(j).getClass().toString().equals("class GridTest.Floor")) {
					ArrayList<Integer> location = new ArrayList<Integer>();
					
					location.add(j);	//add X coordinate of diamond
					location.add(i);	//add Y coordinate of diamond
					
					diamondLocations.add(location);
					System.out.println("X = " + j + ", Y = " + i + " - FOUND!");
				} if(gameMap.get(i).get(j).getClass().toString().equals("class GridTest.Crate")) {
					ArrayList<Integer> location = new ArrayList<Integer>();
					
					location.add(j);	//add X coordinate of diamond
					location.add(i);	//add Y coordinate of diamond
					
					crateLocations.add(location);
					System.out.println("X = " + j + ", Y = " + i + " - FOUND!");
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
				
				System.out.println(crateLocations.get(i).get(0));
				System.out.println(crateLocations.get(i).get(1));
			}
		}
	}
	
	public ArrayList<ArrayList<Integer>> crateLocations() {
		return crateLocations;
	}
	
	public int getDiamondsToWinNum() {
		return diamondsToWin;
	}
	
	public	boolean checkHasCrate(int x, int y) {
		
		boolean hasCrate = false;
		
		for(int i = 0; i < diamondLocations.size(); i++) {
			
			if(diamondLocations.get(i).get(0) == x && diamondLocations.get(i).get(1) == y) {
				hasCrate = false;
			}else {
				hasCrate = true;
			}
			
		}
		
		return hasCrate;
	}

}
