package net.mcreator.microcosm.procedures;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.microcosm.MicrocosmMod;

import java.util.Map;

public class VillageKeyRightClickedInAirProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency entity for procedure VillageKeyRightClickedInAir!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency itemstack for procedure VillageKeyRightClickedInAir!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		(itemstack).getOrCreateTag().putBoolean("TargetSet", (false));
		(itemstack).getOrCreateTag().putString("Target", "null");
		(itemstack).getOrCreateTag().putDouble("Tx", 0);
		(itemstack).getOrCreateTag().putDouble("Ty", 0);
		(itemstack).getOrCreateTag().putDouble("Tz", 0);
		if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
			((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Cleared"), (true));
		}
	}
}
