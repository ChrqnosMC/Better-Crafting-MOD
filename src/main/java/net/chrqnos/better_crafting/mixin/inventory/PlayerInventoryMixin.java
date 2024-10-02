package net.chrqnos.better_crafting.mixin.inventory;

import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.entity.player.PlayerInventory;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At;
import net.minecraft.item.ItemStack;
import net.minecraft.component.DataComponentTypes;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin {
    @Redirect(
            method = "indexOf",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;isDamaged()Z"
            )
    )
    private boolean makeIsDamagedAlwaysFalse(ItemStack instance) {
        return instance.getOrDefault(DataComponentTypes.MAX_STACK_SIZE, 64) != 1 && instance.isDamaged();
    }
}
