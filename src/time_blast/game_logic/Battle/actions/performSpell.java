package time_blast.game_logic.Battle.actions;
import time_blast.game_logic.entities.Entity;
import time_blast.game_logic.entities.StatName;
import time_blast.game_logic.entities.attributes.Spell;
import time_blast.game_logic.entities.attributes.SpellStatName;

public class performSpell extends Action{
	private Spell spell;
	private float effectiveness=1;
	
	public performSpell(Entity source,Entity target,Spell spell){
		super(source,target);
		this.spell=spell;
	}
	
	protected void setEffectiveness(float effectiveness) {
		if(effectiveness>0||effectiveness<=1) this.effectiveness=effectiveness;
		else System.out.println("An improper effectiveness value has been inputted!");
	}
	
	public Spell getSpell(){return spell;}
	
	@Override
	public void execute() {
		int attackerIQ = source.getStat(StatName.IQ);
		int defenderIQ = target.getStat(StatName.IQ);
		int defenderDefence = target.getStat(StatName.DEF);
		int damage = spell.getStat(SpellStatName.BASE_DAMAGE)+
				     attackerIQ-defenderIQ-Math.max(defenderDefence/4,1);
		damage*=effectiveness;
		target.dropStat(StatName.CURHP, damage);
		source.dropStat(StatName.CURMP,spell.getStat(SpellStatName.MANA_COST));
	}
}
