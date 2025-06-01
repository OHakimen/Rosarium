package com.haki.rosarium.common.event;

import com.haki.rosarium.Rosarium;
import com.haki.rosarium.common.packets.SyncVariantChangeC2S;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = Rosarium.MODID, bus = EventBusSubscriber.Bus.MOD)
public class NetworkingRegistry {

    @SubscribeEvent
    public static void networkRegister(RegisterPayloadHandlersEvent event){
        final PayloadRegistrar registrar = event.registrar("1");

        registrar.playToServer(
                SyncVariantChangeC2S.TYPE,
                SyncVariantChangeC2S.STREAM_CODEC,
                SyncVariantChangeC2S::serverHandler
        );
    }
}
