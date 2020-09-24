package skyric.mineInstant.recipe;

import com.google.gson.JsonObject;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class AlchemyCaldronRecipeSerializer<T extends AlchemyCaldronRecipe> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T>{
	
	private final IFactory<T> factory;
	
	public AlchemyCaldronRecipeSerializer(IFactory<T> factory){
		this.factory = factory;
	}

	@Override
	public T read(ResourceLocation recipeId, JsonObject json) {
		String jsonGroup = JSONUtils.getString(json, "group", "");
		
		Ingredient ingredient = Ingredient.deserialize(JSONUtils.getJsonObject(json, "ingredient"));
		Ingredient accessory = Ingredient.deserialize(JSONUtils.getJsonObject(json, "accessory"));
		
		int consume = JSONUtils.getInt(json, "consume");
		float experience = JSONUtils.getFloat(json, "experience", 0.0F);
		
		ItemStack resultStack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
		ItemStack byproductStack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "byproduct"));
		
		return this.factory.create(recipeId, jsonGroup, ingredient, accessory, consume, resultStack, byproductStack, experience);
	}

	
	@Override
	public T read(ResourceLocation recipeId, PacketBuffer buffer) {
		String jsonGroup = buffer.readString(32767);
		Ingredient ingredient = Ingredient.read(buffer);
		Ingredient accessory = Ingredient.read(buffer);
		int consume = buffer.readVarInt();
		ItemStack resultStack = buffer.readItemStack();
		ItemStack byproductStack = buffer.readItemStack();
		float experience = buffer.readFloat();
		
		return this.factory.create(recipeId, jsonGroup, ingredient, accessory, consume, resultStack, byproductStack, experience);
	}

	
	@Override
	public void write(PacketBuffer buffer, T recipe) {
		buffer.writeString(recipe.group);
		recipe.ingredient.write(buffer);
		recipe.accessory.write(buffer);
		buffer.writeInt(recipe.consume);
		buffer.writeItemStack(recipe.result);
		buffer.writeItemStack(recipe.byproduct);
		buffer.writeFloat(recipe.experience);
	}
	
	
	public interface IFactory<T extends AlchemyCaldronRecipe> {
		T create(ResourceLocation recipeResource, String group, Ingredient mainIngredient, Ingredient accessory, int consume, ItemStack result, ItemStack byproduct, float experience);
	}

}
