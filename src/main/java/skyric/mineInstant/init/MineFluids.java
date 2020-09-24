package skyric.mineInstant.init;

import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import skyric.mineInstant.MineInstant;
import skyric.mineInstant.fluid.FrostGlueFluid;


public class MineFluids {
	
	public static final DeferredRegister<Fluid> MINE_FLUIDS = new DeferredRegister<Fluid>(ForgeRegistries.FLUIDS, MineInstant.MODID);
	
	public static final ResourceLocation FROST_GLUE_STILL_RESOURCE = FrostGlueFluid.getStillFluidResource();
	public static final ResourceLocation FROST_GLUE_FLOWING_RESOURCE = FrostGlueFluid.getFlowingFluidResource();
	
	protected static final RegistryObject<FrostGlueFluid.Source> FROST_GLUE_FLUID
		= MINE_FLUIDS.register("frost_glue_fluid", () -> new FrostGlueFluid.Source(MineFluids.FROST_GLUE_FLUID_PROPERTY));
	public static final RegistryObject<FrostGlueFluid.Flowing> FROST_GLUE_FLOWING_FLUID
		= MINE_FLUIDS.register("frost_glue_flowing_fluid", () -> new FrostGlueFluid.Flowing(MineFluids.FROST_GLUE_FLUID_PROPERTY));
	protected static final ForgeFlowingFluid.Properties FROST_GLUE_FLUID_PROPERTY 
		= new ForgeFlowingFluid.Properties(FROST_GLUE_FLUID, FROST_GLUE_FLOWING_FLUID
											, FluidAttributes.builder(FROST_GLUE_STILL_RESOURCE, FROST_GLUE_FLOWING_RESOURCE)
														.density(800).viscosity(3000).temperature(2)
										).bucket(MineItems.FROST_GLUE_BUCKET_ITEM)
											.block(MineBlocks.FROST_GLUE_FLUID_BLOCK)
											.explosionResistance(100.0F);

}
