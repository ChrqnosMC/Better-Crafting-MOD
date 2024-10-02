package net.chrqnos.better_crafting.mixin.recipe;

import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.recipe.Ingredient;
import net.chrqnos.better_crafting.interfaces.IngredientAdditionalMethods;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import org.spongepowered.asm.mixin.injection.At;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.item.ItemStack;
import net.minecraft.component.ComponentChanges;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Ingredient.class)
public class IngredientMixin implements IngredientAdditionalMethods {
    @ModifyReturnValue(
            method = "test(Lnet/minecraft/item/ItemStack;)Z",
            at = @At(
                    value = "RETURN",
                    ordinal = 2
            )
    )
    private boolean test(boolean isOfItem, @Local(ordinal = 0, argsOnly = true)ItemStack itemStack, @Local(ordinal = 1) ItemStack recipeStack) {
        ComponentChanges recipeChanges = recipeStack.getComponentChanges();
        ComponentChanges itemChanges = itemStack.getComponentChanges();
        boolean result;
        if (recipeChanges.isEmpty() && !itemChanges.isEmpty()) {
            result = false;
        } else {
            result = itemChanges.entrySet().containsAll(recipeChanges.entrySet());
        }
        return result;
    }

    @Override
    public boolean CustomCrafting$testClean(ItemStack stack) {
        if (stack == null) {
            return false;
        } else if (this.isEmpty()) {
            return stack.isEmpty();
        } else {
            ItemStack[] var2 = this.getMatchingStacks();
            int var3 = var2.length;
            for(int var4 = 0; var4 < var3; ++var4) {
                ItemStack itemStack2 = var2[var4];
                if (itemStack2.isOf(stack.getItem())) {
                    return true;
                }
            }
            return false;
        }
    }

    @Shadow
    boolean isEmpty() {
        return true;
    }

    @Shadow
    ItemStack[] getMatchingStacks() {
        return new ItemStack[0];
    }
}
