package time_blast.file_reading;
import java.io.*;  
import java.util.*;

// this method is responsible for reading dialogue files. 

public interface FileReader {
	
	// this method returns a hashmap of all entries in the csv file.
	default HashMap<String,ArrayList<String>> readCSV(String filename) throws FileNotFoundException{
		Scanner scan = new Scanner(new File(filename));  						// Create a Scanner object
		HashMap<String,ArrayList<String>> dialogueOptions = new HashMap<>();	// String key hashmap of all dialogue options
		ArrayList<String> categoryArray = new ArrayList<>();					// an array of a single category of dialogue
		
		while (scan.hasNext()){
			String line = scan.nextLine();
			
			// Removes all trailing commas from a line. This code is nasty. 
			// I'm like 1000% sure there's a better way to do this.
			String lastChar =line.substring(line.length()-2, line.length()-1);
			while(lastChar.equals(",")) {
				line = line.substring(0,line.length()-2);
			}	// this code makes me suicidal.
		
			categoryArray = new ArrayList<>(Arrays.asList(line.split(",")));
			dialogueOptions.put(categoryArray.remove(0).trim(), categoryArray);
		}
		scan.close();
		return dialogueOptions;
	}
	
	default <T> T readJSON(String fileName) {
		T t=null;
		
		
		
		
		
		return t;
	}
	
	// this method returns a single instance of an object of generic type T based on 'objectName'
	default <T> T getObject(String filename,String objectName) throws FileNotFoundException {
		T t = null;
		Scanner scan = new Scanner(new File(filename));  // Create a Scanner object
		HashMap<String,ArrayList<String>> ObjectMap = new HashMap<>();	// String key hashmap of all dialogue options
		
		// creates an arraylist of stat titles and removes the first element.(it's not a stat title)
		ArrayList<String> statTitles = new ArrayList<>(Arrays.asList(scan.nextLine().split(",")));
		String ObjectType = statTitles.remove(0);
		
		while (scan.hasNext()) {
			String key = scan.nextLine().split(",")[0];
			if (key.equals(objectName)) {
				
			}	
		}
		
		return t;
	}
}
