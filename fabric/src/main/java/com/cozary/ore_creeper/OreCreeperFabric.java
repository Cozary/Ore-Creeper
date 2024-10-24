package com.cozary.ore_creeper;

import com.cozary.ore_creeper.entities.*;
import com.cozary.ore_creeper.init.ModBlocks;
import com.cozary.ore_creeper.init.ModEntityTypes;
import com.cozary.ore_creeper.init.ModItems;
import com.cozary.ore_creeper.init.ModSpawnEggs;
import com.cozary.ore_creeper.util.ConfigurationHandler;
import fuzs.forgeconfigapiport.fabric.api.forge.v4.ForgeConfigRegistry;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry;
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
import net.neoforged.fml.config.ModConfig;

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

        NeoForgeConfigRegistry.INSTANCE.register(OreCreeper.MOD_ID, ModConfig.Type.COMMON, ConfigurationHandler.spec);

        OreCreeper.init();
        register();
        ModSpawnEggs.loadClass();
    }

    public void register() {

        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), MobCategory.MONSTER, ModEntityTypes.COAL_CREEPER.get(), ConfigurationHandler.GENERAL.coalCreeperWeight.get(), ConfigurationHandler.GENERAL.coalCreeperminGroupSize.get(), ConfigurationHandler.GENERAL.coalCreepermaxGroupSize.get());
        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), MobCategory.MONSTER, ModEntityTypes.COPPER_CREEPER.get(), ConfigurationHandler.GENERAL.copperCreeperWeight.get(), ConfigurationHandler.GENERAL.copperCreeperminGroupSize.get(), ConfigurationHandler.GENERAL.copperCreepermaxGroupSize.get());
        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), MobCategory.MONSTER, ModEntityTypes.DIAMOND_CREEPER.get(), ConfigurationHandler.GENERAL.diamondCreeperWeight.get(), ConfigurationHandler.GENERAL.diamondCreeperminGroupSize.get(), ConfigurationHandler.GENERAL.diamondCreepermaxGroupSize.get());
        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), MobCategory.MONSTER, ModEntityTypes.EMERALD_CREEPER.get(), ConfigurationHandler.GENERAL.emeraldCreeperWeight.get(), ConfigurationHandler.GENERAL.emeraldCreeperminGroupSize.get(), ConfigurationHandler.GENERAL.emeraldCreepermaxGroupSize.get());
        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), MobCategory.MONSTER, ModEntityTypes.GOLD_CREEPER.get(), ConfigurationHandler.GENERAL.goldCreeperWeight.get(), ConfigurationHandler.GENERAL.goldCreeperminGroupSize.get(), ConfigurationHandler.GENERAL.goldCreepermaxGroupSize.get());
        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), MobCategory.MONSTER, ModEntityTypes.IRON_CREEPER.get(), ConfigurationHandler.GENERAL.ironCreeperWeight.get(), ConfigurationHandler.GENERAL.ironCreeperminGroupSize.get(), ConfigurationHandler.GENERAL.ironCreepermaxGroupSize.get());
        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), MobCategory.MONSTER, ModEntityTypes.LAPIS_LAZULI_CREEPER.get(), ConfigurationHandler.GENERAL.lapisLazuliCreeperWeight.get(), ConfigurationHandler.GENERAL.lapisLazuliCreeperminGroupSize.get(), ConfigurationHandler.GENERAL.lapisLazuliCreepermaxGroupSize.get());
        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), MobCategory.MONSTER, ModEntityTypes.REDSTONE_CREEPER.get(), ConfigurationHandler.GENERAL.redstoneCreeperWeight.get(), ConfigurationHandler.GENERAL.redstoneCreeperminGroupSize.get(), ConfigurationHandler.GENERAL.redstoneCreepermaxGroupSize.get());
        BiomeModifications.addSpawn(BiomeSelectors.foundInTheNether(), MobCategory.MONSTER, ModEntityTypes.NETHER_GOLD_CREEPER.get(), ConfigurationHandler.GENERAL.netherGoldCreeperWeight.get(), ConfigurationHandler.GENERAL.netherGoldCreeperminGroupSize.get(), ConfigurationHandler.GENERAL.netherGoldCreepermaxGroupSize.get());
        BiomeModifications.addSpawn(BiomeSelectors.foundInTheNether(), MobCategory.MONSTER, ModEntityTypes.NETHER_QUARTZ_CREEPER.get(), ConfigurationHandler.GENERAL.netherQuartzCreeperWeight.get(), ConfigurationHandler.GENERAL.netherQuartzCreeperminGroupSize.get(), ConfigurationHandler.GENERAL.netherQuartzCreepermaxGroupSize.get());

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

    }
}
