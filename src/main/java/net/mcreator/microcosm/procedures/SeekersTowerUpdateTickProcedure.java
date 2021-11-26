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
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.ITag;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.mcreator.microcosm.particle.ParticleMapParticle;
import net.mcreator.microcosm.block.NetherPBlock;
import net.mcreator.microcosm.block.EndPBlock;
import net.mcreator.microcosm.block.ConduitPortalBlock;
import net.mcreator.microcosm.MicrocosmMod;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Random;
import java.util.Map;

public class SeekersTowerUpdateTickProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency x for procedure SeekersTowerUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency y for procedure SeekersTowerUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency z for procedure SeekersTowerUpdateTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency world for procedure SeekersTowerUpdateTick!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		ItemStack Loot = ItemStack.EMPTY;
		String Destination = "";
		double roll = 0;
		double XPBase = 0;
		double InvLoop = 0;
		double HallX = 0;
		double HallZ = 0;
		double maxstack = 0;
		double CurioRoll = 0;
		HallX = (double) (x + (new Object() {
			public double getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return -1;
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "X")));
		HallZ = (double) (z + (new Object() {
			public double getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return -1;
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Z")));
		XPBase = (double) 64;
		Destination = (String) "Seeking";
		if ((((new Object() {
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
		}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getOrCreateTag().getBoolean("Conduit Portal"))
				&& ((world.getBlockState(new BlockPos((int) ((new Object() {
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
				}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getOrCreateTag().getDouble("X")), (int) ((new Object() {
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
				}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getOrCreateTag().getDouble("Y")), (int) ((new Object() {
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
				}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getOrCreateTag().getDouble("Z")))))
						.getBlock() == ConduitPortalBlock.block))) {
			Destination = (String) "Seeking";
		}
		if ((((new Object() {
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
		}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getOrCreateTag().getBoolean("Nether Portal"))
				&& ((world.getBlockState(new BlockPos((int) ((new Object() {
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
				}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getOrCreateTag().getDouble("X")), (int) ((new Object() {
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
				}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getOrCreateTag().getDouble("Y")), (int) ((new Object() {
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
				}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getOrCreateTag().getDouble("Z")))))
						.getBlock() == NetherPBlock.block))) {
			Destination = (String) "Seeking";
		}
		if ((((new Object() {
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
		}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getOrCreateTag().getBoolean("End Portal"))
				&& ((world.getBlockState(new BlockPos((int) ((new Object() {
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
				}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getOrCreateTag().getDouble("X")), (int) ((new Object() {
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
				}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getOrCreateTag().getDouble("Y")), (int) ((new Object() {
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
				}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getOrCreateTag().getDouble("Z")))))
						.getBlock() == EndPBlock.block))) {
			Destination = (String) "Seeking";
		}
		if (!world.isRemote()) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().putString("Destination", Destination);
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
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "XP")) < (XPBase * 4))) {
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
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "XP")) < (XPBase * 8))) {
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
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "XP")) < (XPBase * 16))) {
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
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "XP")) < (XPBase * 32))) {
			if (!world.isRemote()) {
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putDouble("Level", 4);
				if (world instanceof World)
					((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
		} else {
			if (!world.isRemote()) {
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putDouble("Level", 5);
				if (world instanceof World)
					((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
			if (!world.isRemote()) {
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putDouble("XP", 9001);
				if (world instanceof World)
					((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
		}
		roll = (double) (((new Random()).nextInt((int) 1111 + 1)) + (new Object() {
			public double getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return -1;
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Level")));
		Loot = new ItemStack(Blocks.AIR);
		if ((roll > 1100)) {
			CurioRoll = (double) ((new Random()).nextInt((int) 9 + 1));
			if ((CurioRoll == 0)) {
				Loot = new ItemStack((new Object() {
					public Item getRandomItem(String _tagName) {
						ITag<Item> _tag = ItemTags.getCollection().getTagByID(new ResourceLocation(_tagName));
						return _tag.getAllElements().isEmpty() ? Items.AIR : _tag.getRandomElement(new Random());
					}
				}.getRandomItem(("curios:head").toLowerCase(java.util.Locale.ENGLISH))));
			} else if ((CurioRoll == 1)) {
				Loot = new ItemStack((new Object() {
					public Item getRandomItem(String _tagName) {
						ITag<Item> _tag = ItemTags.getCollection().getTagByID(new ResourceLocation(_tagName));
						return _tag.getAllElements().isEmpty() ? Items.AIR : _tag.getRandomElement(new Random());
					}
				}.getRandomItem(("curios:necklace").toLowerCase(java.util.Locale.ENGLISH))));
			} else if ((CurioRoll == 2)) {
				Loot = new ItemStack((new Object() {
					public Item getRandomItem(String _tagName) {
						ITag<Item> _tag = ItemTags.getCollection().getTagByID(new ResourceLocation(_tagName));
						return _tag.getAllElements().isEmpty() ? Items.AIR : _tag.getRandomElement(new Random());
					}
				}.getRandomItem(("curios:back").toLowerCase(java.util.Locale.ENGLISH))));
			} else if ((CurioRoll == 3)) {
				Loot = new ItemStack((new Object() {
					public Item getRandomItem(String _tagName) {
						ITag<Item> _tag = ItemTags.getCollection().getTagByID(new ResourceLocation(_tagName));
						return _tag.getAllElements().isEmpty() ? Items.AIR : _tag.getRandomElement(new Random());
					}
				}.getRandomItem(("curios:body").toLowerCase(java.util.Locale.ENGLISH))));
			} else if ((CurioRoll == 4)) {
				Loot = new ItemStack((new Object() {
					public Item getRandomItem(String _tagName) {
						ITag<Item> _tag = ItemTags.getCollection().getTagByID(new ResourceLocation(_tagName));
						return _tag.getAllElements().isEmpty() ? Items.AIR : _tag.getRandomElement(new Random());
					}
				}.getRandomItem(("curios:feet").toLowerCase(java.util.Locale.ENGLISH))));
			} else if ((CurioRoll == 5)) {
				Loot = new ItemStack((new Object() {
					public Item getRandomItem(String _tagName) {
						ITag<Item> _tag = ItemTags.getCollection().getTagByID(new ResourceLocation(_tagName));
						return _tag.getAllElements().isEmpty() ? Items.AIR : _tag.getRandomElement(new Random());
					}
				}.getRandomItem(("curios:hands").toLowerCase(java.util.Locale.ENGLISH))));
			} else if ((CurioRoll == 6)) {
				Loot = new ItemStack((new Object() {
					public Item getRandomItem(String _tagName) {
						ITag<Item> _tag = ItemTags.getCollection().getTagByID(new ResourceLocation(_tagName));
						return _tag.getAllElements().isEmpty() ? Items.AIR : _tag.getRandomElement(new Random());
					}
				}.getRandomItem(("curios:ring").toLowerCase(java.util.Locale.ENGLISH))));
			} else if ((CurioRoll == 7)) {
				Loot = new ItemStack((new Object() {
					public Item getRandomItem(String _tagName) {
						ITag<Item> _tag = ItemTags.getCollection().getTagByID(new ResourceLocation(_tagName));
						return _tag.getAllElements().isEmpty() ? Items.AIR : _tag.getRandomElement(new Random());
					}
				}.getRandomItem(("curios:belt").toLowerCase(java.util.Locale.ENGLISH))));
			} else if ((CurioRoll == 8)) {
				Loot = new ItemStack((new Object() {
					public Item getRandomItem(String _tagName) {
						ITag<Item> _tag = ItemTags.getCollection().getTagByID(new ResourceLocation(_tagName));
						return _tag.getAllElements().isEmpty() ? Items.AIR : _tag.getRandomElement(new Random());
					}
				}.getRandomItem(("curios:trinkets").toLowerCase(java.util.Locale.ENGLISH))));
			} else {
				Loot = new ItemStack((new Object() {
					public Item getRandomItem(String _tagName) {
						ITag<Item> _tag = ItemTags.getCollection().getTagByID(new ResourceLocation(_tagName));
						return _tag.getAllElements().isEmpty() ? Items.AIR : _tag.getRandomElement(new Random());
					}
				}.getRandomItem(("curios:charm").toLowerCase(java.util.Locale.ENGLISH))));
			}
		}
		InvLoop = (double) 0;
		maxstack = (double) 64;
		if ((ItemTags.getCollection().getTagByID(new ResourceLocation(("microcosm:stacksixteen").toLowerCase(java.util.Locale.ENGLISH)))
				.contains((Loot).getItem()))) {
			maxstack = (double) 16;
		}
		if ((ItemTags.getCollection().getTagByID(new ResourceLocation(("microcosm:stackone").toLowerCase(java.util.Locale.ENGLISH)))
				.contains((Loot).getItem()))) {
			maxstack = (double) 1;
		}
		if ((!((Loot).getItem() == Blocks.AIR.asItem()))) {
			for (int index0 = 0; index0 < (int) (27); index0++) {
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
				}.getAmount(world, new BlockPos((int) HallX, (int) y, (int) HallZ), (int) (InvLoop))) == 0) || (((new Object() {
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
				}.getItemStack(new BlockPos((int) HallX, (int) y, (int) HallZ), (int) (InvLoop))).getItem() == (Loot).getItem()) && ((new Object() {
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
				}.getAmount(world, new BlockPos((int) HallX, (int) y, (int) HallZ), (int) (InvLoop))) < maxstack)))) {
					{
						TileEntity _ent = world.getTileEntity(new BlockPos((int) HallX, (int) y, (int) HallZ));
						if (_ent != null) {
							final int _sltid = (int) (InvLoop);
							final ItemStack _setstack = (Loot);
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
							}.getAmount(world, new BlockPos((int) HallX, (int) y, (int) HallZ), (int) (InvLoop))) + 1));
							_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
								if (capability instanceof IItemHandlerModifiable) {
									((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
								}
							});
						}
					}
					break;
				}
				InvLoop = (double) (InvLoop + 1);
			}
			if (world instanceof World && !world.isRemote()) {
				((World) world).playSound(null, new BlockPos((int) x, (int) y, (int) z),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.villager.celebrate")),
						SoundCategory.NEUTRAL, (float) 0.25, (float) 1);
			} else {
				((World) world).playSound(x, y, z,
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.villager.celebrate")),
						SoundCategory.NEUTRAL, (float) 0.25, (float) 1, false);
			}
		}
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
		if (world instanceof ServerWorld) {
			((ServerWorld) world).spawnParticle(ParticleMapParticle.particle, (x + 0.5), (y + 0.75), (z + 0.5), (int) 0, 0.12, 0.12, 0.12, 0);
		}
	}
}
