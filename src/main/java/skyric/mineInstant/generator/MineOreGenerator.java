package skyric.mineInstant.generator;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;
import skyric.mineInstant.init.MineBlocks;

public class MineOreGenerator {
	
	protected static final CountRangeConfig SILVER_ORE_RANGE_CONFIG = new CountRangeConfig(2, 0, 0, 32);
	protected static final CountRangeConfig SILVER_ORE_EXTRA_RANGE_CONFIG = new CountRangeConfig(20, 32, 32, 80);
	protected static final OreFeatureConfig SILVER_ORE_FEATURE_CONFIG
		= new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE
								, MineBlocks.SILVER_ORE_BLOCK.get().getDefaultState()
								, 9
							);
	protected static final OreFeatureConfig SILVER_ORE_EXTRA_FEATURE_CONFIG 
		= new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE
								, MineBlocks.SILVER_ORE_BLOCK.get().getDefaultState()
								, 9
							);
	protected static final ConfiguredFeature<?, ?> SILVER_ORE_GENERATE_CONFIG 
		= Feature.ORE.withConfiguration(SILVER_ORE_FEATURE_CONFIG)
					.withPlacement(Placement.COUNT_RANGE.configure(SILVER_ORE_RANGE_CONFIG));
	protected static final ConfiguredFeature<?, ?> SILVER_ORE_EXTRA_GENERATE_CONFIG 
		= Feature.ORE.withConfiguration(SILVER_ORE_FEATURE_CONFIG)
					.withPlacement(Placement.COUNT_RANGE.configure(SILVER_ORE_EXTRA_RANGE_CONFIG));
	
	
	public static void setupOverWorldOreGeneration() {
		
		for (Biome biome: ForgeRegistries.BIOMES.getValues()) {
			
			if ( biome.getCategory()== Biome.Category.THEEND || biome.getCategory() == Biome.Category.NETHER ) {
				continue;
			}
			
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, SILVER_ORE_GENERATE_CONFIG);
			
			if ( biome.getCategory()== Biome.Category.MESA ) {
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, SILVER_ORE_EXTRA_GENERATE_CONFIG);
			}
			
		}
		
	}

}
