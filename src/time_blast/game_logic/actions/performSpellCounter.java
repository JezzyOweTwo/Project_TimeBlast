package time_blast.game_logic.actions;
import time_blast.game_logic.entities.StatName;
import time_blast.game_logic.entities.attributes.Spell;
import time_blast.game_logic.entities.attributes.SpellElement;
import time_blast.game_logic.entities.attributes.SpellStatName;

public class performSpellCounter extends Action{
	private Spell spell;
	private performSpell sourceSpell;
	
	public Spell getSpell(){return spell;}
	public performSpell getSourceSpell(){return sourceSpell;}
	
	public performSpellCounter(performSpell sourceSpell,Spell spell) {		
		// note that the superconstructor arguments are intentionally reversed.
		// this is because the defender becomes the 'attacker' when countering,
		// and vice versa.
		super(sourceSpell.getTarget(), sourceSpell.getSource());
		this.spell = spell;					
		this.sourceSpell = sourceSpell;
	}
	
	@Override
	public void execute() {
		if (!areAlive()) return;
		SpellElement attackerSpellElement = sourceSpell.getSpell().getElement();
		SpellElement defenderSpellElement = spell.getElement();
		
		float spellPowerDifference = spell.getStat(SpellStatName.BASE_DAMAGE)
							       / sourceSpell.getSpell().getStat(SpellStatName.BASE_DAMAGE);
		float effectiveness =   Math.max(spellPowerDifference, 1);   
		
		
		// not very effective countering
		if (Spell.ELEMENT_CHART.get(attackerSpellElement).equals(defenderSpellElement)) {
			System.out.println("Not very effective");
			effectiveness*=.5;
		}
		
		// super effective spell countering
		else if (Spell.ELEMENT_CHART.get(defenderSpellElement).equals(attackerSpellElement)) {
			System.out.println("It's super effective");
			effectiveness*=2;
		}
		
		// normal spell countering
		else System.out.println("No special interaction");
		
		sourceSpell.setEffectiveness(effectiveness);
		source.dropStat(StatName.CURMP, spell.getStat(SpellStatName.MANA_COST));
	}
}
