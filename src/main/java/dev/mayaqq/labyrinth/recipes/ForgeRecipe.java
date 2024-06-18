package dev.mayaqq.labyrinth.recipes;

import dev.mayaqq.labyrinth.registry.LabyrinthItems;
import dev.mayaqq.labyrinth.registry.LabyrinthRecipes;
import dev.mayaqq.labyrinth.utils.recipe.ForgeRecipeInput;
import dev.mayaqq.labyrinth.utils.recipe.IngredientStack;
import eu.pb4.polymer.core.api.item.PolymerRecipe;
import net.minecraft.block.Block;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.input.RecipeInput;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ForgeRecipe implements Recipe<ForgeRecipeInput>, PolymerRecipe {

    protected final DefaultedList<IngredientStack> input;
    private final ItemStack result;
    private final Identifier id;
    private final Block material;

    public ForgeRecipe(DefaultedList<IngredientStack> input, ItemStack result, Identifier id, Block material) {
        this.input = input;
        this.result = result;
        this.id = id;
        this.material = material;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> ingredients = DefaultedList.of();
        for (IngredientStack stack : input) {
            ingredients.add(stack.ingredient());
        }
        return ingredients;
    }

    public DefaultedList<IngredientStack> getIngredientStacks() {
        return this.input;
    }

    @Override
    public boolean matches(ForgeRecipeInput input, World world) {
        return true;
    }

    @Override
    public ItemStack craft(ForgeRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return getResult(lookup);
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return result;
    }

    public Block getMaterial() {
        return this.material;
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(LabyrinthItems.TEST_SWORD);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ForgeRecipeSerializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return LabyrinthRecipes.FORGING;
    }
}
