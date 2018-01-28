package zairus.iskalliumreactors.item;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import zairus.iskalliumreactors.IRConstants;

@ObjectHolder(IRConstants.MOD_ID)
public class IRItems
{
	@ObjectHolder(ItemBase.ISKALLIUM_ESSENCE_ID)
	public static final ItemBase ISKALLIUM_ESSENCE;
	@ObjectHolder(ItemBase.STEEL_INGOT_ID)
	public static final ItemBase STEEL_INGOT;
	
	static
	{
		ISKALLIUM_ESSENCE = new ItemIskalliumEssence().setItemName(ItemBase.ISKALLIUM_ESSENCE_ID);
		STEEL_INGOT = new ItemBase().setItemName(ItemBase.STEEL_INGOT_ID);
	}
	
	public static void initialize()
	{
		;
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerModels()
	{
		final Item[] items = {
				ISKALLIUM_ESSENCE
				,STEEL_INGOT
		};
		
		for (final Item item : items)
		{
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(IRConstants.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
		}
	}
	
	@Mod.EventBusSubscriber(modid = IRConstants.MOD_ID)
	public static class ItemRegistry
	{
		public static final Set<Item> ITEMS = new HashSet<Item>();
		
		@SubscribeEvent
		public static void newRegistry(final RegistryEvent.NewRegistry event)
		{
			;
		}
		
		@SubscribeEvent
		public static void register(final RegistryEvent.Register<Item> event)
		{
			final Item[] items = {
					ISKALLIUM_ESSENCE
					,STEEL_INGOT
			};
			
			final IForgeRegistry<Item> registry = event.getRegistry();
			
			for (final Item item : items)
			{
				registry.register(item);
				ITEMS.add(item);
			}
			
			initialize();
		}
	}
}
