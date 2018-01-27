package zairus.iskalliumreactors;

import org.apache.logging.log4j.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import zairus.iskalliumreactors.block.IRBlocks;
import zairus.iskalliumreactors.handlres.IRCraftingHandler;
import zairus.iskalliumreactors.item.IRItems;
import zairus.iskalliumreactors.proxy.CommonProxy;
import zairus.iskalliumreactors.world.gen.feature.WorldGenIskalliumOre;

@Mod(modid = IRConstants.MODID, name = IRConstants.MODNAME, version = IRConstants.VERSION)
public class IskalliumReactors
{
	@Mod.Instance(IRConstants.MODID)
	public static IskalliumReactors instance;
	
	@SidedProxy(clientSide = IRConstants.CLIENT_PROXY, serverSide = IRConstants.COMMON_PROXY)
	public static CommonProxy proxy;
	
	public static Logger logger;
	
	public static CreativeTabs creativeTab = new CreativeTabs("iskalliumReactors") {
		@Override
		public ItemStack getTabIconItem()
		{
			return new ItemStack(IRBlocks.ISKALLIUM);
		}
	};
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		logger = event.getModLog();
		IRConfig.init(event.getSuggestedConfigurationFile());
		
		IskalliumReactors.proxy.preInit(event);
	}
	
	@Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
		IskalliumReactors.proxy.init(event);
		
		IRItems.register();
		IRBlocks.register();
		
		IskalliumReactors.proxy.initBuiltinShapes();
		
		GameRegistry.registerWorldGenerator(new WorldGenIskalliumOre(), IRConfig.iskalliumGenerationWeight);
		
		IRCraftingHandler.addRecipes();
    }
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		IskalliumReactors.proxy.postInit(event);
	}
}
