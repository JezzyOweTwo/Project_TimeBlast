package time_blast.game_logic.actions;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import time_blast.game_logic.entities.EntityTest;
import time_blast.game_logic.actions.performSpell;
import time_blast.game_logic.actions.performSpellCounter;
import time_blast.game_logic.entities.Enemy;
import time_blast.game_logic.entities.Player;
import time_blast.game_logic.entities.attributes.Spell;
import time_blast.game_logic.entities.attributes.SpellElement;
import time_blast.game_logic.entities.attributes.SpellTest;

public class ActionTest {
    public static performSpell performSpellCreator() {
        Player player = EntityTest.createPlayerWithStats();
        Enemy enemy = EntityTest.createEnemyWithStats();
        Spell spell = SpellTest.createSpellWithStats();
        return new performSpell(player,enemy,spell);
    }


    @Test
    void performSpell_constructor_test_1() {
        performSpell performspell= performSpellCreator();
        performspell.execute();
    }

    @Test
    void performSpellCounter_constructor_test_1() {
        Spell spell = SpellTest.createSpellWithStats();
        performSpellCounter spellCounter= new performSpellCounter(performSpellCreator(),spell);
        spellCounter.execute();
    }

    @Test
    void performSpellCounter_constructor_test_2() {
        ArrayList<Spell> spells = new ArrayList<>();
        for(int i=0;i<5;i++) {
            spells.add(SpellTest.createSpellWithStats());
        }
        spells.get(0).setElement(SpellElement.FIRE);
        spells.get(1).setElement(SpellElement.WIND);
        spells.get(2).setElement(SpellElement.EARTH);
        spells.get(3).setElement(SpellElement.LIGHTNING);
        spells.get(4).setElement(SpellElement.WATER);
        for (Spell s:spells) {
            new performSpellCounter(performSpellCreator(),s).execute();;
        }
    }
}
