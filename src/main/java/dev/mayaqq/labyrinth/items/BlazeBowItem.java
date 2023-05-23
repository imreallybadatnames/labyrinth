package dev.mayaqq.labyrinth.items;

import dev.mayaqq.labyrinth.items.base.LabyrinthItem;
import eu.pb4.polymer.core.api.item.PolymerItem;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static dev.mayaqq.labyrinth.Labyrinth.id;

public class BlazeBowItem extends BowItem implements PolymerItem, LabyrinthItem {
    private final Item polymerItem;
    private final int customModelData;

    public BlazeBowItem(Item polymerItem, String id) {
        super(new Item.Settings());
        this.polymerItem = polymerItem;
        this.customModelData = PolymerResourcePackUtils.requestModel(polymerItem, id("item/" + id)).value();
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return this.polymerItem;
    }
    @Override
    public int getPolymerCustomModelData(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return this.customModelData;
    }
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.labyrinth.blazebow.tooltip").formatted(Formatting.GRAY));
    }
    @Override
    public void onCraft(ItemStack itemStack, World world, net.minecraft.entity.player.PlayerEntity playerEntity) {
        itemStack.addEnchantment(Enchantments.FLAME, 1);
    }
}
