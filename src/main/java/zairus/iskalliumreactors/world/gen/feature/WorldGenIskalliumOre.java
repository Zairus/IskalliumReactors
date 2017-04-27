package zairus.iskalliumreactors.world.gen.feature;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;
import zairus.iskalliumreactors.IRConfig;
import zairus.iskalliumreactors.block.IRBlocks;

import java.util.Random;

public class WorldGenIskalliumOre implements IWorldGenerator
{
	private final WorldGenIRMineable worldGenIRMineable;

	public WorldGenIskalliumOre() {
		worldGenIRMineable = new WorldGenIRMineable(IRBlocks.ISKALLIUM_STONE_ORE.getDefaultState());
	}

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{
		if (world.provider.getDimensionType() != DimensionType.OVERWORLD) {
			return;
		}

		int check = rand.nextInt(9);
		if (check == 0) {
			int x = (chunkX * 16) + rand.nextInt(16);
			int y = 2 + rand.nextInt(15);
			int z = (chunkZ * 16) + rand.nextInt(16);

			int size = 1 + rand.nextInt(IRConfig.iskalliumGenerationPatchSize);
			if (size < 1)
				size = 1;

			for (int i = 0; i < size; i ++) {
				worldGenIRMineable.generate(world, rand, new BlockPos(x, y, z));

				// random walk in one axis at a time
				switch (rand.nextInt(3)) {
					case 0:
						x += rand.nextInt(2) - 1;
						break;
					case 1:
						y += rand.nextInt(2) - 1;
						break;
					case 2:
						z += rand.nextInt(2) - 1;
						break;
				}
			}
		}
	}
}
