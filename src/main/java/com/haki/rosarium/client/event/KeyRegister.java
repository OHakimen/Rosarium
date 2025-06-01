package com.haki.rosarium.client.event;

import com.haki.rosarium.Rosarium;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.util.Lazy;

@EventBusSubscriber(value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD, modid = Rosarium.MODID)
public class KeyRegister {

    public static Lazy<KeyMapping> KEY_OPEN_VARIANT_PICKER = Lazy.of(() ->  new KeyMapping("key.rosarium.choose_variant", InputConstants.KEY_O, "key.categories.rosarium"));

    @SubscribeEvent
    public static void registerKeyEvent(RegisterKeyMappingsEvent event) {
        event.register(KEY_OPEN_VARIANT_PICKER.get());
    }
}
