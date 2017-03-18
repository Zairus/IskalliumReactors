package zairus.iskalliumreactors.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zairus.iskalliumreactors.IskalliumReactors;

public class BlockIskalliumGlass extends Block
{
	public BlockIskalliumGlass()
	{
		super(Material.GLASS);
		this.setCreativeTab(IskalliumReactors.creativeTab);
		this.setHardness(0.3F);
		this.setSoundType(SoundType.GLASS);
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.TRANSLUCENT;
	}
	
	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	protected boolean canSilkHarvest()
	{
		return true;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
	{
		IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
		Block block = iblockstate.getBlock();
		
		//if (this == Blocks.GLASS || this == Blocks.STAINED_GLASS)
		{
			if (blockState != iblockstate)
			{
				return true;
			}
			
			if (block == this)
			{
				return false;
			}
		}
		
		return block == this ? false : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
	}
}
