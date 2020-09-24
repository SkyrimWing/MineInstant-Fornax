package skyric.mineInstant.init;

import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potions;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import skyric.mineInstant.MineInstant;
import skyric.mineInstant.item.CuriousPuddingItem;
import skyric.mineInstant.item.DazzleFlintIgniterItem;
import skyric.mineInstant.item.MarblePelletItem;
import skyric.mineInstant.item.MilkSliceItem;
import skyric.mineInstant.item.MineBucketItem;
import skyric.mineInstant.item.MineIngotItem;
import skyric.mineInstant.item.MineNuggetItem;
import skyric.mineInstant.item.SeaShellSpawnEggItem;
import skyric.mineInstant.item.SlingshotItem;
import skyric.mineInstant.item.TabletItem;


public class MineItems 
{
	public static final DeferredRegister<Item> MINE_ITEMS = new DeferredRegister<Item>(ForgeRegistries.ITEMS, MineInstant.MODID);
	
	public static final RegistryObject<MineIngotItem> SILVER_INGOT_ITEM 
		= MINE_ITEMS.register( "silver_ingot_item"
							, () -> new MineIngotItem(new Item.Properties())
						);
	
	public static final RegistryObject<MineNuggetItem> SILVER_NUGGET_ITEM 
		= MINE_ITEMS.register( "silver_nugget_item"
							, () -> new MineNuggetItem(new Item.Properties())
						);
	
	
	public static final RegistryObject<MarblePelletItem> MARBLE_PELLET_ITEM 
		= MINE_ITEMS.register("marble_pellet_item", () -> new MarblePelletItem());
	
	public static final RegistryObject<Item> SLIME_YARN_BALL 
		= MINE_ITEMS.register("slime_yarn_ball_item"
							, () -> new Item(new Item.Properties().group(MineItemGroups.MINE_MATERIAL_GROUP))
						);
	
	public static final RegistryObject<Item> REFINED_SLIME_YARN_BALL 
		= MINE_ITEMS.register("refined_slime_yarn_ball_item"
							, () -> new Item(new Item.Properties().group(MineItemGroups.MINE_MATERIAL_GROUP))
						);
	
	
	public static final RegistryObject<BucketItem> FROST_GLUE_BUCKET_ITEM
		= MINE_ITEMS.register( "frost_glue_bucket_item"
							, () -> new MineBucketItem(MineFluids.FROST_GLUE_FLUID)
						);
	
	
	public static final RegistryObject<CuriousPuddingItem> CURIOUS_PUDDING_OXEYE_DAISY_ITEM
		= MINE_ITEMS.register("curious_oxeye_daisy_pudding_item", () -> new CuriousPuddingItem(Effects.REGENERATION, 180));
	
	public static final RegistryObject<CuriousPuddingItem> CURIOUS_PUDDING_CORNFLOWER_ITEM
		= MINE_ITEMS.register("curious_cornflower_pudding_item", () -> new CuriousPuddingItem(Effects.JUMP_BOOST, 160));
	
	public static final RegistryObject<CuriousPuddingItem> CURIOUS_PUDDING_LILY_OF_THE_VALLEY_ITEM
		= MINE_ITEMS.register("curious_lily_of_the_valley_pudding_item", () -> new CuriousPuddingItem(Effects.POISON, 300));
	
	public static final RegistryObject<CuriousPuddingItem> CURIOUS_PUDDING_WITHER_ROSE_ITEM
		= MINE_ITEMS.register("curious_wither_rose_pudding_item", () -> new CuriousPuddingItem(Effects.WITHER, 200));
	
	public static final RegistryObject<CuriousPuddingItem> CURIOUS_PUDDING_TULIP_ITEM
		= MINE_ITEMS.register("curious_tulip_pudding_item", () -> new CuriousPuddingItem(Effects.WEAKNESS, 200));
	
	public static final RegistryObject<CuriousPuddingItem> CURIOUS_PUDDING_AZURE_BLUET_ITEM
		= MINE_ITEMS.register("curious_azure_bluet_pudding_item", () -> new CuriousPuddingItem(Effects.BLINDNESS, 200));
	
	public static final RegistryObject<CuriousPuddingItem> CURIOUS_PUDDING_ALLIUM_ITEM
		= MINE_ITEMS.register("curious_allium_pudding_item", () -> new CuriousPuddingItem(Effects.FIRE_RESISTANCE, 120));
	
	public static final RegistryObject<CuriousPuddingItem> CURIOUS_PUDDING_SATIETY_ITEM
		= MINE_ITEMS.register("curious_satiety_pudding_item", () -> new CuriousPuddingItem(Effects.SATURATION, 10));
	
	public static final RegistryObject<CuriousPuddingItem> CURIOUS_PUDDING_APOPPY_ITEM
		= MINE_ITEMS.register("curious_poppy_pudding_item", () -> new CuriousPuddingItem(Effects.NIGHT_VISION, 160));
	
	
	public static final RegistryObject<TabletItem> NIGHT_VISION_TABLET_ITEM
		= MINE_ITEMS.register("night_vision_tablet_item", () -> new TabletItem(Potions.NIGHT_VISION));
	
	public static final RegistryObject<MilkSliceItem> MILK_SLICE_ITEM 
		= MINE_ITEMS.register("milk_slice_item", () -> new MilkSliceItem());
	
	
	public static final RegistryObject<DazzleFlintIgniterItem> DAZZLE_FLINT_IGNITER_ITEM 
		= MINE_ITEMS.register("dazzle_flint_igniter_item", () -> new DazzleFlintIgniterItem());
	
	public static final RegistryObject<SlingshotItem> SLINGSHOT_ITEM
		= MINE_ITEMS.register("slingshot_item", () -> new SlingshotItem());
	
	
	public static final RegistryObject<SeaShellSpawnEggItem> SEA_SHELL_SPAWN_EGG_ITEM
		= MINE_ITEMS.register("sea_shell_spawn_egg_item", () -> new SeaShellSpawnEggItem());
}
