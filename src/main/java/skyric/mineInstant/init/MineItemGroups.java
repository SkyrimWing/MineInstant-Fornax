package skyric.mineInstant.init;

import java.util.function.Supplier;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import skyric.mineInstant.MineInstant;

public class MineItemGroups
{
	public static final ItemGroup MINE_BLOCK_GROUP 
		= new MineInstantItemGroup( MineInstant.MODID + "block", () -> new ItemStack(MineBlocks.SILVER_ORE_BLOCK.get()) );
	
	public static final ItemGroup MINE_MATERIAL_GROUP 
		= new MineInstantItemGroup( MineInstant.MODID + "material", () -> new ItemStack(MineItems.SILVER_INGOT_ITEM.get()) );
	
	public static final ItemGroup MINE_TOOL_GROUP 
		= new MineInstantItemGroup( MineInstant.MODID + "tool", () -> new ItemStack(MineItems.DAZZLE_FLINT_IGNITER_ITEM.get()) );
	
	
	public static class MineInstantItemGroup extends ItemGroup{
		
		private final Supplier<ItemStack> iconSupplier;

		public MineInstantItemGroup(final String label, final Supplier<ItemStack> iconSupplier) {
			super(label);
			this.iconSupplier = iconSupplier;
		}

		@Override
		public ItemStack createIcon() {
			return iconSupplier.get();
		}
		
	}
}
