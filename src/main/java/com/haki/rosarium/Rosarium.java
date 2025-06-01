package com.haki.rosarium;

import com.haki.rosarium.common.Registry;
import com.haki.rosarium.extras.SupporterHelper;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(Rosarium.MODID)
public class Rosarium {


    public static final String MODID = "rosarium";
    public Rosarium(IEventBus modEventBus, ModContainer modContainer) {
        SupporterHelper.loadSupporterData();

        Registry.register(modEventBus);
    }
}
