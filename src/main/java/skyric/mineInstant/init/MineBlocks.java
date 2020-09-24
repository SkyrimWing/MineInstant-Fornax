package skyric.mineInstant.init;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.DyeColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import skyric.mineInstant.MineInstant;
import skyric.mineInstant.block.MineOreBlock;
import skyric.mineInstant.block.MineTreasureBlock;
import skyric.mineInstant.fluid.FrostGlueFluid;
import skyric.mineInstant.block.AlchemyCaldronBlock;
import skyric.mineInstant.block.DazzleFireBlock;


public class MineBlocks {
	
	public static final DeferredRegister<Block> MINE_BLOCKS = new DeferredRegister<Block>(ForgeRegistries.BLOCKS, MineInstant.MODID);
	
	public static final RegistryObject<MineOreBlock> SILVER_ORE_BLOCK 
		= MINE_BLOCKS.register( "silver_ore_block"
							, () -> new MineOreBlock( Block.Properties.create(Material.ROCK)
														.hardnessAndResistance(3.0F, 3.0F)
														.harvestTool(ToolType.PICKAXE)
														.harvestLevel(2)
													)
						);
	
	public static final RegistryObject<FrostGlueFluid.FrostGlueFluidBlock> FROST_GLUE_FLUID_BLOCK
		= MINE_BLOCKS.register( "frost_glue_fluid_block"
							, () -> new FrostGlueFluid.FrostGlueFluidBlock(MineFluids.FROST_GLUE_FLUID)
						);
	
	public static final RegistryObject<MineTreasureBlock> SILVER_BLOCK 
		= MINE_BLOCKS.register( "silver_block"
							, () -> new MineTreasureBlock( Block.Properties.create(Material.IRON, MaterialColor.QUARTZ)
																.hardnessAndResistance(3.0F, 6.0F)
																.sound(SoundType.METAL)
																.harvestTool(ToolType.PICKAXE)
																.harvestLevel(2)
														)
						);
	
	public static final RegistryObject<DazzleFireBlock> DAZZLE_FIRE_WHITE_BLOCK 
		= MINE_BLOCKS.register( "dazzle_fire_white_block", () -> new DazzleFireBlock(DyeColor.WHITE, 2) );
	public static final RegistryObject<DazzleFireBlock> DAZZLE_FIRE_ORANGE_BLOCK 
		= MINE_BLOCKS.register( "dazzle_fire_orange_block", () -> new DazzleFireBlock(DyeColor.ORANGE, 2) );
	public static final RegistryObject<DazzleFireBlock> DAZZLE_FIRE_MAGENTA_BLOCK 
		= MINE_BLOCKS.register( "dazzle_fire_magenta_block", () -> new DazzleFireBlock(DyeColor.MAGENTA, 2) );
	public static final RegistryObject<DazzleFireBlock> DAZZLE_FIRE_LIGHT_BLUE_BLOCK 
		= MINE_BLOCKS.register( "dazzle_fire_light_blue_block", () -> new DazzleFireBlock(DyeColor.LIGHT_BLUE, 2) );
	public static final RegistryObject<DazzleFireBlock> DAZZLE_FIRE_YELLOW_BLOCK 
		= MINE_BLOCKS.register( "dazzle_fire_yellow_block", () -> new DazzleFireBlock(DyeColor.YELLOW, 2) );
	public static final RegistryObject<DazzleFireBlock> DAZZLE_FIRE_LIME_BLOCK 
		= MINE_BLOCKS.register( "dazzle_fire_lime_block", () -> new DazzleFireBlock(DyeColor.LIME, 2) );
	public static final RegistryObject<DazzleFireBlock> DAZZLE_FIRE_PINK_BLOCK 
		= MINE_BLOCKS.register( "dazzle_fire_pink_block", () -> new DazzleFireBlock(DyeColor.PINK, 2) );
	public static final RegistryObject<DazzleFireBlock> DAZZLE_FIRE_GRAY_BLOCK 
		= MINE_BLOCKS.register( "dazzle_fire_gray_block", () -> new DazzleFireBlock(DyeColor.GRAY, 2) );
	public static final RegistryObject<DazzleFireBlock> DAZZLE_FIRE_LIGHT_GRAY_BLOCK 
		= MINE_BLOCKS.register( "dazzle_fire_light_gray_block", () -> new DazzleFireBlock(DyeColor.LIGHT_GRAY, 2) );
	public static final RegistryObject<DazzleFireBlock> DAZZLE_FIRE_CYAN_BLOCK 
		= MINE_BLOCKS.register( "dazzle_fire_cyan_block", () -> new DazzleFireBlock(DyeColor.CYAN, 2) );
	public static final RegistryObject<DazzleFireBlock> DAZZLE_FIRE_PURPLE_BLOCK 
		= MINE_BLOCKS.register( "dazzle_fire_purple_block", () -> new DazzleFireBlock(DyeColor.PURPLE, 2) );
	public static final RegistryObject<DazzleFireBlock> DAZZLE_FIRE_BLUE_BLOCK 
		= MINE_BLOCKS.register( "dazzle_fire_blue_block", () -> new DazzleFireBlock(DyeColor.BLUE, 2) );
	public static final RegistryObject<DazzleFireBlock> DAZZLE_FIRE_BROWN_BLOCK 
		= MINE_BLOCKS.register( "dazzle_fire_brown_block", () -> new DazzleFireBlock(DyeColor.BROWN, 2) );
	public static final RegistryObject<DazzleFireBlock> DAZZLE_FIRE_GREEN_BLOCK 
		= MINE_BLOCKS.register( "dazzle_fire_green_block", () -> new DazzleFireBlock(DyeColor.GREEN, 2) );
	public static final RegistryObject<DazzleFireBlock> DAZZLE_FIRE_RED_BLOCK 
		= MINE_BLOCKS.register( "dazzle_fire_red_block", () -> new DazzleFireBlock(DyeColor.RED, 2) );
	public static final RegistryObject<DazzleFireBlock> DAZZLE_FIRE_BLACK_BLOCK 
		= MINE_BLOCKS.register( "dazzle_fire_black_block", () -> new DazzleFireBlock(DyeColor.BLACK, 2) );
	
	
	public static final RegistryObject<AlchemyCaldronBlock> ALCHEMY_CALDRON_BLOCK 
	= MINE_BLOCKS.register( "alchemy_caldron_block"
						, () -> new AlchemyCaldronBlock( Block.Properties.create(Material.IRON, MaterialColor.QUARTZ)
															.hardnessAndResistance(3.0F, 6.0F)
															.sound(SoundType.METAL)
															.harvestTool(ToolType.PICKAXE)
															.harvestLevel(2)
						)
					);

}
