package net.mcreator.microcosm.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.state.Property;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.mcreator.microcosm.block.GroveCutBlockBlock;
import net.mcreator.microcosm.block.GroveBlockBlock;
import net.mcreator.microcosm.MicrocosmMod;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.Random;
import java.util.Map;

public class SawmillBlockUpdateTickProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency x for procedure SawmillBlockUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency y for procedure SawmillBlockUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency z for procedure SawmillBlockUpdateTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency world for procedure SawmillBlockUpdateTick!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double chance = 0;
		double roll = 0;
		double target = 0;
		double stacksize = 0;
		double logroll = 0;
		double ScanRadius = 0;
		double ScanX = 0;
		double ScanZ = 0;
		double RegrowChance = 0;
		boolean thisTick = false;
		chance = (double) 8;
		ScanRadius = (double) 1;
		RegrowChance = (double) 12;
		ScanX = (double) (0 - ScanRadius);
		ScanZ = (double) (0 - ScanRadius);
		thisTick = (boolean) (false);
		for (int index0 = 0; index0 < (int) ((1 + (ScanRadius * 2))); index0++) {
			for (int index1 = 0; index1 < (int) ((1 + (ScanRadius * 2))); index1++) {
				if (((world.getBlockState(new BlockPos((int) (x + ScanX), (int) y, (int) (z + ScanZ)))).getBlock() == GroveCutBlockBlock.block)) {
					if ((((new Random()).nextInt((int) RegrowChance + 1)) == 0)) {
						{
							BlockPos _bp = new BlockPos((int) (x + ScanX), (int) y, (int) (z + ScanZ));
							BlockState _bs = GroveBlockBlock.block.getDefaultState();
							BlockState _bso = world.getBlockState(_bp);
							for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
								Property _property = _bs.getBlock().getStateContainer().getProperty(entry.getKey().getName());
								if (_property != null && _bs.get(_property) != null)
									try {
										_bs = _bs.with(_property, (Comparable) entry.getValue());
									} catch (Exception e) {
									}
							}
							world.setBlockState(_bp, _bs, 3);
						}
						thisTick = (boolean) (true);
					}
				} else if (((!thisTick)
						&& (((world.getBlockState(new BlockPos((int) (x + ScanX), (int) y, (int) (z + ScanZ)))).getBlock() == GroveBlockBlock.block)
								&& (((new Random()).nextInt((int) chance + 1)) == 0)))) {
					{
						BlockPos _bp = new BlockPos((int) (x + ScanX), (int) y, (int) (z + ScanZ));
						BlockState _bs = GroveCutBlockBlock.block.getDefaultState();
						BlockState _bso = world.getBlockState(_bp);
						for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
							Property _property = _bs.getBlock().getStateContainer().getProperty(entry.getKey().getName());
							if (_property != null && _bs.get(_property) != null)
								try {
									_bs = _bs.with(_property, (Comparable) entry.getValue());
								} catch (Exception e) {
								}
						}
						world.setBlockState(_bp, _bs, 3);
					}
					if (world instanceof ServerWorld) {
						((ServerWorld) world).spawnParticle(ParticleTypes.HAPPY_VILLAGER, ((x + ScanX) + 0.5), (y + 1), ((z + ScanZ) + 0.5), (int) 5,
								0.25, 0.25, 0.25, 0.1);
					}
					if (world instanceof ServerWorld) {
						((ServerWorld) world).spawnParticle(ParticleTypes.HAPPY_VILLAGER, (x + 0.5), (y + 1), (z + 0.5), (int) 1, 0.25, 0.25, 0.25,
								0.1);
					}
					if (world instanceof World && !world.isRemote()) {
						((World) world).playSound(null, new BlockPos((int) (x + ScanX), (int) y, (int) (z + ScanZ)),
								(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.wood.break")),
								SoundCategory.NEUTRAL, (float) 0.25, (float) 1);
					} else {
						((World) world).playSound((x + ScanX), y, (z + ScanZ),
								(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.wood.break")),
								SoundCategory.NEUTRAL, (float) 0.25, (float) 1, false);
					}
					logroll = (double) ((new Random()).nextInt((int) 5 + 1));
					if ((logroll == 0)) {
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
						}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (0))) < 64)) {
							{
								TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
								if (_ent != null) {
									final int _sltid = (int) (0);
									final ItemStack _setstack = new ItemStack(Blocks.OAK_LOG);
									_setstack.setCount((int) ((new Object() {
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
									}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (0))) + 1));
									_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
										if (capability instanceof IItemHandlerModifiable) {
											((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
										}
									});
								}
							}
						}
					} else if ((logroll == 1)) {
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
						}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (1))) < 64)) {
							{
								TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
								if (_ent != null) {
									final int _sltid = (int) (1);
									final ItemStack _setstack = new ItemStack(Blocks.SPRUCE_LOG);
									_setstack.setCount((int) ((new Object() {
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
									}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (1))) + 1));
									_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
										if (capability instanceof IItemHandlerModifiable) {
											((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
										}
									});
								}
							}
						}
					} else if ((logroll == 2)) {
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
						}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (2))) < 64)) {
							{
								TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
								if (_ent != null) {
									final int _sltid = (int) (2);
									final ItemStack _setstack = new ItemStack(Blocks.BIRCH_LOG);
									_setstack.setCount((int) ((new Object() {
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
									}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (2))) + 1));
									_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
										if (capability instanceof IItemHandlerModifiable) {
											((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
										}
									});
								}
							}
						}
					} else if ((logroll == 3)) {
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
						}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (3))) < 64)) {
							{
								TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
								if (_ent != null) {
									final int _sltid = (int) (3);
									final ItemStack _setstack = new ItemStack(Blocks.JUNGLE_LOG);
									_setstack.setCount((int) ((new Object() {
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
									}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (3))) + 1));
									_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
										if (capability instanceof IItemHandlerModifiable) {
											((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
										}
									});
								}
							}
						}
					} else if ((logroll == 4)) {
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
						}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (4))) < 64)) {
							{
								TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
								if (_ent != null) {
									final int _sltid = (int) (4);
									final ItemStack _setstack = new ItemStack(Blocks.ACACIA_LOG);
									_setstack.setCount((int) ((new Object() {
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
									}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (4))) + 1));
									_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
										if (capability instanceof IItemHandlerModifiable) {
											((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
										}
									});
								}
							}
						}
					} else if ((logroll == 5)) {
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
						}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (5))) < 64)) {
							{
								TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
								if (_ent != null) {
									final int _sltid = (int) (5);
									final ItemStack _setstack = new ItemStack(Blocks.DARK_OAK_LOG);
									_setstack.setCount((int) ((new Object() {
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
									}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (5))) + 1));
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
				ScanZ = (double) (ScanZ + 1);
			}
			ScanZ = (double) (0 - ScanRadius);
			ScanX = (double) (ScanX + 1);
		}
	}
}
