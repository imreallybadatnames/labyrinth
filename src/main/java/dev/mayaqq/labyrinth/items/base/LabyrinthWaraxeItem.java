package dev.mayaqq.labyrinth.items.base;

import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static dev.mayaqq.labyrinth.Labyrinth.id;

public class LabyrinthWaraxeItem extends AxeItem implements LabyrinthItem {
    private final Item polymerItem;
    private final int modelData;
    public LabyrinthWaraxeItem(ToolMaterial material, float attackDamage, float attackSpeed, Item polymerItem, Settings settings, String id) {
        super(material, settings.attributeModifiers(AxeItem.createAttributeModifiers(material, attackDamage - 1, attackSpeed)));
        this.polymerItem = polymerItem;
        this.modelData = PolymerResourcePackUtils.requestModel(polymerItem, id("item/" + id)).value();
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return this.polymerItem;
    }

    @Override
    public int getPolymerCustomModelData(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return this.modelData;
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.labyrinth.waraxe.tooltip").formatted(Formatting.GRAY).formatted(Formatting.ITALIC));
        tooltip.add(Text.of(" "));
    }
}
