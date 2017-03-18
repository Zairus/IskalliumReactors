package zairus.iskalliumreactors.item;

public class IRItems
{
	public static final ItemBase ISKALLIUM_ESSENCE;
	public static final ItemBase STEEL_INGOT;
	
	static
	{
		ISKALLIUM_ESSENCE = new ItemIskalliumEssence().setItemName("iskallium_essence");
		STEEL_INGOT = new ItemBase().setItemName("steel_ingot");
	}
	
	public static final void register()
	{
		ISKALLIUM_ESSENCE.register();
		STEEL_INGOT.register();
	}
}
