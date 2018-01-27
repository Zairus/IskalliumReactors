package zairus.iskalliumreactors.block;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.BlockOre;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import zairus.iskalliumreactors.IskalliumReactors;
import zairus.iskalliumreactors.item.IRItems;

public class BlockIskalliumStoneOre extends BlockOre
{
	public BlockIskalliumStoneOre()
	{
		this.setCreativeTab(IskalliumReactors.creativeTab);
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setSoundType(SoundType.STONE);
		this.setLightLevel(0.35F);
	}
	
	@Override
	@Nullable
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return IRItems.ISKALLIUM_ESSENCE;
	}
	
	@Override
	public int quantityDropped(Random random)
	{
		return 2 + random.nextInt(5);
	}
	
	@Override
	public int quantityDroppedWithBonus(int fortune, Random random)
	{
		if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped((IBlockState)this.getBlockState().getValidStates().iterator().next(), random, fortune))
		{
			int i = random.nextInt(fortune + 2) - 1;
			
			if (i < 0)
				i = 0;
			
			return this.quantityDropped(random) * (i + 1);
		}
		else
		{
			return this.quantityDropped(random);
		}
	}
	
	@Override
	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
	{
		super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
	}
	
	@Override
	public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune)
	{
		Random rand = world instanceof World ? ((World)world).rand : new Random();
		
		if (this.getItemDropped(state, rand, fortune) != Item.getItemFromBlock(this))
		{
			int i = 3 + rand.nextInt(5);
			
			return i;
		}
		
		return 0;
	}
	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
	{
		return new ItemStack(this);
	}
	
	@Override
	public int damageDropped(IBlockState state)
	{
		return 0;
	}
}
