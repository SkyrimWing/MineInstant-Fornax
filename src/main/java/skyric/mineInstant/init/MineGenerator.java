package skyric.mineInstant.init;

import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import skyric.mineInstant.MineInstant;
import skyric.mineInstant.generator.AncientEbonyTreeFeature;

public class MineGenerator {
	
	public static final DeferredRegister<Feature<?>> MINE_GENERATE_FEATURES = new DeferredRegister<Feature<?>>(ForgeRegistries.FEATURES, MineInstant.MODID);
	
	public static final RegistryObject<AncientEbonyTreeFeature> K_TREE 
		= MINE_GENERATE_FEATURES.register("k_tree", () -> new AncientEbonyTreeFeature(null));

}
