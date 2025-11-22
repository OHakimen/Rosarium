package com.haki.rosarium.client.api.rendering.postprocessing;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.resources.ResourceLocation;
import oshi.util.tuples.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RosariumPostProcessingShaders {
    static List<Pair<TypedPostShader.Type, ResourceLocation>> defer = new ArrayList<>();
    static List<TypedPostShader> postProcessingShaders = new ArrayList<>();

    public static List<TypedPostShader> getPostProcessingShaders() {
        return postProcessingShaders;
    }

    public static void register(TypedPostShader.Type type, ResourceLocation location) {
        defer.add(new Pair<>(type, location));
    }

    public static void initializePostShaders(){
        for (TypedPostShader postProcessingShader : postProcessingShaders) {
            postProcessingShader.postPass.close();
        }
        postProcessingShaders.clear();
        for(Pair<TypedPostShader.Type, ResourceLocation> pair : defer){

            PostChain postPass = null;
            try {
                postPass = new PostChain(Minecraft.getInstance().getTextureManager(), Minecraft.getInstance().getResourceManager(), Minecraft.getInstance().getMainRenderTarget(), pair.getB());
                postPass.resize(Minecraft.getInstance().getWindow().getWidth(), Minecraft.getInstance().getWindow().getHeight());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            postProcessingShaders.add(new TypedPostShader(postPass, pair.getA()));
        }
    }
}
