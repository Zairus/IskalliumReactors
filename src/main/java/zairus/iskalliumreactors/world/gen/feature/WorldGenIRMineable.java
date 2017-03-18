package zairus.iskalliumreactors.world.gen.feature;

import java.util.Random;

import com.google.common.base.Predicate;

import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenIRMineable extends WorldGenerator
{
	private final IBlockState oreBlock;
	private final Predicate<IBlockState> predicate;
	
	public WorldGenIRMineable(IBlockState state)
	{
		this.oreBlock = state;
		this.predicate = BlockMatcher.forBlock(Blocks.STONE);
	}
	
	@Override
	public boolean generate(World world, Random rand, BlockPos pos)
	{
		boolean generated = false;
		IBlockState state = world.getBlockState(pos);
		
		if (state.getBlock().isReplaceableOreGen(state, world, pos, this.predicate))
		{
			generated = world.setBlockState(pos, this.oreBlock, 2);
		}
		
		return generated;
	}
}
