package com.haki.rosarium.client.screens;

import com.haki.rosarium.common.api.item.IVariantHolder;
import com.haki.rosarium.common.packets.SyncVariantChangeC2S;
import com.haki.rosarium.extras.SupporterHelper;
import com.mojang.blaze3d.platform.Lighting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.PacketDistributor;
import org.joml.Quaternionf;

public class PickVariantScreen extends Screen {
    ItemStack stack;
    ItemStack renderedStack;

    public PickVariantScreen(ItemStack stack) {
        super(Component.empty());
        this.stack = stack;
        this.renderedStack = stack.copy();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    protected void init() {


        if (renderedStack.getItem() instanceof IVariantHolder holder) {
            Integer[] ints = new Integer[holder.getMaxVariants(renderedStack)];

            for (int i = 0; i < holder.getMaxVariants(renderedStack); i++) {
                ints[i] = i;
            }

            addRenderableWidget(new CycleButton.Builder<Integer>(integer -> holder.variantNames(renderedStack).get(integer))
                    .withValues(ints)
                    .withInitialValue(holder.getVariant(renderedStack))
                    .create(this.getMinecraft().getWindow().getGuiScaledWidth()/2 - 75, getMinecraft().getWindow().getGuiScaledHeight() - 60, 150, 20, Component.literal("Variant"), (cycleButton, integer) -> {
                        holder.setVariant(renderedStack, integer);
                    }));

            addRenderableWidget(new Button.Builder(Component.literal("Confirm"), button -> {
                // this is client side so
                PacketDistributor.sendToServer(new SyncVariantChangeC2S(holder.getVariant(renderedStack)));
                this.onClose();

            }).pos(this.getMinecraft().getWindow().getGuiScaledWidth()/2 - 75, getMinecraft().getWindow().getGuiScaledHeight() - 35).build());
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        guiGraphics.pose().pushPose();
        
        BakedModel bakedmodel = this.minecraft.getItemRenderer().getModel(renderedStack, getMinecraft().level, null, 0);
        guiGraphics.pose().translate((float) (getMinecraft().getWindow().getGuiScaledWidth()/2), (float) getMinecraft().getWindow().getGuiScaledHeight()/2 - 32, (float) 200);
        guiGraphics.pose().mulPose(new Quaternionf().rotateXYZ(0, (float) (getMinecraft().level.getGameTime() + partialTick) / 10,0));


        guiGraphics.pose().scale(64.0F, -64.0F, 64.0F);
        this.minecraft.getItemRenderer().render(renderedStack, ItemDisplayContext.GUI, false, guiGraphics.pose(), guiGraphics.bufferSource(), 15728880, OverlayTexture.NO_OVERLAY, bakedmodel);
        Lighting.setupForFlatItems();
        Lighting.setupFor3DItems();
        guiGraphics.pose().popPose();
    }
}
