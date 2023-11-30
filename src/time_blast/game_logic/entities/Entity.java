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
    
    // default no args constructor
	Entity(){
		statLoader(new HashMap<StatName,Integer>());
    	Count++;
    	ID=Count;
    }
	
	Entity(Inventory inv,String name){
		this();
    	this.inv = new Inventory(inv);
    	this.name = new String(name);
	}
	
	// stat loader method
	protected void statLoader(HashMap<StatName,Integer> stats) {
		StatName[] statnames = StatName.values();
		ArrayList<StatName> addedStats = new ArrayList<>();
		 for (StatName stat:statnames) {
			if (stats.keySet().contains(stat)&&!addedStats.contains(stat)) {
				this.stats.put(stat,stats.get(stat));
				addedStats.add(stat);
		 	}
			else if (addedStats.contains(stat));	// exists so this condition doesn't trigger else condition
			else this.stats.put(stat, 0);			// sets stat to 0 if it doesn't exist
		}
	}
    
    // copy constructor
    Entity(Entity en){
    	this(new Inventory(en.getInv()),new String(en.getName()));
    }
    
    // validates if a given stat title is actually in the stat hashmap
	private boolean validStatChecker(StatName stat) {
		for(StatName s : stats.keySet()) {
			if (stat.equals(s)) return true;		
		}	
		System.out.println("Stat '"+stat+"' does not exist in this stat list!");
		return false;
	}
	
	// negative number checker
	protected boolean aboveZeroCheck(float value) {
		if (value>0) return true;
		System.out.println("Error! "+value+" is a nonpositive number!");
		return false;
	}
	
	// adds to a stat 
	public void addStat(StatName statName,int value){
		if (!validStatChecker(statName)||!aboveZeroCheck(value)) return;
		stats.put(statName,Math.max(stats.get(statName) + value, 0));
	}
	
	// subtracts from a stat
	public void dropStat(StatName statName,int value) {
		if (!validStatChecker(statName)||!aboveZeroCheck(value)) return;
		stats.put(statName,Math.max(stats.get(statName) - value, 0));
	}
	
	// multiplies a stat
	public void multiplyStat(StatName statName,float factor) {
		if (!validStatChecker(statName)||!aboveZeroCheck(factor)) return;
		stats.put(statName,(int)(Math.max(stats.get(statName) * factor, 0)));
	}
	
    // setters and getters
    public HashMap<StatName,Integer> getStats() {return this.stats;}
    public int getStat(StatName key) { if (validStatChecker(key))return stats.get(key); return -1;}
	public void setStats(HashMap<StatName,Integer> stats){statLoader(stats);}
	public void setStat(StatName statName,int value) {
		if (validStatChecker(statName)) stats.put(statName, value);
	}
	protected void removeStat(StatName statName) {
		if (validStatChecker(statName)) stats.remove(statName);
	}
	protected void removeStats(StatName[] statNames) {
	 	for (StatName s:statNames) {
	 		this.removeStat(s);
	 	}
	}
	
	public Inventory getInv() {return inv;}
	public void setInv(Inventory inv) {this.inv = new Inventory(inv);}
	public String getName() {return name;}
	public void setName(String name) {this.name = new String(name);}
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