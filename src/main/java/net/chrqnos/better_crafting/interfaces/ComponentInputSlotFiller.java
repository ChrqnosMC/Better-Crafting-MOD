package net.chrqnos.better_crafting.interfaces;

import net.minecraft.recipe.RecipeEntry;
import java.util.Iterator;
import net.minecraft.item.ItemStack;

public interface ComponentInputSlotFiller {
    default void CustomCrafting$alignComponentRecipeToGrid(int gridWidth, int gridHeight, int gridOutputSlot, RecipeEntry<?> recipe, Iterator<ItemStack> inputs, int amount) {

    }
}
