package skyric.mineInstant.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import skyric.mineInstant.entity.MarblePelletEntity;

public class MarblePelletModel extends EntityModel<MarblePelletEntity>{
	
	private final ModelRenderer block1;
	private final ModelRenderer block2;
	
	public MarblePelletModel() {
		textureWidth = 16;
		textureHeight = 16;

		block1 = new ModelRenderer(this);
		block1.setRotationPoint(0.0F, 24.0F, 0.0F);
		block1.setTextureOffset(0, 0).addBox(-2.0F, -2.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		block2 = new ModelRenderer(this);
		block2.setRotationPoint(-1.0F, 23.0F, 1.0F);
		setRotationAngle(block2, 0.0F, -0.7854F, 0.7854F);
		block2.setTextureOffset(8, 12).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(MarblePelletEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
	
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		block1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
		block2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
	}

}
