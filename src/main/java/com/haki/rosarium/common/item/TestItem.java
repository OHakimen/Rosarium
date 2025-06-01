package com.haki.rosarium.common.item;

import com.haki.rosarium.RosariumConstants;
import com.haki.rosarium.client.screens.PickVariantScreen;
import com.haki.rosarium.common.api.item.IVariantHolder;
import com.haki.rosarium.common.api.item.IWipItem;
import com.haki.rosarium.common.api.item.RosariumItem;
import net.minecraft.client.Minecraft;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
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

        if(player.level().isClientSide){
            Minecraft.getInstance().setScreen(new PickVariantScreen(stack));
        }

        return InteractionResultHolder.success(stack);
    }

    @Override
    public void playerArmorTick(ItemStack stack, Level world, Player player, EquipmentSlot slot) {

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
    public void setVariant(ItemStack stack,int variant) {
        CompoundTag tag = new CompoundTag();
        tag.putInt("variation", variant);
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
    }

    @Override
    public List<Component> variantNames(ItemStack stack) {
        return List.of(
                Component.literal("The Stick of D  E  A  T  H").withColor(RosariumConstants.getRoseRed()),
                Component.literal("Empty Bottle").withColor(RosariumConstants.getRoseRed()),
                Component.literal("Sworb").withColor(RosariumConstants.getRoseRed())
        );
    }

    @Override
    public Component getName(ItemStack stack) {
        return variantNames(stack).get(getVariant(stack));
    }
}
