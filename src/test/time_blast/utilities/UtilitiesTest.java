package time_blast.utilities;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import time_blast.game_logic.battle.BattleTest;
import time_blast.game_logic.entities.Enemy;

class UtilitiesTest implements Utilities{

    public void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Test
    void utilities_test_1() {
        int actual = response("What will you do?\n1.Attack\n2.Defend\n3.Magic\n4.Item\n5.Run");
        int expected = 0;
        String message = ("Unexpected response!");
        assertEquals(actual,expected,message);
    }
    @Test
    void utilities_test_2() {
        provideInput("1");
        ArrayList<Enemy> enemies = BattleTest.createEnemyArrayWithoutStats();
        int actual = response("Which enemy will you target? ",enemies);
        int expected = 0;
        String message = ("Unexpected response!");
        assertEquals(actual,expected,message);
    }

}
