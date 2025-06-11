package com.haki.rosarium.client.api.rendering;

import com.haki.rosarium.mixins.client.ShaderInstanceAccessor;
import com.mojang.blaze3d.shaders.Uniform;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;

public class RosariumShader {
    public ResourceLocation name;
    public RosariumShader(ResourceLocation name) {
        this.name = name;
    }

    public RenderType applyWithTexture(ResourceLocation texture) {
        ShaderRegistry.RosariumRenderType.shards.computeIfAbsent(name, resourceLocation -> new RenderStateShard.ShaderStateShard(() -> ShaderRegistry.RosariumRenderType.shaderInstanceHashMap.get(name)));
        return ShaderRegistry.RosariumRenderType.shaderDefinition.get(name)
                .apply(ShaderRegistry.RosariumRenderType.shards.get(name),
                        texture
                );
    }

    public RenderType apply() {
        ShaderRegistry.RosariumRenderType.shards.computeIfAbsent(name, resourceLocation -> new RenderStateShard.ShaderStateShard(() -> ShaderRegistry.RosariumRenderType.shaderInstanceHashMap.get(name)));
        return ShaderRegistry.RosariumRenderType.shaderDefinition.get(name)
                .apply(ShaderRegistry.RosariumRenderType.shards.get(name),
                       null
                );
    }

    public void attachExtraUniform(Uniform uniform){
        ShaderInstance instance = getShaderInstance();

        if(instance instanceof ShaderInstanceAccessor accessor){
            if(!accessor.getUniforms().contains(uniform)){
                accessor.getUniforms().add(uniform);
                instance.markDirty();
            }
        }
    }

    public ShaderInstance getShaderInstance(){
        return ShaderRegistry.RosariumRenderType.shaderInstanceHashMap.get(name);
    }
}
