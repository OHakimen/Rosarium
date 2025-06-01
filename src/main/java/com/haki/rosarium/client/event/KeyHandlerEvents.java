package com.haki.rosarium.client.event;

import com.haki.rosarium.Rosarium;
import com.haki.rosarium.client.screens.PickVariantScreen;
import com.haki.rosarium.common.api.item.IVariantHolder;
import com.haki.rosarium.common.utils.PlayerUtils;
import com.haki.rosarium.extras.SupporterHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

@EventBusSubscriber(value = Dist.CLIENT, bus = EventBusSubscriber.Bus.GAME, modid = Rosarium.MODID)
public class KeyHandlerEvents {

    @SubscribeEvent
    public static void registerKeyEvent(ClientTickEvent.Post event) {
        if(KeyRegister.KEY_OPEN_VARIANT_PICKER.get().consumeClick() && Minecraft.getInstance().player != null){
            Player player =  Minecraft.getInstance().player;

            ItemStack stack = PlayerUtils.getFromMainOrOffHand(Minecraft.getInstance().player);

            if(!(stack.getItem() instanceof IVariantHolder)){
                return;
            }

            if(!SupporterHelper.isSupporter(player.getUUID())){
                player.displayClientMessage(Component.literal("Only supporters can pick variants"), true);
                return;
            }



            Minecraft.getInstance().setScreen(new PickVariantScreen(stack));
        }
    }
}
