package com.cozary.ore_creeper.util;

import com.cozary.ore_creeper.config.CommonConfigManager;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Set;

public class ExplosionTypes {

    private static final Set<Block> BASE_BLOCKS = Set.of(
            Blocks.STONE,
            Blocks.GRANITE,
            Blocks.DIORITE,
            Blocks.ANDESITE,
            Blocks.GRAVEL,
            Blocks.CLAY,
            Blocks.DRIPSTONE_BLOCK,
            Blocks.DEEPSLATE,
            Blocks.CALCITE,
            Blocks.TUFF
    );

    public void oreExplosionEffect(Entity entity, Level entityWorld, double entityX, double entityY, double entityZ, OreType oreType) {
        if (!(entityWorld instanceof ServerLevel)) return;

        double radius = getRadius(oreType);
        createExplosion(entity, entityWorld, entityX, entityY, entityZ, radius);

        processExplosionArea(entityWorld, entityX, entityY, entityZ, radius, (blockPos, state) -> {
            Block block = state.getBlock();
            if (BASE_BLOCKS.contains(block)) {
                boolean isDeepslate = block == Blocks.DEEPSLATE;
                Block targetOre = isDeepslate ? oreType.getDeepslateOreBlock() : oreType.getOreBlock();

                if (targetOre == null) return;

                int chance = entityWorld.random.nextInt(10) + 1;
                if (chance <= 3) {
                    return;
                } else if (chance <= 9) {
                    entityWorld.setBlockAndUpdate(blockPos, targetOre.defaultBlockState());
                } else {
                    if (oreType.getRawBlock() != null) {
                        entityWorld.setBlockAndUpdate(blockPos, oreType.getRawBlock().defaultBlockState());
                    }
                }
            }
        });
    }

    public void netherExplosionEffect(Entity entity, Level entityWorld, double entityX, double entityY, double entityZ, OreType oreType) {
        if (!(entityWorld instanceof ServerLevel)) return;

        double radius = getRadius(oreType);
        createExplosion(entity, entityWorld, entityX, entityY, entityZ, radius);

        processExplosionArea(entityWorld, entityX, entityY, entityZ, radius, (blockPos, state) -> {
            if (state.is(Blocks.NETHERRACK)) {
                int chance = entityWorld.random.nextInt(10) + 1;
                if (chance > 3) {
                    entityWorld.setBlockAndUpdate(blockPos, oreType.getOreBlock().defaultBlockState());
                }
            }
        });
    }

    private double getRadius(OreType oreType) {
        return switch (oreType) {
            case COAL -> CommonConfigManager.getConfig().coalCreeperExplosionRadius();
            case COPPER -> CommonConfigManager.getConfig().copperCreeperExplosionRadius();
            case DIAMOND -> CommonConfigManager.getConfig().diamondCreeperExplosionRadius();
            case EMERALD -> CommonConfigManager.getConfig().emeraldCreeperExplosionRadius();
            case GOLD -> CommonConfigManager.getConfig().goldCreeperExplosionRadius();
            case IRON -> CommonConfigManager.getConfig().ironCreeperExplosionRadius();
            case LAPIS -> CommonConfigManager.getConfig().lapisLazuliCreeperExplosionRadius();
            case REDSTONE -> CommonConfigManager.getConfig().redstoneCreeperExplosionRadius();
            case NETHERGOLD -> CommonConfigManager.getConfig().netherGoldCreeperExplosionRadius();
            case NETHERQUARTZ -> CommonConfigManager.getConfig().netherQuartzCreeperExplosionRadius();
            case ANCIENT_DEBRIS -> CommonConfigManager.getConfig().ancientDebrisCreeperExplosionRadius();
        };
    }

    private void createExplosion(Entity entity, Level level, double x, double y, double z, double radius) {
        boolean explodeLikeNormal = CommonConfigManager.getConfig().oreCreepersExplodeLikeNormalCreepers();
        level.explode(entity, x, y, z, explodeLikeNormal ? (float) radius : 0,
                explodeLikeNormal ? Level.ExplosionInteraction.MOB : Level.ExplosionInteraction.NONE);
    }

    private void processExplosionArea(Level level, double x, double y, double z, double radius, BlockProcessor processor) {
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
        NETHERQUARTZ(Blocks.NETHER_QUARTZ_ORE, null, null),
        ANCIENT_DEBRIS(Blocks.ANCIENT_DEBRIS, null, null);

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
}
