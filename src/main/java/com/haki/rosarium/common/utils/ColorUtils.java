package com.haki.rosarium.common.utils;

import org.joml.Math;

import java.awt.*;

public class ColorUtils {

    public static int[] unpackRGB(int rgb){
        return new int[] {
                rgb >> 16 & 0xff,
                rgb >> 8 & 0xff,
                rgb & 0xff
        };
    }

    public static int packRGB(int[] rgb){
        return (rgb[0] << 16) | (rgb[1] << 8) | rgb[2];
    }

    public static float[] RGBtoHSV(int rgb){

        int[] unpackRGB = unpackRGB(rgb);

        float[] hsv = new float[3];
        int r = unpackRGB[0];
        int g = unpackRGB[1];
        int b = unpackRGB[2];

        float hue, saturation, brightness;

        int cmax = (r > g) ? r : g;
        if (b > cmax) cmax = b;
        int cmin = (r < g) ? r : g;
        if (b < cmin) cmin = b;

        brightness = ((float) cmax) / 255.0f;
        if (cmax != 0)
            saturation = ((float) (cmax - cmin)) / ((float) cmax);
        else
            saturation = 0;
        if (saturation == 0)
            hue = 0;
        else {
            float redc = ((float) (cmax - r)) / ((float) (cmax - cmin));
            float greenc = ((float) (cmax - g)) / ((float) (cmax - cmin));
            float bluec = ((float) (cmax - b)) / ((float) (cmax - cmin));
            if (r == cmax)
                hue = bluec - greenc;
            else if (g == cmax)
                hue = 2.0f + redc - bluec;
            else
                hue = 4.0f + greenc - redc;
            hue = hue / 6.0f;
            if (hue < 0)
                hue = hue + 1.0f;
        }
        hsv[0] = hue;
        hsv[1] = saturation;
        hsv[2] = brightness;
        return hsv;
    }

    public static int HSVtoRGB(float hue, float saturation, float value){
        int r = 0, g = 0, b = 0;
        if (saturation == 0) {
            r = g = b = (int) (value * 255.0f + 0.5f);
        } else {
            float h = (hue - (float) java.lang.Math.floor(hue)) * 6.0f;
            float f = h - (float)java.lang.Math.floor(h);
            float p = value * (1.0f - saturation);
            float q = value * (1.0f - saturation * f);
            float t = value * (1.0f - (saturation * (1.0f - f)));
            switch ((int) h) {
                case 0:
                    r = (int) (value * 255.0f + 0.5f);
                    g = (int) (t * 255.0f + 0.5f);
                    b = (int) (p * 255.0f + 0.5f);
                    break;
                case 1:
                    r = (int) (q * 255.0f + 0.5f);
                    g = (int) (value * 255.0f + 0.5f);
                    b = (int) (p * 255.0f + 0.5f);
                    break;
                case 2:
                    r = (int) (p * 255.0f + 0.5f);
                    g = (int) (value * 255.0f + 0.5f);
                    b = (int) (t * 255.0f + 0.5f);
                    break;
                case 3:
                    r = (int) (p * 255.0f + 0.5f);
                    g = (int) (q * 255.0f + 0.5f);
                    b = (int) (value * 255.0f + 0.5f);
                    break;
                case 4:
                    r = (int) (t * 255.0f + 0.5f);
                    g = (int) (p * 255.0f + 0.5f);
                    b = (int) (value * 255.0f + 0.5f);
                    break;
                case 5:
                    r = (int) (value * 255.0f + 0.5f);
                    g = (int) (p * 255.0f + 0.5f);
                    b = (int) (q * 255.0f + 0.5f);
                    break;
            }
        }
        return packRGB(new int[]{r, g, b});
    }

    public static int lerpColors(int startingColor, int endingColor, float lerp){

        float [] aHSV = RGBtoHSV(startingColor);
        float [] bHSV = RGBtoHSV(endingColor);

        return HSVtoRGB(
                Math.lerp(aHSV[0], bHSV[0], lerp),
                Math.lerp(aHSV[1], bHSV[1], lerp),
                Math.lerp(aHSV[2], bHSV[2], lerp)
        );
    }
}
