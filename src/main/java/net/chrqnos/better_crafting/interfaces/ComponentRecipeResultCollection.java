package net.chrqnos.better_crafting.interfaces;

import net.chrqnos.better_crafting.recipe.ComponentRecipeMatcher;
import net.minecraft.recipe.book.RecipeBook;

public interface ComponentRecipeResultCollection {
    default void computeComponentCraftables(ComponentRecipeMatcher recipeFinder, int gridWidth, int gridHeight, RecipeBook recipeBook) {
    }
}
