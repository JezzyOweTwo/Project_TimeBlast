package time_blast.file_reading;
import time_blast.game_logic.entities.attributes.Item;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class CSVReader<T extends FileReadable<T>> extends FileReader<T>{
	private final String[] CATEGORIES;
	
	public CSVReader(String filepath) {
		super(filepath);
		startScanner();
		CATEGORIES = scan.nextLine().split(",");
		stopScanner();
	}
	
	// this method returns a hashmap of all entries in the csv file.
	@Override
	public HashMap<String,T> readAll() {
		startScanner();
		scan.nextLine();						// skips first line b/c those are category headers
		String key;
		ArrayList<String> keyArray;
		T value;
		HashMap<String, T> allDialogue = new HashMap<>();
		for(int i=1;i< lineCount;i++) {			// starts from the second line because first line is category headers
			keyArray = new ArrayList<>(Arrays.asList(scan.nextLine().split(",")));
			key = keyArray.get(0).strip();
			value = generateObject(keyArray);
			allDialogue.put(key, value);
		}
		stopScanner();
		return allDialogue;
	}

	private T generateObject(ArrayList<String> values) {
		T t= (T)(new Dialogue());
		return t.create(values);
	}
	@Override
	public T readline(int lineNumber) {
		String[] line = null;
		startScanner();
		try (Stream<String> streamOfLines = Files.lines( Paths.get(filePath) )){
			line = streamOfLines.skip(lineNumber)
		         .findFirst()
		         .get()
				 .trim()
		         .split(",");
		}
		catch (IOException e){
		  e.printStackTrace();
		  return null;
		}
		finally {
			stopScanner();
		}
		ArrayList<String> values = new ArrayList<>(Arrays.asList(line));
		return generateObject(values);
	}

	@Override
	public T readline(String objectName) {
		startScanner();
		String bigString=null;
		ArrayList<String> values = new ArrayList<>();
		scan.useDelimiter(objectName);
		try{
			bigString = scan.next();
			scan.useDelimiter(",");
		}catch (NoSuchElementException e){
			e.printStackTrace();
			return null;
		}

		for(int i=0;i<CATEGORIES.length;i++){
			values.add(scan.next());
		}
		stopScanner();
		return generateObject(values);
	}

}
