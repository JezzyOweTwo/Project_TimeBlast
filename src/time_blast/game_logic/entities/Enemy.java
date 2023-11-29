package time_blast.game_logic.entities;

import java.util.ArrayList;
import java.util.HashMap;

import time_blast.game_logic.Inventory;

//enemy subclass
public class Enemy extends Entity{	
	public Enemy(HashMap<StatName,Integer> stats,Inventory inv,String name){
		super(inv,name);
	 	final ArrayList<StatName> rejectedStats = new ArrayList<>();
	 	rejectedStats.add(StatName.CUREXP);
	 	rejectedStats.add(StatName.MAXEXP);
	 	super.statLoader(stats,rejectedStats);
	}		
}
