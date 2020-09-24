package skyric.mineInstant.item;

import net.minecraft.item.Food;

public class MilkSliceItem extends AbstractFoodItem {

	public MilkSliceItem() {
		super(	(new Food.Builder()).hunger(1).saturation(0.5F).fastToEat().build() );
	}

}
