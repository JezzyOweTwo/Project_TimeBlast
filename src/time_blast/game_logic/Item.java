package time_blast.game_logic;
import java.util.*;

public class Item{
    private HashMap<String,Integer> stats; 
    static int count=1;
    final int ID;
        
    Item(HashMap<String,Integer> stats){
        ID=count;
        count++;
    	this.stats = stats;
    } 
    Item(){
        ID=count;
        count++;	
    }
    
    //constructor for special items
//    Item(){
//        ID=count;
//    }
    
    //Copy constructor for item
    Item(Item item){
    	 this(new HashMap<String,Integer>(item.getStats()));
    }
    
    public HashMap<String,Integer> getStats(){return stats;}
    public void setStats(HashMap<String,Integer> stats) {this.stats=stats;}
    public static int getCount() {return count;}
    public int getID() {return ID;}
}
