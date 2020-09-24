package skyric.mineInstant.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public abstract class AbstractFlamingBlock extends Block
{
	public AbstractFlamingBlock(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}
	
	
	public AbstractFlamingBlock(Properties properties, int level) {
		super(properties
				.doesNotBlockMovement()
				.hardnessAndResistance(0.0F, 0.0F)
				//.tickRandomly()
			);
	}
	
	public abstract BlockState getStateForPlacement(IWorld worldIn, BlockPos posIn);

}
