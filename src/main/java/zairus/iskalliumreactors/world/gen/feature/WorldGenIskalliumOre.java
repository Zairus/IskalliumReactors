package zairus.iskalliumreactors.world.gen.feature;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import zairus.iskalliumreactors.block.IRBlocks;

public class WorldGenIskalliumOre implements IWorldGenerator
{
	private final WorldGenIRMineable worldGenIRMineable;

	public WorldGenIskalliumOre() {
		worldGenIRMineable = new WorldGenIRMineable(IRBlocks.ISKALLIUM_ORE.getDefaultState());
	}

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{
		int check = rand.nextInt(9);
		if (check == 0)
		{
			int x = (chunkX * 16) + rand.nextInt(16);
			int y = 2 + rand.nextInt(15);
			int z = (chunkZ * 16) + rand.nextInt(16);

			worldGenIRMineable.generate(world, rand, new BlockPos(x, y, z));
		}
	}
}
