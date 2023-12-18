package time_blast.file_reading;
import time_blast.game_logic.entities.attributes.Item;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class CSVReader extends FileReader{

	public CSVReader(String relativePath){
		super(relativePath);
	}
	
	// this method returns a hashmap of all entries in the csv file.
	@Override
	public HashMap<String,ArrayList<String>> readAll() {
		Scanner scan = null;
		try {
			scan = new Scanner(new File(filePath));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		ArrayList<String> line;
		HashMap<String, ArrayList<String>> allDialogue = new HashMap<>();

		while(scan.hasNext()){
			line = new ArrayList<>(Arrays.asList(scan.nextLine().split(",")));
			allDialogue.put(line.remove(0), line);
		}
		scan.close();
		return allDialogue;
	}

	@Override
	public ArrayList<String> readline(int lineNumber) {
		try (Stream<String> streamOfLines = Files.lines( Paths.get(filePath) )){
			return new ArrayList<>(
							Arrays.asList(streamOfLines.skip(lineNumber)
							.findFirst()
							.get()
							.trim()
							.split(",")));
		}
		catch (IOException e){
		  e.printStackTrace();
		  return null;
		}
	}

	@Override
	public ArrayList<String> readline(String objectName) {
//		startScanner();
//		ArrayList<String> values = new ArrayList<>();
//		scan.useDelimiter(objectName);
//		try{
//			scan.next();
//			scan.useDelimiter(",");
//		}catch (NoSuchElementException e){
//			e.printStackTrace();
//			return null;
//		}
//
//		for(int i=0;i<CATEGORIES.length;i++){
//			values.add(scan.next());
//		}
//		stopScanner();
//		return generateObject(values);
		return null;
	}
}