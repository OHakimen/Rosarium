package com.haki.rosarium.common;

import com.haki.rosarium.Rosarium;
import com.haki.rosarium.common.item.TestItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Registry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, Rosarium.MODID);

    public static final DeferredHolder<Item, TestItem> TEST_ITEM = ITEMS.register("test_item", () -> new TestItem(new Item.Properties().stacksTo(1)));

    public static void register(IEventBus bus){
        //ITEMS.register(bus);
    }
}
