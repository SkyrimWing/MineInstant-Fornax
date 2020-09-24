package skyric.mineInstant.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.tags.Tag;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SeaShellEntity extends WaterMobEntity{

	public SeaShellEntity(EntityType<? extends WaterMobEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
		this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(12.0D);
		this.getAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(12.0D);
		this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.8D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1D);
	}
	
	
	@Override
	public int getMaxAir() {
		return 800;
	}
	
	
	@Override
	protected int determineNextAir(int currentAir) {
		return Math.min(currentAir + 8, this.getMaxAir());
	}
	
	
	@Override
	protected void updateAir(int airValue) {
		if (this.isAlive() && !this.isInWaterOrBubbleColumn()) {
			this.setAir(airValue - 1);
			if (this.getAir() == -100) {
				this.setAir(0);
				this.attackEntityFrom(DamageSource.DROWN, 0.5F);
			}
		} else {
			this.setAir(1000);
		}
	}
	
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox() {
		return this.isAlive() ? this.getBoundingBox() : null;
	}
	
	
	@Override
	public void applyEntityCollision(Entity entityIn) {}
	
	
	@Override
	public float getCollisionBorderSize() {
		return 0.0F;
	}
	
	
	@Override
	public boolean canBeLeashedTo(PlayerEntity player) {
		return true;
	}
	
	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return sizeIn.height * 0.45F;
	}
	
	//protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return null;}
	
	//protected SoundEvent getDeathSound() {}
	
	//protected ResourceLocation getLootTable() {return null;}
	
	//protected float getSoundVolume() {}
	
}
