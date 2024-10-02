package net.chrqnos.better_crafting.interfaces;

import net.chrqnos.better_crafting.recipe.ComponentRecipeMatcher;

public interface ComponentRecipeInputProvider {
    default void provideComponentRecipeInputs(ComponentRecipeMatcher finder) {
    }
}
