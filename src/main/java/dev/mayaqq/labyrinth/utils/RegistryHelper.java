package dev.mayaqq.labyrinth.utils;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;

public class RegistryHelper {
    public static RegistryEntry<Enchantment> getEnchantment(RegistryKey<Enchantment> enchantment, World world) {
        return world.getRegistryManager().get(RegistryKeys.ENCHANTMENT).entryOf(enchantment);
    }
}
