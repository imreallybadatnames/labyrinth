package dev.mayaqq.labyrinth.items.base;

import dev.mayaqq.labyrinth.utils.RegistryHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class LabyrinthCleaverItem extends LabyrinthSwordItem {
    public LabyrinthCleaverItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Item polymerItem, Settings settings, String id) {
        super(toolMaterial, attackDamage, attackSpeed, polymerItem, settings, id);
    }
    @Override
    public void onCraft(ItemStack stack, World world) {
        stack.addEnchantment(RegistryHelper.getEnchantment(Enchantments.SMITE, world), 4);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.labyrinth.cleaver.tooltip").formatted(Formatting.GRAY).formatted(Formatting.ITALIC));
        tooltip.add(Text.of(" "));
    }
}
