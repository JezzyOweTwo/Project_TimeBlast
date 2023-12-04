package time_blast.game_logic.Battle.actions;
import java.util.HashMap;

import time_blast.game_logic.entities.Entity;
import time_blast.game_logic.entities.StatName;
import time_blast.game_logic.entities.attributes.Item;
import time_blast.game_logic.entities.attributes.Spell;
import time_blast.game_logic.entities.attributes.SpellElement;

//this class contains all actions available to the player on the battle menu
public abstract class Action implements Comparable<Action>{
	private Entity source;
	private Entity target;
	
	public Action(Entity source,Entity target){
		this.source=source;
		this.target=target;
	}
	
	public abstract void execute();
	public Entity getSource() {return source;}
	public Entity getTarget() {return target;}
	
	@Override
	public int compareTo(Action a) {
		return this.getSource().getStat(StatName.SPEED)-a.getSource().getStat(StatName.SPEED);
	}
}
