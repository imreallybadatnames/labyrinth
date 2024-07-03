package dev.mayaqq.labyrinth.items;

import dev.mayaqq.labyrinth.entities.RidableSquidEntity;

import dev.mayaqq.labyrinth.items.base.LabyrinthItem;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static dev.mayaqq.labyrinth.Labyrinth.id;

public class SquidFlierItem extends Item implements LabyrinthItem {
    private final Item polymerItem;
    private final int modelData;

    public SquidFlierItem(Item polymerItem, Item.Settings settings, String id) {
        super(settings);
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
        tooltip.add(Text.translatable("item.labyrinth.squid_flier.tooltip").formatted(Formatting.GRAY).formatted(Formatting.ITALIC));
        tooltip.add(Text.of(" "));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        ServerPlayerEntity player = (ServerPlayerEntity) user;
        user.getItemCooldownManager().set(this, 60);
        Vec3d vec = player.getRotationVector();
        RidableSquidEntity entity = new RidableSquidEntity(world, player.getX() + vec.x, player.getY() + 1, player.getZ() + vec.z, player);

        entity.setVelocity(vec.x, vec.y, vec.z);
        // set the skull's position to the player's position but add 1 to the direction the player is looking
        entity.updatePosition(user.getX() + vec.x, user.getY() + vec.y + 1, user.getZ() + vec.z);
        entity.setOwner(user);
        world.spawnEntity(entity);
        player.startRiding(entity);
        itemStack.damage(1, user, EquipmentSlot.MAINHAND);

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        return TypedActionResult.success(itemStack, true);
    }

}
