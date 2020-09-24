package skyric.mineInstant.entity;

import javax.annotation.Nonnull;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import skyric.mineInstant.init.MineEntityTypes;

public class MarblePelletEntity extends ThrowableEntity{

	public MarblePelletEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	
	public MarblePelletEntity(LivingEntity livingEntityIn, World worldIn) {
		super(MineEntityTypes.MARBLE_PELLET_ENTITY.get(), livingEntityIn, worldIn);
	}
	
	
	public MarblePelletEntity(EntityType<? extends ThrowableEntity> type, LivingEntity livingEntityIn, World worldIn) {
		super(type, livingEntityIn, worldIn);
	}
	

	@Override
	protected void onImpact(RayTraceResult result) {
		boolean removeFlag = false;
		
		if (result.getType() == RayTraceResult.Type.ENTITY) {
			Entity entity = ((EntityRayTraceResult)result).getEntity();
			float damage = (float) (this.getMotion().length() / 2);
			entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), damage);
			removeFlag = true;
		} else if(result.getType() == RayTraceResult.Type.BLOCK){
			BlockPos blockPos = ((BlockRayTraceResult)result).getPos();
			BlockState blockState = world.getBlockState(blockPos);
			VoxelShape voxelshape = blockState.getCollisionShape(world, blockPos);
			if (!voxelshape.isEmpty()) {
				removeFlag = true;
			}
		}
		
		if(removeFlag && (!world.isRemote)) {
			this.world.setEntityState(this, (byte)3);
			this.remove();
		}
	}
	
	
	@Nonnull
	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}


	@Override
	protected void registerData() {
	}
	
}
