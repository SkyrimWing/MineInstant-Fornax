package skyric.mineInstant.container;

import javax.annotation.Nonnull;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.items.SlotItemHandler;
import skyric.mineInstant.init.MineBlocks;
import skyric.mineInstant.init.MineContainerTypes;
import skyric.mineInstant.tile.AlchemyCaldronTileEntity;


public class AlchemyCaldronContainer extends Container{
	
	public final AlchemyCaldronTileEntity tileEntity;
	private final IWorldPosCallable canInteractWithCallable;

	public AlchemyCaldronContainer(int windowId, PlayerInventory playerInventory, PacketBuffer data) {
		this(windowId, playerInventory, getTileEntity(playerInventory, data));
	}
	
	
	public AlchemyCaldronContainer(int windowId, PlayerInventory playerInventory, AlchemyCaldronTileEntity tileEntity) {
		super(MineContainerTypes.ALCHEMY_CALDRON_CONTAINER.get(), windowId);
		this.tileEntity = tileEntity;
		this.canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());
		
		this.trackInt( new ContainerIntReferenceHolder(() -> tileEntity.totalTime, v -> tileEntity.totalTime = (short) v) );
		this.trackInt( new ContainerIntReferenceHolder(() -> tileEntity.fuelTime, v -> tileEntity.fuelTime = (short) v) );
		this.trackInt( new ContainerIntReferenceHolder(() -> tileEntity.finishTimer, v -> tileEntity.finishTimer = (short) v) );
		this.trackInt( new ContainerIntReferenceHolder(() -> tileEntity.burnTimer, v -> tileEntity.burnTimer = (short) v) );
		this.trackInt( new ContainerIntReferenceHolder(() -> tileEntity.getStateInByte(), v -> tileEntity.clientSetState(v)) );
		
		this.addSlot( new SlotItemHandler(tileEntity.inventoryStacks, AlchemyCaldronTileEntity.FUEL_SLOT, 80, 53) );
		this.addSlot( new SlotItemHandler(tileEntity.inventoryStacks, AlchemyCaldronTileEntity.INGREDIENT_SLOT, 16, 35) );
		this.addSlot( new SlotItemHandler(tileEntity.inventoryStacks, AlchemyCaldronTileEntity.ACCESSORY_SLOT, 36, 35) );
		this.addSlot( new SlotItemHandler(tileEntity.inventoryStacks, AlchemyCaldronTileEntity.WORKING_SLOT_1, 70, 16) {
			@Override
		    public boolean isItemValid(@Nonnull ItemStack stack){
				return false;
		    }
			@Override
		    public boolean canTakeStack(PlayerEntity playerIn){
				return false;
		    }
		});
		this.addSlot( new SlotItemHandler(tileEntity.inventoryStacks, AlchemyCaldronTileEntity.WORKING_SLOT_2, 90, 17) {
			@Override
		    public boolean isItemValid(@Nonnull ItemStack stack){
				return false;
		    }
			@Override
		    public boolean canTakeStack(PlayerEntity playerIn){
				return false;
		    }
		});
		this.addSlot( new SlotItemHandler(tileEntity.inventoryStacks, AlchemyCaldronTileEntity.RESLUT_SLOT, 124, 35) {
			@Override
		    public boolean isItemValid(@Nonnull ItemStack stack){
				return false;
		    }
		});
		this.addSlot( new SlotItemHandler(tileEntity.inventoryStacks, AlchemyCaldronTileEntity.BYPRODUCT_SLOT, 144, 35) {
			@Override
		    public boolean isItemValid(@Nonnull ItemStack stack){
				return false;
		    }
		});
		
		final int playerInventoryStartX = 8;
		final int playerInventoryStartY = 84;
		final int slotSizePlus2 = 18;
		for (int row = 0; row < 3; ++row) {
			for (int column = 0; column < 9; ++column) {
				this.addSlot(new Slot(playerInventory, 9 + (row * 9) + column, playerInventoryStartX + (column * slotSizePlus2), playerInventoryStartY + (row * slotSizePlus2)));
			}
		}
		
		final int playerHotbarY = playerInventoryStartY + slotSizePlus2 * 3 + 4;
		for (int column = 0; column < 9; ++column) {
			this.addSlot(new Slot(playerInventory, column, playerInventoryStartX + (column * slotSizePlus2), playerHotbarY));
		}
	}
	
	
	
	private static AlchemyCaldronTileEntity getTileEntity(PlayerInventory playerInventory, PacketBuffer data) {
		
		if(playerInventory != null && data != null) {
			TileEntity tileAtPos = playerInventory.player.world.getTileEntity(data.readBlockPos());
			return (AlchemyCaldronTileEntity) tileAtPos;
		} else {
			throw new IllegalStateException("Alchemy Caldron Tile Packet Error!");
		}
	}
	
	
	@Override
	public ItemStack transferStackInSlot(PlayerEntity player, int index) {
		ItemStack returnStack = ItemStack.EMPTY;
		final Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			final ItemStack slotStack = slot.getStack();
			returnStack = slotStack.copy();

			final int containerSlots = this.inventorySlots.size() - player.inventory.mainInventory.size();
			if (index < containerSlots) {
				if (!mergeItemStack(slotStack, containerSlots, this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!mergeItemStack(slotStack, 0, containerSlots, false)) {
				return ItemStack.EMPTY;
			}
			
			if (slotStack.getCount() == 0) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
			
			if (slotStack.getCount() == returnStack.getCount()) {
				return ItemStack.EMPTY;
			}
			
			slot.onTake(player, slotStack);
		}
		return returnStack;
	}
	

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return isWithinUsableDistance(canInteractWithCallable, playerIn, MineBlocks.ALCHEMY_CALDRON_BLOCK.get());
	}

}
