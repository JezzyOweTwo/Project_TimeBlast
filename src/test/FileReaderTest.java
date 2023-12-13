package test;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import time_blast.file_reading.CSVReader;
import time_blast.file_reading.Dialogue;

class FileReaderTest {

	@Test
	void test_readCSV_1() {
		Dialogue dialogue;
		CSVReader<Dialogue> csvReader=new CSVReader<>("\\src\\time_blast\\game_logic\\Battle\\BattleDialogue.csv");
		dialogue = csvReader.readline(0);
		System.out.println(dialogue.get(0));
	}
}
