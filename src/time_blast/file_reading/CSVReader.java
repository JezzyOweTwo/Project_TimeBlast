package time_blast.file_reading;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
	public HashMap<String, T> readAll() {
		startScanner();
		String key;
		T value;
		HashMap<String,T> allDialogue = new HashMap<>();
		int i=1;	// starts from the second line because first line is category headers
		while (scan.hasNext()){
			key = new ArrayList<>(Arrays.asList(scan.nextLine().split(","))).get(0).strip();
			value = readline(i);
			allDialogue.put(key,value);
			i++;
		}
		stopScanner();
		return allDialogue;
	}
	
	@Override
	public T readline(int lineNumber) {
		startScanner();
		String[] line = null;
		
		try (Stream<String> streamOfLines = Files.lines( Paths.get(filePath) )){
			line = streamOfLines.skip(lineNumber)
		         .findFirst()
		         .get()
		         .split(",");
			stopScanner();
		}
		
		catch (IOException e){
		  e.printStackTrace();
		  stopScanner();
		  return null;
		}
		
		ArrayList<String> values = new ArrayList<>(Arrays.asList(line));
		T t= (T)(new Dialogue());
		return t.create(CATEGORIES, values);
	}

	@Override
	public T readline(String objectName) {
		return null;
	}	
}
