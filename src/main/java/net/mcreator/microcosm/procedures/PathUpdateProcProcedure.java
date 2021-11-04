package net.mcreator.microcosm.procedures;

import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.FluidStack;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Direction;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tags.BlockTags;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.fluid.Fluids;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.mcreator.microcosm.block.WellBlockBlock;
import net.mcreator.microcosm.block.PathXCornerBlock;
import net.mcreator.microcosm.block.PathTCornerBlock;
import net.mcreator.microcosm.block.PathStraightBlockBlock;
import net.mcreator.microcosm.block.PathNoneBlockBlock;
import net.mcreator.microcosm.block.PathEndBlockBlock;
import net.mcreator.microcosm.block.PathCornerBlockBlock;
import net.mcreator.microcosm.block.FarmHouseBlockBlock;
import net.mcreator.microcosm.MicrocosmMod;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.Map;

public class PathUpdateProcProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency x for procedure PathUpdateProc!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency y for procedure PathUpdateProc!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency z for procedure PathUpdateProc!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency world for procedure PathUpdateProc!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		String fenceUpdate = "";
		String fenceGroup = "";
		double LowestPath = 0;
		double CurrentPath = 0;
		double PathEast = 0;
		double PathWest = 0;
		double PathNorth = 0;
		double PathSouth = 0;
		double LowestEW = 0;
		double LowestSN = 0;
		double MaxPath = 0;
		boolean North = false;
		boolean South = false;
		boolean East = false;
		boolean West = false;
		boolean FluidReciever = false;
		fenceGroup = (String) "Path";
		fenceUpdate = (String) "PathUpdate";
		MaxPath = (double) 100;
		North = (boolean) (BlockTags.getCollection().getTagByID(new ResourceLocation(("forge:micropath").toLowerCase(java.util.Locale.ENGLISH)))
				.contains((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock()));
		South = (boolean) (BlockTags.getCollection().getTagByID(new ResourceLocation(("forge:micropath").toLowerCase(java.util.Locale.ENGLISH)))
				.contains((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock()));
		West = (boolean) (BlockTags.getCollection().getTagByID(new ResourceLocation(("forge:micropath").toLowerCase(java.util.Locale.ENGLISH)))
				.contains((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock()));
		East = (boolean) (BlockTags.getCollection().getTagByID(new ResourceLocation(("forge:micropath").toLowerCase(java.util.Locale.ENGLISH)))
				.contains((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock()));
		if (((new Object() {
			public boolean getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getBoolean(tag);
				return false;
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), fenceUpdate)) == (true))) {
			if (((((new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) (z - 1)), fenceGroup)) == (true)) || North) && ((((new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) (x + 1), (int) y, (int) z), fenceGroup)) == (true)) || East) && ((((new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) (z + 1)), fenceGroup)) == (true)) || South) && (((new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) (x - 1), (int) y, (int) z), fenceGroup)) == (true)) || West))))) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), PathXCornerBlock.block.getDefaultState(), 3);
				try {
					BlockState _bs = world.getBlockState(new BlockPos((int) x, (int) y, (int) z));
					DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
					if (_property != null) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), _bs.with(_property, Direction.NORTH), 3);
					} else {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), _bs.with(
								(EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"), Direction.NORTH.getAxis()), 3);
					}
				} catch (Exception e) {
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean(fenceGroup, (true));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean(fenceUpdate, (false));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			} else if (((((new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) (z - 1)), fenceGroup)) == (true)) || North) && ((((new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) (x + 1), (int) y, (int) z), fenceGroup)) == (true)) || East) && (((new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) (z + 1)), fenceGroup)) == (true)) || South)))) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), PathTCornerBlock.block.getDefaultState(), 3);
				try {
					BlockState _bs = world.getBlockState(new BlockPos((int) x, (int) y, (int) z));
					DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
					if (_property != null) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), _bs.with(_property, Direction.EAST), 3);
					} else {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), _bs.with(
								(EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"), Direction.EAST.getAxis()), 3);
					}
				} catch (Exception e) {
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean(fenceGroup, (true));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean(fenceUpdate, (false));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			} else if (((((new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) (x + 1), (int) y, (int) z), fenceGroup)) == (true)) || East) && ((((new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) (z + 1)), fenceGroup)) == (true)) || South) && (((new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) (x - 1), (int) y, (int) z), fenceGroup)) == (true)) || West)))) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), PathTCornerBlock.block.getDefaultState(), 3);
				try {
					BlockState _bs = world.getBlockState(new BlockPos((int) x, (int) y, (int) z));
					DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
					if (_property != null) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), _bs.with(_property, Direction.SOUTH), 3);
					} else {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), _bs.with(
								(EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"), Direction.SOUTH.getAxis()), 3);
					}
				} catch (Exception e) {
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean(fenceGroup, (true));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean(fenceUpdate, (false));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			} else if (((((new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) (z + 1)), fenceGroup)) == (true)) || South) && ((((new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) (x - 1), (int) y, (int) z), fenceGroup)) == (true)) || West) && (((new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) (z - 1)), fenceGroup)) == (true)) || North)))) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), PathTCornerBlock.block.getDefaultState(), 3);
				try {
					BlockState _bs = world.getBlockState(new BlockPos((int) x, (int) y, (int) z));
					DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
					if (_property != null) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), _bs.with(_property, Direction.WEST), 3);
					} else {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), _bs.with(
								(EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"), Direction.WEST.getAxis()), 3);
					}
				} catch (Exception e) {
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean(fenceGroup, (true));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean(fenceUpdate, (false));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			} else if (((((new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) (x - 1), (int) y, (int) z), fenceGroup)) == (true)) || West) && ((((new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) (z - 1)), fenceGroup)) == (true)) || North) && (((new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) (x + 1), (int) y, (int) z), fenceGroup)) == (true)) || East)))) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), PathTCornerBlock.block.getDefaultState(), 3);
				try {
					BlockState _bs = world.getBlockState(new BlockPos((int) x, (int) y, (int) z));
					DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
					if (_property != null) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), _bs.with(_property, Direction.NORTH), 3);
					} else {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), _bs.with(
								(EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"), Direction.NORTH.getAxis()), 3);
					}
				} catch (Exception e) {
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean(fenceGroup, (true));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean(fenceUpdate, (false));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			} else if (((((new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) (z - 1)), fenceGroup)) == (true)) || North) && (((new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) (x + 1), (int) y, (int) z), fenceGroup)) == (true)) || East))) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), PathCornerBlockBlock.block.getDefaultState(), 3);
				try {
					BlockState _bs = world.getBlockState(new BlockPos((int) x, (int) y, (int) z));
					DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
					if (_property != null) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), _bs.with(_property, Direction.NORTH), 3);
					} else {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), _bs.with(
								(EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"), Direction.NORTH.getAxis()), 3);
					}
				} catch (Exception e) {
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean(fenceGroup, (true));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean(fenceUpdate, (false));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			} else if (((((new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) (x + 1), (int) y, (int) z), fenceGroup)) == (true)) || East) && (((new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) (z + 1)), fenceGroup)) == (true)) || South))) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), PathCornerBlockBlock.block.getDefaultState(), 3);
				try {
					BlockState _bs = world.getBlockState(new BlockPos((int) x, (int) y, (int) z));
					DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
					if (_property != null) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), _bs.with(_property, Direction.EAST), 3);
					} else {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), _bs.with(
								(EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"), Direction.EAST.getAxis()), 3);
					}
				} catch (Exception e) {
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean(fenceGroup, (true));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean(fenceUpdate, (false));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			} else if (((((new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) (z + 1)), fenceGroup)) == (true)) || South) && (((new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) (x - 1), (int) y, (int) z), fenceGroup)) == (true)) || West))) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), PathCornerBlockBlock.block.getDefaultState(), 3);
				try {
					BlockState _bs = world.getBlockState(new BlockPos((int) x, (int) y, (int) z));
					DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
					if (_property != null) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), _bs.with(_property, Direction.SOUTH), 3);
					} else {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), _bs.with(
								(EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"), Direction.SOUTH.getAxis()), 3);
					}
				} catch (Exception e) {
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean(fenceGroup, (true));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean(fenceUpdate, (false));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			} else if (((((new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) (x - 1), (int) y, (int) z), fenceGroup)) == (true)) || West) && (((new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) (z - 1)), fenceGroup)) == (true)) || North))) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), PathCornerBlockBlock.block.getDefaultState(), 3);
				try {
					BlockState _bs = world.getBlockState(new BlockPos((int) x, (int) y, (int) z));
					DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
					if (_property != null) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), _bs.with(_property, Direction.WEST), 3);
					} else {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), _bs.with(
								(EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"), Direction.WEST.getAxis()), 3);
					}
				} catch (Exception e) {
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean(fenceGroup, (true));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean(fenceUpdate, (false));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			} else if (((((new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) (z - 1)), fenceGroup)) == (true)) || North) && (((new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) (z + 1)), fenceGroup)) == (true)) || South))) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), PathStraightBlockBlock.block.getDefaultState(), 3);
				try {
					BlockState _bs = world.getBlockState(new BlockPos((int) x, (int) y, (int) z));
					DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
					if (_property != null) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), _bs.with(_property, Direction.EAST), 3);
					} else {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), _bs.with(
								(EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"), Direction.EAST.getAxis()), 3);
					}
				} catch (Exception e) {
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean(fenceGroup, (true));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean(fenceUpdate, (false));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			} else if (((((new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) (x + 1), (int) y, (int) z), fenceGroup)) == (true)) || East) && (((new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) (x - 1), (int) y, (int) z), fenceGroup)) == (true)) || West))) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), PathStraightBlockBlock.block.getDefaultState(), 3);
				try {
					BlockState _bs = world.getBlockState(new BlockPos((int) x, (int) y, (int) z));
					DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
					if (_property != null) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), _bs.with(_property, Direction.NORTH), 3);
					} else {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), _bs.with(
								(EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"), Direction.NORTH.getAxis()), 3);
					}
				} catch (Exception e) {
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean(fenceGroup, (true));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean(fenceUpdate, (false));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			} else if ((((new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) (z - 1)), fenceGroup)) == (true)) || North)) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), PathEndBlockBlock.block.getDefaultState(), 3);
				try {
					BlockState _bs = world.getBlockState(new BlockPos((int) x, (int) y, (int) z));
					DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
					if (_property != null) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), _bs.with(_property, Direction.NORTH), 3);
					} else {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), _bs.with(
								(EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"), Direction.NORTH.getAxis()), 3);
					}
				} catch (Exception e) {
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean(fenceGroup, (true));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean(fenceUpdate, (false));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			} else if ((((new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) (x + 1), (int) y, (int) z), fenceGroup)) == (true)) || East)) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), PathEndBlockBlock.block.getDefaultState(), 3);
				try {
					BlockState _bs = world.getBlockState(new BlockPos((int) x, (int) y, (int) z));
					DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
					if (_property != null) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), _bs.with(_property, Direction.EAST), 3);
					} else {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), _bs.with(
								(EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"), Direction.EAST.getAxis()), 3);
					}
				} catch (Exception e) {
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean(fenceGroup, (true));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean(fenceUpdate, (false));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			} else if ((((new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) (z + 1)), fenceGroup)) == (true)) || South)) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), PathEndBlockBlock.block.getDefaultState(), 3);
				try {
					BlockState _bs = world.getBlockState(new BlockPos((int) x, (int) y, (int) z));
					DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
					if (_property != null) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), _bs.with(_property, Direction.SOUTH), 3);
					} else {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), _bs.with(
								(EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"), Direction.SOUTH.getAxis()), 3);
					}
				} catch (Exception e) {
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean(fenceGroup, (true));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean(fenceUpdate, (false));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			} else if ((((new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) (x - 1), (int) y, (int) z), fenceGroup)) == (true)) || West)) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), PathEndBlockBlock.block.getDefaultState(), 3);
				try {
					BlockState _bs = world.getBlockState(new BlockPos((int) x, (int) y, (int) z));
					DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
					if (_property != null) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), _bs.with(_property, Direction.WEST), 3);
					} else {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), _bs.with(
								(EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"), Direction.WEST.getAxis()), 3);
					}
				} catch (Exception e) {
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean(fenceGroup, (true));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean(fenceUpdate, (false));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			} else {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), PathNoneBlockBlock.block.getDefaultState(), 3);
				try {
					BlockState _bs = world.getBlockState(new BlockPos((int) x, (int) y, (int) z));
					DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
					if (_property != null) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), _bs.with(_property, Direction.NORTH), 3);
					} else {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), _bs.with(
								(EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"), Direction.NORTH.getAxis()), 3);
					}
				} catch (Exception e) {
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean(fenceGroup, (true));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean(fenceUpdate, (false));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			}
		}
		if ((((((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock() == WellBlockBlock.block)
				|| ((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock() == WellBlockBlock.block))
				|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock() == WellBlockBlock.block))
				|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock() == WellBlockBlock.block))) {
			if (!world.isRemote()) {
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putDouble("WellPath", 1);
				if (world instanceof World)
					((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
		} else {
			PathEast = (double) (new Object() {
				public double getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(world, new BlockPos((int) (x + 1), (int) y, (int) z), "WellPath"));
			PathWest = (double) (new Object() {
				public double getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(world, new BlockPos((int) (x - 1), (int) y, (int) z), "WellPath"));
			PathNorth = (double) (new Object() {
				public double getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) (z + 1)), "WellPath"));
			PathSouth = (double) (new Object() {
				public double getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) (z - 1)), "WellPath"));
			LowestEW = (double) 0;
			LowestSN = (double) 0;
			if (((PathEast > 0) && (PathWest > 0))) {
				if ((PathEast > PathWest)) {
					LowestEW = (double) PathWest;
				} else {
					LowestEW = (double) PathEast;
				}
			} else if ((PathEast > 0)) {
				LowestEW = (double) PathEast;
			} else if ((PathWest > 0)) {
				LowestEW = (double) PathWest;
			}
			if (((PathSouth > 0) && (PathNorth > 0))) {
				if ((PathSouth > PathNorth)) {
					LowestSN = (double) PathNorth;
				} else {
					LowestSN = (double) PathSouth;
				}
			} else if ((PathSouth > 0)) {
				LowestSN = (double) PathSouth;
			} else if ((PathNorth > 0)) {
				LowestSN = (double) PathNorth;
			}
			if (((LowestEW > 0) && (LowestSN > 0))) {
				if ((LowestEW > LowestSN)) {
					LowestPath = (double) LowestSN;
				} else {
					LowestPath = (double) LowestEW;
				}
			} else if ((LowestEW > 0)) {
				LowestPath = (double) LowestEW;
			} else if ((LowestSN > 0)) {
				LowestPath = (double) LowestSN;
			}
			if ((LowestPath > 0)) {
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putDouble("WellPath", (LowestPath + 1));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			}
		}
		if (((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock() == FarmHouseBlockBlock.block)) {
			if (!world.isRemote()) {
				BlockPos _bp = new BlockPos((int) (x + 1), (int) y, (int) z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putBoolean("WellReciever", (true));
				if (world instanceof World)
					((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
		}
		if (((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock() == FarmHouseBlockBlock.block)) {
			if (!world.isRemote()) {
				BlockPos _bp = new BlockPos((int) (x - 1), (int) y, (int) z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putBoolean("WellReciever", (true));
				if (world instanceof World)
					((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
		}
		if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock() == FarmHouseBlockBlock.block)) {
			if (!world.isRemote()) {
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) (z + 1));
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putBoolean("WellReciever", (true));
				if (world instanceof World)
					((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
		}
		if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock() == FarmHouseBlockBlock.block)) {
			if (!world.isRemote()) {
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) (z - 1));
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putBoolean("WellReciever", (true));
				if (world instanceof World)
					((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
		}
		if (((new Object() {
			public double getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return -1;
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "WellPath")) > MaxPath)) {
			if (!world.isRemote()) {
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putDouble("WellPath", 0);
				if (world instanceof World)
					((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
		}
		if (((new Object() {
			public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
				AtomicInteger _retval = new AtomicInteger(0);
				TileEntity _ent = world.getTileEntity(pos);
				if (_ent != null)
					_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
							.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
				return _retval.get();
			}
		}.drainTankSimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) 4)) > 3)) {
			if (((((new Object() {
				public double getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(world, new BlockPos((int) (x + 1), (int) y, (int) z), "WellPath")) > (new Object() {
				public double getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "WellPath"))) && ((new Object() {
				public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
					AtomicInteger _retval = new AtomicInteger(0);
					TileEntity _ent = world.getTileEntity(pos);
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
					return _retval.get();
				}
			}.drainTankSimulate(world, new BlockPos((int) (x + 1), (int) y, (int) z), (int) 50)) < (new Object() {
				public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
					AtomicInteger _retval = new AtomicInteger(0);
					TileEntity _ent = world.getTileEntity(pos);
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
					return _retval.get();
				}
			}.drainTankSimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) 50)))) || (new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) (x + 1), (int) y, (int) z), "WellReciever")))) {
				if (((new Object() {
					public int fillTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval
									.set(capability.fill(new FluidStack(Fluids.WATER, amount), IFluidHandler.FluidAction.SIMULATE)));
						return _retval.get();
					}
				}.fillTankSimulate(world, new BlockPos((int) (x + 1), (int) y, (int) z), (int) 1)) > 0)) {
					{
						TileEntity _ent = world.getTileEntity(new BlockPos((int) (x + 1), (int) y, (int) z));
						int _amount = (int) 1;
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(
									capability -> capability.fill(new FluidStack(Fluids.WATER, _amount), IFluidHandler.FluidAction.EXECUTE));
					}
					{
						TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
						int _amount = (int) 1;
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
									.ifPresent(capability -> capability.drain(_amount, IFluidHandler.FluidAction.EXECUTE));
					}
				}
			}
			if (((((new Object() {
				public double getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(world, new BlockPos((int) (x - 1), (int) y, (int) z), "WellPath")) > (new Object() {
				public double getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "WellPath"))) && ((new Object() {
				public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
					AtomicInteger _retval = new AtomicInteger(0);
					TileEntity _ent = world.getTileEntity(pos);
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
					return _retval.get();
				}
			}.drainTankSimulate(world, new BlockPos((int) (x - 1), (int) y, (int) z), (int) 50)) < (new Object() {
				public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
					AtomicInteger _retval = new AtomicInteger(0);
					TileEntity _ent = world.getTileEntity(pos);
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
					return _retval.get();
				}
			}.drainTankSimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) 50)))) || (new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) (x - 1), (int) y, (int) z), "WellReciever")))) {
				if (((new Object() {
					public int fillTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval
									.set(capability.fill(new FluidStack(Fluids.WATER, amount), IFluidHandler.FluidAction.SIMULATE)));
						return _retval.get();
					}
				}.fillTankSimulate(world, new BlockPos((int) (x - 1), (int) y, (int) z), (int) 1)) > 0)) {
					{
						TileEntity _ent = world.getTileEntity(new BlockPos((int) (x - 1), (int) y, (int) z));
						int _amount = (int) 1;
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(
									capability -> capability.fill(new FluidStack(Fluids.WATER, _amount), IFluidHandler.FluidAction.EXECUTE));
					}
					{
						TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
						int _amount = (int) 1;
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
									.ifPresent(capability -> capability.drain(_amount, IFluidHandler.FluidAction.EXECUTE));
					}
				}
			}
			if (((((new Object() {
				public double getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) (z + 1)), "WellPath")) > (new Object() {
				public double getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "WellPath"))) && ((new Object() {
				public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
					AtomicInteger _retval = new AtomicInteger(0);
					TileEntity _ent = world.getTileEntity(pos);
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
					return _retval.get();
				}
			}.drainTankSimulate(world, new BlockPos((int) x, (int) y, (int) (z + 1)), (int) 50)) < (new Object() {
				public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
					AtomicInteger _retval = new AtomicInteger(0);
					TileEntity _ent = world.getTileEntity(pos);
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
					return _retval.get();
				}
			}.drainTankSimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) 50)))) || (new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) (z + 1)), "WellReciever")))) {
				if (((new Object() {
					public int fillTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval
									.set(capability.fill(new FluidStack(Fluids.WATER, amount), IFluidHandler.FluidAction.SIMULATE)));
						return _retval.get();
					}
				}.fillTankSimulate(world, new BlockPos((int) x, (int) y, (int) (z + 1)), (int) 1)) > 0)) {
					{
						TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) (z + 1)));
						int _amount = (int) 1;
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(
									capability -> capability.fill(new FluidStack(Fluids.WATER, _amount), IFluidHandler.FluidAction.EXECUTE));
					}
					{
						TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
						int _amount = (int) 1;
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
									.ifPresent(capability -> capability.drain(_amount, IFluidHandler.FluidAction.EXECUTE));
					}
				}
			}
			if (((((new Object() {
				public double getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) (z - 1)), "WellPath")) > (new Object() {
				public double getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "WellPath"))) && ((new Object() {
				public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
					AtomicInteger _retval = new AtomicInteger(0);
					TileEntity _ent = world.getTileEntity(pos);
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
					return _retval.get();
				}
			}.drainTankSimulate(world, new BlockPos((int) x, (int) y, (int) (z - 1)), (int) 50)) < (new Object() {
				public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
					AtomicInteger _retval = new AtomicInteger(0);
					TileEntity _ent = world.getTileEntity(pos);
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
					return _retval.get();
				}
			}.drainTankSimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) 50)))) || (new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) (z - 1)), "WellReciever")))) {
				if (((new Object() {
					public int fillTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval
									.set(capability.fill(new FluidStack(Fluids.WATER, amount), IFluidHandler.FluidAction.SIMULATE)));
						return _retval.get();
					}
				}.fillTankSimulate(world, new BlockPos((int) x, (int) y, (int) (z - 1)), (int) 1)) > 0)) {
					{
						TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) (z - 1)));
						int _amount = (int) 1;
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(
									capability -> capability.fill(new FluidStack(Fluids.WATER, _amount), IFluidHandler.FluidAction.EXECUTE));
					}
					{
						TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
						int _amount = (int) 1;
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
									.ifPresent(capability -> capability.drain(_amount, IFluidHandler.FluidAction.EXECUTE));
					}
				}
			}
		}
		if (((((new Object() {
			public double getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return -1;
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "WellPath")) == 0) || ((new Object() {
			public double getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return -1;
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "WellPath")) > 99)) && ((new Object() {
			public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
				AtomicInteger _retval = new AtomicInteger(0);
				TileEntity _ent = world.getTileEntity(pos);
				if (_ent != null)
					_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
							.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
				return _retval.get();
			}
		}.drainTankSimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) 1)) > 0))) {
			{
				TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
				int _amount = (int) 1;
				if (_ent != null)
					_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
							.ifPresent(capability -> capability.drain(_amount, IFluidHandler.FluidAction.EXECUTE));
			}
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(ParticleTypes.SPLASH, (x + 0.5), (y + 0.5), (z + 0.5), (int) 10, 0.5, 0.5, 0.5, 0.1);
			}
		}
	}
}
