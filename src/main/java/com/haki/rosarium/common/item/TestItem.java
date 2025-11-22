package com.haki.rosarium.common.item;

import com.haki.rosarium.client.api.rendering.postprocessing.RosariumPostProcessingShaders;
import com.haki.rosarium.client.api.rendering.postprocessing.TypedPostShader;
import com.haki.rosarium.common.api.item.IVariantHolder;
import com.haki.rosarium.common.api.item.IWipItem;
import com.haki.rosarium.common.api.item.RosariumItem;
import com.haki.rosarium.common.utils.ColorUtils;
import com.haki.rosarium.common.utils.ComponentsUtils;
import com.haki.rosarium.mixins.client.PostShaderAccessor;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;

import java.util.List;

public class TestItem extends RosariumItem implements IWipItem, IVariantHolder {
    public TestItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);

        if (level.isClientSide) {
            if (!RosariumPostProcessingShaders.getPostProcessingShaders().isEmpty()) {
                TypedPostShader thing = RosariumPostProcessingShaders.getPostProcessingShaders().getFirst();
                thing.setActive(!RosariumPostProcessingShaders.getPostProcessingShaders().getFirst().isActive());
            }
        }

        return InteractionResultHolder.success(stack);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }

    @Override
    public int getVariant(ItemStack stack) {
        return stack.has(DataComponents.CUSTOM_DATA) ? stack.get(DataComponents.CUSTOM_DATA).copyTag().getInt("variation") : 0;
    }

    @Override
    public int getMaxVariants(ItemStack stack) {
        return 3;
    }

    @Override
    public void setVariant(ItemStack stack, int variant) {
        CompoundTag tag = new CompoundTag();
        tag.putInt("variation", variant);
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
    }

    @Override
    public List<Component> variantNames(ItemStack stack) {
        return List.of(
                Component.literal("ZA WARUDO"),
                Component.literal("Empty Bottle"),
                Component.literal("Sworb")
        );
    }

    @Override
    public Component getName(ItemStack stack) {
        return variantNames(stack).get(getVariant(stack));
    }
}
