package net.mcreator.microcosm.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.BlockState;

import net.mcreator.microcosm.block.SmelteryBlock;
import net.mcreator.microcosm.block.SiloBlockBlock;
import net.mcreator.microcosm.block.MineBlockBlock;
import net.mcreator.microcosm.MicrocosmMod;

import java.util.Map;

public class VillageKeyRightClickOnBlockProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency entity for procedure VillageKeyRightClickOnBlock!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency itemstack for procedure VillageKeyRightClickOnBlock!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency x for procedure VillageKeyRightClickOnBlock!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency y for procedure VillageKeyRightClickOnBlock!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency z for procedure VillageKeyRightClickOnBlock!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency world for procedure VillageKeyRightClickOnBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == SiloBlockBlock.block)
				|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == SmelteryBlock.block))) {
			(itemstack).getOrCreateTag().putString("Target",
					((new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())).getDisplayName().getString()));
			(itemstack).getOrCreateTag().putDouble("Tx", x);
			(itemstack).getOrCreateTag().putDouble("Ty", y);
			(itemstack).getOrCreateTag().putDouble("Tz", z);
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(((((itemstack).getOrCreateTag().getString("Target"))) + "" + (": ")
						+ "" + (((itemstack).getOrCreateTag().getDouble("Tx"))) + "" + (", ") + "" + (((itemstack).getOrCreateTag().getDouble("Ty")))
						+ "" + (", ") + "" + (((itemstack).getOrCreateTag().getDouble("Tz"))))), (true));
			}
			(itemstack).getOrCreateTag().putBoolean("TargetSet", (true));
		}
		if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == MineBlockBlock.block)) {
			if (((((itemstack).getOrCreateTag().getString("Target"))).equals(((new ItemStack(SmelteryBlock.block)).getDisplayName().getString())))) {
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean(((itemstack).getOrCreateTag().getString("Target")), (true));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putDouble(((((itemstack).getOrCreateTag().getString("Target"))) + "" + ("X")),
								((itemstack).getOrCreateTag().getDouble("Tx")));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putDouble(((((itemstack).getOrCreateTag().getString("Target"))) + "" + ("Y")),
								((itemstack).getOrCreateTag().getDouble("Ty")));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putDouble(((((itemstack).getOrCreateTag().getString("Target"))) + "" + ("Z")),
								((itemstack).getOrCreateTag().getDouble("Tz")));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent((((new Object() {
						public boolean getValue(IWorld world, BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getBoolean(tag);
							return false;
						}
					}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Target"))) + "" + (": ") + "" + ((new Object() {
						public double getValue(IWorld world, BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(world, new BlockPos((int) x, (int) y, (int) z), ((((itemstack).getOrCreateTag().getString("Target"))) + "" + ("X")))))
							+ "" + (", ") + "" + ((new Object() {
								public double getValue(IWorld world, BlockPos pos, String tag) {
									TileEntity tileEntity = world.getTileEntity(pos);
									if (tileEntity != null)
										return tileEntity.getTileData().getDouble(tag);
									return -1;
								}
							}.getValue(world, new BlockPos((int) x, (int) y, (int) z),
									((((itemstack).getOrCreateTag().getString("Target"))) + "" + ("Y")))))
							+ "" + (", ") + "" + ((new Object() {
								public double getValue(IWorld world, BlockPos pos, String tag) {
									TileEntity tileEntity = world.getTileEntity(pos);
									if (tileEntity != null)
										return tileEntity.getTileData().getDouble(tag);
									return -1;
								}
							}.getValue(world, new BlockPos((int) x, (int) y, (int) z),
									((((itemstack).getOrCreateTag().getString("Target"))) + "" + ("Z"))))))),
							(true));
				}
			}
		}
	}
}
