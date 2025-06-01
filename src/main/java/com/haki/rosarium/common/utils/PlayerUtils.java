package com.haki.rosarium.common.utils;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class PlayerUtils {
    public static ItemStack getFromMainOrOffHand(Player player) {
       return player.getItemInHand(InteractionHand.MAIN_HAND).equals(ItemStack.EMPTY) ?
               player.getItemInHand(InteractionHand.OFF_HAND) : player.getItemInHand(InteractionHand.MAIN_HAND);
    }
}
