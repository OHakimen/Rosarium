package com.haki.rosarium.client;

import com.haki.rosarium.Rosarium;
import com.haki.rosarium.client.api.rendering.RosariumShaders;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD, modid = Rosarium.MODID)
public class RosariumClient {

    @SubscribeEvent
    public static void onClient(FMLClientSetupEvent event){
        RosariumShaders.boostrap();
    }
}
