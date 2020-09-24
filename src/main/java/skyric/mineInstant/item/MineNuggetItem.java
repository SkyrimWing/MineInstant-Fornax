package skyric.mineInstant.item;

import net.minecraft.item.Item;
import skyric.mineInstant.init.MineItemGroups;

public class MineNuggetItem extends Item{

	public MineNuggetItem(Properties properties) {
		super(properties.group(MineItemGroups.MINE_MATERIAL_GROUP));
	}

}
