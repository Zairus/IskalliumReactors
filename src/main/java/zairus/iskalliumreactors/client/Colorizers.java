package zairus.iskalliumreactors.client;

import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.biome.BiomeColorHelper;

public class Colorizers
{
	public static IBlockColor getBlockLeaves(int tint)
	{
		return (state, world, pos, tintIndex) ->
		{
			if (tint == -1 || tintIndex == tint)
			{
				if (world != null && pos != null)
					return BiomeColorHelper.getFoliageColorAtPos(world, pos);
				else
					return ColorizerFoliage.getFoliageColorBasic();
			}
			else
			{
				return 0xFFFFFF;
			}
		};
	}
	
	public static IBlockColor getBlockGray()
	{
		return (state, world, pos, tintIndex) ->
		{
			return 0x457c9f;
		};
	}
	
	public static final IBlockColor BLOCK_LEAVES = getBlockLeaves(-1);
	public static final IBlockColor BLOCK_GRAY = getBlockGray();
}
