package zairus.iskalliumreactors.block;

import java.util.HashSet;
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
	
	static
	{
		ISKALLIUM = new BlockIskallium().setRegistryName(IRBlock.ISKALLIUM_ID).setUnlocalizedName(IRBlock.ISKALLIUM_ID);
		ISKALLIUM_ORE = new BlockIskalliumStoneOre().setRegistryName(IRBlock.ISKALLIUM_ORE_ID).setUnlocalizedName(IRBlock.ISKALLIUM_ORE_ID);
		ISKALLIUM_GLASS = new BlockIskalliumGlass().setRegistryName(IRBlock.ISKALLIUM_GLASS_ID).setUnlocalizedName(IRBlock.ISKALLIUM_GLASS_ID);
		STEEL_CASING = new BlockSteelCasing().setRegistryName(IRBlock.STEEL_CASING_ID).setUnlocalizedName(IRBlock.STEEL_CASING_ID);
		STEEL_CONTROLLER = new BlockIRController().setRegistryName(IRBlock.STEEL_CONTROLLER_ID).setUnlocalizedName(IRBlock.STEEL_CONTROLLER_ID);
		STEEL_POWERTAP = new BlockIRPowerTap().setRegistryName(IRBlock.STEEL_POWERTAP_ID).setUnlocalizedName(IRBlock.STEEL_POWERTAP_ID);
	}
	
	public static void initialize()
	{
		;
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerModels()
	{
		final Item[] blocks = {
				Item.getItemFromBlock(ISKALLIUM)
				,Item.getItemFromBlock(ISKALLIUM_ORE)
				,Item.getItemFromBlock(ISKALLIUM_GLASS)
				,Item.getItemFromBlock(STEEL_CASING)
				,Item.getItemFromBlock(STEEL_CONTROLLER)
				,Item.getItemFromBlock(STEEL_POWERTAP)
		};
		
		for (final Item block : blocks)
		{
			ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation(IRConstants.MOD_ID + ":" + block.getUnlocalizedName().substring(5), "inventory"));
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
			
			final Block[] blocks = {
					ISKALLIUM
					, ISKALLIUM_ORE
					, ISKALLIUM_GLASS
					, STEEL_CASING
					, STEEL_CONTROLLER
					, STEEL_POWERTAP
			};
			
			registry.registerAll(blocks);
		}
		
		@SubscribeEvent
		public static void registerItemBlocks(final RegistryEvent.Register<Item> event)
		{
			final IForgeRegistry<Item> registry = event.getRegistry();
			
			final ItemBlock[] items = {
					new ItemBlock(ISKALLIUM)
					,new ItemBlock(ISKALLIUM_ORE)
					,new ItemBlock(ISKALLIUM_GLASS)
					,new ItemBlock(STEEL_CASING)
					,new ItemBlock(STEEL_CONTROLLER)
					,new ItemBlock(STEEL_POWERTAP)
			};
			
			for (final ItemBlock item : items)
			{
				final Block block = item.getBlock();
				final ResourceLocation registryName = Preconditions.checkNotNull(block.getRegistryName(), "Block %s has null registry name", block);
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
		
		registerColors(blockColors, itemColors, Colorizers.BLOCK_LEAVES, new Block[] { /**/ });
	}
	
	private static void registerColors(BlockColors blockColors, ItemColors itemColors, IBlockColor color, Block... blocks)
	{
		blockColors.registerBlockColorHandler(color, blocks);
		itemColors.registerItemColorHandler((s, t) -> color.colorMultiplier(Block.getBlockFromItem(s.getItem()).getDefaultState(), null, null, t), blocks);
	}
}
