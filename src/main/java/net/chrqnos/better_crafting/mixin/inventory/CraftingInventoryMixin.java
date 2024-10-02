package net.chrqnos.better_crafting.mixin.inventory;

import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.inventory.CraftingInventory;
import net.chrqnos.better_crafting.interfaces.ComponentRecipeInputProvider;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.item.ItemStack;
import net.chrqnos.better_crafting.recipe.ComponentRecipeMatcher;

@Mixin(CraftingInventory.class)
public class CraftingInventoryMixin implements ComponentRecipeInputProvider {
    @Final
    @Shadow
    private DefaultedList<ItemStack> stacks;

    @Override
    public void provideComponentRecipeInputs(ComponentRecipeMatcher finder) {
        for (ItemStack itemStack : this.stacks) {
            finder.addUnenchantedInput(itemStack);
        }
    }
}
