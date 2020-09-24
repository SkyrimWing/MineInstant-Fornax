package skyric.mineInstant.init;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


import skyric.mineInstant.MineInstant;
import skyric.mineInstant.container.AlchemyCaldronContainer;

public class MineContainerTypes {
	
	public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = new DeferredRegister<>(ForgeRegistries.CONTAINERS, MineInstant.MODID);
	
	public static final RegistryObject<ContainerType<AlchemyCaldronContainer>> ALCHEMY_CALDRON_CONTAINER 
		= CONTAINER_TYPES.register("alchemy_caldron_container", () -> IForgeContainerType.create(AlchemyCaldronContainer::new));

}
