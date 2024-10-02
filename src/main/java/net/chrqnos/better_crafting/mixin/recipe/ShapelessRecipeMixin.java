package net.chrqnos.better_crafting.mixin.recipe;

import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.recipe.ShapelessRecipe;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At;
import net.minecraft.recipe.RecipeMatcher;
import net.minecraft.recipe.Recipe;
import it.unimi.dsi.fastutil.ints.IntList;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.chrqnos.better_crafting.interfaces.ComponentCraftingRecipeInput;

@Mixin(ShapelessRecipe.class)
public class ShapelessRecipeMixin {
    @Redirect(
            method = "matches(Lnet/minecraft/recipe/input/CraftingRecipeInput;Lnet/minecraft/world/World;)Z",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/recipe/RecipeMatcher;match(Lnet/minecraft/recipe/Recipe;Lit/unimi/dsi/fastutil/ints/IntList;)Z"
            )
    )
    private boolean matches(RecipeMatcher instance, Recipe<?> recipe, IntList output, @Local(argsOnly = true) CraftingRecipeInput crInput) {
        return ((ComponentCraftingRecipeInput)crInput).CustomCrafting$getComponentRecipeMatcher().match(recipe, null);
    }
}
