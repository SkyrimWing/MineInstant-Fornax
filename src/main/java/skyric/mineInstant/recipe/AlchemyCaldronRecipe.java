package skyric.mineInstant.recipe;

import java.util.HashMap;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SuspiciousStewItem;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import skyric.mineInstant.init.MineItems;
import skyric.mineInstant.init.MineRecipeTypes;
import skyric.mineInstant.item.CuriousPuddingItem;

public class AlchemyCaldronRecipe implements IRecipe<IInventory>{
	
	public static final IRecipeType<AlchemyCaldronRecipe> ALCHEMY_CALDRON_RECIPE_TYPE
		= IRecipeType.register("alchemy_caldron_recipe");
	
	protected final IRecipeType<?> reipeType;
	protected final ResourceLocation recipeResource;
	final String group;
	final Ingredient ingredient;
	final Ingredient accessory;
	final int consume;
	final ItemStack result;
	final ItemStack byproduct;
	final float experience;
	public static HashMap<Effect, CuriousPuddingItem> puddingMap = new HashMap<Effect, CuriousPuddingItem>();
	
	public AlchemyCaldronRecipe(ResourceLocation recipeResource, String group, Ingredient ingredient, Ingredient accessory, int consume, ItemStack result, ItemStack byproduct, float experience){
		reipeType = ALCHEMY_CALDRON_RECIPE_TYPE;
		this.recipeResource = recipeResource;
		this.group = group;
		this.ingredient = ingredient;
		this.accessory = accessory;
		this.consume = consume;
		this.result = result;
		this.byproduct = byproduct;
		this.experience = experience;
	}
	

	@Override
	public boolean matches(IInventory inv, World worldIn) {
		if(inv.getSizeInventory() == 1) {
			if( ingredient.test(inv.getStackInSlot(0)) || accessory.test(inv.getStackInSlot(0)) ) {
				return true;
			} else {
				return false;
			}
		} else {
			if( ingredient.test(inv.getStackInSlot(0)) ) {
				if( accessory.test(inv.getStackInSlot(1)) ) {
					if( inv.getStackInSlot(1).getCount() >= consume ) {
						return true;
					}
				}
			}
			return false;
		}
	}
	
	public static ItemStack getCraftingResult(ItemStack itemStack, int j) {
		CompoundNBT compoundNBT = itemStack.getTag();
		
		if (compoundNBT != null && compoundNBT.contains("Effects", 9)) {
			ListNBT listNBT = compoundNBT.getList("Effects", 10);
			for(int i = 0; i < listNBT.size(); ++i) {
				CompoundNBT subNBT = listNBT.getCompound(i);
				Effect effect = Effect.get(subNBT.getByte("EffectId"));
				if(effect != null)
					return new ItemStack(puddingMap.get(effect));
			}
		}

		return null;
	}
	
	
	public Ingredient getMainIngredient() {
		return ingredient;
	}
	
	
	public Ingredient getAccessory() {
		return accessory;
	}
	
	public int getAccessoryConsume() {
		return consume;
	}
	
	
	public double getExperience() {
		return experience;
	}
	

	@Override
	public ItemStack getCraftingResult(IInventory inv) {
		if(inv.getStackInSlot(0).getItem() instanceof SuspiciousStewItem) {
			CompoundNBT compoundNBT = inv.getStackInSlot(0).getTag();
			if (compoundNBT != null && compoundNBT.contains("Effects", 9)) {
				ListNBT listNBT = compoundNBT.getList("Effects", 10);
				if(listNBT.size() > 0) {
					CompoundNBT subNBT = listNBT.getCompound(0);
					Effect effect = Effect.get(subNBT.getByte("EffectId"));
					if(effect != null) {
						return new ItemStack(puddingMap.get(effect));
					}
				}
			}
			return null;
		} else {
			return result.copy();
		}
	}
	
	
	public ItemStack getByproduct(IInventory inv) {
		return byproduct.copy();
	}
	

	@Override
	public boolean canFit(int width, int height) {
		return true;
	}
	

	@Override
	public ItemStack getRecipeOutput() {
		return new ItemStack(MineItems.CURIOUS_PUDDING_ALLIUM_ITEM.get());
	}
	

	@Override
	public ResourceLocation getId() {
		return recipeResource;
	}
	

	@Override
	public IRecipeSerializer<?> getSerializer() {
		return MineRecipeTypes.ALCHEMY_CALDRON_RECIPE_SERIALIZER.get();
	}
	

	@Override
	public IRecipeType<?> getType() {
		return reipeType;
	}
	
	
	@Override
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> list = NonNullList.create();
		list.add(ingredient);
		return list;
	}
	
	
	static {
		puddingMap.put(Effects.REGENERATION, MineItems.CURIOUS_PUDDING_OXEYE_DAISY_ITEM.get());
		puddingMap.put(Effects.JUMP_BOOST, MineItems.CURIOUS_PUDDING_CORNFLOWER_ITEM.get());
		puddingMap.put(Effects.POISON, MineItems.CURIOUS_PUDDING_LILY_OF_THE_VALLEY_ITEM.get());
		puddingMap.put(Effects.WITHER, MineItems.CURIOUS_PUDDING_WITHER_ROSE_ITEM.get());
		puddingMap.put(Effects.WEAKNESS, MineItems.CURIOUS_PUDDING_TULIP_ITEM.get());
		puddingMap.put(Effects.BLINDNESS, MineItems.CURIOUS_PUDDING_AZURE_BLUET_ITEM.get());
		puddingMap.put(Effects.FIRE_RESISTANCE, MineItems.CURIOUS_PUDDING_ALLIUM_ITEM.get());
		puddingMap.put(Effects.SATURATION, MineItems.CURIOUS_PUDDING_SATIETY_ITEM.get());
		puddingMap.put(Effects.NIGHT_VISION, MineItems.CURIOUS_PUDDING_APOPPY_ITEM.get());
	}

}
