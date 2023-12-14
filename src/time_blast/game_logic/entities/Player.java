package time_blast.game_logic.entities;
import java.nio.file.Paths;
import java.util.HashMap;

import time_blast.game_logic.entities.attributes.Inventory;
import time_blast.graphics.assets.Direction;

//player subclass
public class Player extends Entity{	
	private final StatName[] REJECTED_STATS = {StatName.EXPYIELD,StatName.AGGR};
	
	public Player(HashMap<StatName,Integer> stats,Inventory inv,String name){
		 super(inv,name);
		 this.statLoader(stats);
		 this.imageLoader(Paths.get("").toAbsolutePath().toString()+"\\src\\time_blast\\game_logic\\entities\\player.png");
		 this.removeStats(REJECTED_STATS);
	}

	 // copy constructor
	 public Player(Player player){
	 	super(player);
	 	this.statLoader(player.getStats());
	 	this.removeStats(REJECTED_STATS);
	 }
	 
	 public Player(){
	 	super();
		this.imageLoader(Paths.get("").toAbsolutePath().toString()+"\\src\\time_blast\\game_logic\\entities\\player.png");
	 }
	
	public void xpGain(int EXPGain){
		if (!aboveZeroCheck(EXPGain)) return;
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
