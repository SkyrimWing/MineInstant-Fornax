package skyric.mineInstant.item;

import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.Potion;

public class TabletItem extends AbstractMedicineItem{

	public TabletItem(Potion potion) {
		super(potion.getEffects());
	}
	
	
	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.EAT;
	}
	
	
	@Override
	public int getUseDuration(ItemStack stack) {
		return 32;
	}
	
	
	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

}