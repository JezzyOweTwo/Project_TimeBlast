package time_blast.game_logic.entities;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import time_blast.game_logic.entities.attributes.Inventory;
import time_blast.graphics.Animatable;
import time_blast.graphics.AnimationHandler;
import time_blast.graphics.GamePanel;
import time_blast.graphics.Moveable;
import time_blast.graphics.assets.Direction;

import javax.imageio.ImageIO;

/* Entity is an abstract parent class containing the following information about an entity:
 * 
 * [1.] A HashMap of stats. 
 *      Note that each child class of Entity has additional stats. 
 * 
 * [2.] A Inventory object which contains the following information:
 *  -  ArrayList <Item>
 *  -  ArrayList <Spell>
 *  -  ArrayList <Attack>
 *  -  int gold
 *  
 *  [3.] The Entity's name
 *  
 *  [4.] A unique ID Number for the entity
 * 
 * Note that only the children can be instantiated.  */

public abstract class Entity implements Moveable,Animatable {
    private Inventory inv = new Inventory();							  // inventory of entity 
    private String name ="";		                  					  // name of entity
    private final int ID;												  // ID Number of the entity	
    static int Count=0;													  // Number of created entities
	private boolean isIdle;											      // tracks whether entity is idle or not
    private HashMap<StatName,Integer> stats= new HashMap<>();	  		  // hashmap of stats
	private int width=0;												  // width of entity in pixels
	private int height=0;												  // height of entity in pixels
	private int x=500;												      // x position of the entity
	private int y=500;												  	  // y position of the entity
	BufferedImage currentFrame;											  // current rendered frame of entity
	Direction direction;												  // current direction of player
	private AnimationHandler<Entity> ah;								  // AnimationHandler of entity
	private Dimension speed=new Dimension(4,4);			  // speed of speed
	private Dimension acceleration=new Dimension(0,0);		  // entity's acceleration
    
    // default no args constructor
	Entity(){
		statLoader(new HashMap<StatName,Integer>());	// generates an empty stat array
    	Count++;
    	ID=Count;
    }
	
	Entity(Inventory inv,String name){
		this();
		direction = Direction.down;
		isIdle = true;
		ah = new AnimationHandler<>(this);
    	this.inv = new Inventory(inv);
    	this.name = new String(name);
	}

	protected void imageLoader(String imagePath){
		try{
			currentFrame = ImageIO.read(new File(imagePath));
			width=currentFrame.getWidth();
			height=currentFrame.getHeight();
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	// stat loader method
	protected void statLoader(HashMap<StatName,Integer> stats) {
		StatName[] statnames = StatName.values();
		ArrayList<StatName> addedStats = new ArrayList<>();
		 for (StatName stat:statnames) {
			if (stats.containsKey(stat)&&!addedStats.contains(stat)) {
				this.stats.put(stat,stats.get(stat));
				addedStats.add(stat);
		 	}
			else if (addedStats.contains(stat));	// exists so this condition doesn't trigger else condition
			else this.stats.put(stat, 0);			// sets stat to 0 if it doesn't exist
		}
	}
    
    // copy constructor
    Entity(Entity en){
    	this(new Inventory(en.getInv()),new String(en.getName()));
    }

	// animation stuff
	public void setisIdle(boolean isIdle){this.isIdle = isIdle;}
	public void setCurrentFrame(BufferedImage img){currentFrame = img;}
	public boolean getisIdle() {return isIdle;}
	public Direction getDirection() {return direction;}


	// drawable stuff
	public int getWidth(){return width;}
	public int getHeight(){return height;}
	public int getX(){return  x;}
	public int getY(){return y;}
	public BufferedImage getImage(){return currentFrame;}

	//moveable stuff
	public Dimension getAcceleration(){return  acceleration;}
	public Dimension getSpeed(){return speed;}
	public void move(Dimension speed) {
		if (x>0&&x<GamePanel.WINDOW_SIZE.width)x+=speed.width;
		if (y>0&&y<GamePanel.WINDOW_SIZE.height)y+=speed.height;
	}

    // validates if a given stat title is actually in the stat hashmap
	private boolean validStatChecker(StatName stat) {
		for(StatName s : stats.keySet()) {
			if (stat.equals(s)) return true;		
		}	
		System.out.println("Stat '"+stat+"' does not exist in this stat list!");
		return false;
	}

	// negative number checker
	protected boolean aboveZeroCheck(float value) {
		if (value>0) return true;
		System.out.println("Error! "+value+" is a nonpositive number!");
		return false;
	}
	
	// adds to a stat 
	public void addStat(StatName statName,int value){
		if (!validStatChecker(statName)||!aboveZeroCheck(value)) return;
		stats.put(statName,Math.max(stats.get(statName) + value, 0));
	}
	
	// subtracts from a stat
	public void dropStat(StatName statName,int value) {
		if (!validStatChecker(statName)||!aboveZeroCheck(value)) return;
		stats.put(statName,Math.max(stats.get(statName) - value, 0));
	}
	
	// multiplies a stat
	public void multiplyStat(StatName statName,float factor) {
		if (!validStatChecker(statName)||!aboveZeroCheck(factor)) return;
		stats.put(statName,(int)(Math.max(stats.get(statName) * factor, 0)));
	}
	
    // setters and getters
    public HashMap<StatName,Integer> getStats() {return this.stats;}
    public int getStat(StatName key) { if (validStatChecker(key))return stats.get(key); return -1;}
	public void setStats(HashMap<StatName,Integer> stats){statLoader(stats);}
	public void setStat(StatName statName,int value) {
		if (validStatChecker(statName)) stats.put(statName, value);
	}
	protected void removeStat(StatName statName) {
		if (validStatChecker(statName)) stats.remove(statName);
	}
	protected void removeStats(StatName[] statNames) {
	 	for (StatName s:statNames) {
	 		this.removeStat(s);
	 	}
	}
	public Inventory getInv() {return inv;}
	public void setInv(Inventory inv) {this.inv = new Inventory(inv);}
	public String getName() {return name;}
	public void setName(String name) {this.name = new String(name);}
	public int getID() {return ID;}
	public static int getCount() {return Count;}
	
	@Override
	public String toString() {
		return this.getName()+", ID: "+this.getID();
	}   
}	