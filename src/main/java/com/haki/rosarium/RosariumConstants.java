package com.haki.rosarium;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RosariumConstants {

    public static final int ROSE_RED = 0xff0052;

    static Logger LOGGER = LoggerFactory.getLogger(Rosarium.class);

    public static Logger getLogger() {
        return LOGGER;
    }

    public static ResourceLocation modLoc(String path) {
        return ResourceLocation.fromNamespaceAndPath(Rosarium.MODID, path);
    }
}
