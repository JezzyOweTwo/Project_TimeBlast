package time_blast.game_logic.Battle.actions;

import java.util.ArrayList;

import time_blast.game_logic.entities.Entity;
import time_blast.game_logic.entities.StatName;

public class performRun extends Action{

	private ArrayList<Entity> combattants;
	
	public performRun(Entity source,Entity target,ArrayList<Entity> combattants){
		super(source,target);
		this.combattants = combattants;
	}
	
	@Override
	public void execute() {
		if (!areAlive()) return;
		int sourceSpeed = source.getStat(StatName.SPEED);
		int targetSpeed = target.getStat(StatName.SPEED);
		String sourceName = source.getName();
		
		// successful escape
		if (sourceSpeed>targetSpeed) {
			combattants.remove(source);
			System.out.println(sourceName+" has escaped sucessfully!");
		}
			
		
		// speed tie
		else if (sourceSpeed==targetSpeed)
			if (Math.random()>.5)combattants.remove(source);
		
		// unable to escape
		else if (sourceSpeed<targetSpeed) {
			System.out.println(sourceName+" has failed to escape!");
			return;
		}
	}

}
