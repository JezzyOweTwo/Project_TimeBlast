package time_blast.game_logic.actions;

import time_blast.game_logic.entities.Entity;
import time_blast.game_logic.entities.attributes.Item;

public class performItem extends Action{
	private Item item;
	
	public performItem(Entity source,Entity target,Item item){
		super(source,target);
		this.item=item;
	}
	public Item getItem() {return item;}
	
	@Override
	public void execute() {
		if (!areAlive()) return;
		// TODO Auto-generated method stub
	}
}
