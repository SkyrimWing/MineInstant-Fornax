package skyric.mineInstant.block;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SixWayBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.DyeColor;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class DazzleFireBlock extends AbstractFlamingBlock
{
	public static final BooleanProperty NORTH = SixWayBlock.NORTH;
	public static final BooleanProperty EAST = SixWayBlock.EAST;
	public static final BooleanProperty SOUTH = SixWayBlock.SOUTH;
	public static final BooleanProperty WEST = SixWayBlock.WEST;
	public static final BooleanProperty UP = SixWayBlock.UP;
	
	protected static final VoxelShape NORTH_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D);
	protected static final VoxelShape EAST_SHAPE = Block.makeCuboidShape(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape SOUTH_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape WEST_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D);
	protected static final VoxelShape UP_SHAPE = Block.makeCuboidShape(0.0D, 10.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	
	private final HashMap<BlockState, VoxelShape> STATE_TO_SHAPE_MAP;
	private final DyeColor dazzleFireColor;
	
	private static final Map<Direction, BooleanProperty> FACING_TO_PROPERTY_MAP 
		= SixWayBlock.FACING_TO_PROPERTY_MAP.entrySet().stream().filter((directionEntry) -> {
				return directionEntry.getKey() != Direction.DOWN;
			}).collect(Util.toMapCollector());
	
	
	public DazzleFireBlock(DyeColor dyeColor, int level) {
		
		super(Block.Properties.create(Material.FIRE, dyeColor.getMapColor())
					.doesNotBlockMovement()
					.lightValue(15)
					.sound(SoundType.CLOTH), 
				level
			);
		
		this.dazzleFireColor = dyeColor;
		this.setDefaultState(this.stateContainer.getBaseState()
								.with(NORTH, Boolean.valueOf(false))
								.with(EAST, Boolean.valueOf(false))
								.with(SOUTH, Boolean.valueOf(false))
								.with(WEST, Boolean.valueOf(false))
								.with(UP, Boolean.valueOf(false))
							);
		
		STATE_TO_SHAPE_MAP = new HashMap<BlockState, VoxelShape>();
		for(BlockState state: getStateContainer().getValidStates()) {
			VoxelShape shape = VoxelShapes.empty();
			if(state.get(UP)) {
				shape = VoxelShapes.or(shape, UP_SHAPE);
			}
			if(state.get(NORTH)) {
				shape = VoxelShapes.or(shape, NORTH_SHAPE);
			}
			if(state.get(EAST)) {
				shape = VoxelShapes.or(shape, EAST_SHAPE);
			}
			if(state.get(SOUTH)) {
				shape = VoxelShapes.or(shape, SOUTH_SHAPE);
			}
			if(state.get(WEST)) {
				shape = VoxelShapes.or(shape, WEST_SHAPE);
			}
			if( !(state.get(NORTH) || state.get(EAST) || state.get(SOUTH) || state.get(WEST) || state.get(UP)) ) {
				shape = VoxelShapes.fullCube();
			}
			STATE_TO_SHAPE_MAP.put(state, shape);
		}
		
	}
	
	
	@Override
	public boolean isBurning(BlockState state, IBlockReader world, BlockPos pos){
		return true;
	}
	
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(NORTH, EAST, SOUTH, WEST, UP);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return STATE_TO_SHAPE_MAP.get(state);
	}
	
	/*
	@Override
	public int tickRate(IWorldReader worldIn) {
		return 50;
	}
	*/
	
	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, 
											BlockState facingState, IWorld worldIn, 
											BlockPos currentPos, BlockPos facingPos
										) {
		return this.isValidPosition(stateIn, worldIn, currentPos) ? 
				getStateForPlacement(worldIn, currentPos) : Blocks.AIR.getDefaultState();
	}
	
	
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockPos blockpos = pos.down();
		return worldIn.getBlockState(blockpos).isSolidSide(worldIn, blockpos, Direction.UP) 
				|| this.areNeighborsFlammable(worldIn, pos);
	}
	
	
	@Override
	public BlockState getStateForPlacement(IWorld worldIn, BlockPos posIn) {
		BlockPos posOn = posIn.down();
		BlockState baseBlockState = worldIn.getBlockState(posOn);
		
		if (!this.canCatchFire(worldIn, posIn, Direction.UP) && !Block.hasSolidSide(baseBlockState, worldIn, posOn, Direction.UP)) {
			BlockState preBlockState = this.getDefaultState();
			for(Direction direction : Direction.values()) {
				BooleanProperty booleanproperty = FACING_TO_PROPERTY_MAP.get(direction);
				if (booleanproperty != null) {
					preBlockState = preBlockState.with(booleanproperty, 
															Boolean.valueOf(this.canCatchFire(worldIn, posIn.offset(direction), 
																				direction.getOpposite())
																			)
														);
				}
			}
			return preBlockState;
		} else {
			return this.getDefaultState();
		}
	}
	
	
	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (oldState.getBlock() != state.getBlock()) {
				if (!state.isValidPosition(worldIn, pos)) {
					worldIn.removeBlock(pos, false);
				} else {
					//worldIn.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(worldIn) + worldIn.rand.nextInt(200));
				}
		}
	}
	
	/*
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos posIn, Random random) {
		if (worldIn.getGameRules().getBoolean(GameRules.DO_FIRE_TICK)) {
			if (!worldIn.isAreaLoaded(posIn, 2)) return;
			if (!state.isValidPosition(worldIn, posIn)) {
				worldIn.removeBlock(posIn, false);
	         }
		}
	}
	*/
	
	public boolean canCatchFire(IBlockReader world, BlockPos pos, Direction face) {
		return world.getBlockState(pos).isFlammable(world, pos, face);
	}
	
	
	private boolean areNeighborsFlammable(IBlockReader worldIn, BlockPos pos) {
		for(Direction direction : Direction.values()) {
			if (this.canCatchFire(worldIn, pos.offset(direction), direction.getOpposite())) {
				return true;
			}
		}

		return false;
	}
	
	
	public DyeColor getColor() {
		return this.dazzleFireColor;
	}
}
