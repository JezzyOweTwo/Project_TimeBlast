package time_blast.file_reading;
import java.io.*;
import java.nio.file.Paths;
import java.util.*;
import java.nio.file.*;

public abstract class FileReader {
    protected String filePath;
	public FileReader(String relativePath){
        filePath= Paths.get("").toAbsolutePath().toString()+relativePath;
    }
    public abstract<T,G> HashMap<T,G> readAll();
    public abstract <T> ArrayList<T> readline(int lineNumber);
    public abstract <T> ArrayList<T> readline(String objectName);
}
