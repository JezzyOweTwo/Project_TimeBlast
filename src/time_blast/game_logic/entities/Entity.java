package time_blast.game_logic.entities;
import java.util.*;

import time_blast.game_logic.Inventory;

/* Entity is an abstract parent class containing the following information about an entity:
 * 
 * [1.] A HashMap of stats. 
 *      Note that each child class of Entity has additional stats. 
 * 
 * [2.] A Inventory object which contains the following information:
 *  -  ArrayList <Item>
 *  -  ArrayList <Spell>
 *  -  ArrayList <Attack>
 *  -  int gold
 *  
 *  [3.] The Entity's name
 *  
 *  [4.] A unique ID Number for the entity
 * 
 * Note that only the children can be instantiated.  */

public abstract class Entity  {
	
    private Inventory inv;												  // inventory of entity 
    private String name;						                          // name of entity
    private final int ID;												  // ID Number of the entity	
    static int Count=0;													  // Number of created entities
    private HashMap<StatName,Integer> stats= new HashMap<>();	  		  // hashmap of stats				
    
    // main constructor 
    Entity(HashMap<StatName,Integer> stats, Inventory inv,String name){
    	this();
    	final int DEFAULT_STAT_COUNT = 9;
    	ArrayList<StatName> omittedStats = new ArrayList<>();
    	StatName[] statname = StatName.values();
    	
    	for (int i =DEFAULT_STAT_COUNT;i<stats.size();i++) 
    		omittedStats.add(statname[i]);
    	
    	statLoader(stats,omittedStats);
    	this.inv = (inv);
    	this.name = (name);
    }
    
    // default no args constructor
	Entity(){
    	Count++;
    	ID=Count;
    }
	
	Entity(Inventory inv,String name){
		this();
    	this.inv = (inv);
    	this.name = (name);
	}
	
	// stat loader method
	protected void statLoader(HashMap<StatName,Integer> stats,ArrayList<StatName> omitStats) {
    	this.stats = new HashMap<StatName,Integer>(stats);
		for(StatName statname:omitStats) {
    		this.stats.remove(statname);
    	}
	}
    
    // copy constructor
    Entity(Entity en){
    	this(new HashMap<StatName,Integer>(en.getStats()),
    			new Inventory(en.getInv()),new String (en.getName()));
    }
    
    // validates if a given stat title is actually in the stat hashmap
	private boolean validStatChecker(StatName stat) {
		for(StatName s : stats.keySet()) {
			if (stat.equals(s)) return true;		
		}	
		System.out.println("Stat '"+stat+"' does not exist in this stat list!");
		return false;
	}
	
	// adds to a stat 
	public void addStat(StatName statName,int value){
		if (validStatChecker(statName)) stats.put(statName,Math.max(stats.get(statName) + value, 0));
	}
	
	// subtracts from a stat
	public void dropStat(StatName statName,int value) {
		if (validStatChecker(statName)) stats.put(statName,Math.max(stats.get(statName) - value, 0));
	}
	
	// multiplies a stat
	public void multiplyStat(StatName statName,float factor) {
		if (validStatChecker(statName)) stats.put(statName,(int)(Math.max(stats.get(statName) * factor, 0)));
	}
	
    // setters and getters
    public HashMap<StatName,Integer> getStats() {return this.stats;}
    public int getStat(StatName key) { if (validStatChecker(key))return stats.get(key); return -1;}
	public void setStats(HashMap<StatName,Integer> stats){
		statLoader(stats,new ArrayList<StatName>());
	}
	public void setStat(StatName statName,int value) {
		if (validStatChecker(statName)) stats.put(statName, value);
	}
	protected void removeStat(StatName statName) {
		if (validStatChecker(statName)) stats.remove(statName);
	}
	public Inventory getInv() {return inv;}
	public void setInv(Inventory inv) {this.inv = inv;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public int getID() {return ID;}
	public static int getCount() {return Count;}
	
	@Override
	public String toString() {
		ArrayList<String> all = new ArrayList<>();
		all.add(name);
		all.add(stats.toString());
		all.add(inv.toString());
		all.add(Integer.toString(ID));
		return all.toString();
	}   
}	