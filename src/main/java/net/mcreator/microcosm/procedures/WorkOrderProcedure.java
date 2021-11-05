package net.mcreator.microcosm.procedures;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.item.ItemStack;

import net.mcreator.microcosm.item.WorkOrderBaseItem;
import net.mcreator.microcosm.MicrocosmMod;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.Map;

public class WorkOrderProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency x for procedure WorkOrder!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency y for procedure WorkOrder!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency z for procedure WorkOrder!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency world for procedure WorkOrder!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		ItemStack WorkOrder = ItemStack.EMPTY;
		if (((new Object() {
			public int getAmount(IWorld world, BlockPos pos, int sltid) {
				AtomicInteger _retval = new AtomicInteger(0);
				TileEntity _ent = world.getTileEntity(pos);
				if (_ent != null) {
					_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
						_retval.set(capability.getStackInSlot(sltid).getCount());
					});
				}
				return _retval.get();
			}
		}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (2))) <= 0)) {
			WorkOrder = new ItemStack(WorkOrderBaseItem.block);
			(WorkOrder).getOrCreateTag().putBoolean(
					((new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())).getDisplayName().getString()),
					(true));
			(WorkOrder).getOrCreateTag().putString("WorkOrder",
					((new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())).getDisplayName().getString()));
			(WorkOrder).getOrCreateTag().putDouble("X", x);
			(WorkOrder).getOrCreateTag().putDouble("Y", y);
			(WorkOrder).getOrCreateTag().putDouble("Z", z);
			((WorkOrder)).setDisplayName(new StringTextComponent(
					((((new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())).getDisplayName().getString())) + ""
							+ (": ") + "" + (((WorkOrder).getOrCreateTag().getDouble("X"))) + "" + (", ") + ""
							+ (((WorkOrder).getOrCreateTag().getDouble("Y"))) + "" + (", ") + "" + (((WorkOrder).getOrCreateTag().getDouble("Z"))))));
			{
				TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
				if (_ent != null) {
					final int _sltid = (int) (2);
					final ItemStack _setstack = (WorkOrder);
					_setstack.setCount((int) 1);
					_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
						if (capability instanceof IItemHandlerModifiable) {
							((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
						}
					});
				}
			}
		}
	}
}
