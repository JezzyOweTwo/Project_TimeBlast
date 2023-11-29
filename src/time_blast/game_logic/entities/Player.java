package time_blast.game_logic.entities;

import java.util.ArrayList;
import java.util.HashMap;

import time_blast.game_logic.Inventory;

//player subclass
public class Player extends Entity{	
	public Player(HashMap<StatName,Integer> stats,Inventory inv,String name){
	 	super(inv,name);
	 	final ArrayList<StatName> rejectedStats = new ArrayList<>();
	 	rejectedStats.add(StatName.AGGR);
	 	rejectedStats.add(StatName.EXPYIELD);
	 	super.statLoader(stats,rejectedStats);
	}
	
 // copy constructor
 public Player(Player player){
 	super(player);
 	removeStat(StatName.AGGR);
 	removeStat(StatName.EXPYIELD);
 }
 public Player(){
 	super();
 }
	
	public void xpGain(int EXPGain){
		addStat(StatName.CUREXP,EXPGain);
		while (this.getStat(StatName.CUREXP)>=this.getStat(StatName.MAXEXP)) {
			levelUp();
			dropStat(StatName.CUREXP,this.getStat(StatName.MAXEXP));
		}
	}
	
	public void levelUp() {
		// adding some stats
		multiplyStat(StatName.MAXEXP, this.getStat(StatName.LVL)*1.15f);
		addStat(StatName.LVL,1);	
	}
}
