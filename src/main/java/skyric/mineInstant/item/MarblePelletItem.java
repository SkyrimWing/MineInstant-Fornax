package skyric.mineInstant.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import skyric.mineInstant.entity.MarblePelletEntity;
import skyric.mineInstant.init.MineItemGroups;

public class MarblePelletItem extends Item{

	public MarblePelletItem() {
		super(new Item.Properties().group(MineItemGroups.MINE_MATERIAL_GROUP));
	}
	
	
	public MarblePelletEntity createProjectile(World worldIn, PlayerEntity playerEntity, float velocity) {
		MarblePelletEntity marblePelletEntity = new MarblePelletEntity(playerEntity, worldIn);
		marblePelletEntity.shoot(playerEntity, playerEntity.rotationPitch, playerEntity.rotationYaw, 0.0f, velocity * 3.0f, 0.5f);
		return marblePelletEntity;
	}
}
