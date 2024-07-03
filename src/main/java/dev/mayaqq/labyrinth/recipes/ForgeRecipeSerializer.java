package dev.mayaqq.labyrinth.recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.mayaqq.labyrinth.utils.recipe.IngredientStack;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.collection.DefaultedList;

public class ForgeRecipeSerializer implements RecipeSerializer<ForgeRecipe> {
    private static final MapCodec<ForgeRecipe> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            Codec.STRING.optionalFieldOf("group", "").forGetter((recipe) -> recipe.group),
            IngredientStack.CODEC.listOf().fieldOf("input").flatXmap(ingredientStacks -> {
                IngredientStack[] results = ingredientStacks.stream().filter((ingredient) -> !ingredient.ingredient().isEmpty()).toArray(IngredientStack[]::new);
                if (results.length == 0) {
                    return DataResult.error(() -> "No ingredients for shapeless recipe");
                } else {
                    return DataResult.success(DefaultedList.copyOf(IngredientStack.EMPTY, results));
                }
            }, DataResult::success).forGetter((recipe) -> recipe.input),
            Block.CODEC.fieldOf("material").forGetter((recipe) -> recipe.getMaterial()),
            ItemStack.VALIDATED_CODEC.fieldOf("result").forGetter((recipe) -> recipe.getResult())
    ).apply(instance, ForgeRecipe::new));

    public static final PacketCodec<RegistryByteBuf, ForgeRecipe> PACKET_CODEC = PacketCodec.tuple(
            PacketCodecs.STRING, ForgeRecipe::getGroup,
            IngredientStack.PACKET_CODEC, ForgeRecipe::getIngredientStacks,
            B, ForgeRecipe::getMaterial,
            ItemStack.PACKET_CODEC, ForgeRecipe::getResult,
            ForgeRecipe::new
    );

    @Override
    public MapCodec<ForgeRecipe> codec() {
        return null;
    }

    @Override
    public PacketCodec<RegistryByteBuf, ForgeRecipe> packetCodec() {
        return null;
    }
}
