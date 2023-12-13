package time_blast.game_logic.actions;
import time_blast.game_logic.entities.Entity;
import time_blast.game_logic.entities.StatName;

//this class contains all actions available to the player on the battle menu
public abstract class Action implements Comparable<Action>{
	protected Entity source;
	protected Entity target;
	
	public Action(Entity source,Entity target){
		// note that these objects are intentionally not deep copied.
		this.source=source;	
		this.target=target;
	}
	
	// method that actually performs the action.
	public abstract void execute();
	
	// checks if source and target are both still alive.
	protected boolean areAlive() {
        return source.getStat(StatName.CURHP) > 0 && target.getStat(StatName.CURHP) > 0;
    }
	
	// getters
	public Entity getSource() {return source;}
	public Entity getTarget() {return target;}
	
	// allows actions to be compared based on the speed on each entity performing the action.
	@Override
	public int compareTo(Action a) {
		return this.getSource().getStat(StatName.SPEED)-a.getSource().getStat(StatName.SPEED);
	}
}