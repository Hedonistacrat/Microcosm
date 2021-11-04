
package net.mcreator.microcosm.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

import net.mcreator.microcosm.itemgroup.MicrocosmItemGroup;
import net.mcreator.microcosm.MicrocosmModElements;

@MicrocosmModElements.ModElement.Tag
public class TraceElementsItem extends MicrocosmModElements.ModElement {
	@ObjectHolder("microcosm:trace_elements")
	public static final Item block = null;
	public TraceElementsItem(MicrocosmModElements instance) {
		super(instance, 29);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(MicrocosmItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("trace_elements");
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
	}
}
