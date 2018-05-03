package zairus.iskalliumreactors.world.gen.feature;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import zairus.iskalliumreactors.IRConfig;
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
			
			int patchSize = IRConfig.iskalliumGenerationPatchSize;
			if (patchSize < 1)
				patchSize = 1;
			
			int size = 1 + rand.nextInt(patchSize);
			
			for (int i = 0; i < size; i ++)
			{
				worldGenIRMineable.generate(world, rand, new BlockPos(x, y, z));
				
				// random walk in one axis at a time
				switch (rand.nextInt(3))
				{
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
