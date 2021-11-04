package net.mcreator.microcosm.procedures;

import net.minecraft.item.ItemStack;

import net.mcreator.microcosm.MicrocosmMod;

import java.util.Map;

public class VillageKeyMakeItemGlowProcedure {
	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency itemstack for procedure VillageKeyMakeItemGlow!");
			return false;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		return ((itemstack).getOrCreateTag().getBoolean("TargetSet"));
	}
}
