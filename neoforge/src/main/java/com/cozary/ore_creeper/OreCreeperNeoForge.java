package com.cozary.ore_creeper;


import com.cozary.ore_creeper.init.ModTabs;
import com.cozary.ore_creeper.network.CommonNetwork;
import com.cozary.ore_creeper.network.OreCreeperSyncPayload;
import com.cozary.ore_creeper.util.BiomeModifierGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import java.nio.file.Path;
import java.util.Optional;

@Mod(OreCreeper.MOD_ID)
public class OreCreeperNeoForge {

    public OreCreeperNeoForge(IEventBus eventBus, ModContainer container) {
        OreCreeper.init();

        ModTabs.init(eventBus);

        eventBus.addListener(this::registerPayloads);
        eventBus.addListener(this::addPackFinders);
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

    private void registerPayloads(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");
        registrar.playToClient(
                OreCreeperSyncPayload.TYPE,
                OreCreeperSyncPayload.STREAM_CODEC,
                (payload, context) -> context.enqueueWork(() -> CommonNetwork.handleBaseSync(payload, context))
        );
    }
}
