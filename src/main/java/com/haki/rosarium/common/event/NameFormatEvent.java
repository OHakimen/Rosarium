package com.haki.rosarium.common.event;

import com.haki.rosarium.Rosarium;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber(modid = Rosarium.MODID)
public class NameFormatEvent {

    @SubscribeEvent
    public static void nameFormat(PlayerEvent.NameFormat event){
//        if(SupporterHelper.isSupporter(event.getEntity().getUUID())){
//            SupporterHelper.getSupporter(event.getEntity().getUUID()).ifPresent(
//                    supporter -> event.setDisplayname(event.getDisplayname().copy().withColor(supporter.getTier().getColor()))
//            );
//        }
    }
}
