package skyric.mineInstant.effect;

import java.util.HashMap;
import java.util.function.BiPredicate;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraft.util.FoodStats;

public class TriggerEffect extends Effect{
	
	public static final HashMap<Effect, BiPredicate<LivingEntity, EffectInstance>> TRIGGER_PREDICATE 
		= new HashMap<Effect, BiPredicate<LivingEntity, EffectInstance>>();
	protected Effect innerEffect;
	
	static {
		TRIGGER_PREDICATE.put(
			Effects.FIRE_RESISTANCE, 
			(livingEntity, effectInstance) -> {
				if(livingEntity.isInLava() || livingEntity.isBurning()) {
					if(livingEntity.getActivePotionEffect(Effects.FIRE_RESISTANCE) == null) {
						livingEntity.addPotionEffect(effectInstance);
						return true;
					}
				}
				return false;
			}
		);
		
		TRIGGER_PREDICATE.put(
			Effects.POISON, 
			(livingEntity, effectInstance) -> {
				if(livingEntity.getHealth() < livingEntity.getMaxHealth() * 0.6) {
					if(livingEntity.getActivePotionEffect(Effects.POISON) == null) {
						livingEntity.addPotionEffect(effectInstance);
						return true;
					}
				}
				return false;
			}
		);
		
		TRIGGER_PREDICATE.put(
				Effects.SATURATION, 
				(livingEntity, effectInstance) -> {
					if(!(livingEntity instanceof PlayerEntity)) {
						return true;
					}
					FoodStats foodStats = ((PlayerEntity)livingEntity).getFoodStats();
					if(foodStats.getFoodLevel() < 20 * 0.6) {
						if(livingEntity.getActivePotionEffect(Effects.SATURATION) == null) {
							livingEntity.addPotionEffect(new EffectInstance(Effects.SATURATION, 30));
							return true;
						}
					}
					return false;
				}
			);
	}

	public TriggerEffect(Effect innerEffect) {
		super(EffectType.NEUTRAL, 16777215);
		this.innerEffect = innerEffect;
	}
	
	
	@Override
	public boolean isReady(int duration, int amplifier) {
		return true;
	}
	
	
	@Override
	public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
		if(TRIGGER_PREDICATE.containsKey(innerEffect)) {
			if(TRIGGER_PREDICATE.get(innerEffect).test(entityLivingBaseIn, new EffectInstance(innerEffect, 200))) {
				entityLivingBaseIn.removePotionEffect(this);
			}
		}
	}
	
	/*
	@Override
	public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
		EffectInstance activeEffect = entityLivingBaseIn.getActivePotionEffect(this);
		EffectInstance hiddenEffect = null;
		
		try {
			Field hiddenEffectField = activeEffect.getClass().getDeclaredField(FIELD_HIDDEN_EFFECT_ID);
			hiddenEffectField.setAccessible(true);
			hiddenEffect = (EffectInstance) hiddenEffectField.get(activeEffect);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		System.out.println(hiddenEffect);
		if(hiddenEffect != null) {
			System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
			if(TRIGGER_PREDICATE.containsKey(hiddenEffect.getPotion())) {
				if(TRIGGER_PREDICATE.get(hiddenEffect.getPotion()).test(entityLivingBaseIn, hiddenEffect)) {
					System.out.println("ccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc");
					entityLivingBaseIn.removeActivePotionEffect(this);
				} else {
					System.out.println("dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
					try {
						Field durationField = hiddenEffect.getClass().getDeclaredField(FIELD_DURATION_ID);
						durationField.setAccessible(true);
						durationField.setInt(hiddenEffect, 200);
					} catch (NoSuchFieldException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	*/
	/*
	public static class TriggerEffectInstance extends EffectInstance{
		
		protected static final Logger LOGGER = LogManager.getLogger();
		
		protected static final String FIELD_AMPLIFIER_ID = "field_193466_a";
		protected static final String FIELD_DURATION_ID = "field_193466_b";
		
		protected static final int MAX_READY_LIST_SIZE = 10;
		protected static final int MAX_TRIGGER_SIZE = 5;
		
		protected LinkedList<EffectInstance> readyEffectList;
		
		public TriggerEffectInstance(int durationIn, int amplifierIn, EffectInstance... readyEffect) {
			super(MineEffects.TRIGGER_EFFECT.get(), durationIn, amplifierIn, false, false, true, null);
			readyEffectList = new LinkedList<EffectInstance>();
			Collections.addAll(readyEffectList, readyEffect);
		}
		
		
		public LinkedList<EffectInstance> getReadyEffectList(){
			return readyEffectList;
		}
		
		
		@Override
		public boolean combine(EffectInstance other) {
			
			if(!getPotion().equals(other.getPotion())) {
				LOGGER.warn("This method should only be called for matching effects!");
			}
			
			if(!(other instanceof TriggerEffectInstance)) {
				return false;
			}
			
			int newDuration = other.getDuration();
			int newAmpilfier = other.getAmplifier();
			
			if(newDuration > getDuration()) {
				try {
					Field durationParam = getClass().getSuperclass().getField(FIELD_DURATION_ID);
					durationParam.setAccessible(true);
					durationParam.setInt(this, newDuration);
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			
			if(newAmpilfier > getAmplifier()) {
				try {
					Field amplifierParam = getClass().getSuperclass().getField(FIELD_AMPLIFIER_ID);
					amplifierParam.setAccessible(true);
					amplifierParam.setInt(this, newAmpilfier);
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			
			LinkedList<EffectInstance> listToMerge = ((TriggerEffectInstance)other).getReadyEffectList();
			int maxEffectQuantity = Math.min(getAmplifier(), MAX_READY_LIST_SIZE);
			if(readyEffectList.size() + listToMerge.size() > maxEffectQuantity) {
				while(listToMerge.size() < maxEffectQuantity) {
					listToMerge.add(readyEffectList.removeLast());
				}
				readyEffectList = listToMerge;
			} else {
				while(listToMerge.size() > 0) {
					readyEffectList.add(listToMerge.removeFirst());
				}
			}
			
			return true;
			
		}
		
		
		@Override
		public void performEffect(LivingEntity entityIn) {
			int count = 0;
			for(Iterator<EffectInstance> iterEffect = readyEffectList.iterator(); iterEffect.hasNext();) {
				EffectInstance instance = iterEffect.next();
				if(TRIGGER_PREDICATE.containsKey(instance.getPotion())) {
					if(TRIGGER_PREDICATE.get(instance.getPotion()).test(entityIn, instance)) {
						iterEffect.remove();
						count++;
					}
				}
				if(count > MAX_TRIGGER_SIZE) {
					break;
				}
			}
			if(readyEffectList.size() < 1) {
				try {
					Field durationParam = getClass().getSuperclass().getField(FIELD_DURATION_ID);
					durationParam.setAccessible(true);
					durationParam.setInt(this, 0);
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		@Override
		public CompoundNBT write(CompoundNBT nbt) {
			super.write(nbt);
			int i = 0;
			for(EffectInstance readyEffect : readyEffectList) {
				CompoundNBT innerNBT = new CompoundNBT();
				readyEffect.write(innerNBT);
				nbt.put("ReadyEffect" + i, innerNBT);
				i++;
			}
			return nbt;
		}
		
		
	}
	*/

}
