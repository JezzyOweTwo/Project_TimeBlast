package test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import time_blast.file_reading.FileReader;
import time_blast.game_logic.Battle.Battle;
import time_blast.game_logic.entities.Enemy;
import time_blast.game_logic.entities.Player;

class BattleTest implements FileReader{
	
	public static ArrayList<Enemy> createEnemyArrayWithoutStats(){
		ArrayList<Enemy> enemyarray = new ArrayList<>();
		for (int i=0;i<3;i++) {
			enemyarray.add(new Enemy());
		}
		return enemyarray;
	}
	
	public static ArrayList<Enemy> createEnemyArrayWithStats(){
		String[] names = {"Joe","Bob","Charles"};
		ArrayList<Enemy> enemyarray = new ArrayList<>();
		for (int j=0;j<3;j++) {
			enemyarray.add(EntityTest.createEnemyWithStats());
			enemyarray.get(j).setName(names[j]);
		}
		return enemyarray;
	}
	
	@Test
	void Constructor_test_1() {
		ArrayList<Enemy> enemyarray = createEnemyArrayWithStats();
		Player player = EntityTest.createPlayerWithStats();
		Battle battle = new Battle(player,enemyarray);
	}
	@Test
	void Constructor_test_2() {
		ArrayList<Enemy> enemyarray = createEnemyArrayWithStats();
		Player player = EntityTest.createPlayerWithStats();
		Battle battle = new Battle(player,enemyarray);
		boolean expected = true;
		boolean actual = player != battle.getPlayer();
		assertEquals(actual,expected,"Player object was not deeply copied");
	}
	@Test
	void Constructor_test_3() {
		ArrayList<Enemy> enemyarray = createEnemyArrayWithStats();
		Player player = EntityTest.createPlayerWithStats();
		Battle battle = new Battle(player,enemyarray);
		boolean expected = true;
		boolean actual = enemyarray != battle.getEnemies();
		assertEquals(actual,expected,"Enemy object was not deeply copied");
	}
	@Test
	void Constructor_test_4() {
		ArrayList<Enemy> enemyarray = createEnemyArrayWithStats();
		Player player = EntityTest.createPlayerWithStats();
		Battle battle = new Battle(player,enemyarray);
		HashMap<String,ArrayList<String>> dialogue = readCSV("BattleDialogue.csv");
		boolean expected = true;
		boolean actual = dialogue != battle.getDialogue();
		assertEquals(actual,expected,"Dialogue object was not deeply copied");
	}
	@Test
	void Constructor_test_5() {
		ArrayList<Enemy> enemyarray = createEnemyArrayWithStats();
		Player player = EntityTest.createPlayerWithStats();
		Battle battle = new Battle(player,enemyarray);
		battle.runBattle();
	}
}
