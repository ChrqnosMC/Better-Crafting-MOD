package net.chrqnos.better_crafting.mixin.block.entity;

import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.block.entity.CrafterBlockEntity;
import net.chrqnos.better_crafting.interfaces.ComponentRecipeInputProvider;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.item.ItemStack;
import net.chrqnos.better_crafting.recipe.ComponentRecipeMatcher;

@Mixin(CrafterBlockEntity.class)
public class CrafterBlockEntityMixin implements ComponentRecipeInputProvider {
    @Shadow
    private DefaultedList<ItemStack> inputStacks;

    @Override
    public void provideComponentRecipeInputs(ComponentRecipeMatcher finder) {
        for (ItemStack itemStack : this.inputStacks) {
            finder.addUnenchantedInput(itemStack);
        }
    }
}
