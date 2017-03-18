package zairus.iskalliumreactors.world.gen.feature;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;
import zairus.iskalliumreactors.IRConfig;
import zairus.iskalliumreactors.block.IRBlocks;

public class WorldGenIskalliumOre implements IWorldGenerator
{
	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{
		if (rand.nextInt(3) == 0 && world.provider.getDimensionType() == DimensionType.OVERWORLD)
		{
			int x = (chunkX * 16) + rand.nextInt(16);
			int y = 2 + rand.nextInt(15);
			int z = (chunkZ * 16) + rand.nextInt(16);
			
			BlockPos pos = new BlockPos(x, y, z);
			
			int size = 1 + rand.nextInt(IRConfig.iskalliumGenerationPatchSize);
			int check = rand.nextInt(3);
			
			if (size < 1)
				size = 1;
			
			if (check == 0)
			{
				(new WorldGenIRMineable(IRBlocks.ISKALLIUM_STONE_ORE.getDefaultState())).generate(world, rand, pos);
			}
		}
	}
}
