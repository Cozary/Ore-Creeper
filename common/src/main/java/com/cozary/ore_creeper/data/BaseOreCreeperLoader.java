package com.cozary.ore_creeper.data;

import com.cozary.ore_creeper.OreCreeper;
import com.cozary.ore_creeper.platform.Services;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import net.minecraft.resources.Identifier;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BaseOreCreeperLoader {
    public static final Map<Identifier, BaseOreCreeper> LOADED_TYPES = new HashMap<>();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    public static final String[] BUILT_IN_TYPES = {
            "ancient_debris_creeper",
            "coal_creeper",
            "copper_creeper",
            "diamond_creeper",
            "emerald_creeper",
            "gold_creeper",
            "iron_creeper",
            "lapis_lazuli_creeper",
            "nether_gold_creeper",
            "nether_quartz_creeper",
            "redstone_creeper"
    };

    public static void loadTypes() {
        LOADED_TYPES.clear();

        for (String type : BUILT_IN_TYPES) {
            String path = "/data/ore_creeper/ore_creeper_types/" + type + ".json";
            try (InputStream is = BaseOreCreeperLoader.class.getResourceAsStream(path)) {
                if (is != null) {
                    loadInputStream(is, type);
                } else {
                    OreCreeper.LOG.error("Could not find built-in type file: {}", path);
                }
            } catch (IOException e) {
                OreCreeper.LOG.error("Failed to read built-in type file {}", type, e);
            }
        }

        BaseOreCreeperManager.getTypes().putAll(LOADED_TYPES);

        OreCreeper.LOG.info("Loaded {} ore creeper types", LOADED_TYPES.size());
    }



    private static void loadInputStream(InputStream is, String id) {
        try (InputStreamReader reader = new InputStreamReader(is)) {
            Identifier identifier = Identifier.fromNamespaceAndPath(OreCreeper.MOD_ID, id);
            JsonElement json = GSON.fromJson(reader, JsonElement.class);
            BaseOreCreeper.CODEC.parse(JsonOps.INSTANCE, json)
                    .resultOrPartial(error -> OreCreeper.LOG.error("Failed to parse type {}: {}", id, error))
                    .ifPresent(base -> {
                        boolean modLoaded = true;
                        String oreNamespace = base.oreBlockId().getNamespace();
                        if (!oreNamespace.equals("minecraft") && !oreNamespace.equals("ore_creeper") && !Services.PLATFORM.isModLoaded(oreNamespace)) {
                            modLoaded = false;
                        }
                        if (base.rawBlockId().isPresent()) {
                            String rawNamespace = base.rawBlockId().get().getNamespace();
                            if (!rawNamespace.equals("minecraft") && !rawNamespace.equals("ore_creeper") && !Services.PLATFORM.isModLoaded(rawNamespace)) {
                                modLoaded = false;
                            }
                        }
                        if (base.deepslateOreBlockId().isPresent()) {
                            String deepslateNamespace = base.deepslateOreBlockId().get().getNamespace();
                            if (!deepslateNamespace.equals("minecraft") && !deepslateNamespace.equals("ore_creeper") && !Services.PLATFORM.isModLoaded(deepslateNamespace)) {
                                modLoaded = false;
                            }
                        }

                        if (modLoaded) {
                            LOADED_TYPES.put(identifier, base);
                        } else {
                            OreCreeper.LOG.info("Skipping ore creeper type {} because a required mod is not loaded", id);
                        }
                    });
        } catch (Exception e) {
            OreCreeper.LOG.error("Failed to parse json for {}", id, e);
        }
    }
}
