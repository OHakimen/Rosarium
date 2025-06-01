package com.haki.rosarium.client.api.rendering;

import com.haki.rosarium.Rosarium;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RosariumShaders {
    public static RosariumShader TRIANGLE_FAN = ShaderRegistry.addShader(ResourceLocation.fromNamespaceAndPath(Rosarium.MODID, "rendertype_triangle"), (shaderStateShard, resourceLocation) -> {
        RenderType.CompositeState rendertype$state =
                RenderType.CompositeState.builder()
                        .setShaderState(shaderStateShard)
                        .setTextureState(new RenderStateShard.TextureStateShard(resourceLocation, true, true))
                        .setLightmapState(RenderStateShard.NO_LIGHTMAP)
                        .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                        .setCullState(RenderStateShard.NO_CULL)
                        .createCompositeState(false);
        return RenderType.create("triangle_fan", DefaultVertexFormat.POSITION_COLOR_TEX_LIGHTMAP, VertexFormat.Mode.TRIANGLE_STRIP, 1536, false, true, rendertype$state);
    }, DefaultVertexFormat.POSITION_COLOR_TEX_LIGHTMAP);
}
