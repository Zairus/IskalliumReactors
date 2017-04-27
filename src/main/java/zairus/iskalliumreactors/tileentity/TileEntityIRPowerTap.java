package zairus.iskalliumreactors.tileentity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import cofh.api.energy.IEnergyProvider;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.energy.IEnergyStorage;
import zairus.iskalliumreactors.IRConfig;
import zairus.iskalliumreactors.block.IRBlocks;

public class TileEntityIRPowerTap extends TileEntity implements ITickable, IEnergyStorage, IEnergyProvider //, IEnergyConnection, IEnergyHandler, cofh.api.energy.IEnergyStorage
{
	public int energy = 0;
	public int capacity = 0;
    public int maxReceive = 0;
    public int maxExtract = 1000;
	
    private TileEntityIRController controller;
    
	public TileEntityIRPowerTap()
	{
		;
	}
	
	protected void setController(TileEntityIRController c)
	{
		this.controller = c;
	}
	
	@Override
	public void update()
	{
		this.capacity = (controller != null && controller.getIsReactor())? controller.getGeneratorBlockCount() * IRConfig.eachIskalliumBlockPowerValue : 0;
		
		if (controller != null &&controller.getPos() != null)
		{
			if (this.worldObj.getBlockState(controller.getPos()).getBlock() != IRBlocks.STEEL_CONTROLLER)
			{
				this.capacity = 0;
			}
		}
		
		for (EnumFacing facing : EnumFacing.HORIZONTALS)
		{
			TileEntity te = this.worldObj.getTileEntity(this.getPos().offset(facing));
			if (te != null && !(te instanceof TileEntityIRController))
			{
				try {
					Method m = te.getClass().getMethod("receiveEnergy", new Class[] { EnumFacing.class, int.class, boolean.class });
					
					if (m != null)
					{
						try {
							m.invoke(te, facing.getOpposite(), this.capacity, false);
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
					}
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public boolean hasCapability(net.minecraftforge.common.capabilities.Capability<?> capability, net.minecraft.util.EnumFacing facing)
	{
		if (capability != null && capability.getName() == "net.minecraftforge.energy.IEnergyStorage")
		{
			return true;
		}
		
		return super.hasCapability(capability, facing);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		NBTTagCompound tag = super.writeToNBT(compound);
		
		return tag;
	}
	
	@Override
	public int getEnergyStored(EnumFacing facing)
	{
		return this.capacity;
	}
	
	@Override
	public int getMaxEnergyStored(EnumFacing facing)
	{
		return this.capacity;
	}
	
	@Override
	public boolean canConnectEnergy(EnumFacing facing)
	{
		return true;
	}
	
	@Override
	public int extractEnergy(EnumFacing facing, int maxExtract, boolean simulate)
	{
		return this.extractEnergy(maxExtract, simulate);
	}
	
	@Override
	public int extractEnergy(int extract, boolean simulate)
	{
		return (this.capacity > 1000)? 1000 : this.capacity;
	}
	
	@Override
	public int getEnergyStored()
	{
		return this.capacity;
	}
	
	@Override
	public int getMaxEnergyStored()
	{
		return this.capacity;
	}
	
	@Override
	public int receiveEnergy(int receive, boolean sumulate)
	{
		return 0;
	}
	
	@Override
	public boolean canExtract()
	{
		return true;
	}
	
	@Override
	public boolean canReceive()
	{
		return false;
	}
}
