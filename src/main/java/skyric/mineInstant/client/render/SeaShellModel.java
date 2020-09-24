package skyric.mineInstant.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import skyric.mineInstant.entity.SeaShellEntity;

public class SeaShellModel extends SegmentedModel<SeaShellEntity>{
	
	private final ModelRenderer leave;
	private final ModelRenderer edge_1;
	private final ModelRenderer edge_2;
	private final ModelRenderer edge_side;
	private final ModelRenderer side_W;
	private final ModelRenderer side_E;
	private final ModelRenderer top;
	private final ModelRenderer top_1;
	private final ModelRenderer top_2;
	private final ModelRenderer top_3;
	private final ModelRenderer top_4;
	private final ModelRenderer top_5;
	private final ModelRenderer top_6;
	private final ModelRenderer top_7;
	private final ModelRenderer top_8;
	private final ModelRenderer top_plate;
	private final ModelRenderer bottom;
	private final ModelRenderer bottom_1;
	private final ModelRenderer bottom_2;
	private final ModelRenderer bottom_3;
	private final ModelRenderer bottom_4;
	private final ModelRenderer bottom_5;
	private final ModelRenderer bottom_6;
	private final ModelRenderer bottom_7;
	private final ModelRenderer bottom_8;
	private final ModelRenderer bottom_plate;
	
	public SeaShellModel() {
		textureWidth = 128;
		textureHeight = 128;

		leave = new ModelRenderer(this);
		leave.setRotationPoint(0.0F, 22.0F, -6.5F);
		leave.setTextureOffset(0, 0).addBox(2.0F, -0.5F, 0.5F, 3.0F, 1.0F, 3.0F, 0.0F, false);
		leave.setTextureOffset(0, 0).addBox(-5.0F, -0.5F, 0.5F, 3.0F, 1.0F, 3.0F, 0.0F, false);

		edge_1 = new ModelRenderer(this);
		edge_1.setRotationPoint(0.0F, 0.0F, -0.366F);
		leave.addChild(edge_1);
		setRotationAngle(edge_1, -1.0472F, 0.0F, 0.0F);
		edge_1.setTextureOffset(92, 0).addBox(-5.0F, -1.0F, 0.0F, 10.0F, 1.0F, 0.0F, 0.0F, false);

		edge_2 = new ModelRenderer(this);
		edge_2.setRotationPoint(0.0F, 0.0F, -0.366F);
		leave.addChild(edge_2);
		setRotationAngle(edge_2, 1.0472F, 0.0F, 0.0F);
		edge_2.setTextureOffset(0, 0).addBox(-5.0F, 0.0F, 0.0F, 10.0F, 1.0F, 0.0F, 0.0F, false);

		edge_side = new ModelRenderer(this);
		edge_side.setRotationPoint(0.0F, 0.0F, 0.0F);
		leave.addChild(edge_side);
		edge_side.setTextureOffset(10, 5).addBox(5.0F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		edge_side.setTextureOffset(12, 4).addBox(-6.0F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		side_W = new ModelRenderer(this);
		side_W.setRotationPoint(6.0F, -0.5F, 0.5F);
		leave.addChild(side_W);
		setRotationAngle(side_W, 0.0F, -0.2618F, 0.0F);
		side_W.setTextureOffset(0, 0).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);

		side_E = new ModelRenderer(this);
		side_E.setRotationPoint(-6.0F, -0.5F, 0.5F);
		leave.addChild(side_E);
		setRotationAngle(side_E, 0.0F, 0.2618F, 0.0F);
		side_E.setTextureOffset(5, 4).addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);

		top = new ModelRenderer(this);
		top.setRotationPoint(0.0F, 22.0F, -6.5F);
		setRotationAngle(top, 0.0873F, 0.0F, 0.0F);
		

		top_1 = new ModelRenderer(this);
		top_1.setRotationPoint(1.0F, 2.0F, -0.5F);
		top.addChild(top_1);
		setRotationAngle(top_1, 0.0F, -0.7854F, 0.0F);
		top_1.setTextureOffset(92, 16).addBox(0.0F, -4.0F, 0.0F, 9.0F, 2.0F, 1.0F, 0.0F, false);

		top_2 = new ModelRenderer(this);
		top_2.setRotationPoint(-1.0F, 2.0F, -0.5F);
		top.addChild(top_2);
		setRotationAngle(top_2, 0.0F, 0.7854F, 0.0F);
		top_2.setTextureOffset(92, 16).addBox(-9.0F, -4.0F, 0.0F, 9.0F, 2.0F, 1.0F, 0.0F, true);

		top_3 = new ModelRenderer(this);
		top_3.setRotationPoint(0.0F, 2.0F, 6.5F);
		top.addChild(top_3);
		top_3.setTextureOffset(92, 14).addBox(6.364F, -4.0F, -0.636F, 1.0F, 2.0F, 3.0F, 0.0F, false);

		top_4 = new ModelRenderer(this);
		top_4.setRotationPoint(0.0F, 2.0F, 6.5F);
		top.addChild(top_4);
		top_4.setTextureOffset(92, 14).addBox(-7.364F, -4.0F, -0.636F, 1.0F, 2.0F, 3.0F, 0.0F, false);

		top_5 = new ModelRenderer(this);
		top_5.setRotationPoint(7.364F, 2.0F, 8.864F);
		top.addChild(top_5);
		setRotationAngle(top_5, 0.0F, -0.7854F, 0.0F);
		top_5.setTextureOffset(92, 11).addBox(-1.0F, -4.0F, 0.0F, 1.0F, 2.0F, 6.0F, 0.0F, true);

		top_6 = new ModelRenderer(this);
		top_6.setRotationPoint(-7.364F, 2.0F, 8.864F);
		top.addChild(top_6);
		setRotationAngle(top_6, 0.0F, 0.7854F, 0.0F);
		top_6.setTextureOffset(92, 11).addBox(0.0F, -4.0F, 0.0F, 1.0F, 2.0F, 6.0F, 0.0F, false);

		top_7 = new ModelRenderer(this);
		top_7.setRotationPoint(7.0F, 2.0F, 12.5F);
		top.addChild(top_7);
		top_7.setTextureOffset(34, 0).addBox(-10.5F, -4.0F, 0.0F, 7.0F, 2.0F, 1.0F, 0.0F, false);

		top_8 = new ModelRenderer(this);
		top_8.setRotationPoint(-3.0F, 2.0F, 6.5F);
		top.addChild(top_8);
		top_8.setTextureOffset(92, 16).addBox(1.5F, -4.0F, -7.0F, 3.0F, 2.0F, 1.0F, 0.0F, false);

		top_plate = new ModelRenderer(this);
		top_plate.setRotationPoint(0.0F, 2.0F, 6.5F);
		top.addChild(top_plate);
		top_plate.setTextureOffset(0, 0).addBox(-3.5F, -3.999F, -4.0F, 7.0F, 1.0F, 10.0F, 0.0F, false);
		top_plate.setTextureOffset(92, 0).addBox(3.5F, -3.999F, -3.125F, 1.0F, 1.0F, 8.0F, 0.0F, false);
		top_plate.setTextureOffset(92, 9).addBox(4.5F, -3.999F, -2.125F, 1.0F, 1.0F, 6.0F, 0.0F, false);
		top_plate.setTextureOffset(92, 0).addBox(5.5F, -3.999F, -1.125F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		top_plate.setTextureOffset(92, 0).addBox(-4.5F, -3.999F, -3.125F, 1.0F, 1.0F, 8.0F, 0.0F, true);
		top_plate.setTextureOffset(92, 9).addBox(-5.5F, -3.999F, -2.125F, 1.0F, 1.0F, 6.0F, 0.0F, true);
		top_plate.setTextureOffset(92, 0).addBox(-6.5F, -3.999F, -1.125F, 1.0F, 1.0F, 4.0F, 0.0F, true);
		top_plate.setTextureOffset(92, 17).addBox(-3.0F, -3.999F, -5.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
		top_plate.setTextureOffset(92, 0).addBox(-2.0F, -3.999F, -6.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);

		bottom = new ModelRenderer(this);
		bottom.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		bottom_1 = new ModelRenderer(this);
		bottom_1.setRotationPoint(1.0F, 0.0F, -7.0F);
		bottom.addChild(bottom_1);
		setRotationAngle(bottom_1, 0.0F, -0.7854F, 0.0F);
		bottom_1.setTextureOffset(93, 0).addBox(0.0F, -2.0F, 0.0F, 9.0F, 2.0F, 1.0F, 0.0F, false);

		bottom_2 = new ModelRenderer(this);
		bottom_2.setRotationPoint(-1.0F, 0.0F, -7.0F);
		bottom.addChild(bottom_2);
		setRotationAngle(bottom_2, 0.0F, 0.7854F, 0.0F);
		bottom_2.setTextureOffset(93, 0).addBox(-9.0F, -2.0F, 0.0F, 9.0F, 2.0F, 1.0F, 0.0F, true);

		bottom_3 = new ModelRenderer(this);
		bottom_3.setRotationPoint(0.0F, 0.0F, 0.0F);
		bottom.addChild(bottom_3);
		bottom_3.setTextureOffset(92, 0).addBox(6.364F, -2.0F, -0.636F, 1.0F, 2.0F, 3.0F, 0.0F, false);

		bottom_4 = new ModelRenderer(this);
		bottom_4.setRotationPoint(0.0F, 0.0F, 0.0F);
		bottom.addChild(bottom_4);
		bottom_4.setTextureOffset(92, 0).addBox(-7.364F, -2.0F, -0.636F, 1.0F, 2.0F, 3.0F, 0.0F, false);

		bottom_5 = new ModelRenderer(this);
		bottom_5.setRotationPoint(7.364F, 0.0F, 2.364F);
		bottom.addChild(bottom_5);
		setRotationAngle(bottom_5, 0.0F, -0.7854F, 0.0F);
		bottom_5.setTextureOffset(92, 0).addBox(-1.0F, -2.0F, 0.0F, 1.0F, 2.0F, 6.0F, 0.0F, true);

		bottom_6 = new ModelRenderer(this);
		bottom_6.setRotationPoint(-7.364F, 0.0F, 2.364F);
		bottom.addChild(bottom_6);
		setRotationAngle(bottom_6, 0.0F, 0.7854F, 0.0F);
		bottom_6.setTextureOffset(92, 0).addBox(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 6.0F, 0.0F, false);

		bottom_7 = new ModelRenderer(this);
		bottom_7.setRotationPoint(7.0F, 0.0F, 6.0F);
		bottom.addChild(bottom_7);
		bottom_7.setTextureOffset(34, 0).addBox(-10.5F, -2.0F, 0.0F, 7.0F, 2.0F, 1.0F, 0.0F, false);

		bottom_8 = new ModelRenderer(this);
		bottom_8.setRotationPoint(-3.0F, 0.0F, 0.0F);
		bottom.addChild(bottom_8);
		bottom_8.setTextureOffset(0, 0).addBox(1.5F, -2.0F, -7.0F, 3.0F, 2.0F, 1.0F, 0.0F, false);

		bottom_plate = new ModelRenderer(this);
		bottom_plate.setRotationPoint(0.0F, 0.0F, 0.0F);
		bottom.addChild(bottom_plate);
		bottom_plate.setTextureOffset(0, 0).addBox(-3.5F, -1.0F, -4.0F, 7.0F, 1.0F, 10.0F, 0.0F, false);
		bottom_plate.setTextureOffset(0, 0).addBox(3.5F, -1.0F, -3.125F, 1.0F, 1.0F, 8.0F, 0.0F, false);
		bottom_plate.setTextureOffset(0, 0).addBox(4.5F, -1.0F, -2.125F, 1.0F, 1.0F, 6.0F, 0.0F, false);
		bottom_plate.setTextureOffset(0, 0).addBox(5.5F, -1.0F, -1.125F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		bottom_plate.setTextureOffset(0, 0).addBox(-4.5F, -1.0F, -3.125F, 1.0F, 1.0F, 8.0F, 0.0F, true);
		bottom_plate.setTextureOffset(0, 0).addBox(-5.5F, -1.0F, -2.125F, 1.0F, 1.0F, 6.0F, 0.0F, true);
		bottom_plate.setTextureOffset(0, 0).addBox(-6.5F, -1.0F, -1.125F, 1.0F, 1.0F, 4.0F, 0.0F, true);
		bottom_plate.setTextureOffset(0, 0).addBox(-3.0F, -1.0F, -5.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
		bottom_plate.setTextureOffset(0, 0).addBox(-2.0F, -1.0F, -6.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
	}
	
	
	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		leave.render(matrixStack, buffer, packedLight, packedOverlay);
		top.render(matrixStack, buffer, packedLight, packedOverlay);
		bottom.render(matrixStack, buffer, packedLight, packedOverlay);
	}
	
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	

	@Override
	public Iterable<ModelRenderer> getParts() {
		return null;
	}

	@Override
	public void setRotationAngles(SeaShellEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
	}

}
