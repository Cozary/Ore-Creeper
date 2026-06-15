package com.cozary.ore_creeper;

import com.cozary.ore_creeper.data.BaseOreCreeper;
import com.cozary.ore_creeper.data.BaseOreCreeperLoader;
import com.cozary.ore_creeper.data.BaseOreCreeperManager;
import com.cozary.ore_creeper.entities.BaseOreCreeperEntity;
import com.cozary.ore_creeper.init.ModBlocks;
import com.cozary.ore_creeper.init.ModSpawnEggs;
import com.cozary.ore_creeper.init.ModTags;
import com.cozary.ore_creeper.register.EntityRegister;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.PackType;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.Map;
import java.util.Optional;

public class OreCreeperFabric implements ModInitializer {

    private static final ResourceKey<CreativeModeTab> ITEM_GROUP = ResourceKey.create(Registries.CREATIVE_MODE_TAB, Identifier.fromNamespaceAndPath(OreCreeper.MOD_ID, "ore_creeper"));


    @Override
    public void onInitialize() {

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ITEM_GROUP, FabricCreativeModeTab.builder()
                .title(Component.translatable("itemGroup.ore_creeper"))
                .icon(() -> new ItemStack(ModBlocks.ORE_TNT.get()))
                .displayItems((parameters, output) -> ModSpawnEggs.SPAWNEGGS_TAB.forEach((item) -> output.accept(item.get())))
                .build()
        );

        OreCreeper.init();
        register();
        EntityRegister.registerAttributes();

        ResourceLoader.get(PackType.SERVER_DATA).registerReloadListener(
                Identifier.fromNamespaceAndPath(OreCreeper.MOD_ID, "ore_creeper_types"),
                new BaseOreCreeperManager()
        );
    }

    public void register() {

        for (Map.Entry<Identifier, BaseOreCreeper> entry : BaseOreCreeperLoader.LOADED_TYPES.entrySet()) {
            Identifier id = entry.getKey();
            BaseOreCreeper base = entry.getValue();

            Optional<Holder.Reference<EntityType<?>>> optional = BuiltInRegistries.ENTITY_TYPE.get(id);

            if (optional.isPresent() && optional.get().key().identifier().equals(id)) {
                EntityType<?> type = optional.get().value();

                TagKey<Biome> spawnTag = base.spawnInBiomes().orElse(
                        base.isNether() ? ModTags.SPAWNABLE_BIOMES_NETHER : ModTags.SPAWNABLE_BIOMES
                );

                TagKey<Biome> blacklistTag = base.removeFromBiomes().orElse(
                        base.isNether() ? ModTags.BLACKLIST_BIOMES_NETHER : ModTags.BLACKLIST_BIOMES
                );

                var selector = BiomeSelectors.tag(spawnTag)
                        .and(context -> !context.hasTag(blacklistTag));

                BiomeModifications.addSpawn(selector, MobCategory.MONSTER, type, base.spawnWeight(), base.minGroupSize(), base.maxGroupSize());

                SpawnPlacements.register((EntityType<BaseOreCreeperEntity>) type, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BaseOreCreeperEntity::checkSpawnRules);
            }
        }

    }


}
