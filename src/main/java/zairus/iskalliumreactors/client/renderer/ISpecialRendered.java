package zairus.iskalliumreactors.client.renderer;

import javax.annotation.Nullable;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface ISpecialRendered
{
	@SideOnly(Side.CLIENT)
	@Nullable
	public abstract Object getTESR();
}
