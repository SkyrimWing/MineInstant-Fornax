package skyric.mineInstant.item;

import java.util.List;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import skyric.mineInstant.init.MineItemGroups;

public class AbstractMedicineItem extends Item{
	
	protected List<EffectInstance> effectInstances;

	public AbstractMedicineItem(List<EffectInstance> effectInstances) {
		super((new Item.Properties()).group(MineItemGroups.MINE_MATERIAL_GROUP));
		this.effectInstances = effectInstances;
	}
	
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		playerIn.setActiveHand(handIn);
		return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
	}
	
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		PlayerEntity playerentity = entityLiving instanceof PlayerEntity ? (PlayerEntity)entityLiving : null;
		if (playerentity instanceof ServerPlayerEntity) {
			CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayerEntity)playerentity, stack);
		}
		
		if (!worldIn.isRemote) {
			for(EffectInstance effectinstance : effectInstances) {
				if (effectinstance.getPotion().isInstant()) {
					effectinstance.getPotion().affectEntity(playerentity, playerentity, entityLiving, effectinstance.getAmplifier(), 1.0D);
				} else {
					entityLiving.addPotionEffect(new EffectInstance(effectinstance));
				}
			}
		}
		
		if (playerentity != null) {
			playerentity.addStat(Stats.ITEM_USED.get(this));
			if (!playerentity.abilities.isCreativeMode) {
				stack.shrink(1);
			}
		}
		
		return stack;
	}

}
