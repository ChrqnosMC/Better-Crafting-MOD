package net.chrqnos.better_crafting.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.gui.screen.recipebook.RecipeResultCollection;
import net.chrqnos.better_crafting.interfaces.ComponentRecipeResultCollection;
import org.spongepowered.asm.mixin.Shadow;
import java.util.List;
import net.minecraft.recipe.RecipeEntry;
import java.util.Set;
import com.google.common.collect.Sets;
import net.chrqnos.better_crafting.recipe.ComponentRecipeMatcher;
import net.minecraft.recipe.book.RecipeBook;
import java.util.Iterator;

@Mixin(RecipeResultCollection.class)
public class RecipeResultCollectionMixin implements ComponentRecipeResultCollection {
    @Shadow
    private List<RecipeEntry<?>> recipes;

    @Shadow
    private Set<RecipeEntry<?>> craftableRecipes = Sets.newHashSet();

    @Shadow
    private Set<RecipeEntry<?>> fittingRecipes = Sets.newHashSet();

    @Override
    public void computeComponentCraftables(ComponentRecipeMatcher recipeFinder, int gridWidth, int gridHeight, RecipeBook recipeBook) {
        Iterator<RecipeEntry<?>> recipeIterator = this.recipes.iterator();
        while (recipeIterator.hasNext()) {
            RecipeEntry<?> recipeEntry = recipeIterator.next();
            boolean bl = recipeEntry.value().fits(gridWidth, gridHeight) && recipeBook.contains(recipeEntry);
            if (bl) {
                this.fittingRecipes.add(recipeEntry);
            } else {
                this.fittingRecipes.remove(recipeEntry);
            }
            if (bl && recipeFinder.match(recipeEntry.value(), null)) {
                this.craftableRecipes.add(recipeEntry);
            } else {
                this.craftableRecipes.remove(recipeEntry);
            }
        }
    }
}
