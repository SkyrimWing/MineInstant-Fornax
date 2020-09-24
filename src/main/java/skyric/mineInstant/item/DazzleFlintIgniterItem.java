package skyric.mineInstant.item;

import java.util.function.Predicate;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ShootableItem;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import skyric.mineInstant.init.MineBlocks;
import skyric.mineInstant.init.MineItemGroups;

public class DazzleFlintIgniterItem extends ShootableItem
{
	public static final Predicate<ItemStack> DYES = (inventoryItemStack) -> {
		return inventoryItemStack.getItem() instanceof DyeItem;
	};

	public DazzleFlintIgniterItem() {
		super(new Item.Properties()
				.maxDamage(128)
				.group(MineItemGroups.MINE_TOOL_GROUP)
		);
	}
	
	
	public ActionResultType onItemUse(ItemUseContext context) {
		
		PlayerEntity playerentity = context.getPlayer();
		IWorld iworld = context.getWorld();
		BlockPos blockpos = context.getPos();
		BlockState blockstate = iworld.getBlockState(blockpos);
		ItemStack igniterItemStack = context.getItem();
		
		if (blockstate.getBlock() == Blocks.CAMPFIRE 
			&& !blockstate.get(BlockStateProperties.WATERLOGGED) 
			&& !blockstate.get(BlockStateProperties.LIT)
			) {
			
			iworld.playSound(playerentity, blockpos, 
								SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 
								1.0F, random.nextFloat() * 0.4F + 0.8F
							);
			iworld.setBlockState(blockpos, blockstate.with(BlockStateProperties.LIT, Boolean.valueOf(true)), 11);
			if (playerentity != null) {
				context.getItem().damageItem(1, playerentity, (playerInAction) -> {
					playerInAction.sendBreakAnimation(context.getHand());
				});
			}
			
			return ActionResultType.SUCCESS;
			
		} else {
			
			BlockPos blockPosFacing = blockpos.offset(context.getFace());
			ItemStack dyeStack = playerentity.findAmmo(igniterItemStack);
			
			if (canSetFire(iworld.getBlockState(blockPosFacing), iworld, blockPosFacing)) {
				iworld.playSound(playerentity, blockPosFacing,
									SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 
									1.0F, random.nextFloat() * 0.4F + 0.8F
								);
				iworld.setBlockState(blockPosFacing, 
										mapDazzleFire(dyeStack, iworld, blockPosFacing),
										11
									);
				
				if (playerentity instanceof ServerPlayerEntity) {
					CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity)playerentity, blockPosFacing, igniterItemStack);
					igniterItemStack.damageItem(1, playerentity, (playerInAction) -> {
						playerInAction.sendBreakAnimation(context.getHand());
					});
				}
				
				if (!playerentity.abilities.isCreativeMode) {
					dyeStack.shrink(1);
					if (dyeStack.isEmpty()) {
						playerentity.inventory.deleteStack(dyeStack);
					}
				}

				return ActionResultType.SUCCESS;
			} else {
	        	 return ActionResultType.FAIL;
			}
			
		}
		
	}
	
	
	private boolean canSetFire(BlockState existingState, IWorld worldIn, BlockPos posIn) {
		BlockState blockstate = ((FireBlock)Blocks.FIRE).getStateForPlacement(worldIn, posIn);
		boolean flag = false;
		
		for(Direction direction : Direction.Plane.HORIZONTAL) {
			BlockPos framePos = posIn.offset(direction);
			if (worldIn.getBlockState(framePos).isPortalFrame(worldIn, framePos) && ((NetherPortalBlock)Blocks.NETHER_PORTAL).isPortal(worldIn, posIn) != null) {
				flag = true;
			}
		}
		
		return existingState.isAir(worldIn, posIn) && (blockstate.isValidPosition(worldIn, posIn) || flag);
	}
	
	
	private BlockState mapDazzleFire(ItemStack dyeItemStack, IWorld worldIn, BlockPos posIn) {
		if(DYES.test(dyeItemStack)) {
			switch( ((DyeItem)dyeItemStack.getItem()).getDyeColor() ) {
				case WHITE: return MineBlocks.DAZZLE_FIRE_WHITE_BLOCK.get().getStateForPlacement(worldIn, posIn);
				case ORANGE: return MineBlocks.DAZZLE_FIRE_ORANGE_BLOCK.get().getStateForPlacement(worldIn, posIn);
				case MAGENTA: return MineBlocks.DAZZLE_FIRE_MAGENTA_BLOCK.get().getStateForPlacement(worldIn, posIn);
				case LIGHT_BLUE: return MineBlocks.DAZZLE_FIRE_LIGHT_BLUE_BLOCK.get().getStateForPlacement(worldIn, posIn);
				case YELLOW: return MineBlocks.DAZZLE_FIRE_YELLOW_BLOCK.get().getStateForPlacement(worldIn, posIn);
				case LIME: return MineBlocks.DAZZLE_FIRE_LIME_BLOCK.get().getStateForPlacement(worldIn, posIn);
				case PINK: return MineBlocks.DAZZLE_FIRE_PINK_BLOCK.get().getStateForPlacement(worldIn, posIn);
				case GRAY: return MineBlocks.DAZZLE_FIRE_GRAY_BLOCK.get().getStateForPlacement(worldIn, posIn);
				case LIGHT_GRAY: return MineBlocks.DAZZLE_FIRE_LIGHT_GRAY_BLOCK.get().getStateForPlacement(worldIn, posIn);
				case CYAN: return MineBlocks.DAZZLE_FIRE_CYAN_BLOCK.get().getStateForPlacement(worldIn, posIn);
				case PURPLE: return MineBlocks.DAZZLE_FIRE_PURPLE_BLOCK.get().getStateForPlacement(worldIn, posIn);
				case BLUE: return MineBlocks.DAZZLE_FIRE_BLUE_BLOCK.get().getStateForPlacement(worldIn, posIn);
				case BROWN: return MineBlocks.DAZZLE_FIRE_BROWN_BLOCK.get().getStateForPlacement(worldIn, posIn);
				case GREEN: return MineBlocks.DAZZLE_FIRE_GREEN_BLOCK.get().getStateForPlacement(worldIn, posIn);
				case RED: return MineBlocks.DAZZLE_FIRE_RED_BLOCK.get().getStateForPlacement(worldIn, posIn);
				case BLACK: return MineBlocks.DAZZLE_FIRE_BLACK_BLOCK.get().getStateForPlacement(worldIn, posIn);
				default: return MineBlocks.DAZZLE_FIRE_RED_BLOCK.get().getStateForPlacement(worldIn, posIn);
			}
		} else {
			return ((FireBlock)Blocks.FIRE).getStateForPlacement(worldIn, posIn);
		}
	}


	@Override
	public Predicate<ItemStack> getInventoryAmmoPredicate() {
		return DYES;
	}
	
	
	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
