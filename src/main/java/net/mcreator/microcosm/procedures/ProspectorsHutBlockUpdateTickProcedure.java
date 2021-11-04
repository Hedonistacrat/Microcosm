package net.mcreator.microcosm.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.microcosm.item.UncutGemItem;
import net.mcreator.microcosm.item.TraceElementsItem;
import net.mcreator.microcosm.block.QuarryBlockBlock;
import net.mcreator.microcosm.block.GrindstoneBlockBlock;
import net.mcreator.microcosm.MicrocosmMod;

import java.util.function.Function;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Random;
import java.util.Map;
import java.util.Comparator;

public class ProspectorsHutBlockUpdateTickProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency x for procedure ProspectorsHutBlockUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency y for procedure ProspectorsHutBlockUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency z for procedure ProspectorsHutBlockUpdateTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency world for procedure ProspectorsHutBlockUpdateTick!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		boolean GrindstonePresent = false;
		boolean IndustryPresent = false;
		double roll = 0;
		double ScanRadius = 0;
		double PickChance = 0;
		double ScanX = 0;
		double ScanZ = 0;
		double GemChance = 0;
		ScanRadius = (double) 1;
		PickChance = (double) 2;
		GemChance = (double) 9;
		ScanX = (double) (0 - ScanRadius);
		ScanZ = (double) (0 - ScanRadius);
		GrindstonePresent = (boolean) (false);
		IndustryPresent = (boolean) (false);
		if (((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) (z + 0)))).getBlock() == GrindstoneBlockBlock.block)) {
			GrindstonePresent = (boolean) (true);
		} else if (((world.getBlockState(new BlockPos((int) (x + (-1)), (int) y, (int) (z + 0)))).getBlock() == GrindstoneBlockBlock.block)) {
			GrindstonePresent = (boolean) (true);
		} else if (((world.getBlockState(new BlockPos((int) (x + 0), (int) y, (int) (z + 1)))).getBlock() == GrindstoneBlockBlock.block)) {
			GrindstonePresent = (boolean) (true);
		} else if (((world.getBlockState(new BlockPos((int) (x + 0), (int) y, (int) (z + (-1))))).getBlock() == GrindstoneBlockBlock.block)) {
			GrindstonePresent = (boolean) (true);
		}
		if (GrindstonePresent) {
			for (int index0 = 0; index0 < (int) ((1 + (ScanRadius * 2))); index0++) {
				for (int index1 = 0; index1 < (int) ((1 + (ScanRadius * 2))); index1++) {
					if (((world.getBlockState(new BlockPos((int) (x + ScanX), (int) y, (int) (z + ScanZ)))).getBlock() == QuarryBlockBlock.block)) {
						IndustryPresent = (boolean) (true);
						roll = (double) ((new Random()).nextInt((int) (PickChance - 1) + 1));
						if ((roll == 0)) {
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
							}.getAmount(world, new BlockPos((int) (x + ScanX), (int) y, (int) (z + ScanZ)), (int) (0))) > 0)) {
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
										TileEntity _ent = world.getTileEntity(new BlockPos((int) (x + ScanX), (int) y, (int) (z + ScanZ)));
										if (_ent != null) {
											final int _sltid = (int) (0);
											final ItemStack _setstack = (new Object() {
												public ItemStack getItemStack(BlockPos pos, int sltid) {
													AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
													TileEntity _ent = world.getTileEntity(pos);
													if (_ent != null) {
														_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
																.ifPresent(capability -> {
																	_retval.set(capability.getStackInSlot(sltid).copy());
																});
													}
													return _retval.get();
												}
											}.getItemStack(new BlockPos((int) (x + ScanX), (int) y, (int) (z + ScanZ)), (int) (0)));
											_setstack.setCount((int) ((new Object() {
												public int getAmount(IWorld world, BlockPos pos, int sltid) {
													AtomicInteger _retval = new AtomicInteger(0);
													TileEntity _ent = world.getTileEntity(pos);
													if (_ent != null) {
														_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
																.ifPresent(capability -> {
																	_retval.set(capability.getStackInSlot(sltid).getCount());
																});
													}
													return _retval.get();
												}
											}.getAmount(world, new BlockPos((int) (x + ScanX), (int) y, (int) (z + ScanZ)), (int) (0))) - 1));
											_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
												if (capability instanceof IItemHandlerModifiable) {
													((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
												}
											});
										}
									}
									{
										TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
										if (_ent != null) {
											final int _sltid = (int) (0);
											final ItemStack _setstack = new ItemStack(TraceElementsItem.block);
											_setstack.setCount((int) ((new Object() {
												public int getAmount(IWorld world, BlockPos pos, int sltid) {
													AtomicInteger _retval = new AtomicInteger(0);
													TileEntity _ent = world.getTileEntity(pos);
													if (_ent != null) {
														_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
																.ifPresent(capability -> {
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
									if ((((new Object() {
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
									}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (2))) < 64)
											&& (((new Random()).nextInt((int) GemChance + 1)) == 0))) {
										{
											TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
											if (_ent != null) {
												final int _sltid = (int) (2);
												final ItemStack _setstack = new ItemStack(UncutGemItem.block);
												_setstack.setCount((int) ((new Object() {
													public int getAmount(IWorld world, BlockPos pos, int sltid) {
														AtomicInteger _retval = new AtomicInteger(0);
														TileEntity _ent = world.getTileEntity(pos);
														if (_ent != null) {
															_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
																	.ifPresent(capability -> {
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
												final ItemStack _setstack = new ItemStack(Blocks.GRAVEL);
												_setstack.setCount((int) ((new Object() {
													public int getAmount(IWorld world, BlockPos pos, int sltid) {
														AtomicInteger _retval = new AtomicInteger(0);
														TileEntity _ent = world.getTileEntity(pos);
														if (_ent != null) {
															_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
																	.ifPresent(capability -> {
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
									if (world instanceof ServerWorld) {
										((ServerWorld) world).spawnParticle(ParticleTypes.HAPPY_VILLAGER, (x + 0.5), (y + 1), (z + 0.5), (int) 3,
												0.12, 0.12, 0.12, 0.1);
									}
									if (world instanceof World && !world.isRemote()) {
										((World) world).playSound(null, new BlockPos((int) x, (int) y, (int) z),
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("entity.blaze.burn")),
												SoundCategory.NEUTRAL, (float) 0.25, (float) 1);
									} else {
										((World) world).playSound(x, y, z,
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("entity.blaze.burn")),
												SoundCategory.NEUTRAL, (float) 0.25, (float) 1, false);
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
		} else {
			if (((Entity) world
					.getEntitiesWithinAABB(ServerPlayerEntity.class,
							new AxisAlignedBB(x - (6 / 2d), y - (6 / 2d), z - (6 / 2d), x + (6 / 2d), y + (6 / 2d), z + (6 / 2d)), null)
					.stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
						}
					}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof PlayerEntity && !((Entity) world
							.getEntitiesWithinAABB(ServerPlayerEntity.class,
									new AxisAlignedBB(x - (6 / 2d), y - (6 / 2d), z - (6 / 2d), x + (6 / 2d), y + (6 / 2d), z + (6 / 2d)), null)
							.stream().sorted(new Object() {
								Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
									return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
								}
							}.compareDistOf(x, y, z)).findFirst().orElse(null)).world.isRemote()) {
				((PlayerEntity) ((Entity) world
						.getEntitiesWithinAABB(ServerPlayerEntity.class,
								new AxisAlignedBB(x - (6 / 2d), y - (6 / 2d), z - (6 / 2d), x + (6 / 2d), y + (6 / 2d), z + (6 / 2d)), null)
						.stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
							}
						}.compareDistOf(x, y, z)).findFirst().orElse(null)))
								.sendStatusMessage(new StringTextComponent("Prospector's Hut requires an adjacent Grindstone  "), (true));
			}
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(ParticleTypes.ANGRY_VILLAGER, (x + 0.5), (y + 1), (z + 0.5), (int) 3, 0.12, 0.12, 0.12, 0.1);
			}
		}
		if (((!IndustryPresent) && GrindstonePresent)) {
			if (((Entity) world
					.getEntitiesWithinAABB(ServerPlayerEntity.class,
							new AxisAlignedBB(x - (6 / 2d), y - (6 / 2d), z - (6 / 2d), x + (6 / 2d), y + (6 / 2d), z + (6 / 2d)), null)
					.stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
						}
					}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof PlayerEntity && !((Entity) world
							.getEntitiesWithinAABB(ServerPlayerEntity.class,
									new AxisAlignedBB(x - (6 / 2d), y - (6 / 2d), z - (6 / 2d), x + (6 / 2d), y + (6 / 2d), z + (6 / 2d)), null)
							.stream().sorted(new Object() {
								Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
									return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
								}
							}.compareDistOf(x, y, z)).findFirst().orElse(null)).world.isRemote()) {
				((PlayerEntity) ((Entity) world
						.getEntitiesWithinAABB(ServerPlayerEntity.class,
								new AxisAlignedBB(x - (6 / 2d), y - (6 / 2d), z - (6 / 2d), x + (6 / 2d), y + (6 / 2d), z + (6 / 2d)), null)
						.stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
							}
						}.compareDistOf(x, y, z)).findFirst().orElse(null)))
								.sendStatusMessage(new StringTextComponent((("Prospector's Hut found no Quarries within ") + ""
										+ ((new java.text.DecimalFormat("##").format(ScanRadius))) + "" + (" blocks"))), (true));
			}
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(ParticleTypes.ANGRY_VILLAGER, (x + 0.5), (y + 1), (z + 0.5), (int) 3, 0.12, 0.12, 0.12, 0.1);
			}
		}
	}
}
