package skyric.mineInstant.client.render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import skyric.mineInstant.MineInstant;
import skyric.mineInstant.entity.SeaShellEntity;

public class SeaShellRender extends MobRenderer<SeaShellEntity, SeaShellModel>{
	
	private static final ResourceLocation SEA_SHELL_TEXTURE 
		= new ResourceLocation(MineInstant.MODID, "textures/entity/sea_shell_model_image.png");

	public SeaShellRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new SeaShellModel(), 0.7f);
	}

	@Override
	public ResourceLocation getEntityTexture(SeaShellEntity entity) {
		return SEA_SHELL_TEXTURE;
	}

}
