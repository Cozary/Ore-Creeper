package com.cozary.ore_creeper.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.Level;

public abstract class AbstractOreCreeperEntity extends Creeper {

    //Why are we still here? Just to suffer?

    public AbstractOreCreeperEntity(EntityType<? extends Creeper> type, Level level) {
        super(type, level);
    }

    @Override
    protected boolean shouldDespawnInPeaceful() {
        return false;
    }

}
