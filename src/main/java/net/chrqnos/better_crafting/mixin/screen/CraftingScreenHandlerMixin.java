package net.chrqnos.better_crafting.mixin.screen;

import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.screen.CraftingScreenHandler;
import net.chrqnos.better_crafting.interfaces.ComponentRecipeScreenHandler;
import net.minecraft.recipe.input.CraftingRecipeInput;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.chrqnos.better_crafting.recipe.ComponentRecipeMatcher;
import net.chrqnos.better_crafting.interfaces.ComponentRecipeInputProvider;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.Recipe;

@Mixin(CraftingScreenHandler.class)
public class CraftingScreenHandlerMixin implements ComponentRecipeScreenHandler<CraftingRecipeInput> {
    @Final
    @Shadow
    private RecipeInputInventory input;

    @Final
    @Shadow
    private PlayerEntity player;

    @Override
    public void populateComponentRecipeFinder(ComponentRecipeMatcher finder) {
        ((ComponentRecipeInputProvider)this.input).provideComponentRecipeInputs(finder);
    }

    @Override
    public boolean matchesWithComponents(RecipeEntry<? extends Recipe<CraftingRecipeInput>> recipe) {
        return recipe.value().matches(this.input.createRecipeInput(), this.player.getWorld());
    }
}
