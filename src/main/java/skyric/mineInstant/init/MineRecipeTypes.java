package skyric.mineInstant.init;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import skyric.mineInstant.MineInstant;
import skyric.mineInstant.recipe.AlchemyCaldronRecipe;
import skyric.mineInstant.recipe.AlchemyCaldronRecipeSerializer;

public class MineRecipeTypes {
	
	public static final DeferredRegister<IRecipeSerializer<?>> MINE_RECIPES = new DeferredRegister<IRecipeSerializer<?>>(ForgeRegistries.RECIPE_SERIALIZERS, MineInstant.MODID);
	
	public static final RegistryObject<AlchemyCaldronRecipeSerializer<AlchemyCaldronRecipe>> ALCHEMY_CALDRON_RECIPE_SERIALIZER
		= MINE_RECIPES.register("alchemy_caldron_recipe_type"
								, () -> new AlchemyCaldronRecipeSerializer<>(AlchemyCaldronRecipe::new)
							);
	
}
