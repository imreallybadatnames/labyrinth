package dev.mayaqq.labyrinth.entities;

import dev.mayaqq.labyrinth.registry.LabyrinthEntities;
import dev.mayaqq.labyrinth.registry.LabyrinthItems;
import dev.mayaqq.labyrinth.registry.materials.CustomMaterials;
import eu.pb4.polymer.virtualentity.api.elements.DisplayElement;
import eu.pb4.polymer.virtualentity.api.elements.ItemDisplayElement;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.HashMap;

public class TomahawkEntity extends BaseProjectileEntity {
    public TomahawkEntity(EntityType<TomahawkEntity> entityType, World world, ItemStack stack ,ToolMaterial material, int slot) {
        super(entityType, world, stack, material, slot);
    }

    public static TomahawkEntity create(World world, LivingEntity owner, ItemStack stack, ToolMaterial material, int slot, BlockPos pos) {
        var entity = new TomahawkEntity(LabyrinthEntities.TOMAHAWK, world, stack, material, slot);
        var vec = Vec3d.ofCenter(pos);
        entity.setPosition(vec.x, vec.y, vec.z);
        return entity;
    }


    @Override
    protected Item materialToItem(ToolMaterial material) {
        HashMap<ToolMaterial, Item> materialToItem = new HashMap<>() {{
            put(CustomMaterials.IRON, LabyrinthItems.IRON_TOMAHAWK);
            put(CustomMaterials.GOLD, LabyrinthItems.GOLDEN_TOMAHAWK);
            put(CustomMaterials.DIAMOND, LabyrinthItems.DIAMOND_TOMAHAWK);
            put(CustomMaterials.NETHERITE, LabyrinthItems.NETHERITE_TOMAHAWK);
        }};
        return materialToItem.get(material);
    }

    @Override
    protected DisplayElement createMainDisplayElement() {
        var x = new ItemDisplayElement();
        x.setInterpolationDuration(1);
        x.setItem(this.projectileStack);
        return x;
    }
}
