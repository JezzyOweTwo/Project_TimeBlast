package time_blast;

import time_blast.game_logic.entities.Player;
import time_blast.graphics.GamePanel;
import time_blast.graphics.GameWindow;
import time_blast.graphics.OverworldPanel;
import time_blast.graphics.StateHandler;

public class Main {

	public static void main(String[] args) {
		StateHandler sh = new StateHandler(new OverworldPanel(new Player()));
		sh.run();
	}

}
