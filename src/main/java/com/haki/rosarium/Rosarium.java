package com.haki.rosarium;

import com.haki.rosarium.common.Registry;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

import static com.haki.rosarium.extras.SupporterHelper.loadSupporterData;

@Mod(Rosarium.MODID)
public class Rosarium {


    public static final String MODID = "rosarium";
    public Rosarium(IEventBus modEventBus, ModContainer modContainer) {
        loadSupporterData();

        Registry.register(modEventBus);
    }
}
