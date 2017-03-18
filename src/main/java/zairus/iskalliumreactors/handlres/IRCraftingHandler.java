package zairus.iskalliumreactors.handlres;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import zairus.iskalliumreactors.block.IRBlocks;
import zairus.iskalliumreactors.item.IRItems;

public class IRCraftingHandler
{
	public static void addRecipes()
	{
		GameRegistry.addShapedRecipe(
				new ItemStack(IRBlocks.ISKALLIUM_GLASS, 9, 0)
				, new Object[] {
						"ggg"
						,"gig"
						,"ggg"
						,'g'
						,Blocks.GLASS
						,'i'
						,IRItems.ISKALLIUM_ESSENCE
				});
		
		GameRegistry.addShapedRecipe(
				new ItemStack(IRBlocks.ISKALLIUM_GLASS, 9, 0)
				, new Object[] {
						"ggg"
						,"gig"
						,"ggg"
						,'g'
						,Blocks.STAINED_GLASS
						,'i'
						,IRItems.ISKALLIUM_ESSENCE
				});
		
		GameRegistry.addShapedRecipe(
				new ItemStack(IRItems.STEEL_INGOT, 9, 0)
				, new Object[] {
						"ggg"
						,"gig"
						,"ggg"
						,'g'
						,Items.IRON_INGOT
						,'i'
						,Blocks.COAL_BLOCK
				});
		
		GameRegistry.addShapelessRecipe(
				new ItemStack(IRBlocks.STEEL_CASING, 1, 0)
				, new Object[] {
						new ItemStack(IRItems.STEEL_INGOT)
						,new ItemStack(IRItems.STEEL_INGOT)
						,new ItemStack(IRItems.STEEL_INGOT)
						,new ItemStack(IRItems.STEEL_INGOT)
						,new ItemStack(IRItems.STEEL_INGOT)
						,new ItemStack(IRItems.STEEL_INGOT)
						,new ItemStack(IRItems.STEEL_INGOT)
						,new ItemStack(IRItems.STEEL_INGOT)
						,new ItemStack(IRItems.STEEL_INGOT)
				});
		
		GameRegistry.addShapelessRecipe(
				new ItemStack(IRBlocks.ISKALLIUM_ORE, 1, 0)
				, new Object[] {
						new ItemStack(IRItems.ISKALLIUM_ESSENCE)
						,new ItemStack(IRItems.ISKALLIUM_ESSENCE)
						,new ItemStack(IRItems.ISKALLIUM_ESSENCE)
						,new ItemStack(IRItems.ISKALLIUM_ESSENCE)
						,new ItemStack(IRItems.ISKALLIUM_ESSENCE)
						,new ItemStack(IRItems.ISKALLIUM_ESSENCE)
						,new ItemStack(IRItems.ISKALLIUM_ESSENCE)
						,new ItemStack(IRItems.ISKALLIUM_ESSENCE)
						,new ItemStack(IRItems.ISKALLIUM_ESSENCE)
				});
		
		GameRegistry.addShapelessRecipe(
				new ItemStack(IRItems.ISKALLIUM_ESSENCE, 9, 0)
				, new Object[] {
						new ItemStack(IRBlocks.ISKALLIUM_ORE)
				});
		
		GameRegistry.addShapelessRecipe(
				new ItemStack(IRBlocks.STEEL_CONTROLLER)
				, new Object[] {
						new ItemStack(IRBlocks.STEEL_CASING)
						, new ItemStack(IRItems.ISKALLIUM_ESSENCE)
				});
		
		GameRegistry.addShapelessRecipe(
				new ItemStack(IRBlocks.STEEL_POWERTAP)
				, new Object[] {
						new ItemStack(IRBlocks.STEEL_CASING)
						, new ItemStack(Items.REDSTONE)
				});
	}
}
