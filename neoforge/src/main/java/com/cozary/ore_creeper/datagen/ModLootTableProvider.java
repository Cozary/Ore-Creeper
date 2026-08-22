package com.cozary.ore_creeper.datagen;

import com.cozary.ore_creeper.init.ModEntityTypes;
import com.cozary.ore_creeper.init.RegistryObject;
import net.minecraft.advancements.predicates.entity.EntityFlagsPredicate;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
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

import java.util.function.BiConsumer;

public class ModLootTableProvider implements LootTableSubProvider {

    private final HolderLookup.Provider lookupProvider;

    public ModLootTableProvider(HolderLookup.Provider lookupProvider) {
        this.lookupProvider = lookupProvider;
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
        // Coal Creeper
        consumer.accept(getEntityType("coal_creeper").getDefaultLootTable().orElseThrow(), LootTable.lootTable()
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
        consumer.accept(getEntityType("ancient_debris_creeper").getDefaultLootTable().orElseThrow(), LootTable.lootTable()
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
        consumer.accept(getEntityType("copper_creeper").getDefaultLootTable().orElseThrow(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.RAW_COPPER)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 10.0F)))
                                .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true)))))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.lookupProvider, UniformGenerator.between(0.0F, 4.0F)))))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.COPPER_BLOCK.weathering().unaffected())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))))
                        .when(LootItemRandomChanceCondition.randomChance(0.1F))));

        // Diamond Creeper
        consumer.accept(getEntityType("diamond_creeper").getDefaultLootTable().orElseThrow(), LootTable.lootTable()
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
        consumer.accept(getEntityType("emerald_creeper").getDefaultLootTable().orElseThrow(), LootTable.lootTable()
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
        consumer.accept(getEntityType("gold_creeper").getDefaultLootTable().orElseThrow(), LootTable.lootTable()
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
        consumer.accept(getEntityType("iron_creeper").getDefaultLootTable().orElseThrow(), LootTable.lootTable()
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
        consumer.accept(getEntityType("lapis_lazuli_creeper").getDefaultLootTable().orElseThrow(), LootTable.lootTable()
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
        consumer.accept(getEntityType("nether_gold_creeper").getDefaultLootTable().orElseThrow(), LootTable.lootTable()
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
        consumer.accept(getEntityType("nether_quartz_creeper").getDefaultLootTable().orElseThrow(), LootTable.lootTable()
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
        consumer.accept(getEntityType("redstone_creeper").getDefaultLootTable().orElseThrow(), LootTable.lootTable()
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

    private EntityType<?> getEntityType(String name) {
        if (ModEntityTypes.ENTITY_MAP.isEmpty()) {
            ModEntityTypes.loadClass();
        }

        RegistryObject<EntityType<?>> regObj = ModEntityTypes.ENTITY_MAP.get(name);
        if (regObj != null) {
            return regObj.get();
        }
        throw new IllegalStateException("Entity type not found: " + name);
    }
}