package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;

import time_blast.game_logic.Inventory;
import time_blast.game_logic.entities.Player;
import time_blast.game_logic.entities.StatName;

class EntityTest {

	@Test
	void constructor_test_1() {
		HashMap<StatName,Integer> playerStats = new HashMap<>();
		playerStats.put(StatName.LVL, 5);
		playerStats.put(StatName.IQ, 2);
		Inventory inv = new Inventory();
		String name = "Jamal";
		Player player1 = new Player(playerStats,inv,name);
		Player player2 = new Player(playerStats,inv,"James");	
		boolean actual = player1.getStat(StatName.CURHP) == player2.getStat(StatName.CURHP);
		boolean expected = true;
		assertEquals(actual,expected,"Stat Assignment is Incorrect!");	
	}
	
	@Test
	void constructor_test_2() {
		HashMap<StatName,Integer> playerStats = new HashMap<>();
		playerStats.put(StatName.LVL, 5);
		playerStats.put(StatName.IQ, 2);
		Inventory inv = new Inventory();
		String name = "Jamal";
		Player player1 = new Player(playerStats,inv,name);
		Player player2 = new Player(playerStats,inv,"James");	
		boolean actual = player1.getStat(StatName.LVL) == player2.getStat(StatName.CURHP);
		boolean expected = false;
		assertEquals(actual,expected,"Stat Assignment is Incorrect!");	
	}
	
	@Test
	void constructor_test_3() {
		HashMap<StatName,Integer> playerStats = new HashMap<>();
		playerStats.put(StatName.LVL, 5);
		playerStats.put(StatName.IQ, 2);
		Inventory inv = new Inventory();
		String name = "Jamal";
		Player player1 = new Player(playerStats,inv,name);
		Player player2 = new Player(playerStats,inv,"James");	
		boolean actual = player1.getStat(StatName.LVL) == player2.getStat(StatName.LVL);
		boolean expected = true;
		assertEquals(actual,expected,"Stat Assignment is Incorrect!");	
	}
	
	@Test
	void constructor_test_4() {
		HashMap<StatName,Integer> playerStats = new HashMap<>();
		playerStats.put(StatName.LVL, 5);
		playerStats.put(StatName.IQ, 2);
		Inventory inv = new Inventory();
		String name = "Jamal";
		Player player1 = new Player(playerStats,inv,name);
		Player player2 = new Player(player1);
		
		boolean actual = player1.getName()== player2.getName(); 
		boolean expected = false;
		assertEquals(actual,expected,"Copy constructor is not deep copying!");
	}
	
	@Test
	void constructor_test_5() {
		HashMap<StatName,Integer> playerStats = new HashMap<>();
		Integer[] st = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18};
		ArrayList<Integer> stats = new ArrayList<>(Arrays.asList(st));
		
		int temp = 0;
		for (StatName s:StatName.values()) {
			playerStats.put(s, stats.get(temp));
			temp++;
		}	
		
		Inventory inv = new Inventory();
		String name = "Jamal";
		Player player1 = new Player(playerStats,inv,name);
		int actual  = player1.getStat(StatName.AGGR);
		int expected = -1;
		assertEquals(actual,expected,"Stat Assignment is not working as intended!");	
	}

}
