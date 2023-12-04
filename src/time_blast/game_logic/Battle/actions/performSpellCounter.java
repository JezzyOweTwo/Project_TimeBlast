package time_blast.game_logic.Battle.actions;

import time_blast.game_logic.entities.attributes.Spell;
import time_blast.game_logic.entities.attributes.SpellElement;

public class performSpellCounter extends Action{
	private Spell spell;
	private performSpell sourceSpell;
	
	public Spell getSpell(){return spell;}
	public performSpell getSourceSpell(){return sourceSpell;}
	
	public performSpellCounter(performSpell sourceSpell,Spell spell) {		
		// note that the constructor arguments are intentionally reversed.
		super(sourceSpell.getTarget(), sourceSpell.getSource());
		this.spell=spell;
		this.sourceSpell = sourceSpell;
	}
	
	@Override
	public void execute() {
		SpellElement attackerSpellElement = sourceSpell.getSpell().getElement();
		SpellElement defenderSpellElement = spell.getElement();
		
		// not very effective countering
		if (Spell.ELEMENT_CHART.get(attackerSpellElement).equals(defenderSpellElement)) {
			System.out.println("Not very effective");
		}
		
		// super effective spell countering
		else if (Spell.ELEMENT_CHART.get(defenderSpellElement).equals(attackerSpellElement)) {
			System.out.println("It's super effective");
		}
		
		// normal spell countering
		else {
			System.out.println("No special interaction");
		}
	}
}
