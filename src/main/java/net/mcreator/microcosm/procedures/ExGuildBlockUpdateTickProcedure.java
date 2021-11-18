package net.mcreator.microcosm.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.ITag;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.item.ItemEntity;

import net.mcreator.microcosm.MicrocosmMod;

import java.util.Random;
import java.util.Map;

public class ExGuildBlockUpdateTickProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency x for procedure ExGuildBlockUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency y for procedure ExGuildBlockUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency z for procedure ExGuildBlockUpdateTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MicrocosmMod.LOGGER.warn("Failed to load dependency world for procedure ExGuildBlockUpdateTick!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double choice = 0;
		choice = (double) ((new Random()).nextInt((int) 9 + 1));
		if ((choice == 0)) {
			if (world instanceof World && !world.isRemote()) {
				ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, new ItemStack((new Object() {
					public Item getRandomItem(String _tagName) {
						ITag<Item> _tag = ItemTags.getCollection().getTagByID(new ResourceLocation(_tagName));
						return _tag.getAllElements().isEmpty() ? Items.AIR : _tag.getRandomElement(new Random());
					}
				}.getRandomItem(("curios:head").toLowerCase(java.util.Locale.ENGLISH)))));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
		} else if ((choice == 1)) {
			if (world instanceof World && !world.isRemote()) {
				ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, new ItemStack((new Object() {
					public Item getRandomItem(String _tagName) {
						ITag<Item> _tag = ItemTags.getCollection().getTagByID(new ResourceLocation(_tagName));
						return _tag.getAllElements().isEmpty() ? Items.AIR : _tag.getRandomElement(new Random());
					}
				}.getRandomItem(("curios:necklace").toLowerCase(java.util.Locale.ENGLISH)))));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
		} else if ((choice == 2)) {
			if (world instanceof World && !world.isRemote()) {
				ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, new ItemStack((new Object() {
					public Item getRandomItem(String _tagName) {
						ITag<Item> _tag = ItemTags.getCollection().getTagByID(new ResourceLocation(_tagName));
						return _tag.getAllElements().isEmpty() ? Items.AIR : _tag.getRandomElement(new Random());
					}
				}.getRandomItem(("curios:back").toLowerCase(java.util.Locale.ENGLISH)))));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
		} else if ((choice == 3)) {
			if (world instanceof World && !world.isRemote()) {
				ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, new ItemStack((new Object() {
					public Item getRandomItem(String _tagName) {
						ITag<Item> _tag = ItemTags.getCollection().getTagByID(new ResourceLocation(_tagName));
						return _tag.getAllElements().isEmpty() ? Items.AIR : _tag.getRandomElement(new Random());
					}
				}.getRandomItem(("curios:body").toLowerCase(java.util.Locale.ENGLISH)))));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
		} else if ((choice == 4)) {
			if (world instanceof World && !world.isRemote()) {
				ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, new ItemStack((new Object() {
					public Item getRandomItem(String _tagName) {
						ITag<Item> _tag = ItemTags.getCollection().getTagByID(new ResourceLocation(_tagName));
						return _tag.getAllElements().isEmpty() ? Items.AIR : _tag.getRandomElement(new Random());
					}
				}.getRandomItem(("curios:trinkets").toLowerCase(java.util.Locale.ENGLISH)))));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
		} else if ((choice == 5)) {
			if (world instanceof World && !world.isRemote()) {
				ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, new ItemStack((new Object() {
					public Item getRandomItem(String _tagName) {
						ITag<Item> _tag = ItemTags.getCollection().getTagByID(new ResourceLocation(_tagName));
						return _tag.getAllElements().isEmpty() ? Items.AIR : _tag.getRandomElement(new Random());
					}
				}.getRandomItem(("curios:hands").toLowerCase(java.util.Locale.ENGLISH)))));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
		} else if ((choice == 6)) {
			if (world instanceof World && !world.isRemote()) {
				ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, new ItemStack((new Object() {
					public Item getRandomItem(String _tagName) {
						ITag<Item> _tag = ItemTags.getCollection().getTagByID(new ResourceLocation(_tagName));
						return _tag.getAllElements().isEmpty() ? Items.AIR : _tag.getRandomElement(new Random());
					}
				}.getRandomItem(("curios:ring").toLowerCase(java.util.Locale.ENGLISH)))));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
		} else if ((choice == 7)) {
			if (world instanceof World && !world.isRemote()) {
				ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, new ItemStack((new Object() {
					public Item getRandomItem(String _tagName) {
						ITag<Item> _tag = ItemTags.getCollection().getTagByID(new ResourceLocation(_tagName));
						return _tag.getAllElements().isEmpty() ? Items.AIR : _tag.getRandomElement(new Random());
					}
				}.getRandomItem(("curios:belt").toLowerCase(java.util.Locale.ENGLISH)))));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
		} else if ((choice == 8)) {
			if (world instanceof World && !world.isRemote()) {
				ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, new ItemStack((new Object() {
					public Item getRandomItem(String _tagName) {
						ITag<Item> _tag = ItemTags.getCollection().getTagByID(new ResourceLocation(_tagName));
						return _tag.getAllElements().isEmpty() ? Items.AIR : _tag.getRandomElement(new Random());
					}
				}.getRandomItem(("curios:charm").toLowerCase(java.util.Locale.ENGLISH)))));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
		} else {
			if (world instanceof World && !world.isRemote()) {
				ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, new ItemStack((new Object() {
					public Item getRandomItem(String _tagName) {
						ITag<Item> _tag = ItemTags.getCollection().getTagByID(new ResourceLocation(_tagName));
						return _tag.getAllElements().isEmpty() ? Items.AIR : _tag.getRandomElement(new Random());
					}
				}.getRandomItem(("curios:feet").toLowerCase(java.util.Locale.ENGLISH)))));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
		}
	}
}
