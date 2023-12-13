package time_blast.game_logic.entities.attributes;
import java.util.*;

import time_blast.file_reading.FileReadable;
import time_blast.game_logic.entities.StatName;

public class Item implements FileReadable<Item>{
    private HashMap<StatName,Integer> stats; 
    static int count=1;
    final int ID;
        
    public Item(HashMap<StatName,Integer> stats){
        ID=count;
        count++;
    	this.stats = stats;
    } 
    
    public Item(){
        ID=count;
        count++;	
    }
    
    //Copy constructor for item
    Item(Item item){
    	 this(new HashMap<StatName,Integer>(item.getStats()));
    }
    
    // getters and setters
    public HashMap<StatName,Integer> getStats(){return stats;}
    public void setStats(HashMap<StatName,Integer> stats) {this.stats=stats;}
    public void setStat(StatName stat,int value) {stats.put(stat, value);}
    public static int getCount() {return count;}
    public int getID() {return ID;}

	@Override
	public Item create(Object key, Object value) {
		Item item = new Item();
		return item;
	}
}
