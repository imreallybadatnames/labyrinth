package dev.mayaqq.labyrinth.registry.materials;

import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;

public enum CustomMaterials implements ToolMaterial {
    IRON(BlockTags.INCORRECT_FOR_IRON_TOOL, 2, 200, 6.0F, 0.0F, 14, () -> {
        return Ingredient.ofItems(Items.IRON_INGOT);
    }),
    DIAMOND(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3, 3000, 8.0F, 0.0F, 10, () -> {
        return Ingredient.ofItems(Items.DIAMOND);
    }),
    GOLD(BlockTags.INCORRECT_FOR_GOLD_TOOL, 0, 100, 12.0F, 0.0F, 22, () -> {
        return Ingredient.ofItems(Items.GOLD_INGOT);
    }),
    NETHERITE(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 4, 3800, 9.0F, 0.0F, 15, () -> {
        return Ingredient.ofItems(Items.NETHERITE_INGOT);
    });

    private final TagKey<Block> inverseTag;
    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Lazy<Ingredient> repairIngredient;

    private CustomMaterials(TagKey<Block> inverseTag, int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier repairIngredient) {
        this.inverseTag = inverseTag;
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = new Lazy(repairIngredient);
    }

    public int getDurability() {
        return this.itemDurability;
    }

    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public TagKey<Block> getInverseTag() {
        return this.inverseTag;
    }

    public int getMiningLevel() {
        return this.miningLevel;
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
