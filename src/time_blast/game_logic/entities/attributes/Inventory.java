package time_blast.game_logic.entities.attributes;
import java.util.ArrayList;

public class Inventory{
	private ArrayList<Item> items;
    private ArrayList<Spell> spells;
    private ArrayList<Attack> attacks;
    private int gold;
    
	// creates an inventory with items
    public Inventory(ArrayList<Item> items,ArrayList<Spell> spells,ArrayList<Attack> attacks,int gold){
    	this.gold    = gold;
        this.items   = items; 
        this.spells  = spells; 
        this.attacks = attacks;
    }
    public Inventory() {
    	gold=0;
    }
    
    //  
    public Inventory(Inventory inv) {
    	 gold = inv.getGold();
    	 items = (inv.getItems()!=null)    ?  new ArrayList<Item>(inv.getItems()): 	  null;
    	 spells = (inv.getSpells()!=null)  ?  new ArrayList<Spell>(inv.getSpells()):  null;
    	 attacks = (inv.getAttacks()!=null)?  new ArrayList<Attack>(inv.getAttacks()):null;
    }
    
    public Spell getSpell(int index) {return spells.get(index);}
    public Item getItem(int index) {return items.get(index);}
    public ArrayList<Item> getItems() {return items;}
	public void setItems(ArrayList<Item> items) {this.items = items;}
	public ArrayList<Spell> getSpells() {return spells;}
	public void setSpells(ArrayList<Spell> spells) {this.spells = spells;}
	public ArrayList<Attack> getAttacks() {return attacks;}
	public void setAttacks(ArrayList<Attack> attacks) {this.attacks = attacks;}
	public int getGold() {return gold;}
	public void setGold(int gold) {this.gold = gold;}
    
	// this method returns a String containing the object's inventory.
	@Override
	public String toString() {
		ArrayList<String> all = new ArrayList<>();
		all.add(spells.toString());
		all.add(attacks.toString());
		all.add(items.toString());
		all.add(Integer.toString(gold));
		return all.toString();
    }
    
}
