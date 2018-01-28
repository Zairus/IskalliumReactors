package zairus.iskalliumreactors.block;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.BlockBreakable;
import net.minecraft.block.BlockStone;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zairus.iskalliumreactors.IskalliumReactors;

public class BlockIskallium extends BlockBreakable
{
	public BlockIskallium()
	{
		super(Material.CLAY, false, MapColor.GRASS);
		this.setCreativeTab(IskalliumReactors.creativeTab);
		this.setLightLevel(0.75F);
		this.setSoundType(SoundType.STONE);
		this.setTickRandomly(true);
		this.setHardness(0.5F);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.TRANSLUCENT;
	}
	
	@Override
	public float getSlipperiness(IBlockState state, IBlockAccess world, BlockPos pos, @Nullable Entity entity)
	{
		return 0.98F;
	}
	
	@Override
	public void randomTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		super.randomTick(world, pos, state, rand);
		
		BlockPos targetPos = pos.add(2 - rand.nextInt(5), 2 - rand.nextInt(5), 2 - rand.nextInt(5));
		
		if (world.getBlockState(targetPos).getBlock() == Blocks.STONE)
			world.setBlockState(targetPos, Blocks.COBBLESTONE.getDefaultState());
		else if (world.getBlockState(targetPos) == Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE))
			world.setBlockToAir(targetPos);
	}
	
	@Override
	public void onFallenUpon(World world, BlockPos pos, Entity entity, float fallDistance)
	{
		if (entity.isSneaking())
		{
			super.onFallenUpon(world, pos, entity, fallDistance);
		}
		else
		{
			entity.fall(fallDistance, 0.0F);
		}
	}
	
	@Override
	public void onLanded(World world, Entity entity)
	{
		if (entity.isSneaking())
		{
			super.onLanded(world, entity);
		}
		else if (entity.motionY < 0.0D)
		{
			entity.motionY = -entity.motionY;
			
			if (!(entity instanceof EntityLivingBase))
			{
				entity.motionY *= 0.8D;
			}
		}
	}
}
