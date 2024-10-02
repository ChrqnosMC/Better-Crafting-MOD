package net.chrqnos.better_crafting.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.gui.screen.recipebook.RecipeBookWidget;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.recipebook.ClientRecipeBook;
import net.minecraft.screen.AbstractRecipeScreenHandler;
import org.spongepowered.asm.mixin.Unique;
import net.chrqnos.better_crafting.recipe.ComponentRecipeMatcher;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At;
import net.minecraft.recipe.RecipeMatcher;
import net.chrqnos.better_crafting.interfaces.ComponentRecipeScreenHandler;
import net.minecraft.entity.player.PlayerInventory;
import net.chrqnos.better_crafting.interfaces.ComponentPlayerInventory;
import java.util.List;
import net.minecraft.client.gui.screen.recipebook.RecipeResultCollection;
import java.util.function.Consumer;
import net.chrqnos.better_crafting.interfaces.ComponentRecipeResultCollection;

@Mixin(RecipeBookWidget.class)
public class RecipeBookWidgetMixin {
    @Shadow
    private ClientRecipeBook recipeBook;

    @Shadow
    protected AbstractRecipeScreenHandler<?, ?> craftingScreenHandler;

    @Unique
    private final ComponentRecipeMatcher matcher = new ComponentRecipeMatcher();

    public RecipeBookWidgetMixin() {

    }

    @Redirect(
            method = {"reset"},
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/recipe/RecipeMatcher;clear()V"
            )
    )
    private void clearMatcher(RecipeMatcher instance) {
        this.matcher.clear();
    }

    @Redirect(
            method = {"reset"},
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/screen/AbstractRecipeScreenHandler;populateRecipeFinder(Lnet/minecraft/recipe/RecipeMatcher;)V"
            )
    )
    private void populateFromScreen(AbstractRecipeScreenHandler instance, RecipeMatcher recipeMatcher) {
        ((ComponentRecipeScreenHandler)instance).populateComponentRecipeFinder(this.matcher);
    }

    @Redirect(
            method = {"reset"},
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/PlayerInventory;populateRecipeFinder(Lnet/minecraft/recipe/RecipeMatcher;)V"
            )
    )
    private void populateFromPlayerInventory(PlayerInventory instance, RecipeMatcher finder) {
        ((ComponentPlayerInventory)instance).populateComponentRecipeFinder(this.matcher);
    }

    @Redirect(
            method = {"refreshResults"},
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/List;forEach(Ljava/util/function/Consumer;)V"
            )
    )
    private void refreshResults(List<RecipeResultCollection> instance, Consumer consumer) {
        instance.forEach((collection) -> {
            ((ComponentRecipeResultCollection)collection).computeComponentCraftables(this.matcher, this.craftingScreenHandler.getCraftingWidth(), this.craftingScreenHandler.getCraftingHeight(), this.recipeBook);
        });
    }

    @Redirect(
            method = {"refreshInputs"},
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/recipe/RecipeMatcher;clear()V"
            )
    )
    private void clearMatcher2(RecipeMatcher instance) {
        this.matcher.clear();
    }

    @Redirect(
            method = {"refreshInputs"},
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/screen/AbstractRecipeScreenHandler;populateRecipeFinder(Lnet/minecraft/recipe/RecipeMatcher;)V"
            )
    )
    private void populateFromScreen2(AbstractRecipeScreenHandler instance, RecipeMatcher recipeMatcher) {
        ((ComponentRecipeScreenHandler)instance).populateComponentRecipeFinder(this.matcher);
    }

    @Redirect(
            method = {"refreshInputs"},
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/PlayerInventory;populateRecipeFinder(Lnet/minecraft/recipe/RecipeMatcher;)V"
            )
    )
    private void populateFromPlayerInventory2(PlayerInventory instance, RecipeMatcher finder) {
        ((ComponentPlayerInventory)instance).populateComponentRecipeFinder(this.matcher);
    }
}
