package time_blast.file_reading;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Dialogue extends ArrayList<String> implements  FileReadable<Dialogue>{
	
	public Dialogue(ArrayList<String> dialogue){
		super(dialogue);
	}
	public Dialogue(){}

	@Override
	public <J> Dialogue create(J value) {
		try {
			ArrayList<String> line = (ArrayList<String>)value;
			line.remove(0);
			return new Dialogue(line);
		} catch(ClassCastException e) {
			e.printStackTrace();
		}
		return null;
	}
}
