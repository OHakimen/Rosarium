package com.haki.rosarium;

public class RosariumConstants {
    static final int ROSE_RED = 0xff0052;

    public static int getRoseRed(boolean argb) {
        return argb ? ROSE_RED | 0xff000000 : ROSE_RED;
    }
}
