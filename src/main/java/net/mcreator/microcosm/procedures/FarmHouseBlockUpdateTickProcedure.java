package net.mcreator.microcosm.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.state.Property;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.mcreator.microcosm.particle.ParticleWheatParticle;
import net.mcreator.microcosm.particle.ParticleSugarCaneParticle;
import net.mcreator.microcosm.particle.ParticleStoneHoeParticle;
import net.mcreator.microcosm.particle.ParticlePumpkinParticle;
import net.mcreator.microcosm.particle.ParticlePotatoParticle;
import net.mcreator.microcosm.particle.ParticleMelonParticle;
import net.mcreator.microcosm.particle.ParticleCarrotParticle;
import net.mcreator.microcosm.particle.ParticleBeetParticle;
import net.mcreator.microcosm.block.ReadyFieldBlock;
import net.mcreator.microcosm.block.PlotBlockBlock;
import net.mcreator.microcosm.block.MiddenBlockBlock;
import net.mcreator.microcosm.block.HarvestedFieldBlock;
import net.mcreator.microcosm.block.GrowingFieldBlockBlock;
import net.mcreator.microcosm.block.BeeHivesBlockBlock;
import net.mcreator.microcosm.MicrocosmMod;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Random;
import java.util.Map;

public class FarmHouseBlockUpdateTickProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency x for procedure FarmHouseBlockUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency y for procedure FarmHouseBlockUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency z for procedure FarmHouseBlockUpdateTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency world for procedure FarmHouseBlockUpdateTick!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		ItemStack HarvestedCrop = ItemStack.EMPTY;
		String SaturationEffect = "";
		boolean Irragated = false;
		boolean FieldOpenSky = false;
		boolean IsMiddenPresent = false;
		boolean Pollinated = false;
		boolean Rancher = false;
		double ScanRadius = 0;
		double ScanX = 0;
		double ScanZ = 0;
		double PlowChance = 0;
		double InventorySize = 0;
		double PlantChance = 0;
		double HarvestChance = 0;
		double GrowthChance = 0;
		double CropSelectTotal = 0;
		double WheatChance = 0;
		double PotatoChance = 0;
		double CarrotChance = 0;
		double BeetChance = 0;
		double SugarCaneChance = 0;
		double MelonChance = 0;
		double PumpkinChance = 0;
		double HarvestRoll = 0;
		double InventoryLoop = 0;
		double xpPerHarvest = 0;
		double XPBase = 0;
		XPBase = (double) 64;
		PlowChance = (double) (10 - (new Object() {
			public double getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return -1;
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Level")));
		PlantChance = (double) (24 - (new Object() {
			public double getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return -1;
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Level")));
		GrowthChance = (double) (25 - (new Object() {
			public double getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return -1;
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Level")));
		HarvestChance = (double) (10 - (new Object() {
			public double getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return -1;
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Level")));
		InventorySize = (double) 18;
		ScanRadius = (double) 1;
		if (((new Object() {
			public double getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return -1;
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Level")) == 1)) {
			ScanRadius = (double) 2;
		}
		if (((new Object() {
			public double getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return -1;
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Level")) >= 3)) {
			ScanRadius = (double) 3;
		}
		if (((new Object() {
			public double getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return -1;
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Level")) >= 4)) {
			SaturationEffect = (String) (("effect give @e[distance=..") + "" + ((new java.text.DecimalFormat("##").format((1 * (new Object() {
				public double getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Level")))))) + "" + ("] minecraft:saturation ") + ""
					+ ((new java.text.DecimalFormat("##").format((20 * (new Object() {
						public double getValue(IWorld world, BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Level")))))));
			if (world instanceof ServerWorld) {
				((World) world).getServer().getCommandManager()
						.handleCommand(new CommandSource(ICommandSource.DUMMY, new Vector3d(x, y, z), Vector2f.ZERO, (ServerWorld) world, 4, "",
								new StringTextComponent(""), ((World) world).getServer(), null).withFeedbackDisabled(), SaturationEffect);
			}
		}
		if (((new Object() {
			public double getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return -1;
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Level")) >= 5)) {
			ScanRadius = (double) 4;
		}
		xpPerHarvest = (double) 1;
		WheatChance = (double) (0 + (new Object() {
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
		}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (18))));
		PotatoChance = (double) (0 + (new Object() {
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
		}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (19))));
		CarrotChance = (double) (0 + (new Object() {
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
		}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (20))));
		BeetChance = (double) (0 + (new Object() {
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
		}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (21))));
		SugarCaneChance = (double) (0 + (new Object() {
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
		}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (22))));
		MelonChance = (double) (0 + (new Object() {
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
		}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (23))));
		PumpkinChance = (double) (0 + (new Object() {
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
		}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (24))));
		CropSelectTotal = (double) (WheatChance + (PotatoChance + (CarrotChance + (BeetChance + (SugarCaneChance + (MelonChance + PumpkinChance))))));
		if ((CropSelectTotal > 0)) {
			Irragated = (boolean) (false);
			IsMiddenPresent = (boolean) (false);
			Pollinated = (boolean) (false);
			Rancher = (boolean) (false);
			ScanX = (double) (0 - ScanRadius);
			ScanZ = (double) (0 - ScanRadius);
			for (int index0 = 0; index0 < (int) ((1 + (ScanRadius * 2))); index0++) {
				for (int index1 = 0; index1 < (int) ((1 + (ScanRadius * 2))); index1++) {
					if (((world.getBlockState(new BlockPos((int) (x + ScanX), (int) y, (int) (z + ScanZ)))).getBlock() == MiddenBlockBlock.block)) {
						IsMiddenPresent = (boolean) (true);
					}
					if (((world.getBlockState(new BlockPos((int) (x + ScanX), (int) y, (int) (z + ScanZ)))).getBlock() == BeeHivesBlockBlock.block)) {
						Pollinated = (boolean) (true);
					}
					ScanZ = (double) (ScanZ + 1);
				}
				ScanZ = (double) (0 - ScanRadius);
				ScanX = (double) (ScanX + 1);
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
			}.drainTankSimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) 25)) >= 50)) {
				Irragated = (boolean) (true);
				PlantChance = (double) (PlantChance - 2);
				GrowthChance = (double) (GrowthChance - 2);
			}
			if (IsMiddenPresent) {
				PlantChance = (double) (PlantChance / 2);
				GrowthChance = (double) (GrowthChance / 2);
			}
			if (Pollinated) {
				GrowthChance = (double) (GrowthChance - 1);
			}
			ScanX = (double) (0 - ScanRadius);
			ScanZ = (double) (0 - ScanRadius);
			for (int index2 = 0; index2 < (int) ((1 + (ScanRadius * 2))); index2++) {
				for (int index3 = 0; index3 < (int) ((1 + (ScanRadius * 2))); index3++) {
					if ((((world.getBlockState(new BlockPos((int) (x + ScanX), (int) y, (int) (z + ScanZ)))).getBlock() == BeeHivesBlockBlock.block)
							&& ((((new Random()).nextInt((int) HarvestChance + 1)) == 0) && ((new Object() {
								public double getValue(IWorld world, BlockPos pos, String tag) {
									TileEntity tileEntity = world.getTileEntity(pos);
									if (tileEntity != null)
										return tileEntity.getTileData().getDouble(tag);
									return -1;
								}
							}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Level")) >= 4)))) {
						HarvestedCrop = new ItemStack(Items.HONEY_BOTTLE);
						if (world instanceof ServerWorld) {
							((ServerWorld) world).spawnParticle(ParticleTypes.DRIPPING_HONEY, ((x + ScanX) + 0.5), (y + 0.75), ((z + ScanZ) + 0.5),
									(int) 1, 0.25, 0.25, 0.25, 1);
						}
						InventoryLoop = (double) 0;
						while ((InventoryLoop < InventorySize)) {
							if (((((HarvestedCrop).getItem() == (new Object() {
								public ItemStack getItemStack(BlockPos pos, int sltid) {
									AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
									TileEntity _ent = world.getTileEntity(pos);
									if (_ent != null) {
										_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
											_retval.set(capability.getStackInSlot(sltid).copy());
										});
									}
									return _retval.get();
								}
							}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (InventoryLoop))).getItem()) && ((new Object() {
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
							}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (InventoryLoop))) < 16)) || ((new Object() {
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
							}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (InventoryLoop))) < 1))) {
								{
									TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
									if (_ent != null) {
										final int _sltid = (int) (InventoryLoop);
										final ItemStack _setstack = (HarvestedCrop);
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
										}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (InventoryLoop))) + 1));
										_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
											if (capability instanceof IItemHandlerModifiable) {
												((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
											}
										});
									}
								}
								break;
							}
							InventoryLoop = (double) (InventoryLoop + 1);
						}
					}
					if ((((world.getBlockState(new BlockPos((int) (x + ScanX), (int) y, (int) (z + ScanZ)))).getBlock() == ReadyFieldBlock.block)
							&& (((new Random()).nextInt((int) HarvestChance + 1)) == 0))) {
						HarvestRoll = (double) (((new Random()).nextInt((int) (CropSelectTotal - 1) + 1)) + 1);
						if ((HarvestRoll <= WheatChance)) {
							HarvestedCrop = new ItemStack(Items.WHEAT);
							if (world instanceof ServerWorld) {
								((ServerWorld) world).spawnParticle(ParticleWheatParticle.particle, ((x + ScanX) + 0.5), (y + 0.75),
										((z + ScanZ) + 0.5), (int) 1, 0.25, 0.25, 0.25, 0);
							}
						} else if ((HarvestRoll <= (WheatChance + PotatoChance))) {
							HarvestedCrop = new ItemStack(Items.POTATO);
							if (world instanceof ServerWorld) {
								((ServerWorld) world).spawnParticle(ParticlePotatoParticle.particle, ((x + ScanX) + 0.5), (y + 0.75),
										((z + ScanZ) + 0.5), (int) 1, 0.25, 0.25, 0.25, 0);
							}
						} else if ((HarvestRoll <= (WheatChance + (PotatoChance + CarrotChance)))) {
							HarvestedCrop = new ItemStack(Items.CARROT);
							if (world instanceof ServerWorld) {
								((ServerWorld) world).spawnParticle(ParticleCarrotParticle.particle, ((x + ScanX) + 0.5), (y + 0.75),
										((z + ScanZ) + 0.5), (int) 1, 0.25, 0.25, 0.25, 0);
							}
						} else if ((HarvestRoll <= (WheatChance + (PotatoChance + (CarrotChance + BeetChance))))) {
							HarvestedCrop = new ItemStack(Items.BEETROOT);
							if (world instanceof ServerWorld) {
								((ServerWorld) world).spawnParticle(ParticleBeetParticle.particle, ((x + ScanX) + 0.5), (y + 0.75),
										((z + ScanZ) + 0.5), (int) 1, 0.25, 0.25, 0.25, 0);
							}
						} else if ((HarvestRoll <= (WheatChance + (PotatoChance + (CarrotChance + (BeetChance + SugarCaneChance)))))) {
							HarvestedCrop = new ItemStack(Blocks.SUGAR_CANE);
							if (world instanceof ServerWorld) {
								((ServerWorld) world).spawnParticle(ParticleSugarCaneParticle.particle, ((x + ScanX) + 0.5), (y + 0.75),
										((z + ScanZ) + 0.5), (int) 1, 0.25, 0.25, 0.25, 0);
							}
						} else if ((HarvestRoll <= (WheatChance
								+ (PotatoChance + (CarrotChance + (BeetChance + (SugarCaneChance + MelonChance))))))) {
							HarvestedCrop = new ItemStack(Blocks.MELON);
							if (world instanceof ServerWorld) {
								((ServerWorld) world).spawnParticle(ParticleMelonParticle.particle, ((x + ScanX) + 0.5), (y + 0.75),
										((z + ScanZ) + 0.5), (int) 1, 0.25, 0.25, 0.25, 0);
							}
						} else if ((HarvestRoll <= (WheatChance
								+ (PotatoChance + (CarrotChance + (BeetChance + (SugarCaneChance + (MelonChance + PumpkinChance)))))))) {
							HarvestedCrop = new ItemStack(Blocks.PUMPKIN);
							if (world instanceof ServerWorld) {
								((ServerWorld) world).spawnParticle(ParticlePumpkinParticle.particle, ((x + ScanX) + 0.5), (y + 0.75),
										((z + ScanZ) + 0.5), (int) 1, 0.25, 0.25, 0.25, 0);
							}
						}
						InventoryLoop = (double) 0;
						while ((InventoryLoop < InventorySize)) {
							if (((((HarvestedCrop).getItem() == (new Object() {
								public ItemStack getItemStack(BlockPos pos, int sltid) {
									AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
									TileEntity _ent = world.getTileEntity(pos);
									if (_ent != null) {
										_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
											_retval.set(capability.getStackInSlot(sltid).copy());
										});
									}
									return _retval.get();
								}
							}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (InventoryLoop))).getItem()) && ((new Object() {
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
							}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (InventoryLoop))) < 64)) || ((new Object() {
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
							}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (InventoryLoop))) < 1))) {
								{
									TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
									if (_ent != null) {
										final int _sltid = (int) (InventoryLoop);
										final ItemStack _setstack = (HarvestedCrop);
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
										}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (InventoryLoop))) + 1));
										_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
											if (capability instanceof IItemHandlerModifiable) {
												((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
											}
										});
									}
								}
								break;
							}
							InventoryLoop = (double) (InventoryLoop + 1);
						}
						{
							BlockPos _bp = new BlockPos((int) (x + ScanX), (int) y, (int) (z + ScanZ));
							BlockState _bs = HarvestedFieldBlock.block.getDefaultState();
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
						if (world instanceof World && !world.isRemote()) {
							((World) world).playSound(null, new BlockPos((int) x, (int) y, (int) z),
									(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.crop.break")),
									SoundCategory.NEUTRAL, (float) 0.25, (float) 1);
						} else {
							((World) world).playSound(x, y, z,
									(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.crop.break")),
									SoundCategory.NEUTRAL, (float) 0.25, (float) 1, false);
						}
						if (((new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Level")) < 5)) {
							if (!world.isRemote()) {
								BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
								TileEntity _tileEntity = world.getTileEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_tileEntity != null)
									_tileEntity.getTileData().putDouble("XP", ((new Object() {
										public double getValue(IWorld world, BlockPos pos, String tag) {
											TileEntity tileEntity = world.getTileEntity(pos);
											if (tileEntity != null)
												return tileEntity.getTileData().getDouble(tag);
											return -1;
										}
									}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "XP")) + 1));
								if (world instanceof World)
									((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
							}
							if (((new Object() {
								public double getValue(IWorld world, BlockPos pos, String tag) {
									TileEntity tileEntity = world.getTileEntity(pos);
									if (tileEntity != null)
										return tileEntity.getTileData().getDouble(tag);
									return -1;
								}
							}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "XP")) < XPBase)) {
								if (!world.isRemote()) {
									BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
									TileEntity _tileEntity = world.getTileEntity(_bp);
									BlockState _bs = world.getBlockState(_bp);
									if (_tileEntity != null)
										_tileEntity.getTileData().putDouble("Level", 0);
									if (world instanceof World)
										((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
								}
							} else if (((new Object() {
								public double getValue(IWorld world, BlockPos pos, String tag) {
									TileEntity tileEntity = world.getTileEntity(pos);
									if (tileEntity != null)
										return tileEntity.getTileData().getDouble(tag);
									return -1;
								}
							}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "XP")) < (XPBase * 3))) {
								if (!world.isRemote()) {
									BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
									TileEntity _tileEntity = world.getTileEntity(_bp);
									BlockState _bs = world.getBlockState(_bp);
									if (_tileEntity != null)
										_tileEntity.getTileData().putDouble("Level", 1);
									if (world instanceof World)
										((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
								}
							} else if (((new Object() {
								public double getValue(IWorld world, BlockPos pos, String tag) {
									TileEntity tileEntity = world.getTileEntity(pos);
									if (tileEntity != null)
										return tileEntity.getTileData().getDouble(tag);
									return -1;
								}
							}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "XP")) < (XPBase * 7))) {
								if (!world.isRemote()) {
									BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
									TileEntity _tileEntity = world.getTileEntity(_bp);
									BlockState _bs = world.getBlockState(_bp);
									if (_tileEntity != null)
										_tileEntity.getTileData().putDouble("Level", 2);
									if (world instanceof World)
										((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
								}
							} else if (((new Object() {
								public double getValue(IWorld world, BlockPos pos, String tag) {
									TileEntity tileEntity = world.getTileEntity(pos);
									if (tileEntity != null)
										return tileEntity.getTileData().getDouble(tag);
									return -1;
								}
							}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "XP")) < (XPBase * 15))) {
								if (!world.isRemote()) {
									BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
									TileEntity _tileEntity = world.getTileEntity(_bp);
									BlockState _bs = world.getBlockState(_bp);
									if (_tileEntity != null)
										_tileEntity.getTileData().putDouble("Level", 3);
									if (world instanceof World)
										((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
								}
							} else if (((new Object() {
								public double getValue(IWorld world, BlockPos pos, String tag) {
									TileEntity tileEntity = world.getTileEntity(pos);
									if (tileEntity != null)
										return tileEntity.getTileData().getDouble(tag);
									return -1;
								}
							}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "XP")) < (XPBase * 31))) {
								if (!world.isRemote()) {
									BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
									TileEntity _tileEntity = world.getTileEntity(_bp);
									BlockState _bs = world.getBlockState(_bp);
									if (_tileEntity != null)
										_tileEntity.getTileData().putDouble("Level", 4);
									if (world instanceof World)
										((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
								}
							} else if (((new Object() {
								public double getValue(IWorld world, BlockPos pos, String tag) {
									TileEntity tileEntity = world.getTileEntity(pos);
									if (tileEntity != null)
										return tileEntity.getTileData().getDouble(tag);
									return -1;
								}
							}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "XP")) >= (XPBase * 31))) {
								if (!world.isRemote()) {
									BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
									TileEntity _tileEntity = world.getTileEntity(_bp);
									BlockState _bs = world.getBlockState(_bp);
									if (_tileEntity != null)
										_tileEntity.getTileData().putDouble("Level", 5);
									if (world instanceof World)
										((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
								}
							}
						}
					} else if ((((world.getBlockState(new BlockPos((int) (x + ScanX), (int) y, (int) (z + ScanZ))))
							.getBlock() == GrowingFieldBlockBlock.block) && (((new Random()).nextInt((int) GrowthChance + 1)) == 0))) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
							int _amount = (int) 10;
							if (_ent != null)
								_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
										.ifPresent(capability -> capability.drain(_amount, IFluidHandler.FluidAction.EXECUTE));
						}
						{
							BlockPos _bp = new BlockPos((int) (x + ScanX), (int) y, (int) (z + ScanZ));
							BlockState _bs = ReadyFieldBlock.block.getDefaultState();
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
						if (world instanceof World && !world.isRemote()) {
							((World) world).playSound(null, new BlockPos((int) x, (int) y, (int) z),
									(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.grass.hit")),
									SoundCategory.NEUTRAL, (float) 0.25, (float) 1);
						} else {
							((World) world).playSound(x, y, z,
									(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.grass.hit")),
									SoundCategory.NEUTRAL, (float) 0.25, (float) 1, false);
						}
						if (world instanceof ServerWorld) {
							((ServerWorld) world).spawnParticle(ParticleStoneHoeParticle.particle, ((x + ScanX) + 0.5), (y + 0.75),
									((z + ScanZ) + 0.5), (int) 1, 0.25, 0.25, 0.25, 0);
						}
					} else if ((((world.getBlockState(new BlockPos((int) (x + ScanX), (int) y, (int) (z + ScanZ))))
							.getBlock() == HarvestedFieldBlock.block) && (((new Random()).nextInt((int) PlantChance + 1)) == 0))) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
							int _amount = (int) 10;
							if (_ent != null)
								_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
										.ifPresent(capability -> capability.drain(_amount, IFluidHandler.FluidAction.EXECUTE));
						}
						{
							BlockPos _bp = new BlockPos((int) (x + ScanX), (int) y, (int) (z + ScanZ));
							BlockState _bs = GrowingFieldBlockBlock.block.getDefaultState();
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
						if (world instanceof World && !world.isRemote()) {
							((World) world).playSound(null, new BlockPos((int) x, (int) y, (int) z),
									(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.hoe.till")),
									SoundCategory.NEUTRAL, (float) 0.25, (float) 1);
						} else {
							((World) world).playSound(x, y, z,
									(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.hoe.till")),
									SoundCategory.NEUTRAL, (float) 0.25, (float) 1, false);
						}
						if (world instanceof ServerWorld) {
							((ServerWorld) world).spawnParticle(ParticleStoneHoeParticle.particle, ((x + ScanX) + 0.5), (y + 0.75),
									((z + ScanZ) + 0.5), (int) 1, 0.25, 0.25, 0.25, 0);
						}
					} else if ((((world.getBlockState(new BlockPos((int) (x + ScanX), (int) y, (int) (z + ScanZ))))
							.getBlock() == PlotBlockBlock.block) && (((new Random()).nextInt((int) PlowChance + 1)) == 0))) {
						{
							BlockPos _bp = new BlockPos((int) (x + ScanX), (int) y, (int) (z + ScanZ));
							BlockState _bs = HarvestedFieldBlock.block.getDefaultState();
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
						if (world instanceof World && !world.isRemote()) {
							((World) world).playSound(null, new BlockPos((int) x, (int) y, (int) z),
									(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.hoe.till")),
									SoundCategory.NEUTRAL, (float) 1, (float) 1);
						} else {
							((World) world).playSound(x, y, z,
									(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.hoe.till")),
									SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
						}
						if (world instanceof ServerWorld) {
							((ServerWorld) world).spawnParticle(ParticleStoneHoeParticle.particle, ((x + ScanX) + 0.5), (y + 0.75),
									((z + ScanZ) + 0.5), (int) 1, 0.25, 0.25, 0.25, 0);
						}
					}
					ScanZ = (double) (ScanZ + 1);
				}
				ScanZ = (double) (0 - ScanRadius);
				ScanX = (double) (ScanX + 1);
			}
		}
		if (!world.isRemote()) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().putString("strXP", (new java.text.DecimalFormat("##").format((new Object() {
					public double getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "XP")))));
			if (world instanceof World)
				((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		if (!world.isRemote()) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().putString("strLevel", (new java.text.DecimalFormat("##").format((new Object() {
					public double getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Level")))));
			if (world instanceof World)
				((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
	}
}
