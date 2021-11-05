package net.mcreator.microcosm.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.item.ItemStack;

import net.mcreator.microcosm.MicrocosmMod;

import java.util.Map;

public class WorkOrderBaseItemInHandTickProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency itemstack for procedure WorkOrderBaseItemInHandTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency world for procedure WorkOrderBaseItemInHandTick!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		IWorld world = (IWorld) dependencies.get("world");
		if (world instanceof ServerWorld) {
			((ServerWorld) world).spawnParticle(ParticleTypes.HAPPY_VILLAGER, (((itemstack).getOrCreateTag().getDouble("X")) + 0.5),
					(((itemstack).getOrCreateTag().getDouble("Y")) + 1), (((itemstack).getOrCreateTag().getDouble("Z")) + 0.5), (int) 1, 0.12, 0.12,
					0.12, 1);
		}
	}
}
