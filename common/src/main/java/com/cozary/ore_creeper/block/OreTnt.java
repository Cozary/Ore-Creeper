package com.cozary.ore_creeper.block;

import com.cozary.ore_creeper.entities.OrePrimedTnt;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OreTnt extends Block {
    public static final BooleanProperty UNSTABLE;

    static {
        UNSTABLE = BlockStateProperties.UNSTABLE;
    }

    public OreTnt() {
        super(Properties.of()
                .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("ore_creeper", "ore_tnt")))
                .mapColor(MapColor.FIRE)
                .ignitedByLava()
                .instabreak()
                .sound(SoundType.GRASS)

        );
        this.registerDefaultState(this.defaultBlockState().setValue(UNSTABLE, false));
    }

    public static void explode(Level $$0, BlockPos $$1) {
        explode($$0, $$1, null);
    }

    private static void explode(Level level, BlockPos pos, @Nullable LivingEntity entity) {
        if (!level.isClientSide) {
            OrePrimedTnt primedtnt = new OrePrimedTnt(level, (double) pos.getX() + 0.5, pos.getY(), (double) pos.getZ() + 0.5, entity);
            level.addFreshEntity(primedtnt);
            level.playSound(null, primedtnt.getX(), primedtnt.getY(), primedtnt.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.gameEvent(entity, GameEvent.PRIME_FUSE, pos);
        }

    }

    @Override
    public void onPlace(BlockState $$0, Level $$1, BlockPos $$2, BlockState $$3, boolean $$4) {
        if (!$$3.is($$0.getBlock())) {
            if ($$1.hasNeighborSignal($$2)) {
                explode($$1, $$2);
                $$1.removeBlock($$2, false);
            }

        }
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, @Nullable Orientation orientation, boolean movedByPiston) {
        if (level.hasNeighborSignal(pos)) {
            explode(level, pos);
            level.removeBlock(pos, false);
        }

    }

    @Override
    public @NotNull BlockState playerWillDestroy(Level $$0, BlockPos $$1, BlockState $$2, Player $$3) {
        if (!$$0.isClientSide() && !$$3.isCreative() && $$2.getValue(UNSTABLE)) {
            explode($$0, $$1);
        }

        return super.playerWillDestroy($$0, $$1, $$2, $$3);
    }

    @Override
    public void wasExploded(ServerLevel level, BlockPos pos, Explosion explosion) {
        OrePrimedTnt primedtnt = new OrePrimedTnt(level, (double) pos.getX() + 0.5, pos.getY(), (double) pos.getZ() + 0.5, explosion.getIndirectSourceEntity());
        int i = primedtnt.getFuse();
        primedtnt.setFuse((short) (level.random.nextInt(i / 4) + i / 8));
        level.addFreshEntity(primedtnt);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (!itemStack.is(Items.FLINT_AND_STEEL) && !itemStack.is(Items.FIRE_CHARGE)) {
            return super.useItemOn(itemStack, blockState, level, blockPos, player, interactionHand, blockHitResult);
        } else {
            explode(level, blockPos, player);
            level.setBlock(blockPos, Blocks.AIR.defaultBlockState(), 11);
            Item $$7 = itemStack.getItem();
            if (!player.isCreative()) {
                if (itemStack.is(Items.FLINT_AND_STEEL)) {
                    itemStack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(interactionHand));
                } else {
                    itemStack.shrink(1);
                }
            }

            player.awardStat(Stats.ITEM_USED.get($$7));
            return InteractionResult.SUCCESS;
        }
    }

    @Override
    protected void onProjectileHit(Level level, BlockState state, BlockHitResult hit, Projectile projectile) {
        if (level instanceof ServerLevel serverlevel) {
            BlockPos blockpos = hit.getBlockPos();
            Entity entity = projectile.getOwner();
            if (projectile.isOnFire() && projectile.mayInteract(serverlevel, blockpos)) {
                explode(level, blockpos, entity instanceof LivingEntity ? (LivingEntity) entity : null);
                level.removeBlock(blockpos, false);
            }
        }

    }

    @Override
    public boolean dropFromExplosion(Explosion $$0) {
        return false;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> $$0) {
        $$0.add(UNSTABLE);
    }

}
