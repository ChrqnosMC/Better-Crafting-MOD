package net.chrqnos.better_crafting.mixin.recipe.input;

import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.chrqnos.better_crafting.interfaces.ComponentCraftingRecipeInput;
import org.spongepowered.asm.mixin.Unique;
import net.chrqnos.better_crafting.recipe.ComponentRecipeMatcher;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At;
import net.minecraft.recipe.RecipeMatcher;
import net.minecraft.item.ItemStack;

@Mixin(CraftingRecipeInput.class)
public class CraftingRecipeInputMixin implements ComponentCraftingRecipeInput {
    @Unique
    private final ComponentRecipeMatcher recipeMatcher = new ComponentRecipeMatcher();

    @Redirect(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/recipe/RecipeMatcher;addInput(Lnet/minecraft/item/ItemStack;I)V"
            )
    )
    private void addInputsToComponentRecipeMatcher(RecipeMatcher instance, ItemStack stack, int maxCount) {
        this.recipeMatcher.addInput(stack, maxCount);
    }

    @Override
    public ComponentRecipeMatcher CustomCrafting$getComponentRecipeMatcher() {
        return recipeMatcher;
    }
}
