package GridTest;

import java.util.ArrayList;

public class DiamondTracker {
	
	private ArrayList<ArrayList<Integer>> diamondLocations;
	
	public DiamondTracker() {
		diamondLocations = new ArrayList<ArrayList<Integer>>();
	}
	
	public void countDiamonds(ArrayList<ArrayList<String>> gameMap) {
		for(int i = 0; i < gameMap.size(); i++) {
			
			for(int j = 0; j < gameMap.get(i).size(); j++) {
				
				if(gameMap.get(i).get(j).getClass().toString().equals("Class GridTest.Diamond")) {
					ArrayList<Integer> location = new ArrayList<Integer>();
					
					location.add(j);	//add X coordinate of diamond
					location.add(i);	//add Y coordinate of diamond
					
					diamondLocations.add(location);
				}
				
			}
		}
	}
	
	
	public ArrayList<ArrayList<Integer>> diamondLocations() {
		return diamondLocations;
	}

}
