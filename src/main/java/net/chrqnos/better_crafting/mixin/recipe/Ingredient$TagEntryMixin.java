package net.chrqnos.better_crafting.mixin.recipe;

import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.recipe.Ingredient;
import org.spongepowered.asm.mixin.Unique;
import net.minecraft.component.ComponentChanges;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At;
import java.util.List;
import net.minecraft.item.ItemStack;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import java.util.function.Function;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.recipe.Ingredient.TagEntry;
import com.mojang.datafixers.kinds.App;

@Mixin(Ingredient.TagEntry.class)
public abstract class Ingredient$TagEntryMixin {
    @Unique
    public ComponentChanges changes;

    @Redirect(
            method = "getStacks",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/List;add(Ljava/lang/Object;)Z"
            )
    )
    private <E> boolean createItemStackWithComponentChanges(List<ItemStack> instance, E e, @Local RegistryEntry<Item> entry) {
        return instance.add(new ItemStack(entry, 1, this.changes != null ? this.changes : ComponentChanges.EMPTY));
    }

    @ModifyArg(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/serialization/codecs/RecordCodecBuilder;create(Ljava/util/function/Function;)Lcom/mojang/serialization/Codec;",
                    remap = false
            )
    )
    private static Function<RecordCodecBuilder.Instance<TagEntry>, ? extends App<RecordCodecBuilder.Mu<TagEntry>, TagEntry>> attachComponentChanges(Function<RecordCodecBuilder.Instance<Ingredient.TagEntry>, ? extends App<RecordCodecBuilder.Mu<Ingredient.TagEntry>, Ingredient.TagEntry>> builder) {
        return instance -> instance.group(
                RecordCodecBuilder.mapCodec(builder).forGetter(Function.identity()),
                ComponentChanges.CODEC.optionalFieldOf("components", ComponentChanges.EMPTY).forGetter(entry -> ((Ingredient$TagEntryMixin)(Object)entry).changes)
        ).apply(instance, (entry, componentChanges) -> {
            ((Ingredient$TagEntryMixin)(Object)entry).changes = componentChanges;
            return entry;
        });
    }
}
