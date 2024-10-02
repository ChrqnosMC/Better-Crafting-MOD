package net.chrqnos.better_crafting.mixin.block.entity;

import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.chrqnos.better_crafting.interfaces.ComponentRecipeInputProvider;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.item.ItemStack;
import net.chrqnos.better_crafting.recipe.ComponentRecipeMatcher;

@Mixin(AbstractFurnaceBlockEntity.class)
public class AbstractFurnaceBlockEntityMixin implements ComponentRecipeInputProvider {
    @Shadow
    protected DefaultedList<ItemStack> inventory;

    @Override
    public void provideComponentRecipeInputs(ComponentRecipeMatcher finder) {
        for (ItemStack itemStack : this.inventory) {
            finder.addInput(itemStack);
        }
    }
}
