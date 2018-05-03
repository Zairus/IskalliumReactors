package zairus.iskalliumreactors.item;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import zairus.iskalliumreactors.IRConstants;

@ObjectHolder(IRConstants.MOD_ID)
public class IRItems
{
	@ObjectHolder(ItemBase.ISKALLIUM_ESSENCE_ID)
	public static final Item ISKALLIUM_ESSENCE;
	@ObjectHolder(ItemBase.STEEL_INGOT_ID)
	public static final Item STEEL_INGOT;
	
	private static final List<Item> ITEMS = new ArrayList<Item>();
	
	static
	{
		ISKALLIUM_ESSENCE = initItem(new ItemIskalliumEssence(), ItemBase.ISKALLIUM_ESSENCE_ID);
		STEEL_INGOT = initItem(new ItemBase(), ItemBase.STEEL_INGOT_ID);
	}
	
	public static void initialize()
	{
		OreDictionary.registerOre("ingotSteel", STEEL_INGOT);
	}
	
	private static Item initItem(Item item, String id)
	{
		item.setRegistryName(new ResourceLocation(IRConstants.MOD_ID, id));
		item.setUnlocalizedName(id);
		ITEMS.add(item);
		return item;
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerModels()
	{
		for (final Item item : ITEMS)
		{
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(IRConstants.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
		}
	}
	
	@Mod.EventBusSubscriber(modid = IRConstants.MOD_ID)
	public static class ItemRegistry
	{
		public static final Set<Item> ITEM_REGISTRY = new HashSet<Item>();
		
		@SubscribeEvent
		public static void newRegistry(final RegistryEvent.NewRegistry event)
		{
			;
		}
		
		@SubscribeEvent
		public static void register(final RegistryEvent.Register<Item> event)
		{
			final IForgeRegistry<Item> registry = event.getRegistry();
			
			for (final Item item : ITEMS)
			{
				registry.register(item);
				ITEM_REGISTRY.add(item);
			}
			
			initialize();
		}
	}
}
