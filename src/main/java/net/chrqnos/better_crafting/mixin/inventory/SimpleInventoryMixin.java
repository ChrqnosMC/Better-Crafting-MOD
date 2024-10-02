package net.chrqnos.better_crafting.mixin.inventory;

import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.inventory.SimpleInventory;
import net.chrqnos.better_crafting.interfaces.ComponentRecipeInputProvider;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.item.ItemStack;
import net.chrqnos.better_crafting.recipe.ComponentRecipeMatcher;

@Mixin(SimpleInventory.class)
public class SimpleInventoryMixin implements ComponentRecipeInputProvider {
    @Final
    @Shadow
    public DefaultedList<ItemStack> heldStacks;

    @Override
    public void provideComponentRecipeInputs(ComponentRecipeMatcher finder) {
        for (ItemStack itemStack : this.heldStacks) {
            finder.addInput(itemStack);
        }
    }
}
