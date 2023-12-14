package time_blast.graphics;

import java.awt.*;

public class StateHandler {
    private GamePanel gamePanel;
    private GameWindow gameWindow;
    private GameState gameState;

    public StateHandler(GamePanel gamePanel){
        gameState = gamePanel.getGameState();
        this.gamePanel = gamePanel;
        gameWindow = new GameWindow(this.gamePanel);
    }

    public void run() {
        gamePanel.run();
    }
}
