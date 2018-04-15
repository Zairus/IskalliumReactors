package zairus.iskalliumreactors.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import zairus.iskalliumreactors.IskalliumReactors;
import zairus.iskalliumreactors.tileentity.TileEntityIRController;

public class BlockIRController extends Block implements ITileEntityProvider, IBlockTileEntity
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
	
	@Override
	public void registerTileEntity()
	{
		GameRegistry.registerTileEntity(TileEntityIRController.class, "tileEntityIRController");
	}

	@Override
	public boolean onBlockActivated(World worldIn,
									BlockPos pos,
									IBlockState state,
									EntityPlayer playerIn,
									EnumHand hand,
									EnumFacing side,
									float hitX,
									float hitY,
									float hit)
	{
		TileEntity tileEntity = worldIn.getTileEntity(pos);

		if (tileEntity instanceof TileEntityIRController)
		{
			if (worldIn.isRemote || hand != EnumHand.MAIN_HAND)
				return true;

			String message;
			TileEntityIRController tileEntityIRController = (TileEntityIRController) tileEntity;

			if (tileEntityIRController.getIsReactor())
			{
				message = I18n.format("info.iskalliumReactors.controller.generating", tileEntityIRController.getReactorYield());
			}
			else
			{
				message = I18n.format("info.iskalliumReactors.controller.invalid");
			}

			playerIn.sendMessage(new TextComponentString(message));
			return true;
		}

		return false;
	}
}
