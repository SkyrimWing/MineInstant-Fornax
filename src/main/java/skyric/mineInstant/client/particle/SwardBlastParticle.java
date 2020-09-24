package skyric.mineInstant.client.particle;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.world.World;

public class SwardBlastParticle extends Particle{

	public SwardBlastParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn,
			double ySpeedIn, double zSpeedIn) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
	}
	
	
	@Override
	public void renderParticle(IVertexBuilder buffer, ActiveRenderInfo renderInfo, float partialTicks) {
	}
	
	
	@Override
	public IParticleRenderType getRenderType() {
		return null;
	}

}
