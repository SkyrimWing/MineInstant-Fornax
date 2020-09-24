package skyric.mineInstant.item;

import java.util.function.Predicate;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.EnderPearlEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.item.EnderPearlItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShootableItem;
import net.minecraft.item.ThrowablePotionItem;
import net.minecraft.item.UseAction;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import skyric.mineInstant.entity.MarblePelletEntity;
import skyric.mineInstant.init.MineItemGroups;

public class SlingshotItem extends ShootableItem{
	
	public static final Predicate<ItemStack> SLINGSHOT_AMMO = (inventoryStack) -> {
		return inventoryStack.getItem() instanceof MarblePelletItem
			|| inventoryStack.getItem() instanceof EnderPearlItem 
			|| inventoryStack.getItem() instanceof ThrowablePotionItem;
	};
	
	
	public SlingshotItem() {
		super(new Item.Properties()
				.maxDamage(320)
				.group(MineItemGroups.MINE_TOOL_GROUP)
		);
		
	}
	
	
	@Override
	public Predicate<ItemStack> getInventoryAmmoPredicate() {
		return SLINGSHOT_AMMO;
	}
	
	
	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.BOW;
	}
	
	
	@Override
	public int getUseDuration(ItemStack stack) {
		return 72000;
	}
	
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		boolean flag = !playerIn.findAmmo(itemstack).isEmpty();
		
		ActionResult<ItemStack> ret = ForgeEventFactory.onArrowNock(itemstack, worldIn, playerIn, handIn, flag);
		if (ret != null) return ret;
		
		playerIn.setActiveHand(handIn);
		return ActionResult.resultConsume(itemstack);
	}
	
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
		if (entityLiving instanceof PlayerEntity) {
			PlayerEntity playerEntity = (PlayerEntity) entityLiving;
			ItemStack ammoStack = playerEntity.findAmmo(stack);
			
			int i = this.getUseDuration(stack) - timeLeft;
			i = ForgeEventFactory.onArrowLoose(stack, worldIn, playerEntity, i, true);
			if (i < 0) return;
			
			float velocity = getArrowVelocity(i);
			if ( velocity >= 0.1F ) {
				if (!worldIn.isRemote) {
					if(ammoStack.isEmpty()) {
						/////////////////////////////////////////////////////////////////////////////////////
					} else {
						Item ammoItem = ammoStack.getItem();
						
						if(ammoItem instanceof MarblePelletItem) {
							MarblePelletEntity marblePelletEntity = ((MarblePelletItem)ammoItem).createProjectile(worldIn, playerEntity, velocity);
							worldIn.addEntity(marblePelletEntity);
						} else if(ammoItem instanceof EnderPearlItem) {
							EnderPearlEntity enderPearlEntity = new EnderPearlEntity(worldIn, playerEntity);
							enderPearlEntity.setItem(ammoStack);
							enderPearlEntity.shoot(playerEntity, playerEntity.rotationPitch, playerEntity.rotationYaw, 0.0F, velocity * 3.0F, 0.5F);
							worldIn.addEntity(enderPearlEntity);
						} else if(ammoItem instanceof ThrowablePotionItem) {
							PotionEntity potionEntity = new PotionEntity(worldIn, playerEntity);
							potionEntity.setItem(ammoStack);
							potionEntity.shoot(playerEntity, playerEntity.rotationPitch, playerEntity.rotationYaw, 0.0F, velocity * 3.0F, 1.0F);
							worldIn.addEntity(potionEntity);
						}
						
						if (!playerEntity.abilities.isCreativeMode) {
							ammoStack.shrink(1);
							if (ammoStack.isEmpty()) {
								playerEntity.inventory.deleteStack(ammoStack);
							}
							stack.damageItem(1, playerEntity, (livingEntity) -> {
								livingEntity.sendBreakAnimation(playerEntity.getActiveHand());
			                  });
						}
					}
					playerEntity.addStat(Stats.ITEM_USED.get(this));
				}
			}
		}
	}
	
	
	public static float getArrowVelocity(int charge) {
		float f = (float)charge / 16.0F;
		f = (f * f + f * 2.0F) / 3.0F;
		if (f > 1.0F) {
			f = 1.0F;
		}
		return f;
	}

}
