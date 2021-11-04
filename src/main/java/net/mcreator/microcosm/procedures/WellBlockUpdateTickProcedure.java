package net.mcreator.microcosm.procedures;

import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.FluidStack;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.fluid.Fluids;

import net.mcreator.microcosm.MicrocosmMod;

import java.util.Map;

public class WellBlockUpdateTickProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency x for procedure WellBlockUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency y for procedure WellBlockUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency z for procedure WellBlockUpdateTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency world for procedure WellBlockUpdateTick!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		{
			TileEntity _ent = world.getTileEntity(new BlockPos((int) (x + 1), (int) y, (int) z));
			int _amount = (int) 15;
			if (_ent != null)
				_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
						.ifPresent(capability -> capability.fill(new FluidStack(Fluids.WATER, _amount), IFluidHandler.FluidAction.EXECUTE));
		}
		{
			TileEntity _ent = world.getTileEntity(new BlockPos((int) (x - 1), (int) y, (int) z));
			int _amount = (int) 15;
			if (_ent != null)
				_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
						.ifPresent(capability -> capability.fill(new FluidStack(Fluids.WATER, _amount), IFluidHandler.FluidAction.EXECUTE));
		}
		{
			TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) (z + 1)));
			int _amount = (int) 15;
			if (_ent != null)
				_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
						.ifPresent(capability -> capability.fill(new FluidStack(Fluids.WATER, _amount), IFluidHandler.FluidAction.EXECUTE));
		}
		{
			TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) (z - 1)));
			int _amount = (int) 15;
			if (_ent != null)
				_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
						.ifPresent(capability -> capability.fill(new FluidStack(Fluids.WATER, _amount), IFluidHandler.FluidAction.EXECUTE));
		}
	}
}
