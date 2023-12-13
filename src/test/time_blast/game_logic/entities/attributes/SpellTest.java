package time_blast.game_logic.entities.attributes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import time_blast.game_logic.entities.attributes.Spell;
import time_blast.game_logic.entities.attributes.SpellElement;
import time_blast.game_logic.entities.attributes.SpellStatName;

public class SpellTest {

    public static Spell createSpellWithStats() {
        HashMap<SpellStatName,Integer> stats = new HashMap<>();
        int[] intStats= {1,2};
        SpellStatName[] statNames = SpellStatName.values();

        int i=0;
        for (SpellStatName s:statNames) {
            stats.put(s, intStats[i]);
            i++;
        }

        return new Spell(stats,"Crazy Water Spell",SpellElement.WATER,false);
    }

    @Test
    void empty_ConstructorRuntimeErrorTest_1() {
        new Spell();
    }

    @Test
    void filled_ConstructorRuntimeErrorTest_2() {
        createSpellWithStats();
    }

    @Test
    void copy_Constructor_RuntimeErrorTest_3() {
        Spell spell1 = createSpellWithStats();;
        new Spell (spell1);
    }

    @Test
    void deep_copying_constructorTest_1() {
        Spell spell1 = createSpellWithStats();;
        Spell spell2 = new Spell (spell1);
        assertTrue(spell1.getName()!=spell2.getName(),"deep copying of name has failed!");
        assertTrue(spell1.getStats()!=spell2.getStats(),"deep copying of element has failed!");
    }
    @Test
    void deep_copying_constructorTest_2() {
        HashMap<SpellStatName,Integer> stats = new HashMap<>();
        int[] intStats= {1,2};
        SpellStatName[] statNames = SpellStatName.values();

        int i=0;
        for (SpellStatName s:statNames) {
            stats.put(s, intStats[i]);
            i++;
        }
        Spell spell = new Spell(stats,"Crazy Water Spell",SpellElement.WATER,false);
        stats = null;
        assertTrue(stats!=spell.getStats(),"variable stats is not independent");
    }
    @Test
    void deep_copying_constructorTest_3() {
        HashMap<SpellStatName,Integer> stats = new HashMap<>();
        int[] intStats= {1,2};
        SpellStatName[] statNames = SpellStatName.values();

        int i=0;
        for (SpellStatName s:statNames) {
            stats.put(s, intStats[i]);
            i++;
        }
        String title = "Crazy Water Spell";
        Spell spell = new Spell(stats,title,SpellElement.WATER,false);
        title = null;
        assertTrue(title!=spell.getName(),"variable name is not independent");
    }

    @Test
    void elementChartGeneratorTest_1() {
        new Spell();
        HashMap<SpellElement,SpellElement> typeChart = Spell.ELEMENT_CHART;
        ArrayList<String> actualList = new ArrayList<>();
        for (SpellElement s:typeChart.keySet()) {
            String keyValue = s.toString()+" "+typeChart.get(s).toString();
            actualList.add(keyValue);
        }

        ArrayList<String> expectedList = new ArrayList<>();
        expectedList.add(SpellElement.FIRE.toString()+" "+SpellElement.WIND.toString());
        expectedList.add(SpellElement.WATER.toString()+" "+SpellElement.FIRE.toString());
        expectedList.add(SpellElement.WIND.toString()+" "+SpellElement.EARTH.toString());
        expectedList.add(SpellElement.EARTH.toString()+" "+SpellElement.LIGHTNING.toString());
        expectedList.add(SpellElement.LIGHTNING.toString()+" "+SpellElement.WATER.toString());

        for (String actual:actualList)
            assertTrue(actualList.contains(actual));
    }


}
