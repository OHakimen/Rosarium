package com.haki.rosarium.common.event;

import com.haki.rosarium.Rosarium;
import com.haki.rosarium.RosariumConstants;
import com.haki.rosarium.common.api.item.IVariantHolder;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.RegisterEvent;

@EventBusSubscriber(modid = Rosarium.MODID, bus = EventBusSubscriber.Bus.MOD)
public class VariantHandlerEvent {

    @SubscribeEvent
    public static void onRegister(final RegisterEvent event) {
        if(event.getRegistry().equals(BuiltInRegistries.ITEM)){
            BuiltInRegistries.ITEM.forEach(
                    (Item item) -> {
                        if(item instanceof IVariantHolder holder){
                            ItemProperties.register(item, RosariumConstants.modLoc("variant"),(itemStack, clientLevel, livingEntity, i) -> holder.getVariant(itemStack));
                        }
                    }
            );
        }
    }
}
