package com.cozary.ore_creeper.datagen;

import com.cozary.ore_creeper.init.ModEntityTypes;
import com.cozary.ore_creeper.init.RegistryObject;
import net.minecraft.advancements.predicates.entity.EntityFlagsPredicate;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.floats.ContextFloatProviders;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

public class ModLootTableProvider implements LootTableSubProvider {

    private final LootTableSubProvider.Context context;
    private final HolderGetter<Enchantment> enchantments;

    public ModLootTableProvider(LootTableSubProvider.Context context) {
        this.context = context;
        this.enchantments = context.lookup(Registries.ENCHANTMENT);
    }

    private LootItemCondition.Builder shouldSmeltLoot() {
        return AnyOfCondition.anyOf(
                LootItemEntityPropertyCondition.hasProperties(
                        LootContext.EntityTarget.THIS,
                        EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true))
                )
        );
    }

    @Override
    public void run() {
        // Coal Creeper
        this.context.accept(getEntityType("coal_creeper").getDefaultLootTable().orElseThrow(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Items.COAL)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 16)))
                                .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 4.0F)))))
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Items.COAL_BLOCK)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2))))
                        .when(LootItemRandomChanceCondition.randomChance(0.4F))));

        // Ancient Debris Creeper
        this.context.accept(getEntityType("ancient_debris_creeper").getDefaultLootTable().orElseThrow(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Items.NETHERRACK)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 16)))
                                .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 4.0F)))))
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Items.NETHERRACK)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))))
                        .when(LootItemRandomChanceCondition.randomChance(0.5F))));

        // Copper Creeper
        this.context.accept(getEntityType("copper_creeper").getDefaultLootTable().orElseThrow(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Items.RAW_COPPER)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 10)))
                                .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 4.0F)))))
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Items.COPPER_BLOCK.weathering().unaffected())
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2))))
                        .when(LootItemRandomChanceCondition.randomChance(0.1F))));

        // Diamond Creeper
        this.context.accept(getEntityType("diamond_creeper").getDefaultLootTable().orElseThrow(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Items.DIAMOND)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 6)))
                                .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 2.0F)))))
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Items.DIAMOND_BLOCK)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 1))))
                        .when(LootItemRandomChanceCondition.randomChance(0.1F))));

        // Emerald Creeper
        this.context.accept(getEntityType("emerald_creeper").getDefaultLootTable().orElseThrow(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Items.EMERALD)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 6)))
                                .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 4.0F)))))
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Items.EMERALD_BLOCK)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2))))
                        .when(LootItemRandomChanceCondition.randomChance(0.1F))));

        // Gold Creeper
        this.context.accept(getEntityType("gold_creeper").getDefaultLootTable().orElseThrow(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Items.RAW_GOLD)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 10)))
                                .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 4.0F)))))
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Items.GOLD_BLOCK)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2))))
                        .when(LootItemRandomChanceCondition.randomChance(0.1F))));

        // Iron Creeper
        this.context.accept(getEntityType("iron_creeper").getDefaultLootTable().orElseThrow(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Items.RAW_IRON)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 12)))
                                .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 4.0F)))))
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Items.IRON_BLOCK)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2))))
                        .when(LootItemRandomChanceCondition.randomChance(0.2F))));

        // Lapis Lazuli Creeper
        this.context.accept(getEntityType("lapis_lazuli_creeper").getDefaultLootTable().orElseThrow(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Items.LAPIS_LAZULI)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 12)))
                                .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 4.0F)))))
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Items.LAPIS_BLOCK)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2))))
                        .when(LootItemRandomChanceCondition.randomChance(0.2F))));

        // Nether Gold Creeper
        this.context.accept(getEntityType("nether_gold_creeper").getDefaultLootTable().orElseThrow(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Items.GOLD_NUGGET)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 32)))
                                .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 4.0F)))))
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Items.GOLD_INGOT)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 6))))
                        .when(LootItemRandomChanceCondition.randomChance(0.6F))));

        // Nether Quartz Creeper
        this.context.accept(getEntityType("nether_quartz_creeper").getDefaultLootTable().orElseThrow(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Items.QUARTZ)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 18)))
                                .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 4.0F)))))
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Items.QUARTZ_BLOCK)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 5))))
                        .when(LootItemRandomChanceCondition.randomChance(0.5F))));

        // Redstone Creeper
        this.context.accept(getEntityType("redstone_creeper").getDefaultLootTable().orElseThrow(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Items.REDSTONE)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 16)))
                                .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 4.0F)))))
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Items.REDSTONE_BLOCK)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))))
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
