package net.chrqnos.better_crafting.mixin.screen;

import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.chrqnos.better_crafting.interfaces.ComponentRecipeScreenHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.inventory.Inventory;
import net.chrqnos.better_crafting.recipe.ComponentRecipeMatcher;
import net.chrqnos.better_crafting.interfaces.ComponentRecipeInputProvider;

@Mixin(AbstractFurnaceScreenHandler.class)
public class AbstractFurnaceScreenHandlerMixin implements ComponentRecipeScreenHandler {
    @Final
    @Shadow
    private Inventory inventory;

    @Override
    public void populateComponentRecipeFinder(ComponentRecipeMatcher finder) {
        if (this.inventory instanceof ComponentRecipeInputProvider) {
            ((ComponentRecipeInputProvider)this.inventory).provideComponentRecipeInputs(finder);
        }
    }
}
