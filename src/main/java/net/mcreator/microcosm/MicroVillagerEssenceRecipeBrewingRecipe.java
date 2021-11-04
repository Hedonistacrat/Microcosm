
package net.mcreator.microcosm;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;

import net.mcreator.microcosm.item.MicroVillagerEssenceItem;

@MicrocosmModElements.ModElement.Tag
public class MicroVillagerEssenceRecipeBrewingRecipe extends MicrocosmModElements.ModElement {
	public MicroVillagerEssenceRecipeBrewingRecipe(MicrocosmModElements instance) {
		super(instance, 96);
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		BrewingRecipeRegistry.addRecipe(new CustomBrewingRecipe());
	}
	public static class CustomBrewingRecipe implements IBrewingRecipe {
		@Override
		public boolean isInput(ItemStack input) {
			return input.getItem() == Items.EMERALD;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == Items.EGG;
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (isInput(input) && isIngredient(ingredient)) {
				return new ItemStack(MicroVillagerEssenceItem.block);
			}
			return ItemStack.EMPTY;
		}
	}
}
