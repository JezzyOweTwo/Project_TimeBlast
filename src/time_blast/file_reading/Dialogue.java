package time_blast.file_reading;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Dialogue implements FileReadable<Dialogue>{
	private ArrayList<String> dialogue;
	
	public Dialogue(ArrayList<String> dialogue){
		this.dialogue= new ArrayList<String>(dialogue);
	}
	public Dialogue(){}
	
	public Dialogue(Dialogue dialogue){this(dialogue.getAll());}
	public ArrayList<String> getAll() {return dialogue;}
	public void set(ArrayList<String> dialogue) {this.dialogue = dialogue;}
    public String get(int index) {return dialogue.get(index).toString();}

	@Override
	public String toString(){
		return dialogue.toString();
	}

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
