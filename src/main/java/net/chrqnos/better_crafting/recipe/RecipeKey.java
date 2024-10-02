package net.chrqnos.better_crafting.recipe;

import oshi.util.tuples.Triplet;
import net.minecraft.component.ComponentChanges;
import org.jetbrains.annotations.NotNull;

public class RecipeKey extends Triplet<Integer, Integer, Integer> implements Comparable<RecipeKey> {
    public RecipeKey(Integer itemId, Integer componentHash, Integer originalComponentHash) {
        super(itemId, componentHash, originalComponentHash);
    }

    public int itemId() {
        return this.getA();
    }

    public int componentHash() {
        return this.getB();
    }

    public int originalComponentHash() {
        return this.getC();
    }

    public int compareTo(@NotNull RecipeKey o) {
        int itemIdDiff = this.getA() - o.getA();
        if (itemIdDiff != 0) {
            return itemIdDiff;
        } else {
            return this.getB() - o.getB();
        }
    }
}
