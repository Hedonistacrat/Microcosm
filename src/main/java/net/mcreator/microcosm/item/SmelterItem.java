
package net.mcreator.microcosm.item;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

import net.mcreator.microcosm.itemgroup.MicrocosmItemGroup;
import net.mcreator.microcosm.MicrocosmModElements;

@MicrocosmModElements.ModElement.Tag
public class SmelterItem extends MicrocosmModElements.ModElement {
	@ObjectHolder("microcosm:smelter")
	public static final Item block = null;
	public SmelterItem(MicrocosmModElements instance) {
		super(instance, 132);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(MicrocosmItemGroup.tab).maxDamage(1).rarity(Rarity.COMMON));
			setRegistryName("smelter");
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
		@OnlyIn(Dist.CLIENT)
		public boolean hasEffect(ItemStack itemstack) {
			return true;
		}
	}
}
