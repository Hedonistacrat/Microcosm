
package net.mcreator.microcosm.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.microcosm.block.PathNoneBlockBlock;
import net.mcreator.microcosm.MicrocosmModElements;

@MicrocosmModElements.ModElement.Tag
public class MicrocosmItemGroup extends MicrocosmModElements.ModElement {
	public MicrocosmItemGroup(MicrocosmModElements instance) {
		super(instance, 4);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabmicrocosm") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(PathNoneBlockBlock.block);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
