package skyric.mineInstant.generator;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public class AncientEbonyTreeFeature extends AbstractTreeFeature<TreeFeatureConfig>{

	public AncientEbonyTreeFeature(Function<Dynamic<?>, ? extends TreeFeatureConfig> p_i225797_1_) {
		super(p_i225797_1_);
	}

	@Override
	protected boolean place(IWorldGenerationReader generationReader, Random rand, BlockPos positionIn,
			Set<BlockPos> p_225557_4_, Set<BlockPos> p_225557_5_, MutableBoundingBox boundingBoxIn,
			TreeFeatureConfig configIn) {
		return false;
	}

}
