package skyric.mineInstant.init;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import skyric.mineInstant.MineInstant;
import skyric.mineInstant.entity.MarblePelletEntity;
import skyric.mineInstant.entity.SeaShellEntity;

public class MineEntityTypes {
	
	public static final DeferredRegister<EntityType<?>> MINE_ENTITY_TYPES = new DeferredRegister<EntityType<?>>(ForgeRegistries.ENTITIES, MineInstant.MODID);
	
	public static final String SEA_SHELL_ENTITY_ID = "sea_shell_entity";
	public static final RegistryObject<EntityType<SeaShellEntity>> SEA_SHELL_ENTITY 
		= MINE_ENTITY_TYPES.register(
				SEA_SHELL_ENTITY_ID
			  , () -> EntityType.Builder.<SeaShellEntity>create(SeaShellEntity::new, EntityClassification.WATER_CREATURE)
						.size(0.9F, 0.3F)
						.build(new ResourceLocation(MineInstant.MODID, SEA_SHELL_ENTITY_ID).toString())
		);
	
	public static final String MARBLE_PELLET_ENTITY_ID = "marble_pellet_entity";
	public static final RegistryObject<EntityType<MarblePelletEntity>> MARBLE_PELLET_ENTITY
		= MINE_ENTITY_TYPES.register(
				MARBLE_PELLET_ENTITY_ID
			  , () -> EntityType.Builder.<MarblePelletEntity>create(MarblePelletEntity::new, EntityClassification.MISC)
			  			.size(0.2F, 0.2F)
			  			.build(new ResourceLocation(MineInstant.MODID, MARBLE_PELLET_ENTITY_ID).toString())
				);

}
