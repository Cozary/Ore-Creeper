package com.cozary.ore_creeper.block;

import com.cozary.ore_creeper.entities.OrePrimedTnt;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
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
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.MapColor;
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
                .mapColor(MapColor.FIRE)
                .ignitedByLava()
                .instabreak()
                .sound(SoundType.GRASS)
        );
        this.registerDefaultState((BlockState) this.defaultBlockState().setValue(UNSTABLE, false));
    }

    public static void explode(Level $$0, BlockPos $$1) {
        explode($$0, $$1, (LivingEntity) null);
    }

    private static void explode(Level level, BlockPos pos, @Nullable LivingEntity $$2) {
        if (!level.isClientSide) {
            OrePrimedTnt primedtnt = new OrePrimedTnt(level, (double) pos.getX() + 0.5D, pos.getY(), (double) pos.getZ() + 0.5D);
            level.addFreshEntity(primedtnt);
            level.playSound(null, primedtnt.getX(), primedtnt.getY(), primedtnt.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
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
    public void neighborChanged(BlockState $$0, Level $$1, BlockPos $$2, Block $$3, BlockPos $$4, boolean $$5) {
        if ($$1.hasNeighborSignal($$2)) {
            explode($$1, $$2);
            $$1.removeBlock($$2, false);
        }

    }

    @Override
    public @NotNull BlockState playerWillDestroy(Level $$0, BlockPos $$1, BlockState $$2, Player $$3) {
        if (!$$0.isClientSide() && !$$3.isCreative() && (Boolean)$$2.getValue(UNSTABLE)) {
            explode($$0, $$1);
        }

        return super.playerWillDestroy($$0, $$1, $$2, $$3);
    }

    @Override
    public void wasExploded(Level $$0, BlockPos $$1, Explosion $$2) {
        if (!$$0.isClientSide) {
            PrimedTnt $$3 = new PrimedTnt($$0, (double) $$1.getX() + 0.5, (double) $$1.getY(), (double) $$1.getZ() + 0.5, $$2.getIndirectSourceEntity());
            int $$4 = $$3.getFuse();
            $$3.setFuse((short) ($$0.random.nextInt($$4 / 4) + $$4 / 8));
            $$0.addFreshEntity($$3);
        }
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
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
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
    }

    @Override
    public void onProjectileHit(Level $$0, BlockState $$1, BlockHitResult $$2, Projectile $$3) {
        if (!$$0.isClientSide) {
            BlockPos $$4 = $$2.getBlockPos();
            Entity $$5 = $$3.getOwner();
            if ($$3.isOnFire() && $$3.mayInteract($$0, $$4)) {
                explode($$0, $$4, $$5 instanceof LivingEntity ? (LivingEntity) $$5 : null);
                $$0.removeBlock($$4, false);
            }
        }

    }

    @Override
    public boolean dropFromExplosion(Explosion $$0) {
        return false;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> $$0) {
        $$0.add(new Property[]{UNSTABLE});
    }

}
