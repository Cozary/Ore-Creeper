package com.cozary.ore_creeper.datagen;

import com.cozary.ore_creeper.init.ModEntityTypes;
import com.cozary.ore_creeper.init.RegistryObject;
import net.minecraft.advancements.criterion.EntityFlagsPredicate;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.stream.Stream;

public class ModLootTableProvider extends EntityLootSubProvider {

    private final HolderLookup.Provider lookupProvider;

    public ModLootTableProvider(HolderLookup.Provider lookupProvider) {
        super(FeatureFlags.DEFAULT_FLAGS, lookupProvider);
        this.lookupProvider = lookupProvider;
    }

    @Override
    public void generate() {
        // Coal Creeper
        this.add(ModEntityTypes.COAL_CREEPER.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.COAL)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 16.0F)))
                                .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true)))))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.lookupProvider, UniformGenerator.between(0.0F, 4.0F)))))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.COAL_BLOCK)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))))
                        .when(LootItemRandomChanceCondition.randomChance(0.4F))));

        // Ancient Debris Creeper
        this.add(ModEntityTypes.ANCIENT_DEBRIS_CREEPER.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.NETHERRACK)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 16.0F)))
                                .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true)))))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.lookupProvider, UniformGenerator.between(0.0F, 4.0F)))))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.NETHERRACK)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .when(LootItemRandomChanceCondition.randomChance(0.5F))));

        // Copper Creeper
        this.add(ModEntityTypes.COPPER_CREEPER.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.RAW_COPPER)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F)))
                                .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true)))))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.lookupProvider, UniformGenerator.between(0.0F, 4.0F)))))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.COPPER_BLOCK)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))))
                        .when(LootItemRandomChanceCondition.randomChance(0.1F))));

        // Diamond Creeper
        this.add(ModEntityTypes.DIAMOND_CREEPER.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.DIAMOND)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F)))
                                .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true)))))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.lookupProvider, UniformGenerator.between(0.0F, 2.0F)))))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.DIAMOND_BLOCK)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F))))
                        .when(LootItemRandomChanceCondition.randomChance(0.1F))));

        // Emerald Creeper
        this.add(ModEntityTypes.EMERALD_CREEPER.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.EMERALD)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F)))
                                .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true)))))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.lookupProvider, UniformGenerator.between(0.0F, 4.0F)))))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.EMERALD_BLOCK)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))))
                        .when(LootItemRandomChanceCondition.randomChance(0.1F))));

        // Gold Creeper
        this.add(ModEntityTypes.GOLD_CREEPER.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.RAW_GOLD)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F)))
                                .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true)))))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.lookupProvider, UniformGenerator.between(0.0F, 4.0F)))))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.GOLD_BLOCK)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))))
                        .when(LootItemRandomChanceCondition.randomChance(0.1F))));

        // Iron Creeper
        this.add(ModEntityTypes.IRON_CREEPER.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.RAW_IRON)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 12.0F)))
                                .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true)))))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.lookupProvider, UniformGenerator.between(0.0F, 4.0F)))))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.IRON_BLOCK)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))))
                        .when(LootItemRandomChanceCondition.randomChance(0.2F))));

        // Lapis Lazuli Creeper
        this.add(ModEntityTypes.LAPIS_LAZULI_CREEPER.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.LAPIS_LAZULI)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 12.0F)))
                                .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true)))))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.lookupProvider, UniformGenerator.between(0.0F, 4.0F)))))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.LAPIS_BLOCK)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))))
                        .when(LootItemRandomChanceCondition.randomChance(0.2F))));

        // Nether Gold Creeper
        this.add(ModEntityTypes.NETHER_GOLD_CREEPER.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.GOLD_NUGGET)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 32.0F)))
                                .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true)))))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.lookupProvider, UniformGenerator.between(0.0F, 4.0F)))))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.GOLD_INGOT)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))))
                        .when(LootItemRandomChanceCondition.randomChance(0.6F))));

        // Nether Quartz Creeper
        this.add(ModEntityTypes.NETHER_QUARTZ_CREEPER.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.QUARTZ)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 18.0F)))
                                .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true)))))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.lookupProvider, UniformGenerator.between(0.0F, 4.0F)))))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.QUARTZ_BLOCK)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 5.0F))))
                        .when(LootItemRandomChanceCondition.randomChance(0.5F))));

        // Redstone Creeper
        this.add(ModEntityTypes.REDSTONE_CREEPER.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.REDSTONE)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 16.0F)))
                                .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true)))))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.lookupProvider, UniformGenerator.between(0.0F, 4.0F)))))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.REDSTONE_BLOCK)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .when(LootItemRandomChanceCondition.randomChance(0.5F))));
    }

    @Override
    protected Stream<EntityType<?>> getKnownEntityTypes() {
        return ModEntityTypes.ENTITY_LIST.stream().map(RegistryObject::get);
    }
}
