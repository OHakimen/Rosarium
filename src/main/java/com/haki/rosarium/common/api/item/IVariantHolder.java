package com.haki.rosarium.common.api.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public interface IVariantHolder {
    int getVariant(ItemStack stack);
    int getMaxVariants(ItemStack stack);
    void setVariant(ItemStack stack, int variant);
    default List<Component> variantNames(ItemStack stack) {return List.of(); };
}
