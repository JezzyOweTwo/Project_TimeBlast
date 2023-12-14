package time_blast.game_logic.entities.attributes;
import time_blast.graphics.RollOverArrayList;

import java.util.Arrays;
import java.util.HashMap;

public class Spell{
	
	private HashMap<SpellStatName,Integer> stats = new HashMap<>();
    private SpellElement element;
    private boolean isSplash;
    static int count=0;
    final private int ID;
    private String name;
    public static final HashMap<SpellElement,SpellElement> ELEMENT_CHART = elementChartGenerator();
    
    public Spell(HashMap<SpellStatName,Integer> stats,String name,SpellElement element,boolean isSplash){
    	for (SpellStatName s:SpellStatName.values()) {
    		if (stats.keySet().contains(s))this.stats.put(s,stats.get(s));
    		else this.stats.put(s, 0);
    	}	
    	this.isSplash = isSplash;
    	this.name=new String(name);
    	this.element = element;
        count++;
        ID=count;
    }
    
    // copy constructor
	public Spell(Spell spell){
		this(new HashMap<SpellStatName,Integer>(spell.getStats()),new String(spell.getName()),spell.getElement(),spell.getIsSplash());
	}
  
	// empty spell constructor
	public Spell(){
      count++;
      ID=count;
	}
	
	// generates the element effectiveness chart 
	private static HashMap<SpellElement,SpellElement> elementChartGenerator(){
		HashMap<SpellElement,SpellElement> elementChart = new HashMap<>();
		RollOverArrayList<SpellElement> spellElements = new RollOverArrayList<>(Arrays.asList(SpellElement.values()));
		for (int j =1;j<=spellElements.size();j++)
			elementChart.put(spellElements.get(j-1), spellElements.get(j));
		return elementChart;
	}
    
	// getters
    public boolean getIsSplash() {return isSplash;}
    public static int getSpellNumber() {return count;}
    public SpellElement getElement() {return element;}
    public HashMap<SpellElement,SpellElement> getElementChart(){return ELEMENT_CHART;}
    public HashMap<SpellStatName,Integer> getStats() {return stats;}
    public int getStat(SpellStatName statName) {
    	if (stats.containsKey(statName)) 
    		return stats.get(statName);
    	else {
    		System.out.println("Stat name is not contained in this stat array!");
    		return -1;
    	}
    }
	public int getID() {return ID;}
	public String getName() {return name;}
    
	// setters
	public void setElement(SpellElement spellelement) {this.element=spellelement;}
	
    @Override
    public String toString() {
    	return name;
    }
    
}
