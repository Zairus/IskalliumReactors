package zairus.iskalliumreactors.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class IRBlock extends Block
{
	public static final String ISKALLIUM_ID = "iskallium_ore";
	public static final String ISKALLIUM_ORE_ID = "iskallium_stone_ore";
	public static final String ISKALLIUM_GLASS_ID = "iskallium_glass";
	public static final String STEEL_CASING_ID = "steel_casing";
	public static final String STEEL_CONTROLLER_ID = "steel_controller";
	public static final String STEEL_POWERTAP_ID = "steel_powertap";
	
	public IRBlock(Material material)
	{
		super(material);
	}
}
