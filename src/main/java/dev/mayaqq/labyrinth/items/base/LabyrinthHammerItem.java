package dev.mayaqq.labyrinth.items.base;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class LabyrinthHammerItem extends LabyrinthSwordItem {
    public LabyrinthHammerItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Item item, Settings settings, String id) {
        super(toolMaterial, attackDamage, attackSpeed, item, settings, id);
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.labyrinth.hammer.tooltip").formatted(Formatting.GRAY).formatted(Formatting.ITALIC));
        tooltip.add(Text.of(" "));
    }
}