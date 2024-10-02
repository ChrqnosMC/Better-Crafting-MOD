package net.chrqnos.better_crafting.interfaces;

import net.minecraft.recipe.input.RecipeInput;
import net.chrqnos.better_crafting.recipe.ComponentRecipeMatcher;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.Recipe;

public interface ComponentRecipeScreenHandler<T extends RecipeInput> {
    default void populateComponentRecipeFinder(ComponentRecipeMatcher matcher) {

    }
    default boolean matchesWithComponents(RecipeEntry<? extends Recipe<T>> recipe) {
        return false;
    }
}
