package skyric.mineInstant.item;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import skyric.mineInstant.init.MineItemGroups;

public abstract class AbstractFoodItem extends Item{

	public AbstractFoodItem(Food foodIn) {
		super( (new Item.Properties()).food(foodIn).group(MineItemGroups.MINE_MATERIAL_GROUP) );
	}
	
}