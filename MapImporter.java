package GridTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MapImporter {

	private HashMap<Integer, ArrayList<ArrayList<String>>> mFileContents;
	private int mFileNumber;
	
	
	
	public MapImporter() {
		mFileContents = new HashMap<Integer, ArrayList<ArrayList<String>>>();
		mFileNumber = 1;
		this.loadMaps();
	}
	
	//Serves only to run the readfile method multiple times
	public void loadMaps() {
		do {
			String filename = "level"+mFileNumber+".txt";												//Makes the filename dynamic depending on loop no
			this.readFile(filename);													
			mFileNumber++;
		}while(mFileNumber < 6);
	}
	
	
	//Reads the file line by line and creates a multidimensional arraylist from the output of the file reading
		public ArrayList<ArrayList<String>> readFile(String filename) {
			ArrayList<ArrayList<String>> mapChars = new ArrayList<ArrayList <String>>();				//Arraylist of String arraylist (String is more versatile)					
			File inputFile = null;																		//File object that takes a file path
			FileReader fileReader = null;																//Reader to read files
			BufferedReader bufferReader =null;															//Buffered reader, more versatile than above but requires base reader
			
			try { //Start try -------------------------------------------------------
				inputFile = new File("src/GridTest/" + filename);											//Give File object its path
				fileReader = new FileReader(inputFile);													//Give the filereader the file object
				bufferReader = new BufferedReader(fileReader);											//Give the buffered reader the filereader to use
				
			} catch (FileNotFoundException fnfe){ //End try start catch--------------------------
				System.out.println("File doesn't exist" + inputFile.getAbsolutePath());
				fnfe.printStackTrace();
				
			} catch(IOException ioe) { //End catch start catch--------------------------
				ioe.printStackTrace();
			} 
			
			
			try { //Start try -------------------------------------------------------
				String tempString = "";																	//Used to hold a line from the file as a placeholder
				int i = 0;																				//Iterates by line (don't know how many lines)
				
				do {																					//Start of do - want to read at least 1 line
					tempString = bufferReader.readLine();												//Read the first line from the file (end at CR)
					ArrayList <String> placeholder = new ArrayList<String>();							//set up a new instance of a placeholder arraylist
					
						if(tempString != null) {														//As long as its null (could return to this point and no next line)...
							
							for(int j = 0; j < tempString.length(); j++) {								//Then going through the string by each character
								String mapChar = Character.toString(tempString.charAt(j));				//Take individual character and assign to variable

								placeholder.add(mapChar);												//Take variable from above and put into placeholder arraylist
								
							}																			
							mapChars.add(placeholder);													//Add placeholder arraylist to multi-dmsnl arraylist
							i++;																	//next line = next outer index for multi-dmsnl
						}
				}while(tempString != null);																//Exit loop if not met (end of file)
				mFileContents.put(mFileNumber, mapChars);												//Add map arraylist to hashmap
			
			} catch (IOException e) {//End try start catch--------------------------
				e.printStackTrace();
			}catch(NullPointerException NPE){//End catch start catch---------------
				NPE.printStackTrace();
			}
			try {							
				bufferReader.close();																	//MUST CLOSE BUFFER READER
			} catch (IOException e) {
				e.printStackTrace();
			}
			return mapChars;
		}

		public ArrayList<ArrayList<String>> getMap(int fileNo) {
			ArrayList<ArrayList<String>> mapChoice = new ArrayList<ArrayList<String>>();
			mapChoice = mFileContents.get(fileNo);
			return mapChoice;
		}

}
