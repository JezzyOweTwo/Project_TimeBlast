package time_blast.game_logic.Battle.actions;

import time_blast.game_logic.entities.Entity;
import time_blast.game_logic.entities.StatName;

public class performDefend extends Action{
	
	public performDefend(Entity source){
		// defend is self-targeting. (target and source are the same)
		super(source,source);
	}
	
	@Override
	public void execute() {
		if (!areAlive()) return;
		source.addStat(StatName.DEF, 15);
	}
	
}
