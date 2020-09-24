package skyric.mineInstant;

import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import skyric.mineInstant.block.AbstractFlamingBlock;
import skyric.mineInstant.init.MineBlocks;
import skyric.mineInstant.init.MineItemGroups;


@EventBusSubscriber(modid = MineInstant.MODID, bus = EventBusSubscriber.Bus.MOD)
public class MineEventSubscriber 
{
	@SubscribeEvent
	public static void onRegisterItemBlocks(RegistryEvent.Register<Item> event) {
		
		final IForgeRegistry<Item> registry = event.getRegistry();
		
		MineBlocks.MINE_BLOCKS.getEntries().stream()
		.map(RegistryObject::get)
		.filter( block -> !(block instanceof AbstractFlamingBlock || block instanceof FlowingFluidBlock) )
		.forEach( block -> {
								final Item.Properties properties = new Item.Properties().group(MineItemGroups.MINE_BLOCK_GROUP);
								final BlockItem blockItem = new BlockItem(block, properties);
								blockItem.setRegistryName(block.getRegistryName());
								registry.register(blockItem);
							} 
				);
	}
	
	
	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name) {
		return setup(entry, new ResourceLocation(MineInstant.MODID, name));
	}
	
	
	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName) {
		entry.setRegistryName(registryName);
		return entry;
	}
}
