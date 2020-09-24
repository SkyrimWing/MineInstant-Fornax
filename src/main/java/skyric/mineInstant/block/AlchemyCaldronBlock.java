package skyric.mineInstant.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.ItemStackHandler;
import skyric.mineInstant.init.MineTileEntityTypes;
import skyric.mineInstant.tile.AlchemyCaldronTileEntity;

public class AlchemyCaldronBlock extends Block{
	
	public static final BooleanProperty ON_LIT = BlockStateProperties.LIT;

	public AlchemyCaldronBlock(Properties properties) {
		super(properties);
	}
	
	
	@Override
	public int getLightValue(BlockState state) {
		return state.get(ON_LIT) ? 10 : 0;
	}
	
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(ON_LIT);
	}
	
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(ON_LIT, false);
	}
	
	
	@Override
	public boolean hasTileEntity(final BlockState state) {
		return true;
	}
	
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return MineTileEntityTypes.ALCHEMY_CALDRON_TILE.get().create();
	}
	
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult traceResult) {
		if (!worldIn.isRemote) {
			TileEntity tile = worldIn.getTileEntity(pos);
			NetworkHooks.openGui((ServerPlayerEntity) player, (AlchemyCaldronTileEntity)tile, pos);
		}
		return ActionResultType.SUCCESS;
	}
	
	
	@SuppressWarnings("deprecation")
	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			TileEntity tileEntity = worldIn.getTileEntity(pos);
			ItemStackHandler inventory = ((AlchemyCaldronTileEntity) tileEntity).inventoryStacks;
			for (int i = 0; i < inventory.getSlots(); i++) {
				InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(i));
			}
		}
		super.onReplaced(state, worldIn, pos, newState, isMoving);
	}
	
	
	@Override
	public boolean hasComparatorInputOverride(BlockState state) {
		return true;
	}
	
	
	@Override
	public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos) {
		return Container.calcRedstone(worldIn.getTileEntity(pos));
	}
	
	
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if(stateIn.get(ON_LIT)) {
			double d0 = (double)pos.getX() + 0.5D;
			double d1 = (double)pos.getY();
			double d2 = (double)pos.getZ() + 0.5D;
			if (rand.nextDouble() < 0.1D) {
				worldIn.playSound(d0, d1, d2, SoundEvents.BLOCK_SMOKER_SMOKE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
			}
			worldIn.addParticle(ParticleTypes.SMOKE, d0, d1 + 1.1D, d2, 0.0D, 0.0D, 0.0D);
			worldIn.addParticle(ParticleTypes.FLAME, d0, d1 + 1.1D, d2, 0.0D, 0.0D, 0.0D);
		}
	}
	
}
