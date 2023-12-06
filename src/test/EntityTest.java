package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import org.junit.jupiter.api.Test;

import time_blast.game_logic.entities.Enemy;
import time_blast.game_logic.entities.Player;
import time_blast.game_logic.entities.StatName;
import time_blast.game_logic.entities.attributes.Inventory;

class EntityTest {
	public static Player createPlayerWithStats() {
		HashMap<StatName,Integer> playerStats = new HashMap<>();
		Integer[] st = {20,20,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18};
		ArrayList<Integer> stats = new ArrayList<>(Arrays.asList(st));
		Inventory inv = new Inventory();
		String name = "Jamal";
		int temp = 0;
		for (StatName s:StatName.values()) {
			playerStats.put(s, stats.get(temp));
			temp++;
		}
		return new Player(playerStats,inv,name);
	}
	public static Enemy createEnemyWithStats() {
		HashMap<StatName,Integer> EnemyStats = new HashMap<>();
		Integer[] st = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18};
		ArrayList<Integer> stats = new ArrayList<>(Arrays.asList(st));
		Inventory inv = new Inventory();
		String name = "James";
		int temp = 0;
		for (StatName s:StatName.values()) {
			EnemyStats.put(s, stats.get(temp));
			temp++;
		}
		return new Enemy(EnemyStats,inv,name);
	}
	
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
		boolean actual = player1.getName() == player2.getName(); 
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
	
	@Test
	void constructor_test_6() {
		HashMap<StatName,Integer> playerStats = new HashMap<>();
		Integer[] st = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18};
		ArrayList<Integer> stats = new ArrayList<>(Arrays.asList(st));
		Inventory inv = new Inventory();
		String name = "Cool guy 24";
		int temp = 0;
		for (StatName s:StatName.values()) {
			playerStats.put(s, stats.get(temp));
			temp++;
		}
		Enemy enemy1 = new Enemy(playerStats,inv,name);
		int actual  = enemy1.getStat(StatName.AGGR);
		int expected = st[9];
		assertEquals(actual,expected,"Stat Assignment is not working as intended!");	
	}
	
	@Test
	void constructor_test_7() {
		StatName[] stats = {StatName.ATK,StatName.ATK,StatName.ATK};
		ArrayList<StatName> arrayStats = new ArrayList<>(Arrays.asList(stats));
		Integer[] numbers = {1,2,3};
		HashMap<StatName,Integer> hashStats = new HashMap<>();
		
		int i=0;
		for (StatName s:arrayStats) {
			hashStats.put(s, numbers[i]);
			i++;
		}
		
		Player player = new Player();
		player.setStats(hashStats);
		int expected = 1;
		int actual = player.getStat(StatName.ATK);
		assertEquals(expected,actual,"Constructor should disgard any repeated keys!");
	}
	@Test
	void constructor_test_8() {
		// default value testing
		Player player = new Player();
		int expected = 0;
		int actual = player.getStat(StatName.ATK);
		assertEquals(expected,actual,"Unassigned stat should default to zero!");
	}
	
	
	@Test
	void test_add_1() {
		HashMap<StatName,Integer> playerStats = new HashMap<>();
		Integer[] st = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18};
		ArrayList<Integer> stats = new ArrayList<>(Arrays.asList(st));
		Inventory inv = new Inventory();
		String name = "Cool guy 24";
		int temp = 0;
		for (StatName s:StatName.values()) {
			playerStats.put(s, stats.get(temp));
			temp++;
		}
		Player player = new Player(playerStats,inv,name);
		int attackstat = player.getStat(StatName.ATK);
		int expected = attackstat+5;
		player.addStat(StatName.ATK, 5);
		int actual = player.getStat(StatName.ATK);
		
		assertEquals(actual,expected,"Player addstat is not working as intended!");
	}
	@Test
	void test_add_2() {
		HashMap<StatName,Integer> playerStats = new HashMap<>();
		Integer[] st = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18};
		ArrayList<Integer> stats = new ArrayList<>(Arrays.asList(st));
		Inventory inv = new Inventory();
		String name = "Cool guy 24";
		int temp = 0;
		for (StatName s:StatName.values()) {
			playerStats.put(s, stats.get(temp));
			temp++;
		}
		Player player = new Player(playerStats,inv,name);
		int expected = player.getStat(StatName.ATK);
		player.addStat(StatName.ATK, -100);
		int actual = player.getStat(StatName.ATK);
		assertEquals(actual,expected,"Player addstat is not working as intended!");
	}
	
	@Test
	void test_drop_1() {
		Player player  = createPlayerWithStats();
		int attackstat = player.getStat(StatName.ATK);
		int expected = Math.max(0, attackstat-1);
		player.dropStat(StatName.ATK, 1);
		int actual = player.getStat(StatName.ATK);	
		assertEquals(actual,expected,"Player dropstat is not working as intended!");
	}
	
	@Test
	void test_drop_2() {
		Player player  = createPlayerWithStats();
		int attackstat = player.getStat(StatName.ATK);
		int expected = Math.max(0, attackstat-5);
		player.dropStat(StatName.ATK, 5);
		int actual = player.getStat(StatName.ATK);
		assertEquals(actual,expected,"Player dropstat is not working as inteded.");
	}
	
	@Test
	void test_drop_3() {
		Player player  = createPlayerWithStats();
		int expected = player.getStat(StatName.ATK);
		player.dropStat(StatName.ATK, -5);
		int actual = player.getStat(StatName.ATK);
		assertEquals(actual,expected,"Player dropstat should not work for negative inputs.");
	}
	
	@Test 
	void test_multiply_1(){
		Player player  = createPlayerWithStats();
		int expected = (int)Math.floor(player.getStat(StatName.ATK)*1.2f);
		player.multiplyStat(StatName.ATK, 1.2f);
		int actual = player.getStat(StatName.ATK);
		assertEquals(actual,expected,"Player dropstat should not work for negative inputs.");
	}
	@Test 
	void test_multiply_2(){
		Player player  = createPlayerWithStats();
		int expected = player.getStat(StatName.ATK);
		player.multiplyStat(StatName.ATK, -1.2f);
		int actual = player.getStat(StatName.ATK);
		assertEquals(actual,expected,"Player dropstat should not work for negative inputs.");
	}
	@Test 
	void test_multiply_3(){
		Player player  = createPlayerWithStats();
		int expected = player.getStat(StatName.ATK);
		player.multiplyStat(StatName.ATK, 0);
		int actual = player.getStat(StatName.ATK);
		assertEquals(actual,expected,"Player dropstat should not work for negative inputs.");
	}
	
	@Test
	void name_independence_test_1() {
		Player player = new Player();
		String name = "Jeff";
		player.setName(name);
		name = "Ricky";
		boolean expected = false;
		boolean actual = player.getName().equals(name);
		assertEquals(actual,expected,"Name must be deep copied!");
	}
	
	@Test
	void name_independence_test_2() {
		Player player = new Player();
		String name = "Bob";
		player.setName(name);
		boolean expected = true;
		boolean actual = name != player.getName();
		assertEquals(actual,expected,"Name must be deep copied!");
	}
	
	@Test
	void name_independence_test_3() {
		HashMap<StatName,Integer> playerStats = new HashMap<>();
		Integer[] st = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18};
		ArrayList<Integer> stats = new ArrayList<>(Arrays.asList(st));
		Inventory inv = new Inventory();
		String name = "Cool guy 24";
		int temp = 0;
		for (StatName s:StatName.values()) {
			playerStats.put(s, stats.get(temp));
			temp++;
		}
		Player player = new Player(playerStats,inv,name);
		boolean expected = true;
		boolean actual = name != player.getName();
		assertEquals(actual,expected,"Name was not deeply copied");
	}
	
	@Test
	void stats_independence_test_1() {
		HashMap<StatName,Integer> playerStats = new HashMap<>();
		Integer[] st = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18};
		ArrayList<Integer> stats = new ArrayList<>(Arrays.asList(st));
		Inventory inv = new Inventory();
		String name = "Cool guy 24";
		int temp = 0;
		for (StatName s:StatName.values()) {
			playerStats.put(s, stats.get(temp));
			temp++;
		}	
		Player player = new Player(playerStats,inv,name);
		playerStats.clear();
		boolean expected = false;
		boolean actual = player.getStats().equals(playerStats);
		assertEquals(actual,expected,"stats were not deeply copied!");
	}
	@Test
	void stats_independence_test_2() {
		Player player = new Player();
		HashMap<StatName,Integer> stats = new HashMap<>();
		player.setStats(stats);
		boolean expected = true;
		boolean actual = player.getStats() != stats;
		assertEquals(actual,expected,"stats were not deeply copied!");
	}
	@Test
	void stats_independence_test_3() {
		HashMap<StatName,Integer> playerStats = new HashMap<>();
		Integer[] st = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18};
		ArrayList<Integer> stats = new ArrayList<>(Arrays.asList(st));
		Inventory inv = new Inventory();
		String name = "Cool guy 24";
		int temp = 0;
		for (StatName s:StatName.values()) {
			playerStats.put(s, stats.get(temp));
			temp++;
		}
		Player player = new Player(playerStats,inv,name);
		boolean expected = true;
		boolean actual = playerStats != player.getStats();
		assertEquals(actual,expected,"Stats were not deeply copied");
	}
	
	@Test
	void inventory_independence_test_1() {

	    Inventory inv = new Inventory();
	    Player player = new Player();
	    player.setInv(inv);
	    inv = null;
	    boolean expected = false;
	    boolean actual = player.getInv().equals(inv);
	    assertEquals(actual,expected,"stats were deeply copied!");
	}
	
	@Test
	void inventory_independence_test_2() {
		Player player = new Player();
		Inventory inv = new Inventory();
		player.setInv(inv);
		boolean expected = true;
		boolean actual = inv != player.getInv();
		assertEquals(actual,expected,"Inv was not deeply copied");
	}
	@Test
	void inventory_independence_test_3() {
		HashMap<StatName,Integer> playerStats = new HashMap<>();
		Integer[] st = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18};
		ArrayList<Integer> stats = new ArrayList<>(Arrays.asList(st));
		Inventory inv = new Inventory();
		String name = "Cool guy 24";
		int temp = 0;
		for (StatName s:StatName.values()) {
			playerStats.put(s, stats.get(temp));
			temp++;
		}
		Player player = new Player(playerStats,inv,name);
		boolean expected = true;
		boolean actual = inv != player.getInv();
		assertEquals(actual,expected,"Inv was not deeply copied");
	}
	
	@Test
	void player_missing_value_test_1() {
		Player player = createPlayerWithStats();
		int expected = -1;
		int actual = player.getStat(StatName.AGGR);
		assertEquals(actual,expected,"Agression stat should not be present in player!");
		actual = player.getStat(StatName.EXPYIELD);
		assertEquals(actual,expected,"exp yield stat should not be present in player!");
	}
	
	@Test
	void player_xpGain_test_1() {
		Player player = createPlayerWithStats();
		int level = player.getStat(StatName.LVL);
		player.xpGain(100);
		assertTrue(level!=player.getStat(StatName.LVL));	
	}
	
	@Test
	void player_xpGain_test_2() {
		Player player = createPlayerWithStats();
		int expected = player.getStat(StatName.CUREXP);
		player.xpGain(-50);
		int actual = player.getStat(StatName.CUREXP);
		assertEquals(actual,expected,"xpGain should not work for negative values!");
	}
	
	@Test
	void enemy_missing_value_test_1() {
		Enemy enemy = createEnemyWithStats();
		int expected = -1;
		int actual = enemy.getStat(StatName.CUREXP);
		assertEquals(actual,expected,"current exp stat should not be present in enemy!");
		actual = enemy.getStat(StatName.MAXEXP);
		assertEquals(actual,expected,"max exp stat should not be present in enemy!");
	}
	

}
