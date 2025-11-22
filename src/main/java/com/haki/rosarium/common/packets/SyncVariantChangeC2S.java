package com.haki.rosarium.common.packets;

import com.haki.rosarium.RosariumConstants;
import com.haki.rosarium.common.api.item.IVariantHolder;
import com.haki.rosarium.common.utils.PlayerUtils;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record SyncVariantChangeC2S(int variant) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<SyncVariantChangeC2S> TYPE = new CustomPacketPayload.Type<SyncVariantChangeC2S>(RosariumConstants.modLoc("sync_variant_change_c2s"));


    public static final StreamCodec<RegistryFriendlyByteBuf, SyncVariantChangeC2S> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            SyncVariantChangeC2S::variant,
            SyncVariantChangeC2S::new
    );

    public static void serverHandler(SyncVariantChangeC2S packet, IPayloadContext context) {
        context.enqueueWork(
                () -> {
                    ItemStack stack = PlayerUtils.getFromMainOrOffHand(context.player());
                    if(stack.getItem() instanceof IVariantHolder holder){
                        holder.setVariant(stack, packet.variant);
                    }
                }
        );

    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
