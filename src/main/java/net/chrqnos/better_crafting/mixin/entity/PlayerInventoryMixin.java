package net.chrqnos.better_crafting.mixin.entity;

import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.entity.player.PlayerInventory;
import net.chrqnos.better_crafting.interfaces.ComponentPlayerInventory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.item.ItemStack;
import net.chrqnos.better_crafting.recipe.ComponentRecipeMatcher;
import java.util.Iterator;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin implements ComponentPlayerInventory {
    @Final
    @Shadow
    public DefaultedList<ItemStack> main;

    public void populateComponentRecipeFinder(ComponentRecipeMatcher matcher) {
        Iterator<ItemStack> mainIterator = this.main.iterator();
        while (mainIterator.hasNext()) {
            ItemStack itemStack = mainIterator.next();
            if (itemStack != ItemStack.EMPTY) {
                matcher.addUnenchantedInput(itemStack);
            }
        }
    }
}
