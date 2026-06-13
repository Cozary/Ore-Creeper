package com.cozary.ore_creeper;

import com.cozary.ore_creeper.data.BaseOreCreeperManager;
import com.cozary.ore_creeper.init.ModTabs;
import com.cozary.ore_creeper.network.CommonNetwork;
import com.cozary.ore_creeper.network.OreCreeperSyncPayload;
import com.cozary.ore_creeper.register.EntityRegister;
import com.cozary.ore_creeper.register.ParticleRegister;
import com.cozary.ore_creeper.register.RendererRegister;
import com.cozary.ore_creeper.register.SpawnPlacementRegister;
import com.cozary.ore_creeper.util.BiomeModifierGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.SimpleChannel;

import java.nio.file.Path;
import java.util.Optional;

@Mod(OreCreeper.MOD_ID)
public class OreCreeperForge {

    private static SimpleChannel CHANNEL;

    public OreCreeperForge(FMLJavaModLoadingContext context) {

        var modBusGroup = context.getModBusGroup();

        OreCreeper.init();

        ModTabs.CREATIVE_MODE_TAB.register(modBusGroup);

        FMLCommonSetupEvent.getBus(modBusGroup).addListener(this::commonSetup);

        //Idk if otherwise it crash, for now here
        AddPackFindersEvent.BUS.addListener(this::addPackFinders);
        RegisterParticleProvidersEvent.BUS.addListener(ParticleRegister::registerFactories);
        EntityAttributeCreationEvent.BUS.addListener(EntityRegister::addEntityAttributes);
        EntityRenderersEvent.RegisterRenderers.BUS.addListener(RendererRegister::registerEntityRenders);
        EntityRenderersEvent.RegisterLayerDefinitions.BUS.addListener(RendererRegister::registerLayer);
        SpawnPlacementRegisterEvent.BUS.addListener(SpawnPlacementRegister::registerSpawnPlacements);

    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {

            CHANNEL = ChannelBuilder.named(OreCreeper.MOD_ID + ":main")
                    .networkProtocolVersion(1)
                    .simpleChannel();

            CHANNEL.messageBuilder(OreCreeperSyncPayload.class, net.minecraftforge.network.NetworkDirection.PLAY_TO_CLIENT)

                    .encoder((msg, buf) -> OreCreeperSyncPayload.STREAM_CODEC.encode(buf, msg))
                    .decoder(buf -> OreCreeperSyncPayload.STREAM_CODEC.decode(buf))
                    .consumerMainThread((payload, context) -> {
                        context.enqueueWork(() -> {
                            CommonNetwork.handleBaseSync(payload, context);
                        });
                        context.setPacketHandled(true);
                    })
                    .add();
        });
    }

    private void addPackFinders(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.SERVER_DATA) {
            Path packDir = BiomeModifierGenerator.generate();

            event.addRepositorySource((packConsumer) -> {
                PackLocationInfo info = new PackLocationInfo(
                        "ore_creeper_dynamic_modifiers",
                        Component.literal("Ore Creeper Dynamic Modifiers"),
                        PackSource.BUILT_IN,
                        Optional.empty()
                );

                Pack.ResourcesSupplier resources = new Pack.ResourcesSupplier() {
                    @Override
                    public net.minecraft.server.packs.PackResources openPrimary(PackLocationInfo locationInfo) {
                        return new PathPackResources(locationInfo, packDir);
                    }

                    @Override
                    public net.minecraft.server.packs.PackResources openFull(PackLocationInfo locationInfo, Pack.Metadata metadata) {
                        return new PathPackResources(locationInfo, packDir);
                    }
                };

                Pack pack = Pack.readMetaAndCreate(
                        info,
                        resources,
                        PackType.SERVER_DATA,
                        new PackSelectionConfig(true, Pack.Position.TOP, false)
                );

                if (pack != null) {
                    packConsumer.accept(pack);
                }
            });
        }
    }

    @Mod.EventBusSubscriber(modid = OreCreeper.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ForgeEvents {

        @SubscribeEvent
        public static void addReloadListener(AddReloadListenerEvent event) {
            event.addListener(new BaseOreCreeperManager());
        }
    }
}
