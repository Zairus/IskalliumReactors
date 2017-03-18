package zairus.iskalliumreactors.block;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zairus.iskalliumreactors.IskalliumReactors;
import zairus.iskalliumreactors.client.Colorizers;
import zairus.iskalliumreactors.tileentity.TileEntityIRController;
import zairus.iskalliumreactors.tileentity.TileEntityIRPowerTap;

public class IRBlocks
{
	public static final Block ISKALLIUM_ORE;
	public static final Block ISKALLIUM_STONE_ORE;
	public static final Block ISKALLIUM_GLASS;
	public static final Block STEEL_CASING;
	public static final Block STEEL_CONTROLLER;
	public static final Block STEEL_POWERTAP;
	
	static
	{
		ISKALLIUM_ORE = new BlockIskallium().setRegistryName("iskallium_ore").setUnlocalizedName("iskallium_ore");
		ISKALLIUM_STONE_ORE = new BlockIskalliumStoneOre().setRegistryName("iskallium_stone_ore").setUnlocalizedName("iskallium_stone_ore");
		ISKALLIUM_GLASS = new BlockIskalliumGlass().setRegistryName("iskallium_glass").setUnlocalizedName("iskallium_glass");
		STEEL_CASING = new BlockSteelCasing().setRegistryName("steel_casing").setUnlocalizedName("steel_casing");
		STEEL_CONTROLLER = new BlockIRController().setRegistryName("steel_controller").setUnlocalizedName("steel_controller");
		STEEL_POWERTAP = new BlockIRPowerTap().setRegistryName("steel_powertap").setUnlocalizedName("steel_powertap");
	}
	
	public static void register()
	{
		registerBlock(ISKALLIUM_ORE, "iskallium_ore");
		registerBlock(ISKALLIUM_STONE_ORE, "iskallium_stone_ore");
		registerBlock(ISKALLIUM_GLASS, "iskallium_glass");
		registerBlock(STEEL_CASING, "steel_casing");
		
		registerBlock(STEEL_CONTROLLER, "steel_controller", TileEntityIRController.class, "tileEntityIRController", true);
		registerBlock(STEEL_POWERTAP, "steel_powertap", TileEntityIRPowerTap.class, "tileEntityIRPowerTap", true);
	}
	
	private static void registerBlock(Block block, String name, Class<? extends TileEntity> teClazz, String id, boolean model)
	{
		IskalliumReactors.proxy.registerBlock(block, name, teClazz, id, model);
	}
	
	private static void registerBlock(Block block, String name)
	{
		IskalliumReactors.proxy.registerBlock(block, name, true);
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
