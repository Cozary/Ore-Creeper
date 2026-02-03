package com.cozary.ore_creeper.data;

import com.cozary.ore_creeper.OreCreeper;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.HashMap;
import java.util.Map;

public class BaseOreCreeperManager extends SimpleJsonResourceReloadListener<BaseOreCreeper> {
    private static final Map<Identifier, BaseOreCreeper> TYPES = new HashMap<>();

    public BaseOreCreeperManager() {
        super(BaseOreCreeper.CODEC, FileToIdConverter.json("ore_creeper_types"));
    }

    public static BaseOreCreeper getType(Identifier id) {
        return TYPES.get(id);
    }

    public static Map<Identifier, BaseOreCreeper> getTypes() {
        return TYPES;
    }

    public static void setClientTypes(Map<Identifier, BaseOreCreeper> bases) {
        TYPES.clear();
        TYPES.putAll(bases);
        OreCreeper.LOG.info("Synced {} ore creeper types to client", TYPES.size());
    }

    @Override
    protected void apply(Map<Identifier, BaseOreCreeper> object, ResourceManager resourceManager, ProfilerFiller profiler) {
        TYPES.clear();
        TYPES.putAll(object);
        OreCreeper.LOG.info("Loaded {} ore creeper types", TYPES.size());
    }
}
