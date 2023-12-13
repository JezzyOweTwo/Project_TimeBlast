package time_blast.file_reading;
import java.io.*;
import java.nio.file.Paths;
import java.util.*;
import java.nio.file.*;

public abstract class FileReader<T> {
	protected Scanner scan;  														// Create a Scanner object
	protected File file;
	protected String filePath;
	protected long lineCount=0;
	
	FileReader(String filepath){
		this.filePath = Paths.get("").toAbsolutePath().toString()+filepath;					
		try {
			file = new File(this.filePath);
			lineCount = Files.lines(Path.of(this.filePath)).count();
		} catch(NullPointerException e) {
			System.out.println("File "+this.filePath+" cannot be located!");
			e.printStackTrace();
			return;
		} catch (IOException e){
			e.printStackTrace();
			System.out.println("File "+this.filePath+" cannot be located!");
			return;
		}
	}
	
	protected void startScanner() {
		try {scan = new Scanner(file);}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File "+this.filePath+" cannot be located!");
		} 
	}
	protected void stopScanner() {
		try {
			scan.close();
		} catch (IllegalStateException e) {
			e.printStackTrace();
			System.out.println("Scanner has already been closed!");
		}
	}


	public abstract T readline(int lineNumber);
	public abstract T readline(String objectName);
	public abstract <I,J> HashMap<I,J> readAll();

}
