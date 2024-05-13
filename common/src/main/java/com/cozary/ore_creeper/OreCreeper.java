package com.cozary.ore_creeper;

import com.cozary.ore_creeper.init.ModBlocks;
import com.cozary.ore_creeper.init.ModEntityTypes;
import com.cozary.ore_creeper.init.ModItems;
import com.cozary.ore_creeper.init.ParticleList;
import com.cozary.ore_creeper.platform.Services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OreCreeper {

    public static final String MOD_ID = "ore_creeper";
    public static final String MOD_NAME = "Ore Creeper";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static void init() {

        ModEntityTypes.loadClass();
        ModItems.loadClass();
        ModBlocks.loadClass();
        ParticleList.loadClass();

    }

}