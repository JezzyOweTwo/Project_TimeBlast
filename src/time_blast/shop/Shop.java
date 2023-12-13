package time_blast.shop;

import java.util.ArrayList;
import java.util.HashMap;

import time_blast.file_reading.CSVReader;
import time_blast.file_reading.Dialogue;
import time_blast.file_reading.FileReader;

public class Shop {
	private HashMap<String,Dialogue> dialogue = new HashMap<>();
	
	Shop(){
		FileReader<Dialogue> fileReader = new CSVReader("\\src\\time_blast\\game_logic\\Battle\\BattleDialogue.csv");
		dialogue = fileReader.readAll();
	}
}
