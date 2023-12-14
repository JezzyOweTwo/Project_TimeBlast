package time_blast.game_logic.battle;
import java.util.*;

import time_blast.file_reading.CSVReader;
import time_blast.file_reading.Dialogue;
import time_blast.game_logic.actions.Action;
import time_blast.game_logic.actions.performAttack;
import time_blast.game_logic.actions.performItem;
import time_blast.game_logic.actions.performRun;
import time_blast.game_logic.actions.performDefend;
import time_blast.game_logic.actions.performSpell;
import time_blast.game_logic.entities.*;
import time_blast.utilities.Utilities;

// This class is responsible for the battling in the game.
public class Battle implements Utilities {
	private final Player player;
	private final ArrayList<Enemy> enemies;
	private final ArrayList<Entity> combattants;
	private final HashMap<String,Dialogue> dialogue;

	public Battle(Player player, ArrayList<Enemy> enemies){

		// note that this constructor intentionally avoids deep copying
		this.player=player;
		this.enemies=enemies;
		combattants = new ArrayList<>(enemies);
		combattants.add(player);
		String DIALOGUE_PATH = "\\src\\time_blast\\game_logic\\Battle\\BattleDialogue.csv";
		dialogue = new CSVReader<Dialogue>(DIALOGUE_PATH).readAll();
	}
	
	// getters (there are no setters for battle)
	public Player getPlayer() {return player;}
	public ArrayList<Enemy> getEnemies(){return enemies;}
	public HashMap<String,Dialogue> getDialogue(){return dialogue;}
	
	// Main battle method -- returns 1 if player wins, -1 if enemies win //
	public int runBattle() {
		List<Action> actions = new ArrayList<>();
		for (Enemy e :enemies) 
			actions.add(enemyInputHandler(e));	
		actions.add(playerInputHandler());
		int i = ActionPerformer(actions);
		if (i!=0)return i;
		else return runBattle();
	}
	
	// ------------------------------------------ helper methods ------------------------------------------------------//
	
	// returns an array of all dead enemies
	private ArrayList<Enemy> deadEnemyArrayGen() {
		ArrayList<Enemy> deadEnemies= new ArrayList<>();
		for(Enemy e:enemies) 
			if(e.getStat(StatName.CURHP)<=0) deadEnemies.add(e);
		return deadEnemies;
	}
	
	//  rewards xp to player for each dead enemy
	private void expReward(ArrayList<Enemy> deadEnemies) {
		if (deadEnemies==null)return;
		for (Enemy e:deadEnemies) {
			System.out.println(e.getName()+" has been slain!");
			int xp = e.getStat(StatName.EXPYIELD);
			System.out.println(player.getName()+" gained "+xp+" xp!");
			player.xpGain(xp);
		}
	}
	
	// returns the fastest non dead enemy
	private Enemy fastestEnemy() {
		Enemy tempEnemy = null;
		int fastestSpeed=0;
		for (Enemy e:enemies)
			if (e.getStat(StatName.SPEED)>fastestSpeed&&e.getStat(StatName.CURHP)>0) tempEnemy=e;
		return tempEnemy;
	}

	// this method is responsible for performing a list of actions and updating and necessary UI elements
	private int ActionPerformer(List<? extends Action> actions) {
		Collections.sort(actions);		// sorts actions based on speed (lowest to highest)
		Collections.reverse(actions);	// reverses the order from fastest to slowest
		ArrayList<Enemy> deadEnemies;	// list of dead enemies
		for(Action a:actions) {
			a.execute();
			deadEnemies = deadEnemyArrayGen();
			expReward(deadEnemies);
			
			if (!combattants.contains(player)) {
				System.out.println("The player has run away");
				return 1;
			}
			
			// player has died
			else if	(player.getStat(StatName.CURHP)<=0) {
				System.out.println("The player has died!");
				return -1;
			}
			
			// no enemies left alive
			else if (deadEnemies.size()==enemies.size()) {
				System.out.println("All enemies have died!");
				return 1;
			}
				
			// last enemy ran away
			else if (combattants.contains(player)&&combattants.size()==1) {
				System.out.println("The final enemy has escaped!");
				return 1;
			}
			
			if (!deadEnemies.isEmpty()) {
				combattants.removeAll(deadEnemies);
				enemies.removeAll(deadEnemies);		
			}
		}
		return 0;
	}
	
	// controls enemy logic
	private Action enemyInputHandler(Enemy e) {
		return new performAttack(e,player); // for now, all enemies just basic attack
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
				playerAction = new performDefend(player);
				break;
			
			// spell option
			case 3:
				if (player.getInv().getSpells().isEmpty()) break;
				playerchoice = response(dialogue.get("Targeters").get(2),player.getInv().getSpells());
				playerchoice2 = response(dialogue.get("Targeters").get(0),enemies);
				playerAction = new performSpell(player,enemies.get(playerchoice2-1),player.getInv().getSpell(playerchoice-1));
				break;
			
			// item option
			case 4:
				if (player.getInv().getItems().isEmpty()) break;
				playerchoice = response(dialogue.get("Targeters").get(1),player.getInv().getItems());
				playerchoice2 = response(dialogue.get("Targeters").get(0),enemies);
				playerAction = new performItem(player,enemies.get(playerchoice2-1),player.getInv().getItem(playerchoice-1));
				break;
			
			// run option
			case 5:
				playerAction = new performRun(player,fastestEnemy(),combattants);
				break;
				
			// error handling	
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