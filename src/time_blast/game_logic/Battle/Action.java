package time_blast.game_logic.Battle;
import time_blast.game_logic.entities.Entity;
import time_blast.game_logic.entities.StatName;
import time_blast.game_logic.entities.attributes.Item;
import time_blast.game_logic.entities.attributes.Spell;

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

class performDefend extends Action{
	
	public performDefend(Entity source,Entity target){
		super(source,target);
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
	}
	
}

class performAttack extends Action{
	
	public performAttack(Entity source,Entity target){
		super(source,target);
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
	}
}

class performSpell extends Action{
	private Spell spell;
	
	public performSpell(Entity source,Entity target,Spell spell){
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
	
	public performItem(Entity source,Entity target,Item item){
		super(source,target);
		this.item=item;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
	}
}

class performRun extends Action{

	public performRun(Entity source,Entity target){
		super(source,target);
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
	}

}
