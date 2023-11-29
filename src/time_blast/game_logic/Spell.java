package time_blast.game_logic;

import java.util.ArrayList;
import java.util.HashMap;

public class Spell{
	
	private HashMap<String,Integer> stats = new HashMap<String,Integer>();
    private final String element;
    private final boolean isSplash;
    private boolean special;
    static int SpellNumber=0;
    final private int ID;
    final private String name;

    
    Spell(HashMap<String,Integer> stats,String name,String element,boolean isSplash){
    	this(stats.values().toArray(new Integer[stats.size()]),name,element,isSplash);
    }
    
    Spell(Integer[] stats,String name,String element,boolean isSplash){
    	String[] titles = {"BaseDmg","ManaCost"};
    	int i=0;
    	for(String t:titles) {
    		this.stats.put(t,stats[i]);
    	}
    	
    	this.isSplash = isSplash;
    	this.name=name;
    	this.special=false;
    	this.element = element;
        SpellNumber++;
        ID=SpellNumber;
    }
    
    // copy constructor
    Spell(Spell spell){
    	this(new HashMap<String,Integer>(spell.getStats()),new String(spell.getName()),new String(spell.getElement()),spell.getIsSplash());
    }
    
    public boolean getIsSplash() {return isSplash;}
    public static int getSpellNumber() {return SpellNumber;}
    public String getElement() {return element;}
    public HashMap<String,Integer> getStats() {return stats;}
	public boolean getSpecial() {return special;}
	public int getID() {return ID;}
	public String getName() {return name;}

	// special spell constructor
    Spell(){
    	element="";
    	stats = new HashMap<>();
        SpellNumber++;
        special=false;
        ID=SpellNumber;
        name = "Void";
        isSplash = false;
    }
    
    @Override
    public String toString() {
    	String all = "";
    	all+=stats.toString();
    	all+=Boolean.toString(special);	
    	return all;
    }
    
    public static String[] nameList(Spell[] spells) {
		String[] names = new String[spells.length];
		int i=0;
		for (Spell e:spells) {
			names[i] = e.getName();
			i++;
		}
		return names;
    }
}
