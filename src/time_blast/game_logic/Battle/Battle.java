package time_blast.game_logic.Battle;
import java.util.*;
import time_blast.file_reading.FileReader;
import time_blast.game_logic.entities.*;
import time_blast.utilities.Utilities;

// This class is responsible for the battling in the game.
public class Battle implements Utilities,FileReader {
	
	private Player player;
	private ArrayList<Enemy> enemies;
	private HashMap<String,ArrayList<String>> dialogue = new HashMap<>();
	
	public Battle(Player player, ArrayList<Enemy> enemies){
		this.player=new Player(player);
		this.enemies=new ArrayList<Enemy>(enemies);
		dialogue = readCSV("\\src\\time_blast\\game_logic\\Battle\\BattleDialogue.csv");
	}
	
	// getters (there are no setters for battle)
	public Player getPlayer() {return player;}
	public ArrayList<Enemy> getEnemies(){return enemies;}
	public HashMap<String,ArrayList<String>> getDialogue(){return dialogue;}
	
	// Main returns 1 if player wins, 0 tie, -1 enemy win
	public int runBattle() {
		while (true) {
			
			// Player AND All Enemies Are Dead // 
			if (player.getStat(StatName.CURHP)<=0&&!areAlive()) {return 0;}
			
			// Enemies Are Dead //
			else if (!areAlive()) {return 1;}
			
			// Player Died //
			else if (player.getStat(StatName.CURHP)<=0) {return -1;}
			
			battle();
		}
		
	}
	
	// -------------------------------- main battle method ----------------------------------------------------- //

	private void battle(){
		Action playerAction = playerInputHandler();
		List<Action> actions = new ArrayList<>();
		for (Enemy e :enemies) {
			actions.add(enemyInputHandler(e));	
		}
		actions.add(playerAction);
		ActionPerformer(actions);
	}	// battle method
	
	// ------------------------------------------ helper methods ------------------------------------------------------//
	
	// returns true if any enemy in the list is still alive, otherwise false.
	boolean areAlive() {
		for(Enemy e:enemies) {
			if(e.getStat(StatName.CURHP)>0) return true; 
		}	
		return false;
	}   


	// this method is responsible for performing a list of actions and updating and necessary UI elements
	private void ActionPerformer(List<? extends Action> actions) {
		Collections.sort(actions);		// sorts actions based on speed (lowest to highest)
		Collections.reverse(actions);	// reverses the order from fastest to slowest
		
		for(Action a:actions) {
			a.execute();
		}
	}
	
	private Action enemyInputHandler(Enemy e) {
		Action enemychoice = new performAttack(e,player); // for now, all enemies just basic attack
		return enemychoice;
	}
	
	// this method gets the player's input and returns the corresponding action
	private Action playerInputHandler() {
		int playerchoice = response(dialogue.get("Menu").get(0));
		int playerchoice2;
		Action playerAction = null;
		
		switch(playerchoice) {
			
			// attack option
			case 1:
				playerchoice = response(dialogue.get("Targeters").get(0),enemies);
				playerAction = new performAttack(player,enemies.get(playerchoice));
				break;
			
			// defend option
			case 2:
				playerAction = new performDefend(player,enemies.get(playerchoice));
				break;
			
			// spell option
			case 3:
				playerchoice = response(dialogue.get("Targeters").get(2),player.getInv().getSpells());
				playerchoice2 = response(dialogue.get("Targeters").get(0),enemies);
				playerAction = new performSpell(player,enemies.get(playerchoice),player.getInv().getSpell(playerchoice2-1));
				break;
			
			// item option
			case 4:
				playerchoice = response(dialogue.get("Targeters").get(1),player.getInv().getItems());
				playerchoice2 = response(dialogue.get("Targeters").get(0),enemies);
				playerAction = new performItem(player,enemies.get(playerchoice),player.getInv().getItem(playerchoice2-1));
				break;
			
			// run option
			case 5:
				playerAction = new performRun(player,enemies.get(playerchoice));
		}
		
		// error handling
		if (playerAction==null) System.out.println("Player action was not added successfully!");
		return playerAction;
	}		
}