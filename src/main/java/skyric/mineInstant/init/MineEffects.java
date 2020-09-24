package skyric.mineInstant.init;

import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import skyric.mineInstant.MineInstant;
import skyric.mineInstant.effect.TriggerEffect;

public class MineEffects {
	
	public static final DeferredRegister<Effect> MINE_EFFECTS = new DeferredRegister<Effect>(ForgeRegistries.POTIONS, MineInstant.MODID);
	
	public static final RegistryObject<TriggerEffect> TRIGGER_FIRE_RESISTANCE_EFFECT
		= MINE_EFFECTS.register("trigger_fire_resistance_effect", () -> new TriggerEffect(Effects.FIRE_RESISTANCE));
	
	public static final RegistryObject<TriggerEffect> TRIGGER_POISON_EFFECT
		= MINE_EFFECTS.register("trigger_poison_effect", () -> new TriggerEffect(Effects.POISON));
	
	public static final RegistryObject<TriggerEffect> TRIGGER_SATURATION_EFFECT
	= MINE_EFFECTS.register("trigger_saturation_effect", () -> new TriggerEffect(Effects.SATURATION));

}
