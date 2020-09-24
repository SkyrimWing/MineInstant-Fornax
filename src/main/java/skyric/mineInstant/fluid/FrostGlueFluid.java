package skyric.mineInstant.fluid;

import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public abstract class FrostGlueFluid extends ForgeFlowingFluid{
	
	protected FrostGlueFluid(Properties properties) {
		super(properties);
	}
	
	
	public static ResourceLocation getStillFluidResource() {
		return new ResourceLocation("mineinstant:block/frost_glue_still_fluid_block");
	}
	
	
	public static ResourceLocation getFlowingFluidResource() {
		return new ResourceLocation("mineinstant:block/frost_glue_flowing_fluid_block");
	}
	
	
	public int getSlopeFindDistance(IWorldReader worldIn) {
		//return worldIn.getDimension().doesWaterVaporize() ? 4 : 2;
		return 3;
	}
	
	
	public int getLevelDecreasePerBlock(IWorldReader worldIn) {
		//return worldIn.getDimension().doesWaterVaporize() ? 1 : 2;
		return 2;
	}
	
	
	/*
	public boolean canDisplace(IFluidState p_215665_1_, IBlockReader p_215665_2_, BlockPos p_215665_3_, Fluid p_215665_4_, Direction p_215665_5_) {
		return true;
	}*/
	
	
	@Override
	protected boolean ticksRandomly() {
		return true;
	}
	
	
	@Override
	public int getTickRate(IWorldReader worldIn) {
		//return p_205569_1_.getDimension().isNether() ? 10 : 30;
		return 15;
	}
	
	
	public static class Source extends FrostGlueFluid{

		public Source(Properties properties) {
			super(properties);
		}

		@Override
		public boolean isSource(IFluidState state) {
			return true;
		}

		@Override
		public int getLevel(IFluidState p_207192_1_) {
			return 8;
		}
		
	}

	public static class Flowing extends FrostGlueFluid{

		public Flowing(Properties properties) {
			super(properties);
		}
		
		
		protected void fillStateContainer(StateContainer.Builder<Fluid, IFluidState> builder) {
			super.fillStateContainer(builder);
			builder.add(LEVEL_1_8);
		}
		

		@Override
		public boolean isSource(IFluidState state) {
			return false;
		}

		@Override
		public int getLevel(IFluidState state) {
			return state.get(LEVEL_1_8);
		}
		
	}
	
	
	public static class FrostGlueFluidBlock extends FlowingFluidBlock{

		public FrostGlueFluidBlock(Supplier<? extends FlowingFluid> supplier) {
			super(supplier, Block.Properties.create(Material.LAVA).doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops());
			//.tickRandomly().lightValue(15) inherite and add surrounding the light
		}
		
		
		//float getSlipperiness(BlockState state, IWorldReader world, BlockPos pos, @Nullable Entity entity);
		//default int getLightValue(BlockState state, IBlockReader world, BlockPos pos);
		
	}

}
