package skyric.mineInstant.init;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import skyric.mineInstant.MineInstant;
import skyric.mineInstant.tile.AlchemyCaldronTileEntity;

public class MineTileEntityTypes {
	
	public static final DeferredRegister<TileEntityType<?>> MINE_TILES
		= new DeferredRegister<TileEntityType<?>>(ForgeRegistries.TILE_ENTITIES, MineInstant.MODID);
	
	public static final RegistryObject<TileEntityType<AlchemyCaldronTileEntity>> ALCHEMY_CALDRON_TILE
		= MINE_TILES.register(	"alchemy_caldron_tile"
								, () -> TileEntityType.Builder.create(
										() -> new AlchemyCaldronTileEntity()
										, MineBlocks.ALCHEMY_CALDRON_BLOCK.get()
									).build(null)
							);
	

}
