package com.cozary.ore_creeper.util;

import com.cozary.ore_creeper.config.CommonConfigManager;
import com.cozary.ore_creeper.init.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class ExplosionTypes {

    private ExplosionTypes() {
    }

    public static void oreExplosionEffect(Entity entity, Level entityWorld, double entityX, double entityY, double entityZ, IOreExplosionConfig oreType) {
        if (!(entityWorld instanceof ServerLevel)) return;

        double radius = oreType.getRadius();
        createExplosion(entity, entityWorld, entityX, entityY, entityZ, radius);

        processExplosionArea(entityWorld, entityX, entityY, entityZ, radius, (blockPos, state) -> {
            if (state.is(ModTags.ORE_CREEPER_REPLACEABLE)) {
                if (entityWorld.getRandom().nextFloat() < oreType.getOreChance()) {
                    boolean isDeepslate = state.is(Blocks.DEEPSLATE);
                    Block targetOre = isDeepslate ? oreType.getDeepslateOreBlock() : oreType.getOreBlock();

                    if (targetOre == null) return;

                    if (oreType.getRawBlock() != null && entityWorld.getRandom().nextFloat() < oreType.getRawChance()) {
                        entityWorld.setBlockAndUpdate(blockPos, oreType.getRawBlock().defaultBlockState());
                    } else {
                        entityWorld.setBlockAndUpdate(blockPos, targetOre.defaultBlockState());
                    }
                }
            }
        });
    }

    public static void netherExplosionEffect(Entity entity, Level entityWorld, double entityX, double entityY, double entityZ, IOreExplosionConfig oreType) {
        if (!(entityWorld instanceof ServerLevel)) return;

        double radius = oreType.getRadius();
        createExplosion(entity, entityWorld, entityX, entityY, entityZ, radius);

        processExplosionArea(entityWorld, entityX, entityY, entityZ, radius, (blockPos, state) -> {
            if (state.is(ModTags.ORE_CREEPER_REPLACEABLE_NETHER)) {
                if (entityWorld.getRandom().nextFloat() < oreType.getOreChance()) {
                    entityWorld.setBlockAndUpdate(blockPos, oreType.getOreBlock().defaultBlockState());
                }
            }
        });
    }

    private static void createExplosion(Entity entity, Level level, double x, double y, double z, double radius) {
        boolean explodeLikeNormal = CommonConfigManager.getConfig().oreCreepersExplodeLikeNormalCreepers();
        level.explode(entity, x, y, z, explodeLikeNormal ? (float) radius : 0,
                explodeLikeNormal ? Level.ExplosionInteraction.MOB : Level.ExplosionInteraction.NONE);
    }

    private static void processExplosionArea(Level level, double x, double y, double z, double radius, BlockProcessor processor) {
        int r = (int) radius;
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

        for (int dx = -r; dx <= r; dx++) {
            for (int dy = -r; dy <= r; dy++) {
                for (int dz = -r; dz <= r; dz++) {
                    if (dx * dx + dy * dy + dz * dz <= radius * radius) {
                        mutablePos.set(x + dx, y + dy, z + dz);
                        BlockState state = level.getBlockState(mutablePos);
                        if (!state.isAir()) {
                            processor.process(mutablePos, state);
                        }
                    }
                }
            }
        }
    }

    @FunctionalInterface
    private interface BlockProcessor {
        void process(BlockPos pos, BlockState state);
    }
}