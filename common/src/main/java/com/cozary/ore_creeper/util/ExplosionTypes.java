package com.cozary.ore_creeper.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PowerableMob;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Arrays;
import java.util.Random;

public class ExplosionTypes {

    public enum OreType {
        COAL(Blocks.COAL_ORE, Blocks.COAL_BLOCK, Blocks.DEEPSLATE_COAL_ORE),
        COPPER(Blocks.COPPER_ORE, Blocks.RAW_COPPER_BLOCK, Blocks.DEEPSLATE_COPPER_ORE),
        DIAMOND(Blocks.DIAMOND_ORE, null, Blocks.DEEPSLATE_DIAMOND_ORE),
        EMERALD(Blocks.EMERALD_ORE, null, Blocks.DEEPSLATE_EMERALD_ORE),
        GOLD(Blocks.GOLD_ORE, Blocks.RAW_GOLD_BLOCK, Blocks.DEEPSLATE_GOLD_ORE),
        IRON(Blocks.IRON_ORE, Blocks.RAW_IRON_BLOCK, Blocks.DEEPSLATE_IRON_ORE),
        LAPIS(Blocks.LAPIS_ORE, null, Blocks.DEEPSLATE_LAPIS_ORE),
        REDSTONE(Blocks.REDSTONE_ORE, null, Blocks.DEEPSLATE_REDSTONE_ORE),
        NETHERGOLD(Blocks.NETHER_GOLD_ORE, Blocks.RAW_GOLD_BLOCK, null),
        NETHERQUARTZ(Blocks.NETHER_QUARTZ_ORE, null, null);

        private final Block oreBlock;
        private final Block rawBlock;
        private final Block deepslateOreBlock;

        OreType(Block oreBlock, Block rawBlock, Block deepslateOreBlock) {
            this.oreBlock = oreBlock;
            this.rawBlock = rawBlock;
            this.deepslateOreBlock = deepslateOreBlock;
        }

        public Block getOreBlock() {
            return oreBlock;
        }

        public Block getRawBlock() {
            return rawBlock;
        }

        public Block getDeepslateOreBlock() {
            return deepslateOreBlock;
        }
    }

    public void oreExplosionEffect(Entity entity, Level entityWorld, double entityX, double entityY, double entityZ, OreType oreType) {
        double radius = 0;
        switch (oreType) {
            case COAL -> radius = ConfigurationHandler.GENERAL.coalCreeperExplosionRadius.get();
            case COPPER -> radius = ConfigurationHandler.GENERAL.copperCreeperExplosionRadius.get();
            case DIAMOND -> radius = ConfigurationHandler.GENERAL.diamondCreeperExplosionRadius.get();
            case EMERALD -> radius = ConfigurationHandler.GENERAL.emeraldCreeperExplosionRadius.get();
            case GOLD -> radius = ConfigurationHandler.GENERAL.goldCreeperExplosionRadius.get();
            case IRON -> radius = ConfigurationHandler.GENERAL.ironCreeperExplosionRadius.get();
            case LAPIS -> radius = ConfigurationHandler.GENERAL.lapisLazuliCreeperExplosionRadius.get();
            case REDSTONE -> radius = ConfigurationHandler.GENERAL.redstoneCreeperExplosionRadius.get();
        }

        if (entity instanceof PowerableMob) {
            radius = ((PowerableMob) entity).isPowered() ? radius * 1.5 : radius;
        }
        entityWorld.explode(entity, entityX, entityY, entityZ, 0, Level.ExplosionInteraction.NONE);
        if (entityWorld.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
            for (int x = (int) -radius; x <= radius; x++) {
                for (int y = (int) -radius; y <= radius; y++) {
                    for (int z = (int) -radius; z <= radius; z++) {
                        BlockPos blockPos = new BlockPos((int) (entityX + x), (int) (entityY + y), (int) (entityZ + z));
                        BlockState state = entityWorld.getBlockState(blockPos);

                        if (state != null && state.getBlock() != null) {
                            Block stoneBlock = state.getBlock();

                            Block[] baseBlockList = {
                                    Blocks.STONE,
                                    Blocks.GRANITE,
                                    Blocks.DIORITE,
                                    Blocks.ANDESITE,
                                    Blocks.GRAVEL,
                                    Blocks.CLAY,
                                    Blocks.DRIPSTONE_BLOCK,
                                    Blocks.DEEPSLATE,
                                    Blocks.CALCITE,
                                    Blocks.TUFF};

                            if (stoneBlock != null && Math.sqrt(Math.pow(x, 2.0D) + Math.pow(y, 2.0D) + Math.pow(z, 2.0D)) <= radius) {
                                if (Arrays.asList(baseBlockList).contains(stoneBlock)) {
                                    if (stoneBlock.defaultBlockState() == Blocks.DEEPSLATE.defaultBlockState()) {
                                        switch (new Random().nextInt(10 - 1 + 1) + 1) {
                                            case 1, 2, 3 ->
                                                    entityWorld.setBlockAndUpdate(blockPos, stoneBlock.defaultBlockState());
                                            case 4, 5, 6, 7, 8, 9 ->
                                                    entityWorld.setBlockAndUpdate(blockPos, oreType.getDeepslateOreBlock().defaultBlockState());
                                            case 10 -> {
                                                if (oreType.getRawBlock() != null)
                                                    entityWorld.setBlockAndUpdate(blockPos, oreType.getRawBlock().defaultBlockState());
                                            }

                                        }
                                    } else {
                                        switch (new Random().nextInt(10 - 1 + 1) + 1) {
                                            case 1, 2, 3 ->
                                                    entityWorld.setBlockAndUpdate(blockPos, stoneBlock.defaultBlockState());
                                            case 4, 5, 6, 7, 8, 9 ->
                                                    entityWorld.setBlockAndUpdate(blockPos, oreType.getOreBlock().defaultBlockState());
                                            case 10 -> {
                                                if (oreType.getRawBlock() != null)
                                                    entityWorld.setBlockAndUpdate(blockPos, oreType.getRawBlock().defaultBlockState());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void netherExplosionEffect(Entity entity, Level entityWorld, double entityX, double entityY, double entityZ, OreType oreType) {
        double radius = 0;
        switch (oreType) {
            case NETHERGOLD -> radius = ConfigurationHandler.GENERAL.netherGoldCreeperExplosionRadius.get();
            case NETHERQUARTZ -> radius = ConfigurationHandler.GENERAL.netherQuartzCreeperExplosionRadius.get();
        }
        if (entity instanceof PowerableMob) {
            radius = ((PowerableMob) entity).isPowered() ? radius * 1.5 : radius;
        }
        entityWorld.explode(entity, entityX, entityY, entityZ, 0, Level.ExplosionInteraction.NONE);
        if (entityWorld.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
            for (int x = (int) -radius; x <= radius; x++) {
                for (int y = (int) -radius; y <= radius; y++) {
                    for (int z = (int) -radius; z <= radius; z++) {
                        BlockPos blockPos = new BlockPos((int) (entityX + x), (int) (entityY + y), (int) (entityZ + z));
                        BlockState state = entityWorld.getBlockState(blockPos);

                        if (state != null && state.getBlock() != null) {
                            Block stoneBlock = state.getBlock();
                            Block baseBlock = Blocks.NETHERRACK;

                            if (stoneBlock != null && baseBlock == stoneBlock && Math.sqrt(Math.pow(x, 2.0D) + Math.pow(y, 2.0D) + Math.pow(z, 2.0D)) <= radius) {

                                switch (new Random().nextInt(10 - 1 + 1) + 1) {
                                    case 1, 2, 3 ->
                                            entityWorld.setBlockAndUpdate(blockPos, stoneBlock.defaultBlockState());
                                    case 4, 5, 6, 7, 8, 9, 10 ->
                                            entityWorld.setBlockAndUpdate(blockPos, oreType.getOreBlock().defaultBlockState());
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
