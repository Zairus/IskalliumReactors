package zairus.iskalliumreactors.item;

import net.minecraft.item.Item;
import zairus.iskalliumreactors.IskalliumReactors;

public class ItemBase extends Item
{
	public static final String ISKALLIUM_ESSENCE_ID = "iskallium_essence";
	public static final String STEEL_INGOT_ID = "steel_ingot";
	
	protected String itemName = "";
	
	public ItemBase()
	{
		this.setCreativeTab(IskalliumReactors.creativeTab);
	}
	
	/*
	protected ItemBase setItemName(String name)
	{
		this.itemName = name;
		
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		
		return this;
	}
	
	protected String getItemName()
	{
		return this.itemName;
	}
	*/
}
