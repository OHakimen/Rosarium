package com.haki.rosarium.client.api.rendering;

import com.haki.rosarium.Rosarium;
import com.haki.rosarium.RosariumConstants;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.Util;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterShadersEvent;

import java.io.IOException;
import java.util.HashMap;
import java.util.function.BiFunction;

public class ShaderRegistry {

    @EventBusSubscriber(value = Dist.CLIENT, modid = Rosarium.MODID, bus = EventBusSubscriber.Bus.MOD)
    public static class ClientEvents {
        @SubscribeEvent
        public static void registerShaders(final RegisterShadersEvent event) {
            RosariumRenderType.shaderDefinition.forEach((resourceLocation, resourceLocationRenderTypeFunction) -> {
                try{
                    event.registerShader(new ShaderInstance(
                                    event.getResourceProvider(),
                                    resourceLocation,
                                    RosariumRenderType.vertexFormatHashMap.get(resourceLocation)),
                            shaderInstance -> {
                                RosariumRenderType.shaderInstanceHashMap.put(resourceLocation, shaderInstance);
                                RosariumRenderType.shards.put(resourceLocation, new RenderStateShard.ShaderStateShard(() -> shaderInstance));
                                RosariumConstants.getLogger().info("Loaded shader {}", resourceLocation);
                        }
                    );
                }catch (IOException e) {
                    RosariumConstants.getLogger().error("Failed to load shader for {}", resourceLocation, e);
                }
            });
        }
    }


    public static class RosariumRenderType extends RenderType {

        public static HashMap<ResourceLocation, BiFunction<RenderStateShard.ShaderStateShard, ResourceLocation, RenderType>> shaderDefinition = new HashMap<>();
        public static HashMap<ResourceLocation, ShaderInstance> shaderInstanceHashMap = new HashMap<>();
        public static HashMap<ResourceLocation, VertexFormat> vertexFormatHashMap = new HashMap<>();
        public static HashMap<ResourceLocation, ShaderStateShard> shards = new HashMap<>();

        public RosariumRenderType(String name, VertexFormat format, VertexFormat.Mode mode, int bufferSize, boolean affectsCrumbling, boolean sortOnUpload, Runnable setupState, Runnable clearState) {
            super(name, format, mode, bufferSize, affectsCrumbling, sortOnUpload, setupState, clearState);
            throw new IllegalStateException("This class is not meant to be constructed!");
        }
    }

    public static RosariumShader addShader(ResourceLocation name, BiFunction<RenderStateShard.ShaderStateShard, ResourceLocation, RenderType> shaderInstance, VertexFormat format){
        RosariumRenderType.shaderDefinition.put(name, Util.memoize(shaderInstance));
        RosariumRenderType.vertexFormatHashMap.put(name, format);

        return new RosariumShader(name);
    }
}
