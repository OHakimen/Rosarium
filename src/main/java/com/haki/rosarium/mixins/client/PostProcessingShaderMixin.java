package com.haki.rosarium.mixins.client;

import com.haki.rosarium.client.api.rendering.postprocessing.RosariumPostProcessingShaders;
import com.haki.rosarium.client.api.rendering.postprocessing.TypedPostShader;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.server.packs.resources.ResourceProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class PostProcessingShaderMixin {

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/pipeline/RenderTarget;bindWrite(Z)V", shift = At.Shift.BY, by = -1), cancellable = true)
    public void renderInjection(DeltaTracker deltaTracker, boolean renderLevel, CallbackInfo ci)
    {
        RosariumPostProcessingShaders.getPostProcessingShaders()
                .stream()
                .filter(typedPostShader -> typedPostShader.getType().equals(TypedPostShader.Type.SCREEN))
                .forEach(
                        typedPostShader -> {
                            typedPostShader.process(deltaTracker.getGameTimeDeltaPartialTick(true));
                        }
                );
    }

    @Inject(method = "reloadShaders", at = @At(value = "INVOKE", target = "Lnet/neoforged/fml/ModLoader;postEvent(Lnet/neoforged/bus/api/Event;)V", shift = At.Shift.AFTER))
    public void reloadShaders(ResourceProvider resourceProvider, CallbackInfo ci) {
        RosariumPostProcessingShaders.initializePostShaders();
    }

    @Inject(method = "resize", at = @At("RETURN"))
    public void reloadShaders(int width, int height, CallbackInfo ci) {
        for (TypedPostShader postProcessingShader : RosariumPostProcessingShaders.getPostProcessingShaders()) {
            postProcessingShader.getPostPass().resize(width, height);
        }
    }
}
