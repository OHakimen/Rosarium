package com.haki.rosarium.common.api.item;

import com.haki.rosarium.RosariumConstants;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public abstract class RosariumItem extends Item {
    public RosariumItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

        if(stack.getItem() instanceof IWipItem) {
            tooltipComponents.add(Component.literal("This item is a WIP").withColor(RosariumConstants.ROSE_RED));
            tooltipComponents.add(Component.literal("This item might not behave as expected, here be dragons").withStyle(ChatFormatting.GRAY));

            if(stack.getItem() instanceof IVariantHolder holder) {
                tooltipComponents.add(Component.literal("   Variant : %s/%s".formatted(holder.getVariant(stack) + 1, holder.getMaxVariants(stack))).withColor(RosariumConstants.ROSE_RED));
            }
        }
    }

    public void playerArmorTick(ItemStack stack, Level world, Player player, EquipmentSlot slot) {

    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if(entity instanceof Player player) {
            if(slotId >= 36 && slotId <= 39) {
                playerArmorTick(stack,level,player,switch (slotId) {
                    case 39 -> EquipmentSlot.HEAD;
                    case 38 -> EquipmentSlot.CHEST;
                    case 37 -> EquipmentSlot.LEGS;
                    case 36 -> EquipmentSlot.FEET;

                    default -> throw new IllegalStateException("Unexpected value: " + slotId);
                });
            }
        }

        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }
}
