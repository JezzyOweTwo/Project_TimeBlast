package time_blast.game_logic.entities;
import java.util.HashMap;

import time_blast.game_logic.entities.attributes.Inventory;

//enemy subclass
public class Enemy extends Entity{	
	private final StatName[] REJECTED_STATS = {StatName.CUREXP,StatName.MAXEXP};
	
	public Enemy(HashMap<StatName,Integer> stats,Inventory inv,String name){
	 	super(inv,name);
	 	this.statLoader(stats);
	 	this.removeStats(REJECTED_STATS);
	}	
	public Enemy() {
		super();
	}
}
