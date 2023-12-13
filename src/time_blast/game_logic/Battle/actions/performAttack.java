package time_blast.game_logic.Battle.actions;

import time_blast.game_logic.entities.Entity;
import time_blast.game_logic.entities.StatName;

public class performAttack extends Action{
	
	public performAttack(Entity source,Entity target){
		super(source,target);
	}
	
	@Override
	public void execute() {
		if (!areAlive()) return;
		int sourceAttack = source.getStat(StatName.ATK);
		int sourceIQ = source.getStat(StatName.IQ);
		int sourceSpeed  = source.getStat(StatName.SPEED);
		int targetDefence = target.getStat(StatName.DEF);
		int damage =1;
		
		float critChance = .01f*(float)sourceSpeed/4*sourceIQ/4;
		if (critChance>Math.random()) {
			damage=2;
			System.out.println("It's a critical Hit!");
		}
		damage *= Math.max(sourceAttack-targetDefence, 1)*(sourceAttack/4);
		
		System.out.println(source.getName()+" attacked "+target.getName()+" dealing "+damage+ " damage!");
		target.dropStat(StatName.CURHP, damage);
	}
}
