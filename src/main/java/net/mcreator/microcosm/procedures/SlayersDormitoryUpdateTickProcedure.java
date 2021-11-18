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
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.mcreator.microcosm.particle.ParticleStoneSwordParticle;
import net.mcreator.microcosm.block.NetherPBlock;
import net.mcreator.microcosm.block.EndPBlock;
import net.mcreator.microcosm.block.ConduitPortalBlock;
import net.mcreator.microcosm.MicrocosmMod;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Random;
import java.util.Map;

public class SlayersDormitoryUpdateTickProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency x for procedure SlayersDormitoryUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency y for procedure SlayersDormitoryUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency z for procedure SlayersDormitoryUpdateTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency world for procedure SlayersDormitoryUpdateTick!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		ItemStack Loot = ItemStack.EMPTY;
		double roll = 0;
		double XPBase = 0;
		double InvLoop = 0;
		double HallX = 0;
		double HallZ = 0;
		double maxstack = 0;
		String Destination = "";
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
		Destination = (String) "Overworld";
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
			Destination = (String) "Ocean";
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
			Destination = (String) "Nether";
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
			Destination = (String) "End";
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
		}
		for (int index0 = 0; index0 < (int) (((new Object() {
			public double getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return -1;
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Level")) + 1)); index0++) {
			roll = (double) (((new Random()).nextInt((int) 1111 + 1)) + (new Object() {
				public double getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Level")));
			if ((roll < 1000)) {
				if (((Destination).equals("Ocean"))) {
					Loot = new ItemStack((new Object() {
						public Item getRandomItem(String _tagName) {
							ITag<Item> _tag = ItemTags.getCollection().getTagByID(new ResourceLocation(_tagName));
							return _tag.getAllElements().isEmpty() ? Items.AIR : _tag.getRandomElement(new Random());
						}
					}.getRandomItem(("microcosm:ocean_slayer_common").toLowerCase(java.util.Locale.ENGLISH))));
				} else if (((Destination).equals("Nether"))) {
					Loot = new ItemStack(Blocks.AIR);
				} else if (((Destination).equals("End"))) {
					Loot = new ItemStack((new Object() {
						public Item getRandomItem(String _tagName) {
							ITag<Item> _tag = ItemTags.getCollection().getTagByID(new ResourceLocation(_tagName));
							return _tag.getAllElements().isEmpty() ? Items.AIR : _tag.getRandomElement(new Random());
						}
					}.getRandomItem(("microcosm:end_slayer_common").toLowerCase(java.util.Locale.ENGLISH))));
				} else {
					Loot = new ItemStack((new Object() {
						public Item getRandomItem(String _tagName) {
							ITag<Item> _tag = ItemTags.getCollection().getTagByID(new ResourceLocation(_tagName));
							return _tag.getAllElements().isEmpty() ? Items.AIR : _tag.getRandomElement(new Random());
						}
					}.getRandomItem(("microcosm:overworld_slayer_common").toLowerCase(java.util.Locale.ENGLISH))));
				}
			} else if ((roll < 1100)) {
				if (((Destination).equals("Ocean"))) {
					Loot = new ItemStack((new Object() {
						public Item getRandomItem(String _tagName) {
							ITag<Item> _tag = ItemTags.getCollection().getTagByID(new ResourceLocation(_tagName));
							return _tag.getAllElements().isEmpty() ? Items.AIR : _tag.getRandomElement(new Random());
						}
					}.getRandomItem(("microcosm:ocean_slayer_uncommon").toLowerCase(java.util.Locale.ENGLISH))));
				} else if (((Destination).equals("Nether"))) {
					Loot = new ItemStack((new Object() {
						public Item getRandomItem(String _tagName) {
							ITag<Item> _tag = ItemTags.getCollection().getTagByID(new ResourceLocation(_tagName));
							return _tag.getAllElements().isEmpty() ? Items.AIR : _tag.getRandomElement(new Random());
						}
					}.getRandomItem(("microcosm:nether_slayer_uncommon").toLowerCase(java.util.Locale.ENGLISH))));
				} else if (((Destination).equals("End"))) {
					Loot = new ItemStack((new Object() {
						public Item getRandomItem(String _tagName) {
							ITag<Item> _tag = ItemTags.getCollection().getTagByID(new ResourceLocation(_tagName));
							return _tag.getAllElements().isEmpty() ? Items.AIR : _tag.getRandomElement(new Random());
						}
					}.getRandomItem(("microcosm:end_slayer_common").toLowerCase(java.util.Locale.ENGLISH))));
				} else {
					Loot = new ItemStack((new Object() {
						public Item getRandomItem(String _tagName) {
							ITag<Item> _tag = ItemTags.getCollection().getTagByID(new ResourceLocation(_tagName));
							return _tag.getAllElements().isEmpty() ? Items.AIR : _tag.getRandomElement(new Random());
						}
					}.getRandomItem(("microcosm:overworld_slayer_uncommon").toLowerCase(java.util.Locale.ENGLISH))));
				}
			} else if ((roll < 1110)) {
				if (((Destination).equals("Ocean"))) {
					Loot = new ItemStack((new Object() {
						public Item getRandomItem(String _tagName) {
							ITag<Item> _tag = ItemTags.getCollection().getTagByID(new ResourceLocation(_tagName));
							return _tag.getAllElements().isEmpty() ? Items.AIR : _tag.getRandomElement(new Random());
						}
					}.getRandomItem(("microcosm:ocean_slayer_rare").toLowerCase(java.util.Locale.ENGLISH))));
					if (((Loot).getItem() == Items.TRIDENT)) {
						Loot = (EnchantmentHelper.addRandomEnchantment(new Random(), (Loot), (int) ((new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Level")) * 3), (true)));
					}
				} else if (((Destination).equals("Nether"))) {
					Loot = new ItemStack((new Object() {
						public Item getRandomItem(String _tagName) {
							ITag<Item> _tag = ItemTags.getCollection().getTagByID(new ResourceLocation(_tagName));
							return _tag.getAllElements().isEmpty() ? Items.AIR : _tag.getRandomElement(new Random());
						}
					}.getRandomItem(("microcosm:nether_slayer_rare").toLowerCase(java.util.Locale.ENGLISH))));
				} else if (((Destination).equals("End"))) {
					Loot = new ItemStack((new Object() {
						public Item getRandomItem(String _tagName) {
							ITag<Item> _tag = ItemTags.getCollection().getTagByID(new ResourceLocation(_tagName));
							return _tag.getAllElements().isEmpty() ? Items.AIR : _tag.getRandomElement(new Random());
						}
					}.getRandomItem(("microcosm:end_slayer_rare").toLowerCase(java.util.Locale.ENGLISH))));
				} else {
					Loot = new ItemStack((new Object() {
						public Item getRandomItem(String _tagName) {
							ITag<Item> _tag = ItemTags.getCollection().getTagByID(new ResourceLocation(_tagName));
							return _tag.getAllElements().isEmpty() ? Items.AIR : _tag.getRandomElement(new Random());
						}
					}.getRandomItem(("microcosm:overworld_slayer_rare").toLowerCase(java.util.Locale.ENGLISH))));
					if (((Loot).getItem() == Items.CROSSBOW)) {
						Loot = (EnchantmentHelper.addRandomEnchantment(new Random(), (Loot), (int) ((new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Level")) * 6), (true)));
					}
				}
			} else {
				if (((Destination).equals("Ocean"))) {
					Loot = new ItemStack((new Object() {
						public Item getRandomItem(String _tagName) {
							ITag<Item> _tag = ItemTags.getCollection().getTagByID(new ResourceLocation(_tagName));
							return _tag.getAllElements().isEmpty() ? Items.AIR : _tag.getRandomElement(new Random());
						}
					}.getRandomItem(("microcosm:ocean_slayer_legendary").toLowerCase(java.util.Locale.ENGLISH))));
					if (((Loot).getItem() == Items.TRIDENT)) {
						Loot = (EnchantmentHelper.addRandomEnchantment(new Random(), (Loot), (int) ((new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Level")) * 6), (true)));
					}
				} else if (((Destination).equals("Nether"))) {
					Loot = new ItemStack((new Object() {
						public Item getRandomItem(String _tagName) {
							ITag<Item> _tag = ItemTags.getCollection().getTagByID(new ResourceLocation(_tagName));
							return _tag.getAllElements().isEmpty() ? Items.AIR : _tag.getRandomElement(new Random());
						}
					}.getRandomItem(("microcosm:nether_slayer_legendary").toLowerCase(java.util.Locale.ENGLISH))));
				} else if (((Destination).equals("End"))) {
					Loot = new ItemStack((new Object() {
						public Item getRandomItem(String _tagName) {
							ITag<Item> _tag = ItemTags.getCollection().getTagByID(new ResourceLocation(_tagName));
							return _tag.getAllElements().isEmpty() ? Items.AIR : _tag.getRandomElement(new Random());
						}
					}.getRandomItem(("microcosm:end_slayer_rare").toLowerCase(java.util.Locale.ENGLISH))));
				} else {
					Loot = new ItemStack((new Object() {
						public Item getRandomItem(String _tagName) {
							ITag<Item> _tag = ItemTags.getCollection().getTagByID(new ResourceLocation(_tagName));
							return _tag.getAllElements().isEmpty() ? Items.AIR : _tag.getRandomElement(new Random());
						}
					}.getRandomItem(("microcosm:overworld_slayer_legendary").toLowerCase(java.util.Locale.ENGLISH))));
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
			for (int index1 = 0; index1 < (int) (27); index1++) {
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
			((ServerWorld) world).spawnParticle(ParticleStoneSwordParticle.particle, (x + 0.5), (y + 0.75), (z + 0.5), (int) 1, 0.12, 0.12, 0.12, 0);
		}
		if (world instanceof World && !world.isRemote()) {
			((World) world).playSound(null, new BlockPos((int) x, (int) y, (int) z),
					(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.skeleton.hurt")),
					SoundCategory.NEUTRAL, (float) 0.25, (float) 1);
		} else {
			((World) world).playSound(x, y, z,
					(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.skeleton.hurt")),
					SoundCategory.NEUTRAL, (float) 0.25, (float) 1, false);
		}
	}
}
