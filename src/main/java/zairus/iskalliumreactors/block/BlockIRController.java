package zairus.iskalliumreactors.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import zairus.iskalliumreactors.IskalliumReactors;
import zairus.iskalliumreactors.tileentity.TileEntityIRController;

public class BlockIRController extends Block implements ITileEntityProvider
{
	public BlockIRController()
	{
		super(Material.ROCK);
		this.setCreativeTab(IskalliumReactors.creativeTab);
		this.setSoundType(SoundType.STONE);
		this.setHardness(1.5F);
		this.setResistance(2000.0F);
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityIRController();
	}
}
