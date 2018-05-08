package zairus.iskalliumreactors.item.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import zairus.iskalliumreactors.IRConstants;
import zairus.iskalliumreactors.block.IRBlocks;
import zairus.iskalliumreactors.item.IRItems;

public class IRCraftingHandler
{
	@Deprecated
	public static void addRecipes()
	{
		GameRegistry.addShapedRecipe(
				new ResourceLocation(IRConstants.MOD_ID, "iskallium_glass")
				, new ResourceLocation(IRConstants.MOD_ID, "iskallium_reactors")
				, new ItemStack(IRBlocks.ISKALLIUM_GLASS, 9, 0)
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
				new ResourceLocation(IRConstants.MOD_ID, "iskallium_glass")
				, new ResourceLocation(IRConstants.MOD_ID, "iskallium_reactors")
				, new ItemStack(IRBlocks.ISKALLIUM_GLASS, 9, 0)
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
				new ResourceLocation(IRConstants.MOD_ID, "steel_ingot")
				, new ResourceLocation(IRConstants.MOD_ID, "iskallium_reactors")
				, new ItemStack(IRItems.STEEL_INGOT, 9, 0)
				, new Object[] {
						"ggg"
						,"gig"
						,"ggg"
						,'g'
						,Items.IRON_INGOT
						,'i'
						,Blocks.COAL_BLOCK
				});
		
		Ingredient ingredient_steel_ingot = Ingredient.fromItem(IRItems.STEEL_INGOT);
		if (OreDictionary.getOres("ingotSteel").size() > 0)
			ingredient_steel_ingot = Ingredient.fromStacks(OreDictionary.getOres("ingotSteel").get(0));
		
		Ingredient ingredient_iskallium_essence = Ingredient.fromItem(IRItems.ISKALLIUM_ESSENCE);
		Ingredient ingredient_iskallium = Ingredient.fromItem(Item.getItemFromBlock(IRBlocks.ISKALLIUM));
		Ingredient ingredient_steel_casing = Ingredient.fromItem(Item.getItemFromBlock(IRBlocks.STEEL_CASING));
		Ingredient ingredient_redstone = Ingredient.fromItem(Items.REDSTONE);
		
		GameRegistry.addShapelessRecipe(
				new ResourceLocation(IRConstants.MOD_ID, "steel_casing")
				, new ResourceLocation(IRConstants.MOD_ID, "iskallium_reactors")
				, new ItemStack(IRBlocks.STEEL_CASING, 1, 0)
				, ingredient_steel_ingot
				, ingredient_steel_ingot
				, ingredient_steel_ingot
				, ingredient_steel_ingot
				, ingredient_steel_ingot
				, ingredient_steel_ingot
				, ingredient_steel_ingot
				, ingredient_steel_ingot
				, ingredient_steel_ingot);
		
		GameRegistry.addShapelessRecipe(
				new ResourceLocation(IRConstants.MOD_ID, "iskallium")
				, new ResourceLocation(IRConstants.MOD_ID, "iskallium_reactors")
				, new ItemStack(IRBlocks.ISKALLIUM, 1, 0)
				, ingredient_iskallium_essence
				, ingredient_iskallium_essence
				, ingredient_iskallium_essence
				, ingredient_iskallium_essence
				, ingredient_iskallium_essence
				, ingredient_iskallium_essence
				, ingredient_iskallium_essence
				, ingredient_iskallium_essence
				, ingredient_iskallium_essence);
		
		GameRegistry.addShapelessRecipe(
				new ResourceLocation(IRConstants.MOD_ID, "iskallium_essence")
				, new ResourceLocation(IRConstants.MOD_ID, "iskallium_reactors")
				, new ItemStack(IRItems.ISKALLIUM_ESSENCE, 9, 0)
				, ingredient_iskallium);
		
		GameRegistry.addShapelessRecipe(
				new ResourceLocation(IRConstants.MOD_ID, "steel_controller")
				, new ResourceLocation(IRConstants.MOD_ID, "iskallium_reactors")
				, new ItemStack(IRBlocks.STEEL_CONTROLLER)
				, ingredient_steel_casing
				, ingredient_iskallium_essence);
		
		GameRegistry.addShapelessRecipe(
				new ResourceLocation(IRConstants.MOD_ID, "steel_powertap")
				, new ResourceLocation(IRConstants.MOD_ID, "iskallium_reactors")
				, new ItemStack(IRBlocks.STEEL_POWERTAP)
				, ingredient_steel_casing
				, ingredient_redstone);
	}
}
