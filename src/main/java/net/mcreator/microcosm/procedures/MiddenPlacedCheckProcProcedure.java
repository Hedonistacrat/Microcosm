package net.mcreator.microcosm.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.Entity;

import net.mcreator.microcosm.block.MiddenBlockBlock;
import net.mcreator.microcosm.block.FarmHouseBlockBlock;
import net.mcreator.microcosm.MicrocosmMod;

import java.util.Map;

public class MiddenPlacedCheckProcProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency entity for procedure MiddenPlacedCheckProc!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency x for procedure MiddenPlacedCheckProc!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency y for procedure MiddenPlacedCheckProc!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency z for procedure MiddenPlacedCheckProc!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency world for procedure MiddenPlacedCheckProc!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		boolean CanBePlaced = false;
		if ((!(((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock() == FarmHouseBlockBlock.block) || (((world
				.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock() == FarmHouseBlockBlock.block)
				|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock() == FarmHouseBlockBlock.block)
						|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock() == FarmHouseBlockBlock.block)))))) {
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Midden must be placed adjacent to a Farm House"), (false));
			}
			world.destroyBlock(new BlockPos((int) x, (int) y, (int) z), false);
			if (world instanceof World && !world.isRemote()) {
				ItemEntity entityToSpawn = new ItemEntity((World) world, x, (y + 1), z, new ItemStack(MiddenBlockBlock.block));
				entityToSpawn.setPickupDelay((int) 20);
				world.addEntity(entityToSpawn);
			}
		}
	}
}
