package com.haki.rosarium.common.utils;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.joml.Math;

import java.awt.*;

public class ComponentsUtils {

    public static MutableComponent lerpColors(MutableComponent component, int startingColor, int endingColor) {
        MutableComponent component1 = Component.empty();

        String info = component.getString();
        for (int i = 0; i < info.length(); i++) {
            int finalI = i;
            component1.append(Component.literal("" + info.charAt(i)).withStyle(style -> style.withColor(ColorUtils.lerpColors(startingColor,endingColor, (float) finalI / info.length()))));
        }

        return component1;
    }

    public static MutableComponent segmentColors(MutableComponent component, int[] segmentColors) {
        MutableComponent component1 = Component.empty();

        String info = component.getString();

        float segmentStep = (float) info.length() / segmentColors.length;

        int sum = 0;
        int segmentIndex = 0;
        for (int i = 0; i < info.length(); i++) {
            if(sum >= segmentStep){
                segmentIndex++;
                sum = 0;
            }
            int finalSegmentIndex = segmentIndex;
            component1.append(Component.literal("" + info.charAt(i)).withStyle(style -> style.withColor(segmentColors[finalSegmentIndex])));
            sum++;
        }

        return component1;
    }
}
