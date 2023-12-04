package time_blast.game_logic.Battle.actions;
import time_blast.game_logic.entities.Entity;
import time_blast.game_logic.entities.attributes.Spell;

public class performSpell extends Action{
	Spell spell;
	
	public performSpell(Entity source,Entity target,Spell spell){
		super(source,target);
		this.spell=spell;
	}
	
	public Spell getSpell(){return spell;}
	
	@Override
	public void execute() {
	}
}
