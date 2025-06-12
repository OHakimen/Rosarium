package com.haki.rosarium.client.utils;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.resources.ResourceLocation;

import java.io.IOException;
import java.io.InputStream;

public class ImageUtils {
    public static void registerImageRLFromBytes(ResourceLocation resourceLocation, InputStream inputStream) {
        try {
            NativeImage image = NativeImage.read(inputStream);
            DynamicTexture dynamicTexture = new DynamicTexture(image);
            Minecraft.getInstance().getTextureManager().register(resourceLocation,dynamicTexture);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
