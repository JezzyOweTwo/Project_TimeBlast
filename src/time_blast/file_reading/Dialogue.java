package time_blast.file_reading;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Dialogue extends ArrayList<String> {
	
	public Dialogue(ArrayList<String> dialogue){
		super(dialogue);
	}
	public Dialogue(){}
	public Dialogue(CSVReader reader, int index){
		this(reader.readline(index));
		this.remove(0);
	}
}