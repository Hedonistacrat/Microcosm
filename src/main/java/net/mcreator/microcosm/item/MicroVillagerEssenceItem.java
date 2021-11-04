
package net.mcreator.microcosm.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

import net.mcreator.microcosm.itemgroup.MicrocosmItemGroup;
import net.mcreator.microcosm.MicrocosmModElements;

@MicrocosmModElements.ModElement.Tag
public class MicroVillagerEssenceItem extends MicrocosmModElements.ModElement {
	@ObjectHolder("microcosm:micro_villager_essence")
	public static final Item block = null;
	public MicroVillagerEssenceItem(MicrocosmModElements instance) {
		super(instance, 95);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(MicrocosmItemGroup.tab).maxStackSize(16).rarity(Rarity.COMMON));
			setRegistryName("micro_villager_essence");
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
