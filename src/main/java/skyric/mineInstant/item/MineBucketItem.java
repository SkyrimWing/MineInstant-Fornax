package skyric.mineInstant.item;


import java.util.function.Supplier;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import skyric.mineInstant.init.MineItemGroups;

public class MineBucketItem extends BucketItem{

	public MineBucketItem(Supplier<? extends Fluid> supplier) {
		super(supplier, new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(MineItemGroups.MINE_MATERIAL_GROUP));
	}

}
