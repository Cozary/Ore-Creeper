package com.cozary.ore_creeper.util;


import com.cozary.ore_creeper.OreCreeper;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.Identifier;

public class ClientEventBusSubscriber {

    public static ModelLayerLocation ORE_CREEPER_BASE = new ModelLayerLocation(Identifier.fromNamespaceAndPath(OreCreeper.MOD_ID, "ore_creeper_base"), "ore_creeper_base");

}
