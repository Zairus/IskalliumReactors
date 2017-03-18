package zairus.iskalliumreactors.item;

import net.minecraft.item.Item;
import zairus.iskalliumreactors.IskalliumReactors;

public class ItemBase extends Item
{
	protected String itemName = "";
	
	public ItemBase()
	{
		this.setCreativeTab(IskalliumReactors.creativeTab);
	}
	
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
	
	protected void register()
	{
		IskalliumReactors.proxy.registerItem(this, this.itemName, 0, true);
	}
}
