package time_blast.game_logic.entities.attributes;

import time_blast.game_logic.entities.StatName;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {

    public static Item createItemWithStats(){
        String name = "Health Potion";
        AttributeRarity rarity = AttributeRarity.rare;
        HashMap<StatName,Integer> stats= new HashMap<>();
        stats.put(StatName.CURHP,5);
        boolean isSplash = false;
        String description = "LoL, cool spell ya got there!";

        return new Item(name,rarity,stats,isSplash,description);
    }
}