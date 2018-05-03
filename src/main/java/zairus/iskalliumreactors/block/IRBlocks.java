package zairus.iskalliumreactors.block;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.base.Preconditions;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import zairus.iskalliumreactors.IRConstants;
import zairus.iskalliumreactors.client.Colorizers;

@ObjectHolder(IRConstants.MOD_ID)
public class IRBlocks
{
	@ObjectHolder(IRBlock.ISKALLIUM_ID)
	public static final Block ISKALLIUM;
	@ObjectHolder(IRBlock.ISKALLIUM_ORE_ID)
	public static final Block ISKALLIUM_ORE;
	@ObjectHolder(IRBlock.ISKALLIUM_GLASS_ID)
	public static final Block ISKALLIUM_GLASS;
	@ObjectHolder(IRBlock.STEEL_CASING_ID)
	public static final Block STEEL_CASING;
	@ObjectHolder(IRBlock.STEEL_CONTROLLER_ID)
	public static final Block STEEL_CONTROLLER;
	@ObjectHolder(IRBlock.STEEL_POWERTAP_ID)
	public static final Block STEEL_POWERTAP;
	
	private static final List<Block> BLOCKS = new ArrayList<Block>();
	
	static
	{
		ISKALLIUM = initBlock(new BlockIskallium(), IRBlock.ISKALLIUM_ID);
		ISKALLIUM_ORE = initBlock(new BlockIskalliumStoneOre(), IRBlock.ISKALLIUM_ORE_ID);
		ISKALLIUM_GLASS = initBlock(new BlockIskalliumGlass(), IRBlock.ISKALLIUM_GLASS_ID);
		STEEL_CASING = initBlock(new BlockSteelCasing(), IRBlock.STEEL_CASING_ID);
		STEEL_CONTROLLER = initBlock(new BlockIRController(), IRBlock.STEEL_CONTROLLER_ID);
		STEEL_POWERTAP = initBlock(new BlockIRPowerTap(), IRBlock.STEEL_POWERTAP_ID);
	}
	
	public static void initialize()
	{
		;
	}
	
	private static Block initBlock(Block block, String id)
	{
		block.setRegistryName(new ResourceLocation(IRConstants.MOD_ID, id));
		block.setUnlocalizedName(id);
		BLOCKS.add(block);
		return block;
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerModels()
	{
		for (final Block block : BLOCKS)
		{
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(IRConstants.MOD_ID + ":" + block.getUnlocalizedName().substring(5), "inventory"));
		}
	}
	
	@Mod.EventBusSubscriber(modid = IRConstants.MOD_ID)
	public static class BlockRegistry
	{
		public static final Set<ItemBlock> ITEM_BLOCKS = new HashSet<ItemBlock>();
		
		@SubscribeEvent
		public static void newRegistry(final RegistryEvent.NewRegistry event)
		{
			;
		}
		
		@SubscribeEvent
		public static void register(final RegistryEvent.Register<Block> event)
		{
			final IForgeRegistry<Block> registry = event.getRegistry();
			
			registry.registerAll(BLOCKS.toArray(new Block[] { }));
		}
		
		@SubscribeEvent
		public static void registerItemBlocks(final RegistryEvent.Register<Item> event)
		{
			final IForgeRegistry<Item> registry = event.getRegistry();
			
			for (final Block block : BLOCKS)
			{
				final ResourceLocation registryName = Preconditions.checkNotNull(block.getRegistryName(), "Block %s has null registry name", block);
				ItemBlock item = new ItemBlock(block);
				registry.register(item.setRegistryName(registryName));
				
				if (block instanceof IBlockTileEntity)
				{
					((IBlockTileEntity)block).registerTileEntity();
				}
				
				ITEM_BLOCKS.add(item);
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	public static void initClient()
	{
		BlockColors blockColors = Minecraft.getMinecraft().getBlockColors();
		ItemColors itemColors = Minecraft.getMinecraft().getItemColors();
		
		registerColors(blockColors, itemColors, Colorizers.BLOCK_LEAVES, new Block[] { });
	}
	
	private static void registerColors(BlockColors blockColors, ItemColors itemColors, IBlockColor color, Block... blocks)
	{
		blockColors.registerBlockColorHandler(color, blocks);
		itemColors.registerItemColorHandler((s, t) -> color.colorMultiplier(Block.getBlockFromItem(s.getItem()).getDefaultState(), null, null, t), blocks);
	}
}
