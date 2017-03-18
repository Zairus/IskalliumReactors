package zairus.iskalliumreactors.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import zairus.iskalliumreactors.IskalliumReactors;

public class BlockSteelCasing extends Block
{
	public BlockSteelCasing()
	{
		super(Material.IRON);
		this.setCreativeTab(IskalliumReactors.creativeTab);
		this.setSoundType(SoundType.STONE);
		this.setHardness(1.5F);
	}
}
