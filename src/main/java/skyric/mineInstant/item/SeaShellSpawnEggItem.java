package skyric.mineInstant.item;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.Lazy;
import skyric.mineInstant.entity.SeaShellEntity;
import skyric.mineInstant.init.MineEntityTypes;
import skyric.mineInstant.init.MineItemGroups;

public class SeaShellSpawnEggItem extends SpawnEggItem{
	
	private final Lazy<? extends EntityType<SeaShellEntity>> entityTypeSupplier;
	

	public SeaShellSpawnEggItem() {
		super(null, 15198183, 16758197, new Item.Properties().group(MineItemGroups.MINE_MATERIAL_GROUP));
		this.entityTypeSupplier = Lazy.concurrentOf(MineEntityTypes.SEA_SHELL_ENTITY);
	}
	
	
	@Override
	public EntityType<?> getType(@Nullable CompoundNBT p_208076_1_) {
		return entityTypeSupplier.get();
	}

}
