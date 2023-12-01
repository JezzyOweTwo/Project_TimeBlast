package test;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.Test;

import time_blast.file_reading.FileReader;

class FileReaderTest implements FileReader{

	@Test
	void test_readCSV_1() {
		HashMap<String, ArrayList<String>> csv = new HashMap<>();
		csv = readCSV("\\src\\time_blast\\game_logic\\Battle\\BattleDialogue.csv");
		for (String s:csv.keySet()) 
			System.out.println(csv.get(s));	
	}
}
