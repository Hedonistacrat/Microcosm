package net.mcreator.microcosm.procedures;

import net.minecraft.util.Hand;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.microcosm.MicrocosmMod;

import java.util.Map;

public class WellRightClickedProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency entity for procedure WellRightClicked!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem() == Items.BUCKET)) {
			if (entity instanceof LivingEntity) {
				ItemStack _setstack = new ItemStack(Items.WATER_BUCKET);
				_setstack.setCount((int) 1);
				((LivingEntity) entity).setHeldItem(Hand.MAIN_HAND, _setstack);
				if (entity instanceof ServerPlayerEntity)
					((ServerPlayerEntity) entity).inventory.markDirty();
			}
		} else if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == Items.GLASS_BOTTLE)) {
			if (entity instanceof LivingEntity) {
				ItemStack _setstack = new ItemStack(Items.POTION);
				_setstack.setCount((int) 1);
				((LivingEntity) entity).setHeldItem(Hand.MAIN_HAND, _setstack);
				if (entity instanceof ServerPlayerEntity)
					((ServerPlayerEntity) entity).inventory.markDirty();
			}
		}
	}
}
