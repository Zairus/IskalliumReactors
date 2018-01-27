package zairus.iskalliumreactors.tileentity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.IEnergyStorage;
import zairus.iskalliumreactors.IRConfig;
import zairus.iskalliumreactors.block.IRBlocks;

public class TileEntityIRPowerTap extends TileEntity implements ITickable, IEnergyStorage
{
	public int energy = 0;
	public int capacity = 1000000;
    public int maxReceive = 0;
    public int maxExtract = 1000;
	
    public static final Capability<IEnergyStorage> ENERGY_HANDLER = null;
    
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
		if (this.worldObj.isRemote)
			return;
		
		int reactorYield = (controller != null && controller.getIsReactor())? controller.getGeneratorBlockCount() * IRConfig.eachIskalliumBlockPowerValue : 0;
		
		if (this.energy < this.capacity)
			this.energy += reactorYield;
		
		if (controller != null && controller.getPos() != null)
		{
			if (this.worldObj.getBlockState(controller.getPos()).getBlock() != IRBlocks.STEEL_CONTROLLER)
			{
				this.energy = 0;
			}
		}
		
		for (EnumFacing facing : EnumFacing.VALUES)
		{
			TileEntity te = this.worldObj.getTileEntity(this.getPos().offset(facing));
			boolean flag = false;
			
			if (te != null && !(te instanceof TileEntityIRController))
			{
				if (te.hasCapability(ENERGY_HANDLER, facing.getOpposite()))
				{
					IEnergyStorage store = te.getCapability(ENERGY_HANDLER, facing.getOpposite());
					int Energy = this.extractEnergy(reactorYield, false);
					int EnergyReceived = store.receiveEnergy(Energy, false);
					reactorYield -= EnergyReceived;
					this.energy += reactorYield;
					flag = true;
				}
			}
			//TileCapBank
			
			if (te != null && !(te instanceof TileEntityIRController) && !flag)
			{
				try {
					Method m = te.getClass().getMethod("receiveEnergy", new Class[] { EnumFacing.class, int.class, boolean.class });
					
					if (m != null)
					{
						try {
							Object o = m.invoke(te, facing.getOpposite(), this.extractEnergy(reactorYield, false), false);
							if (o instanceof Integer)
							{
								reactorYield -= (int)o;
								this.energy += reactorYield;
							}
						} catch (IllegalAccessException e) {
						} catch (IllegalArgumentException e) {
						} catch (InvocationTargetException e) {
						}
					}
				} catch (NoSuchMethodException e) {
				} catch (SecurityException e) {
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
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, net.minecraft.util.EnumFacing facing)
	{
		if (capability != null && capability.getName() == "net.minecraftforge.energy.IEnergyStorage")
		{
			return (T) this;
		}
		
		return super.getCapability(capability, facing);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		if (compound.hasKey("IR_EnergyStored"))
			this.energy = compound.getInteger("IR_EnergyStored");
		
		super.readFromNBT(compound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		NBTTagCompound tag = super.writeToNBT(compound);
		
		tag.setInteger("IR_EnergyStored", this.energy);
		
		return tag;
	}
	
	@Override
	public int extractEnergy(int extract, boolean simulate)
	{
		int amount = (extract <= this.energy)?  extract : this.energy;
		
		this.energy -= amount;
		
		if (this.energy < 0)
			this.energy = 0;
		
		return amount;
	}
	
	@Override
	public int getEnergyStored()
	{
		return this.energy;
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
