package time_blast.game_logic;

import time_blast.game_logic.entities.Entity;
import time_blast.game_logic.entities.StatName;

//this class contains all actions available to the player on the battle menu
public abstract class Action implements Comparable<Action>{
	private Entity source;
	private Entity target;
	
	Action(Entity source,Entity target){
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

class performDefend extends Action{
	
	performDefend(Entity source,Entity target){
		super(source,target);
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
	}
	
}

class performAttack extends Action{
	
	performAttack(Entity source,Entity target){
		super(source,target);
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
	}
}

class performSpell extends Action{
	private Spell spell;
	
	performSpell(Entity source,Entity target,Spell spell){
		super(source,target);
		this.spell=spell;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}

class performItem extends Action{
	private Item item;
	
	performItem(Entity source,Entity target,Item item){
		super(source,target);
		this.item=item;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
	}
}

class performRun extends Action{

	performRun(Entity source,Entity target){
		super(source,target);
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
	}

}
