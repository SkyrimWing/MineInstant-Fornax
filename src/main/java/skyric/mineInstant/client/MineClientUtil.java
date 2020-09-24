package skyric.mineInstant.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import skyric.mineInstant.client.render.MarblePelletRender;
import skyric.mineInstant.client.render.SeaShellRender;
import skyric.mineInstant.client.screen.AlchemyCaldronScreen;
import skyric.mineInstant.init.MineBlocks;
import skyric.mineInstant.init.MineContainerTypes;
import skyric.mineInstant.init.MineEntityTypes;
import skyric.mineInstant.init.MineItems;

public class MineClientUtil {
	
	@OnlyIn(Dist.CLIENT)
	public static void setBlockRenderType() {
		//RenderType.func_228645_f_();
		RenderTypeLookup.setRenderLayer(MineBlocks.DAZZLE_FIRE_WHITE_BLOCK.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(MineBlocks.DAZZLE_FIRE_ORANGE_BLOCK.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(MineBlocks.DAZZLE_FIRE_MAGENTA_BLOCK.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(MineBlocks.DAZZLE_FIRE_LIGHT_BLUE_BLOCK.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(MineBlocks.DAZZLE_FIRE_YELLOW_BLOCK.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(MineBlocks.DAZZLE_FIRE_LIME_BLOCK.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(MineBlocks.DAZZLE_FIRE_PINK_BLOCK.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(MineBlocks.DAZZLE_FIRE_GRAY_BLOCK.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(MineBlocks.DAZZLE_FIRE_LIGHT_GRAY_BLOCK.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(MineBlocks.DAZZLE_FIRE_CYAN_BLOCK.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(MineBlocks.DAZZLE_FIRE_PURPLE_BLOCK.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(MineBlocks.DAZZLE_FIRE_BLUE_BLOCK.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(MineBlocks.DAZZLE_FIRE_BROWN_BLOCK.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(MineBlocks.DAZZLE_FIRE_GREEN_BLOCK.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(MineBlocks.DAZZLE_FIRE_RED_BLOCK.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(MineBlocks.DAZZLE_FIRE_BLACK_BLOCK.get(), RenderType.getTranslucent());
		
		RenderTypeLookup.setRenderLayer(MineBlocks.FROST_GLUE_FLUID_BLOCK.get(), RenderType.getTranslucent());
	}
	
	
	@OnlyIn(Dist.CLIENT)
	public static void tintHandler() {
		Minecraft.getInstance().getItemColors().register((stack, i) -> 65535, MineItems.NIGHT_VISION_TABLET_ITEM.get());
	}
	
	
	@OnlyIn(Dist.CLIENT)
	public static void registerGUI() {
		ScreenManager.registerFactory(MineContainerTypes.ALCHEMY_CALDRON_CONTAINER.get(), AlchemyCaldronScreen::new);
	}
	
	
	@OnlyIn(Dist.CLIENT)
	public static void registerEntityRender() {
		RenderingRegistry.registerEntityRenderingHandler(MineEntityTypes.SEA_SHELL_ENTITY.get(), SeaShellRender::new);
		RenderingRegistry.registerEntityRenderingHandler(MineEntityTypes.MARBLE_PELLET_ENTITY.get(), MarblePelletRender::new);
	}

}
