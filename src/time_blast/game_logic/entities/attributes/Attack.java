package time_blast.game_logic.entities.attributes;

import java.util.*;

// this class defines all the attributes of an attack, damage, accuracy, special properties
public class Attack {
	private HashMap<String,Integer> stats;
	
	Attack(HashMap<String,Integer> stats){
		this.stats = stats;
	}
	
	public HashMap<String,Integer> getStats(){return stats;}
	
}
