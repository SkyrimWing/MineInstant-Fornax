package skyric.mineInstant.tile;

import java.util.List;
import java.util.Optional;

import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.FurnaceTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import skyric.mineInstant.block.AlchemyCaldronBlock;
import skyric.mineInstant.container.AlchemyCaldronContainer;
import skyric.mineInstant.init.MineBlocks;
import skyric.mineInstant.init.MineTileEntityTypes;
import skyric.mineInstant.recipe.AlchemyCaldronRecipe;

public class AlchemyCaldronTileEntity extends LockableTileEntity implements ITickableTileEntity {
	
	public static final int FUEL_SLOT = 0;
	public static final int INGREDIENT_SLOT = 1;
	public static final int ACCESSORY_SLOT = 2;
	public static final int WORKING_SLOT_1 = 3;
	public static final int WORKING_SLOT_2 = 4;
	public static final int RESLUT_SLOT = 5;
	public static final int BYPRODUCT_SLOT = 6;
	
	public final ItemStackHandler inventoryStacks = new ItemStackHandler(7) {
		
		@Override
		public boolean isItemValid(int slot, ItemStack stack) {
			switch(slot) {
				case FUEL_SLOT: return FurnaceTileEntity.isFuel(stack);
				case INGREDIENT_SLOT:return isIngredient(stack);
				case ACCESSORY_SLOT: return isAccessory(stack);
				case WORKING_SLOT_1:
				case WORKING_SLOT_2:
				case RESLUT_SLOT:
				case BYPRODUCT_SLOT:
				default: return true;
			}
		}
		
		
		@Override
		protected void onContentsChanged(final int slot) {
			super.onContentsChanged(slot);
			AlchemyCaldronTileEntity.this.markDirty();
		}
		
	};
	
	private AlchemyCaldronTileState tileState;
	public short totalTime;
	public short fuelTime;
	public short finishTimer;
	public short burnTimer;
	
	private static final String INVENTORY_TAG = "inventory";
	private static final String STATE_TAG = "tileState";
	private static final String TOTAL_TAG = "totalTime";
	private static final String FUEL_TAG = "fuelTime";
	private static final String FINISH_TAG = "finishTime";
	private static final String BURN_TAG = "burnTime";
	
	
	private final LazyOptional<ItemStackHandler> inventoryCapabilityExternal = LazyOptional.of(() -> this.inventoryStacks);
	private final LazyOptional<IItemHandlerModifiable> inventoryCapabilityExternalDown 
		= LazyOptional.of( () -> new InvWrapper(AlchemyCaldronTileEntity.this) {
			
				@Override
				public ItemStack extractItem(int slot, int amount, boolean simulate) {
					if(slot == RESLUT_SLOT || slot == BYPRODUCT_SLOT) {
						return super.extractItem(slot, amount, simulate);
					} else {
						return ItemStack.EMPTY;
					}
				}
				
		});
	

	public AlchemyCaldronTileEntity() {
		super(MineTileEntityTypes.ALCHEMY_CALDRON_TILE.get());
		this.tileState = AlchemyCaldronTileState.IDLE;
		this.finishTimer = 0;
		this.burnTimer = 0;
		this.totalTime = 0;
	}
	
	
	@Override
	public int getSizeInventory() {
		return inventoryStacks.getSlots();
	}
	
	
	@Override
	public boolean isEmpty() {
		ItemStack itemStack;
		for(int i = 0; i < inventoryStacks.getSlots(); i++) {
			itemStack = inventoryStacks.getStackInSlot(i);
			if (!itemStack.isEmpty()) {
				return false;
			}
		}
		return true;
	}
	
	
	@Override
	public ItemStack getStackInSlot(int index) {
		return inventoryStacks.getStackInSlot(index);
	}
	
	
	@Override
	public ItemStack decrStackSize(int index, int count) {
		return inventoryStacks.extractItem(index, count, false);
	}
	
	
	@Override
	public ItemStack removeStackFromSlot(int index) {
		return inventoryStacks.extractItem(index, inventoryStacks.getStackInSlot(index).getCount(), false);
	}
	
	
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		this.inventoryStacks.setStackInSlot(index, stack);
		this.markDirty();
	}

	@Override
	public boolean isUsableByPlayer(PlayerEntity player) {
		if (this.world.getTileEntity(this.pos) != this) {
			return false;
		} else {
			return player.getDistanceSq(	(double)this.pos.getX() + 0.5D
											, (double)this.pos.getY() + 0.5D
											, (double)this.pos.getZ() + 0.5D
									) <= 64.0D;
		}
	}
	
	
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		switch(index) {
			case FUEL_SLOT: return FurnaceTileEntity.isFuel(stack);
			case INGREDIENT_SLOT:return isIngredient(stack);
			case ACCESSORY_SLOT: return isAccessory(stack);
			case WORKING_SLOT_1:
			case WORKING_SLOT_2:
			case RESLUT_SLOT:
			case BYPRODUCT_SLOT:
			default: return false;
		}
	}
	
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction facing) {
		if ( !this.removed && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY )
	         return inventoryCapabilityExternalDown.cast();
	      return super.getCapability(capability, facing);
	}
	
	
	@Override
	public void clear() {
		for(int i = 0; i < inventoryStacks.getSlots(); i++) {
			inventoryStacks.setStackInSlot(i, ItemStack.EMPTY);
		}
	}
	
	
	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent(MineBlocks.ALCHEMY_CALDRON_BLOCK.get().getTranslationKey());
	}
	
	
	protected boolean isIngredient(ItemStack stack) {
		for(AlchemyCaldronRecipe recipe : getAllRecipe(stack)) {
			if(recipe.getMainIngredient().test(stack)) {
				return true;
			}
		}
		
		return false;
	}
	
	
	protected boolean isAccessory(ItemStack stack) {
		for(AlchemyCaldronRecipe recipe : getAllRecipe(stack)) {
			if(recipe.getAccessory().test(stack)) {
				return true;
			}
		}
		
		return false;
	}
	
	
	
	protected short getRefiningTotalTime() {
		return 200;
	}
	
	
	protected List<AlchemyCaldronRecipe> getAllRecipe(ItemStack stack){
		return world.getRecipeManager().getRecipes(AlchemyCaldronRecipe.ALCHEMY_CALDRON_RECIPE_TYPE, new Inventory(stack), world);
	}
	
	
	protected Optional<AlchemyCaldronRecipe> getRecipe(IInventory inventory){
		return world.getRecipeManager().getRecipe(AlchemyCaldronRecipe.ALCHEMY_CALDRON_RECIPE_TYPE, inventory, world);
	}
	
	
	private Optional<AlchemyCaldronRecipe> cachedOptionalRecipe = Optional.ofNullable(null);
	private Item cachedIngredient = null;
	private Item cachedAccessory = null;
	private int cachedAccessoryComsume = 0;
	private int cachedPreviousAccessoryCount = 0;
	
	protected Optional<AlchemyCaldronRecipe> checkRecipe(ItemStack ingredientStack, ItemStack accessoryStack){
		if(ingredientStack.getItem() == cachedIngredient && accessoryStack.getItem() == cachedAccessory) {
			if(accessoryStack.getCount() >= cachedAccessoryComsume && cachedPreviousAccessoryCount >= accessoryStack.getCount()) {
				return cachedOptionalRecipe;
			}
		}
		
		Inventory inventory = new Inventory(ingredientStack, accessoryStack);
		cachedOptionalRecipe = getRecipe(inventory);
		cachedIngredient = ingredientStack.getItem();
		cachedAccessory = accessoryStack.getItem();
		
		if(cachedOptionalRecipe.isPresent()) {
			cachedAccessoryComsume = cachedOptionalRecipe.get().getAccessoryConsume();
		} else {
			cachedAccessoryComsume = 0;
			cachedPreviousAccessoryCount = accessoryStack.getCount();
		}
		
		return cachedOptionalRecipe;
	}
	
	
	protected Optional<AlchemyCaldronRecipe> checkRecipe(IInventory inventory){
		if(inventory.getStackInSlot(0).getItem() == cachedIngredient && inventory.getStackInSlot(1).getItem() == cachedAccessory) {
			return cachedOptionalRecipe;
		} else {
			return getRecipe(inventory);
		}
	}
	
	
	@Override
	public void tick() {///////////////////////////////////////////////////////experience
		
		if (world == null || world.isRemote)
			return;
		
		switch(tileState) {
			case IDLE: 
				if( inventoryStacks.getStackInSlot(FUEL_SLOT).isEmpty() ) {
					return;
				} else {
					Optional<AlchemyCaldronRecipe> optionalRecipe1
						= checkRecipe(	inventoryStacks.getStackInSlot(INGREDIENT_SLOT)
										, inventoryStacks.getStackInSlot(ACCESSORY_SLOT)
							);
					if(optionalRecipe1.isPresent()) {
						burnFuel();
						startNextRefining(optionalRecipe1.get());
						world.setBlockState(pos, this.getBlockState().with(AlchemyCaldronBlock.ON_LIT, true));
						this.tileState = AlchemyCaldronTileState.REFINING;
						return;
					} else {
						return;
					}
				}
			//---------- ---------- ---------- ---------- ---------- ---------- ---------- ---------- ---------- ----------
			case REFINING:
				if(finishTimer > 1) {
					if(burnTimer > 0) {
						finishTimer--;
						burnTimer--;
						return;
					} else {
						if( inventoryStacks.getStackInSlot(FUEL_SLOT).isEmpty() ) {
							world.setBlockState(pos, this.getBlockState().with(AlchemyCaldronBlock.ON_LIT, false));
							this.tileState = AlchemyCaldronTileState.PAUSE;
							return;
						} else {
							burnFuel();
							return;
						}
					}
				} else {
					Inventory inventory2 = new Inventory(   inventoryStacks.getStackInSlot(WORKING_SLOT_1)
																, inventoryStacks.getStackInSlot(WORKING_SLOT_2)
													);
					AlchemyCaldronRecipe workingRecipe2 = checkRecipe(inventory2).get();
					ItemStack result2 = workingRecipe2.getCraftingResult(inventory2);
					ItemStack byproduct2 = workingRecipe2.getByproduct(inventory2);
					
					if(burnTimer > 0) {
						burnTimer--;
						
						if(canProduce(result2, byproduct2)) {
							Optional<AlchemyCaldronRecipe> nextRecipe2A
								= checkRecipe(inventoryStacks.getStackInSlot(INGREDIENT_SLOT)
											, inventoryStacks.getStackInSlot(ACCESSORY_SLOT)
											);
							inventoryStacks.insertItem(RESLUT_SLOT, result2, false);
							inventoryStacks.insertItem(BYPRODUCT_SLOT, byproduct2, false);////////////////////////////////
							spawnExpOrbs(workingRecipe2.getExperience());
							if(nextRecipe2A.isPresent()) {
								startNextRefining(nextRecipe2A.get());
								return;
							} else {
								inventoryStacks.setStackInSlot(WORKING_SLOT_1, ItemStack.EMPTY);
								inventoryStacks.setStackInSlot(WORKING_SLOT_2, ItemStack.EMPTY);
								this.tileState = AlchemyCaldronTileState.RUN_IDLE;
								return;
							}
						} else {
							this.tileState = AlchemyCaldronTileState.RUN_STUCK;
							return;
						}
					} else {
						if(canProduce(result2, byproduct2)) {
							Optional<AlchemyCaldronRecipe> nextRecipe2B
								= checkRecipe(inventoryStacks.getStackInSlot(INGREDIENT_SLOT)
											, inventoryStacks.getStackInSlot(ACCESSORY_SLOT)
									);
								inventoryStacks.insertItem(RESLUT_SLOT, result2, false);
								inventoryStacks.insertItem(BYPRODUCT_SLOT, byproduct2, false);
								spawnExpOrbs(workingRecipe2.getExperience());
								if( nextRecipe2B.isPresent() && (!inventoryStacks.getStackInSlot(FUEL_SLOT).isEmpty()) ) {
									startNextRefining(nextRecipe2B.get());
									burnFuel();
									return;
								} else {
									inventoryStacks.setStackInSlot(WORKING_SLOT_1, ItemStack.EMPTY);
									inventoryStacks.setStackInSlot(WORKING_SLOT_2, ItemStack.EMPTY);
									this.tileState = AlchemyCaldronTileState.IDLE;
									world.setBlockState(pos, this.getBlockState().with(AlchemyCaldronBlock.ON_LIT, false));
									return;
								}
						} else {
							this.tileState = AlchemyCaldronTileState.IDLE_STUCK;
							world.setBlockState(pos, this.getBlockState().with(AlchemyCaldronBlock.ON_LIT, false));
							return;
						}
					}
				}
			//---------- ---------- ---------- ---------- ---------- ---------- ---------- ---------- ---------- ----------
			case RUN_STUCK:
				Inventory inventory3 = new Inventory( inventoryStacks.getStackInSlot(WORKING_SLOT_1)
														, inventoryStacks.getStackInSlot(WORKING_SLOT_2)
											);
				AlchemyCaldronRecipe workingRecipe3 = checkRecipe(inventory3).get();
				ItemStack result3 = workingRecipe3.getCraftingResult(inventory3);
				ItemStack byproduct3 = workingRecipe3.getByproduct(inventory3);
				if(burnTimer > 0) {
					burnTimer--;
					
					if(canProduce(result3, byproduct3)) {
						Optional<AlchemyCaldronRecipe> nextRecipe3A
						= checkRecipe(inventoryStacks.getStackInSlot(INGREDIENT_SLOT)
									, inventoryStacks.getStackInSlot(ACCESSORY_SLOT)
									);
						inventoryStacks.insertItem(RESLUT_SLOT, result3, false);
						inventoryStacks.insertItem(BYPRODUCT_SLOT, byproduct3, false);
						spawnExpOrbs(workingRecipe3.getExperience());
						if(nextRecipe3A.isPresent()) {
							startNextRefining(nextRecipe3A.get());
							this.tileState = AlchemyCaldronTileState.REFINING;
							return;
						} else {
							inventoryStacks.setStackInSlot(WORKING_SLOT_1, ItemStack.EMPTY);
							inventoryStacks.setStackInSlot(WORKING_SLOT_2, ItemStack.EMPTY);
							this.tileState = AlchemyCaldronTileState.RUN_IDLE;
							return;
						}
					} else {
						return;
					}
				} else {
						if(canProduce(result3, byproduct3)) {
						Optional<AlchemyCaldronRecipe> nextRecipe3B
							= checkRecipe(inventoryStacks.getStackInSlot(INGREDIENT_SLOT)
										, inventoryStacks.getStackInSlot(ACCESSORY_SLOT)
								);
						inventoryStacks.insertItem(RESLUT_SLOT, result3, false);
						inventoryStacks.insertItem(BYPRODUCT_SLOT, byproduct3, false);
						spawnExpOrbs(workingRecipe3.getExperience());
						if( nextRecipe3B.isPresent() && (!inventoryStacks.getStackInSlot(FUEL_SLOT).isEmpty()) ) {
							startNextRefining(nextRecipe3B.get());
							burnFuel();
							this.tileState = AlchemyCaldronTileState.REFINING;
							return;
						} else {
							inventoryStacks.setStackInSlot(WORKING_SLOT_1, ItemStack.EMPTY);
							inventoryStacks.setStackInSlot(WORKING_SLOT_2, ItemStack.EMPTY);
							world.setBlockState(pos, this.getBlockState().with(AlchemyCaldronBlock.ON_LIT, false));
							this.tileState = AlchemyCaldronTileState.IDLE;
							return;
						}
					} else {
						world.setBlockState(pos, this.getBlockState().with(AlchemyCaldronBlock.ON_LIT, false));
						this.tileState = AlchemyCaldronTileState.IDLE_STUCK;
						return;
					}
				}
			//---------- ---------- ---------- ---------- ---------- ---------- ---------- ---------- ---------- ----------
			case RUN_IDLE:
				if(burnTimer > 0) {
					burnTimer--;
					
					Optional<AlchemyCaldronRecipe> nextRecipe4
					=  checkRecipe(	inventoryStacks.getStackInSlot(INGREDIENT_SLOT)
									, inventoryStacks.getStackInSlot(ACCESSORY_SLOT)
								);
					if(nextRecipe4.isPresent()) {
						startNextRefining(nextRecipe4.get());
						this.tileState = AlchemyCaldronTileState.REFINING;
						return;
					} else {
						return;
					}
				} else {
					inventoryStacks.setStackInSlot(WORKING_SLOT_1, ItemStack.EMPTY);
					inventoryStacks.setStackInSlot(WORKING_SLOT_2, ItemStack.EMPTY);
					world.setBlockState(pos, this.getBlockState().with(AlchemyCaldronBlock.ON_LIT, false));
					this.tileState = AlchemyCaldronTileState.IDLE;
					return;
				}
			//---------- ---------- ---------- ---------- ---------- ---------- ---------- ---------- ---------- ----------
			case IDLE_STUCK:
				Inventory inventory5 = new Inventory(   inventoryStacks.getStackInSlot(WORKING_SLOT_1)
															, inventoryStacks.getStackInSlot(WORKING_SLOT_2)
												);
				AlchemyCaldronRecipe workingRecipe5 = checkRecipe(inventory5).get();
				ItemStack result5 = workingRecipe5.getCraftingResult(inventory5);
				ItemStack byproduct5 = workingRecipe5.getByproduct(inventory5);
				if(canProduce(result5, byproduct5)) {
					Optional<AlchemyCaldronRecipe> nextRecipe5
					= checkRecipe(inventoryStacks.getStackInSlot(INGREDIENT_SLOT)
								, inventoryStacks.getStackInSlot(ACCESSORY_SLOT)
								);
			
					inventoryStacks.insertItem(RESLUT_SLOT, result5, false);
					inventoryStacks.insertItem(BYPRODUCT_SLOT, byproduct5, false);
					spawnExpOrbs(workingRecipe5.getExperience());
					if( nextRecipe5.isPresent() && (!inventoryStacks.getStackInSlot(FUEL_SLOT).isEmpty()) ) {
						startNextRefining(nextRecipe5.get());
						burnFuel();
						world.setBlockState(pos, this.getBlockState().with(AlchemyCaldronBlock.ON_LIT, true));
						this.tileState = AlchemyCaldronTileState.REFINING;
						return;
					} else {
						inventoryStacks.setStackInSlot(WORKING_SLOT_1, ItemStack.EMPTY);
						inventoryStacks.setStackInSlot(WORKING_SLOT_2, ItemStack.EMPTY);
						this.tileState = AlchemyCaldronTileState.IDLE;
						return;
					}
				} else {
					return;
				}
			//---------- ---------- ---------- ---------- ---------- ---------- ---------- ---------- ---------- ----------
			case PAUSE:
				if( inventoryStacks.getStackInSlot(FUEL_SLOT).isEmpty() ) {
					return;
				} else {
					burnFuel();
					world.setBlockState(pos, this.getBlockState().with(AlchemyCaldronBlock.ON_LIT, true));
					this.tileState = AlchemyCaldronTileState.REFINING;
					return;
				}
			//---------- ---------- ---------- ---------- ---------- ---------- ---------- ---------- ---------- ----------
			default: this.tileState = AlchemyCaldronTileState.IDLE;return;
		}
		
	}	// tick()
	
	
	protected boolean canProduce(ItemStack resultStack, ItemStack byproductStack) {
		ItemStack outputStack1 = inventoryStacks.getStackInSlot(RESLUT_SLOT);
		ItemStack outputStack2 = inventoryStacks.getStackInSlot(BYPRODUCT_SLOT);
		int outputLimit1 = inventoryStacks.getSlotLimit(RESLUT_SLOT);
		int outputLimit2 = inventoryStacks.getSlotLimit(BYPRODUCT_SLOT);

		if(	(outputStack1.getItem() == resultStack.getItem() || outputStack1.isEmpty())
			&& (outputStack2.getItem() == byproductStack.getItem() || outputStack2.isEmpty())
			&& (outputLimit1 > resultStack.getCount() + outputStack1.getCount())
			&& (outputLimit2 > byproductStack.getCount() + outputStack2.getCount())
		) {
			return true;
		} else {
			return false;
		}
	}
	
	
	protected void startNextRefining(AlchemyCaldronRecipe nextRecipe) {
		int accessoryConsume = nextRecipe.getAccessoryConsume();
		this.inventoryStacks.setStackInSlot(WORKING_SLOT_1, inventoryStacks.extractItem(INGREDIENT_SLOT, 1, false));
		this.inventoryStacks.setStackInSlot(WORKING_SLOT_2, inventoryStacks.extractItem(ACCESSORY_SLOT, accessoryConsume, false));
		this.totalTime = getRefiningTotalTime();
		this.finishTimer = totalTime;
	}
	
	
	protected void burnFuel() {
		ItemStack fuelStack = inventoryStacks.getStackInSlot(FUEL_SLOT);
		this.fuelTime = (short) ForgeHooks.getBurnTime(fuelStack);
		this.burnTimer = fuelTime;
		if(fuelStack.hasContainerItem()) {
			if(fuelStack.getCount() == 1) {
				this.inventoryStacks.setStackInSlot(FUEL_SLOT, fuelStack.getContainerItem());
				/////////////////////////////////////////////////////////////////////////////////next:enable auto
			} else {
				ItemEntity itementity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ());
				this.world.addEntity(itementity);
				this.inventoryStacks.extractItem(FUEL_SLOT, 1, false);
			}
		} else {
			this.inventoryStacks.extractItem(FUEL_SLOT, 1, false);
		}
	}
	
	
	protected void spawnExpOrbs(double exp) {
		if(world.isRemote) {
			return;
		}
		
		int expFloor = (int) exp;
		exp = exp - (int)exp;
		
		while(expFloor > 0) {
			int i = ExperienceOrbEntity.getXPSplit(expFloor);
			expFloor -= i;
			world.addEntity(new ExperienceOrbEntity(world, pos.getX(), pos.getY() + 0.5D, pos.getZ(), i));
		}
		
		if(exp >= 0.001) {
			double r = Math.random();
			if(r <= exp) {
				world.addEntity(new ExperienceOrbEntity(world, pos.getX(), pos.getY() + 0.5D, pos.getZ(), 1));
			}
		}
	}
	
	
	@Override
	public void read(CompoundNBT compoundNBT) {
		super.read(compoundNBT);
		this.inventoryStacks.deserializeNBT(compoundNBT.getCompound(INVENTORY_TAG));
		this.tileState = AlchemyCaldronTileState.values()[compoundNBT.getByte(STATE_TAG)];
		this.totalTime = compoundNBT.getShort(TOTAL_TAG);
		this.fuelTime = compoundNBT.getShort(FUEL_TAG);
		this.finishTimer = compoundNBT.getShort(FINISH_TAG);
		this.burnTimer = compoundNBT.getShort(BURN_TAG);
	}
	
	
	@Override
	public CompoundNBT write(CompoundNBT compoundNBT) {
		super.write(compoundNBT);
		compoundNBT.put(INVENTORY_TAG, inventoryStacks.serializeNBT());
		compoundNBT.putByte(STATE_TAG, (byte)tileState.ordinal());
		compoundNBT.putShort(TOTAL_TAG, totalTime);
		compoundNBT.putShort(FUEL_TAG, fuelTime);
		compoundNBT.putShort(FINISH_TAG, finishTimer);
		compoundNBT.putShort(BURN_TAG, burnTimer);
		return compoundNBT;
	}
	
	
	@Override
	public CompoundNBT getUpdateTag() {
		return write(new CompoundNBT());
	}
	
	
	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent(MineBlocks.ALCHEMY_CALDRON_BLOCK.get().getTranslationKey());
	}
	
	
	@Override
	public Container createMenu(int windowId, PlayerInventory playerInventory) {
		return new AlchemyCaldronContainer(windowId, playerInventory, this);
	}
	
	
	@Override
	public void remove() {
		super.remove();
		inventoryCapabilityExternal.invalidate();
	}
	
	
	public byte getStateInByte() {
		return (byte)tileState.ordinal();
	}
	
	
	public void clientSetState(int stateOrdinal) {
		this.tileState = AlchemyCaldronTileState.values()[stateOrdinal];
	}
	
	
	public boolean isBurning() {
		return tileState == AlchemyCaldronTileState.REFINING 
				|| tileState == AlchemyCaldronTileState.RUN_IDLE
				|| tileState == AlchemyCaldronTileState.RUN_STUCK;
	}
	
	
	public boolean onRefining() {
		return tileState == AlchemyCaldronTileState.REFINING || tileState == AlchemyCaldronTileState.PAUSE;
	}
	
	
	public double getRefiningProgress() {
		if(tileState == AlchemyCaldronTileState.REFINING || tileState == AlchemyCaldronTileState.PAUSE) {
			return finishTimer / 20.0;
		} else {
			return 0;
		}
	}
	
	
	public double getFuelRemain() {
		return burnTimer / 20.0;
	}
	
	
	private static enum AlchemyCaldronTileState{
		IDLE,
		REFINING,
		RUN_IDLE,
		RUN_STUCK,
		IDLE_STUCK,
		PAUSE;
	}

}
