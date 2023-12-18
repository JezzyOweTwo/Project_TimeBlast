package time_blast.game_logic.entities.attributes;
import java.util.*;

import time_blast.file_reading.FileReader;
import time_blast.game_logic.entities.StatName;

public class Item {
    private HashMap<StatName,Integer> stats;
    private String description;
    private AttributeRarity rarity;
    private boolean isSplash;
    static int count=1;
    final int ID;
    String name;
        
    public Item(String name,AttributeRarity rarity,HashMap<StatName,Integer> stats,boolean isSplash,String description){
        this.name = name;
        this.rarity = rarity;
        this.isSplash = isSplash;
    	this.stats = stats;
        this.description = description;
        ID=count;
        count++;
    } 

    public Item(FileReader fileReader,int fileLine){
        this();
        ArrayList<String> itemFields = fileReader.readline(fileLine);
        name = (itemFields.get(0));
        // rarity  itemFields.get(1);

        // starts from the third value because name and rarity are in cells 0 & 1 respectively.
        int i = 2;
        int statValue;
        for (StatName stat: StatName.values()){
            statValue = Integer.parseInt(itemFields.get(i));
            if (statValue==0) continue;
            this.setStat(stat,statValue);
            i++;
        }
        this.setIsSplash(Boolean.parseBoolean(itemFields.get(itemFields.size()-2)));
        this.setDescription(itemFields.get(itemFields.size()-1));
    }

    public Item(){
        ID=count;
        count++;
        stats = new HashMap<StatName,Integer>();
    }
    @Override
    public String toString(){
        return name;
    }
    
    //Copy constructor for item
    public Item(Item item){
        this(item.getName(),item.getRarity(),item.getStats(),item.getIsSplash(), item.getDescription());
    }
    
    // getters and setters
    public void setIsSplash(Boolean isSplash) {this.isSplash= isSplash;}
    public void setRarity(AttributeRarity rarity) {this.rarity = rarity;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    public AttributeRarity getRarity() {return rarity;}
    public boolean getIsSplash() {return isSplash;}
    public void setSplash(boolean splash) {isSplash = splash;}
    public static void setCount(int count) {Item.count = count;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public HashMap<StatName,Integer> getStats(){return stats;}
    public void setStats(HashMap<StatName,Integer> stats) {this.stats=stats;}
    public void setStat(StatName stat,int value) {stats.put(stat, value);}
    public static int getCount() {return count;}
    public int getID() {return ID;}

}
