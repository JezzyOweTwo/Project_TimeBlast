package time_blast.game_logic.Battle;
import java.util.*;
import time_blast.file_reading.FileReader;
import time_blast.game_logic.Battle.actions.Action;
import time_blast.game_logic.Battle.actions.performAttack;
import time_blast.game_logic.Battle.actions.performDefend;
import time_blast.game_logic.Battle.actions.performItem;
import time_blast.game_logic.Battle.actions.performRun;
import time_blast.game_logic.Battle.actions.performSpell;
import time_blast.game_logic.entities.*;
import time_blast.utilities.Utilities;

// This class is responsible for the battling in the game.
public class Battle implements Utilities,FileReader {
	
	private Player player;
	private ArrayList<Enemy> enemies;
	private ArrayList<Entity> combattants;
	private HashMap<String,ArrayList<String>> dialogue;
	
	public Battle(Player player, ArrayList<Enemy> enemies){
		
		// note that this constructor intentionally avoids deep copying
		this.player=player;
		this.enemies=enemies;
		combattants = new ArrayList<>(enemies);
		combattants.add(player);
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
		List<Action> actions = new ArrayList<>();
		for (Enemy e :enemies) 
			actions.add(enemyInputHandler(e));	
		actions.add(playerInputHandler());
		ActionPerformer(actions);
	}	// battle method
	
	// ------------------------------------------ helper methods ------------------------------------------------------//
	
	// checks if any enemies have been killed and rewards player xp for any killed enemies
	private boolean areAlive() {
		boolean areAlive = false;
		ArrayList<Entity> removalList = new ArrayList<>();
		for(Enemy e:enemies) {
			if(e.getStat(StatName.CURHP)<=0) {
				player.xpGain(e.getStat(StatName.EXPYIELD));
				removalList.add(e);
			}
			else areAlive=true;
		}	
		combattants.removeAll(removalList);
		enemies.removeAll(removalList);
		return areAlive;
	}  
	private Enemy fastestEnemy() {
		Enemy tempEnemy = null;
		int fastestSpeed=0;
		for (Enemy e:enemies){
			if (e.getStat(StatName.SPEED)>fastestSpeed) tempEnemy=e;
		}
		return tempEnemy;
	}

	// this method is responsible for performing a list of actions and updating and necessary UI elements
	private void ActionPerformer(List<? extends Action> actions) {
		Collections.sort(actions);		// sorts actions based on speed (lowest to highest)
		Collections.reverse(actions);	// reverses the order from fastest to slowest
		
		for(Action a:actions) {
			if (a.getSource().getStat(StatName.CURHP)<=0||
					!combattants.contains(a.getSource())) continue;
			a.execute();
			
			// player has run away
			if (!combattants.contains(player)) {
				System.out.println("The player has run away");
				return;
			}
			
			// player has died
			else if	(player.getStat(StatName.CURHP)<=0) {
				System.out.println("The player has died!");
				return;
			}
			
			// no enemies left alive
			else if (!areAlive()) {
				System.out.println("All enemies have died!");
				return;
			}
				
			// last enemy ran away
			else if (combattants.contains(player)&&combattants.size()==1) {
				System.out.println("The final enemy has escaped!");
				return;
			}
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
				if (enemies.size()>1) playerchoice = response(dialogue.get("Targeters").get(0),enemies);
				else playerchoice=1;
				playerAction = new performAttack(player,enemies.get(playerchoice-1));
				break;
			
			// defend option
			case 2:
				playerAction = new performDefend(player,enemies.get(playerchoice-1));
				break;
			
			// spell option
			case 3:
				if (player.getInv().getSpells()==null) break;
				playerchoice = response(dialogue.get("Targeters").get(2),player.getInv().getSpells());
				playerchoice2 = response(dialogue.get("Targeters").get(0),enemies);
				playerAction = new performSpell(player,enemies.get(playerchoice-1),player.getInv().getSpell(playerchoice2-1));
				break;
			
			// item option
			case 4:
				if (player.getInv().getItems()==null) break;
				playerchoice = response(dialogue.get("Targeters").get(1),player.getInv().getItems());
				playerchoice2 = response(dialogue.get("Targeters").get(0),enemies);
				playerAction = new performItem(player,enemies.get(playerchoice-1),player.getInv().getItem(playerchoice2-1));
				break;
			
			// run option
			case 5:
				playerAction = new performRun(player,fastestEnemy(),combattants);
				break;
				
			default:
				break;
		}
		// error handling
		if (playerAction==null) {
			System.out.println("ERROR! Player action was added unsuccessfully!");
			return playerInputHandler();
		}
		return playerAction;
	}		
}