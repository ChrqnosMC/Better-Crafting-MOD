package net.chrqnos.better_crafting.mixin.recipe;

import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.recipe.Ingredient;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mutable;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.component.ComponentChanges;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Ingredient.StackEntry.class)
public abstract class Ingredient$StackEntryMixin {
    @Shadow
    @Final
    @Mutable
    private static Codec<Ingredient.StackEntry> CODEC;

    static {
        CODEC = RecordCodecBuilder.create(instance -> instance.group(ItemStack.REGISTRY_ENTRY_CODEC.fieldOf("item").forGetter(Ingredient.StackEntry::stack), ComponentChanges.CODEC.optionalFieldOf("components", ComponentChanges.EMPTY).forGetter(entry -> entry.stack().getComponentChanges())).apply(instance, Ingredient$StackEntryMixin::createStack));
    }

    private static Ingredient.StackEntry createStack(ItemStack stack, ComponentChanges changes) {
        return init(new ItemStack(stack.getRegistryEntry(), 1, changes));
    }

    @Invoker("<init>")
    private static Ingredient.StackEntry init(ItemStack stack) {
        throw new AssertionError();
    }
}
