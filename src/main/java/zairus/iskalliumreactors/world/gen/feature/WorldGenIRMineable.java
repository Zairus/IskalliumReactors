package zairus.iskalliumreactors.world.gen.feature;

import com.google.common.base.Predicate;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import zairus.iskalliumreactors.IRConfig;

import java.util.Random;

public class WorldGenIRMineable extends WorldGenerator
{
	private final IBlockState oreBlock;
	private final Predicate<IBlockState> predicate;
	private final WorldGenMinable worldGenMinable;

	public WorldGenIRMineable(IBlockState state)
	{
		this.oreBlock = state;
		this.predicate = BlockMatcher.forBlock(Blocks.STONE);
		this.worldGenMinable = new WorldGenMinable(state, IRConfig.iskalliumGenerationPatchSize);
	}
	
	@Override
	public boolean generate(World world, Random rand, BlockPos pos)
	{
		// Ensure at least 1 ore is generated
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock().isReplaceableOreGen(state, world, pos, this.predicate))
		{
			world.setBlockState(pos, this.oreBlock, 2);
		}

		// Generate rest of the vein
		return worldGenMinable.generate(world, rand, pos);
	}
}
