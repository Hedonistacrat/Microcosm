
package net.mcreator.microcosm;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;

import net.mcreator.microcosm.item.MicroVillagerEssenceItem;
import net.mcreator.microcosm.item.FarmerItem;

@MicrocosmModElements.ModElement.Tag
public class FarmerRecipeBrewingRecipe extends MicrocosmModElements.ModElement {
	public FarmerRecipeBrewingRecipe(MicrocosmModElements instance) {
		super(instance, 98);
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		BrewingRecipeRegistry.addRecipe(new CustomBrewingRecipe());
	}
	public static class CustomBrewingRecipe implements IBrewingRecipe {
		@Override
		public boolean isInput(ItemStack input) {
			return input.getItem() == MicroVillagerEssenceItem.block;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == Items.POTATO;
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (isInput(input) && isIngredient(ingredient)) {
				return new ItemStack(FarmerItem.block);
			}
			return ItemStack.EMPTY;
		}
	}
}
