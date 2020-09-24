package skyric.mineInstant;

import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.block.BlastFurnaceBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.ComparatorBlock;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.FireBlock;
import net.minecraft.block.GlassBlock;
import net.minecraft.block.HopperBlock;
import net.minecraft.block.OreBlock;
import net.minecraft.block.RedstoneOreBlock;
import net.minecraft.block.TorchBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.TransformationMatrix;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BowItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.EnderPearlItem;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.PotionItem;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.SuspiciousStewItem;
import net.minecraft.item.ToolItem;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.profiler.IProfiler;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.tags.NetworkTagManager;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ITickList;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.AbstractChunkProvider;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.storage.MapData;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.client.model.BakedItemModel;


public class MineTest {
	
}

class A1 extends TorchBlock{

	protected A1(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}}

class A2 extends HopperBlock{

	public A2(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}}

class A3 extends GlassBlock{

	public A3(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}}

class A4 extends ChestBlock{

	protected A4(Properties p_i225757_1_, Supplier<TileEntityType<? extends ChestTileEntity>> p_i225757_2_) {
		super(p_i225757_1_, p_i225757_2_);
		// TODO Auto-generated constructor stub
	}}

class B1 extends ShieldItem{

	public B1(Properties builder) {
		super(builder);
		// TODO Auto-generated constructor stub
	}}

class B2 extends SpawnEggItem{

	public B2(EntityType<?> typeIn, int primaryColorIn, int secondaryColorIn, Properties builder) {
		super(typeIn, primaryColorIn, secondaryColorIn, builder);
		// TODO Auto-generated constructor stub
	}}

class B3 extends SuspiciousStewItem{

	public B3(Properties p_i50035_1_) {
		super(p_i50035_1_);
		// TODO Auto-generated constructor stub
	}}

class B4 extends ToolItem{

	protected B4(float attackDamageIn, float attackSpeedIn, IItemTier tier, Set<Block> effectiveBlocksIn,
			Properties builder) {
		super(attackDamageIn, attackSpeedIn, tier, effectiveBlocksIn, builder);
		// TODO Auto-generated constructor stub
	}}

class B5 extends EnderPearlItem{

	public B5(Properties builder) {
		super(builder);
		// TODO Auto-generated constructor stub
	}}

class C1 extends Items{}

class D1 extends Blocks{}

class E1 extends RenderType{

	public E1(String p_i225992_1_, VertexFormat p_i225992_2_, int p_i225992_3_, int p_i225992_4_, boolean p_i225992_5_,
			boolean p_i225992_6_, Runnable p_i225992_7_, Runnable p_i225992_8_) {
		super(p_i225992_1_, p_i225992_2_, p_i225992_3_, p_i225992_4_, p_i225992_5_, p_i225992_6_, p_i225992_7_, p_i225992_8_);
		// TODO Auto-generated constructor stub
	}}

class F1 extends World{

	protected F1(WorldInfo info, DimensionType dimType, BiFunction<World, Dimension, AbstractChunkProvider> provider,
			IProfiler profilerIn, boolean remote) {
		super(info, dimType, provider, profilerIn, remote);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ITickList<Block> getPendingBlockTicks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ITickList<Fluid> getPendingFluidTicks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void playEvent(PlayerEntity player, int type, BlockPos pos, int data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<? extends PlayerEntity> getPlayers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Biome getNoiseBiomeRaw(int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void notifyBlockUpdate(BlockPos pos, BlockState oldState, BlockState newState, int flags) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playSound(PlayerEntity player, double x, double y, double z, SoundEvent soundIn, SoundCategory category,
			float volume, float pitch) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playMovingSound(PlayerEntity playerIn, Entity entityIn, SoundEvent eventIn, SoundCategory categoryIn,
			float volume, float pitch) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Entity getEntityByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MapData getMapData(String mapName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerMapData(MapData mapDataIn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getNextMapId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void sendBlockBreakProgress(int breakerId, BlockPos pos, int progress) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Scoreboard getScoreboard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecipeManager getRecipeManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NetworkTagManager getTags() {
		// TODO Auto-generated method stub
		return null;
	}}

class G1 extends BakedItemModel{

	public G1(ImmutableList<BakedQuad> quads, TextureAtlasSprite particle,
			ImmutableMap<TransformType, TransformationMatrix> transforms, ItemOverrideList overrides,
			boolean untransformed, boolean isSideLit) {
		super(quads, particle, transforms, overrides, untransformed, isSideLit);
		// TODO Auto-generated constructor stub
	}}

class H1 extends CreeperEntity{

	public H1(EntityType<? extends CreeperEntity> type, World worldIn) {
		super(type, worldIn);
		// TODO Auto-generated constructor stub
	}
	
class I1 extends GlStateManager{
	
	}
}