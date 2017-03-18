package zairus.iskalliumreactors.proxy;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import zairus.iskalliumreactors.IRConstants;
import zairus.iskalliumreactors.client.renderer.ISpecialRendered;

public class ClientProxy extends CommonProxy
{
	@Override
	public void preInit(FMLPreInitializationEvent e)
	{
		super.preInit(e);
		
		//Regisgter entity renderer
	}
	
	@Override
	public void init(FMLInitializationEvent e)
	{
		super.init(e);
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent e)
	{
		super.postInit(e);
		//Blocks init client
	}
	
	@Override
	public void registerBlock(Block block, String name, boolean model)
	{
		super.registerBlock(block, name, model);
		
		if (model)
			registerModel(Item.getItemFromBlock(block), 0, name);
	}
	
	@Override
	public void registerBlock(Block block, String name, boolean model, boolean item)
	{
		super.registerBlock(block, name, model, item);
	}
	
	@Override
	public void registerItem(Item item, String name, int meta, boolean model)
	{
		super.registerItem(item, name, meta, model);
		
		if (model && item != null)
		{
			registerModel(item, meta, name);
		}
	}
	
	public void registerModel(Item item, int meta, String name)
	{
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
		ModelResourceLocation modelResourceLocation = new ModelResourceLocation(IRConstants.MODID + ":" + name, "inventory");
		
		renderItem.getItemModelMesher().register(item, meta, modelResourceLocation);
		
		if (meta == 0)
			ModelBakery.registerItemVariants(item, new ResourceLocation(IRConstants.MODID, name));
	}
	
	@Override
	public void registerBlock(Block block, String name, Class<? extends TileEntity> clazz, String tileEntityId, boolean model)
	{
		super.registerBlock(block, name, clazz, tileEntityId, model);
		
		if (block instanceof ISpecialRendered)
		{
			Object tesrObj = ((ISpecialRendered)block).getTESR();
			if (tesrObj instanceof TileEntitySpecialRenderer)
			{
				@SuppressWarnings("unchecked")
				TileEntitySpecialRenderer<TileEntity> tesr = (TileEntitySpecialRenderer<TileEntity>)tesrObj;
				
				ClientRegistry.bindTileEntitySpecialRenderer(clazz, tesr);
			}
		}
		
		if (model)
			registerModel(Item.getItemFromBlock(block), 0, name);
	}
	
	@Override
	public void initBuiltinShapes()
	{
		MinecraftForge.EVENT_BUS.register(this);
	}
}
