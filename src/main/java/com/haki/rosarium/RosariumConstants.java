package com.haki.rosarium;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RosariumConstants {

    static Logger LOGGER = LoggerFactory.getLogger(Rosarium.class);

    public static Logger getLogger() {
        return LOGGER;
    }

    static final int ROSE_RED = 0xff0052;

    public static int getRoseRed(boolean argb) {
        return argb ? ROSE_RED | 0xff000000 : ROSE_RED;
    }

    public static int getRoseRed(){
        return getRoseRed(false);
    }

    public static ResourceLocation modLoc(String path){
        return ResourceLocation.fromNamespaceAndPath(Rosarium.MODID, path);
    }
}
