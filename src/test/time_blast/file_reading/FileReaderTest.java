package time_blast.file_reading;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import time_blast.file_reading.CSVReader;
import time_blast.file_reading.Dialogue;
import time_blast.game_logic.entities.attributes.Item;
import time_blast.game_logic.entities.attributes.ItemTest;

class FileReaderTest {

    @Test
    void test_readCSV_createDialogue_1() {
//        Dialogue dialogue;
//        HashMap<String,Dialogue> hashmap = new HashMap<>();
//        CSVReader<Dialogue> csvReader=new CSVReader<>("\\src\\time_blast\\game_logic\\battle\\BattleDialogue.csv");
//  //      dialogue = csvReader.readline(1);
//        hashmap = csvReader.readAll();
//        System.out.println(hashmap);
// //       System.out.println(dialogue.get(0));
    }

    @Test
    void test_readCSV_createItem_1(){
        Item item ;
        CSVReader<Item> csvReader =new CSVReader<>("\\src\\time_blast\\game_logic\\entities\\attributes\\ItemList.csv");
        HashMap<String,Item> hashmap = new HashMap<>();
        hashmap = csvReader.readAll();
        System.out.println(hashmap);
    }
}
