package com.cozary.ore_creeper.network;

import com.cozary.ore_creeper.OreCreeper;
import com.cozary.ore_creeper.data.BaseOreCreeper;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

import java.util.HashMap;
import java.util.Map;

public record OreCreeperSyncPayload(Map<Identifier, BaseOreCreeper> bases) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<OreCreeperSyncPayload> TYPE = new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath(OreCreeper.MOD_ID, "sync_bases"));

    public static final StreamCodec<RegistryFriendlyByteBuf, OreCreeperSyncPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.map(HashMap::new, Identifier.STREAM_CODEC, BaseOreCreeper.STREAM_CODEC),
            OreCreeperSyncPayload::bases,
            OreCreeperSyncPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
