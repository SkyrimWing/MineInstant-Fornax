package skyric.mineInstant.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import skyric.mineInstant.init.MineItemGroups;

public class MineIngotItem extends Item{

	public MineIngotItem(Properties properties) {
		super(properties.group(MineItemGroups.MINE_MATERIAL_GROUP));
	}
	
	
	public boolean isBeaconPayment(ItemStack stack){
        return true;
    }

}
