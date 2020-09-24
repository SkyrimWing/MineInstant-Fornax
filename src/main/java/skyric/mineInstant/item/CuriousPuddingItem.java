package skyric.mineInstant.item;

import java.util.HashMap;

import net.minecraft.item.Food;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import skyric.mineInstant.effect.TriggerEffect;
import skyric.mineInstant.init.MineEffects;

public class CuriousPuddingItem extends AbstractFoodItem {
	
	public static final HashMap<Effect, RegistryObject<TriggerEffect>> TRIGGER_EFFECT_MAP
		= new HashMap<Effect, RegistryObject<TriggerEffect>>();
	
	static {
		TRIGGER_EFFECT_MAP.put(Effects.REGENERATION, MineEffects.TRIGGER_FIRE_RESISTANCE_EFFECT);
		TRIGGER_EFFECT_MAP.put(Effects.JUMP_BOOST, MineEffects.TRIGGER_FIRE_RESISTANCE_EFFECT);
		TRIGGER_EFFECT_MAP.put(Effects.WITHER, MineEffects.TRIGGER_FIRE_RESISTANCE_EFFECT);
		TRIGGER_EFFECT_MAP.put(Effects.WEAKNESS, MineEffects.TRIGGER_FIRE_RESISTANCE_EFFECT);
		TRIGGER_EFFECT_MAP.put(Effects.BLINDNESS, MineEffects.TRIGGER_FIRE_RESISTANCE_EFFECT);
		TRIGGER_EFFECT_MAP.put(Effects.FIRE_RESISTANCE, MineEffects.TRIGGER_FIRE_RESISTANCE_EFFECT);
		TRIGGER_EFFECT_MAP.put(Effects.SATURATION, MineEffects.TRIGGER_SATURATION_EFFECT);
		TRIGGER_EFFECT_MAP.put(Effects.NIGHT_VISION, MineEffects.TRIGGER_FIRE_RESISTANCE_EFFECT);
		
		TRIGGER_EFFECT_MAP.put(Effects.FIRE_RESISTANCE, MineEffects.TRIGGER_FIRE_RESISTANCE_EFFECT);
		TRIGGER_EFFECT_MAP.put(Effects.POISON, MineEffects.TRIGGER_POISON_EFFECT);
	}

	public CuriousPuddingItem(Effect effect, int tick) {
		super(
				(new Food.Builder())
					.hunger(2).saturation(0.75F).fastToEat()
					.effect(
						() -> new EffectInstance(TRIGGER_EFFECT_MAP.get(effect).get(), 32145, 0, false, false, true)
						, 1.0F 
					)
					.build() 
			);
		;
	}
	
	/*
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add((new TranslationTextComponent(getDefaultTranslationKey() + ".effect")).applyTextStyle(TextFormatting.GRAY));
	}
	*/

}
