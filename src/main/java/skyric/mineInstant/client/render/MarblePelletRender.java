package skyric.mineInstant.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import skyric.mineInstant.MineInstant;
import skyric.mineInstant.entity.MarblePelletEntity;

public class MarblePelletRender extends EntityRenderer<MarblePelletEntity>{
	
	private static final ResourceLocation MARBLE_PELLET_RENDER
		= new ResourceLocation(MineInstant.MODID, "textures/entity/marble_pellet_model_image.png");
	private final MarblePelletModel model;
	
	public MarblePelletRender(EntityRendererManager renderManager) {
		super(renderManager);
		model = new MarblePelletModel();
	}
	

	@Override
	public ResourceLocation getEntityTexture(MarblePelletEntity entity) {
		return MARBLE_PELLET_RENDER;
	}
	
	
	@Override
	public void render(MarblePelletEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		matrixStackIn.push();
		matrixStackIn.translate(0.06D, -1.34D, -0.06D);
		IVertexBuilder ivertexBuilder = bufferIn.getBuffer(model.getRenderType(MARBLE_PELLET_RENDER));
		model.render(matrixStackIn, ivertexBuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		matrixStackIn.pop();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}
	
}
