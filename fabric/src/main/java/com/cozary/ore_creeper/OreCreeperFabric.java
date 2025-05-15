package com.cozary.ore_creeper;

import com.cozary.ore_creeper.config.FabricConfigManager;
import com.cozary.ore_creeper.config.FabricConfigManager;
import com.cozary.ore_creeper.entities.*;
import com.cozary.ore_creeper.init.ModBlocks;
import com.cozary.ore_creeper.init.ModEntityTypes;
import com.cozary.ore_creeper.init.ModSpawnEggs;
import com.cozary.ore_creeper.init.ModTags;
import com.cozary.ore_creeper.register.EntityRegister;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.levelgen.Heightmap;

public class OreCreeperFabric implements ModInitializer {

    private static final ResourceKey<CreativeModeTab> ITEM_GROUP = ResourceKey.create(Registries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(OreCreeper.MOD_ID, "ore_creeper"));


    @Override
    public void onInitialize() {

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ITEM_GROUP, FabricItemGroup.builder()
                .title(Component.translatable("itemGroup.ore_creeper"))
                .icon(() -> new ItemStack(ModBlocks.ORE_TNT.get()))
                .displayItems((parameters, output) -> ModSpawnEggs.SPAWNEGGS_TAB.forEach((item) -> output.accept(item.get())))
                .build()
        );

        OreCreeper.init();
        register();
        EntityRegister.registerAttributes();
        ModSpawnEggs.loadClass();

        FabricConfigManager.loadConfig();
    }

    public void register() {

        var biomeSelector = BiomeSelectors.tag(ModTags.SPAWNABLE_BIOMES)
                .and(context -> !context.hasTag(ModTags.BLACKLIST_BIOMES));

        var biomeSelectorNether = BiomeSelectors.tag(ModTags.SPAWNABLE_BIOMES_NETHER)
                .and(context -> !context.hasTag(ModTags.BLACKLIST_BIOMES_NETHER));

        BiomeModifications.addSpawn(biomeSelector, MobCategory.MONSTER, ModEntityTypes.COAL_CREEPER.get(), FabricConfigManager.getConfig().coalCreeperWeight(), FabricConfigManager.getConfig().coalCreeperminGroupSize(), FabricConfigManager.getConfig().coalCreepermaxGroupSize());
        BiomeModifications.addSpawn(biomeSelector, MobCategory.MONSTER, ModEntityTypes.COPPER_CREEPER.get(), FabricConfigManager.getConfig().copperCreeperWeight(), FabricConfigManager.getConfig().copperCreeperminGroupSize(), FabricConfigManager.getConfig().copperCreepermaxGroupSize());
        BiomeModifications.addSpawn(biomeSelector, MobCategory.MONSTER, ModEntityTypes.DIAMOND_CREEPER.get(), FabricConfigManager.getConfig().diamondCreeperWeight(), FabricConfigManager.getConfig().diamondCreeperminGroupSize(), FabricConfigManager.getConfig().diamondCreepermaxGroupSize());
        BiomeModifications.addSpawn(biomeSelector, MobCategory.MONSTER, ModEntityTypes.EMERALD_CREEPER.get(), FabricConfigManager.getConfig().emeraldCreeperWeight(), FabricConfigManager.getConfig().emeraldCreeperminGroupSize(), FabricConfigManager.getConfig().emeraldCreepermaxGroupSize());
        BiomeModifications.addSpawn(biomeSelector, MobCategory.MONSTER, ModEntityTypes.GOLD_CREEPER.get(), FabricConfigManager.getConfig().goldCreeperWeight(), FabricConfigManager.getConfig().goldCreeperminGroupSize(), FabricConfigManager.getConfig().goldCreepermaxGroupSize());
        BiomeModifications.addSpawn(biomeSelector, MobCategory.MONSTER, ModEntityTypes.IRON_CREEPER.get(), FabricConfigManager.getConfig().ironCreeperWeight(), FabricConfigManager.getConfig().ironCreeperminGroupSize(), FabricConfigManager.getConfig().ironCreepermaxGroupSize());
        BiomeModifications.addSpawn(biomeSelector, MobCategory.MONSTER, ModEntityTypes.LAPIS_LAZULI_CREEPER.get(), FabricConfigManager.getConfig().lapisLazuliCreeperWeight(), FabricConfigManager.getConfig().lapisLazuliCreeperminGroupSize(), FabricConfigManager.getConfig().lapisLazuliCreepermaxGroupSize());
        BiomeModifications.addSpawn(biomeSelector, MobCategory.MONSTER, ModEntityTypes.REDSTONE_CREEPER.get(), FabricConfigManager.getConfig().redstoneCreeperWeight(), FabricConfigManager.getConfig().redstoneCreeperminGroupSize(), FabricConfigManager.getConfig().redstoneCreepermaxGroupSize());
        BiomeModifications.addSpawn(biomeSelectorNether, MobCategory.MONSTER, ModEntityTypes.NETHER_GOLD_CREEPER.get(), FabricConfigManager.getConfig().netherGoldCreeperWeight(), FabricConfigManager.getConfig().netherGoldCreeperminGroupSize(), FabricConfigManager.getConfig().netherGoldCreepermaxGroupSize());
        BiomeModifications.addSpawn(biomeSelectorNether, MobCategory.MONSTER, ModEntityTypes.NETHER_QUARTZ_CREEPER.get(), FabricConfigManager.getConfig().netherQuartzCreeperWeight(), FabricConfigManager.getConfig().netherQuartzCreeperminGroupSize(), FabricConfigManager.getConfig().netherQuartzCreepermaxGroupSize());
        BiomeModifications.addSpawn(biomeSelectorNether, MobCategory.MONSTER, ModEntityTypes.ANCIENT_DEBRIS_CREEPER.get(), FabricConfigManager.getConfig().ancientDebrisCreeperWeight(), FabricConfigManager.getConfig().ancientDebrisCreeperminGroupSize(), FabricConfigManager.getConfig().ancientDebrisCreepermaxGroupSize());

        SpawnPlacements.register(ModEntityTypes.COAL_CREEPER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CoalCreeperEntity::canOreCreeperSpawn);
        SpawnPlacements.register(ModEntityTypes.COPPER_CREEPER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CopperCreeperEntity::canOreCreeperSpawn);
        SpawnPlacements.register(ModEntityTypes.DIAMOND_CREEPER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, DiamondCreeperEntity::canOreCreeperSpawn);
        SpawnPlacements.register(ModEntityTypes.EMERALD_CREEPER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EmeraldCreeperEntity::canOreCreeperSpawn);
        SpawnPlacements.register(ModEntityTypes.GOLD_CREEPER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GoldCreeperEntity::canOreCreeperSpawn);
        SpawnPlacements.register(ModEntityTypes.IRON_CREEPER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, IronCreeperEntity::canOreCreeperSpawn);
        SpawnPlacements.register(ModEntityTypes.LAPIS_LAZULI_CREEPER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LapisLazuliCreeperEntity::canOreCreeperSpawn);
        SpawnPlacements.register(ModEntityTypes.NETHER_GOLD_CREEPER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, NetherGoldCreeperEntity::canOreCreeperSpawn);
        SpawnPlacements.register(ModEntityTypes.NETHER_QUARTZ_CREEPER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, NetherQuartzCreeperEntity::canOreCreeperSpawn);
        SpawnPlacements.register(ModEntityTypes.REDSTONE_CREEPER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RedstoneCreeperEntity::canOreCreeperSpawn);
        SpawnPlacements.register(ModEntityTypes.ANCIENT_DEBRIS_CREEPER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AncientDebrisCreeperEntity::canOreCreeperSpawn);

    }


}
