package skyric.mineInstant.block;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;


public class MineTreasureBlock extends Block{

	public MineTreasureBlock(Properties properties) {
		super(properties);
	}
	
	
	@Override
	public boolean isBeaconBase(BlockState state, IWorldReader world, BlockPos pos, BlockPos beacon){
        return true;
    }
	
}
