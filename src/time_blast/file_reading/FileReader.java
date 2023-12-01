package time_blast.file_reading;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

// You're probably wondering why this isn't a static class right now.
// The short answer is that I plan on switching to UI based design.
// using an interface makes it easier to tell which classes use this
// soon to be deprecated method. 
// If I decide to keep this class, i'll change it.
// also ngl interfaces with method bodies is just generally kinda cool

public interface FileReader {
	
	// this method returns a hashmap of all entries in the csv file.
	default HashMap<String,ArrayList<String>> readCSV(String filename) {
		String defaultPath = Paths.get("").toAbsolutePath().toString();
		filename = defaultPath+filename;
		Scanner scan;  															// Create a Scanner object
		HashMap<String,ArrayList<String>> dialogueOptions = new HashMap<>();	// String key hashmap of all dialogue options
		ArrayList<String> categoryArray = new ArrayList<>();					// an array of a single category of dialogue
		
		try {
			File file = new File(filename);
			scan = new Scanner(file);  							// Create a Scanner object
		} catch(FileNotFoundException e) {
			System.out.println("File not found!");
			return dialogueOptions;
		}
		
		while (scan.hasNext()){
			categoryArray = new ArrayList<>(Arrays.asList(scan.nextLine().split(",")));
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
