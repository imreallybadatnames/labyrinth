package dev.mayaqq.labyrinth.utils.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public class ForgeRecipeInput implements RecipeInput {
    @Override
    public ItemStack getStackInSlot(int slot) {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }
}
