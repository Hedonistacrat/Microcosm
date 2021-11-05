
package net.mcreator.microcosm.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.Entity;
import net.minecraft.block.BlockState;

import net.mcreator.microcosm.procedures.WorkOrderBaseItemInHandTickProcedure;
import net.mcreator.microcosm.itemgroup.MicrocosmItemGroup;
import net.mcreator.microcosm.MicrocosmModElements;

import java.util.Map;
import java.util.HashMap;

@MicrocosmModElements.ModElement.Tag
public class WorkOrderBaseItem extends MicrocosmModElements.ModElement {
	@ObjectHolder("microcosm:work_order_base")
	public static final Item block = null;
	public WorkOrderBaseItem(MicrocosmModElements instance) {
		super(instance, 129);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(MicrocosmItemGroup.tab).maxStackSize(1).rarity(Rarity.COMMON));
			setRegistryName("work_order_base");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}

		@Override
		public void inventoryTick(ItemStack itemstack, World world, Entity entity, int slot, boolean selected) {
			super.inventoryTick(itemstack, world, entity, slot, selected);
			double x = entity.getPosX();
			double y = entity.getPosY();
			double z = entity.getPosZ();
			if (selected) {
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("itemstack", itemstack);
				$_dependencies.put("world", world);
				WorkOrderBaseItemInHandTickProcedure.executeProcedure($_dependencies);
			}
		}
	}
}
