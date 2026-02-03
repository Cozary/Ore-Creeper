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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class BaseOreCreeperLoader {
    public static final Map<Identifier, BaseOreCreeper> LOADED_TYPES = new HashMap<>();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    public static void loadTypes() {
        LOADED_TYPES.clear();

        // "Are You Still There?"
        Path dir = Services.PLATFORM.getResourcePath("data", "ore_creeper", "ore_creeper_types");

        if (dir != null && Files.exists(dir)) {
            try {
                loadFromDirectory(dir);
            } catch (IOException e) {
                OreCreeper.LOG.error("Failed to load ore creeper types from path {}", dir, e);
            }
        } else {
            OreCreeper.LOG.error("Could not find ore creeper types directory");
        }

        BaseOreCreeperManager.getTypes().putAll(LOADED_TYPES);

        OreCreeper.LOG.info("Loaded {} ore creeper types", LOADED_TYPES.size());
    }

    private static void loadFromDirectory(Path dir) throws IOException {
        try (Stream<Path> stream = Files.walk(dir, 1)) {
            stream.filter(p -> p.toString().endsWith(".json")).forEach(p -> {
                String filename = p.getFileName().toString();
                String id = filename.substring(0, filename.length() - 5);

                try (InputStream is = Files.newInputStream(p)) {
                    loadInputStream(is, id);
                } catch (IOException e) {
                    OreCreeper.LOG.error("Failed to read type file {}", filename, e);
                }
            });
        }
    }

    private static void loadInputStream(InputStream is, String id) {
        try (InputStreamReader reader = new InputStreamReader(is)) {
            Identifier identifier = Identifier.fromNamespaceAndPath(OreCreeper.MOD_ID, id);
            JsonElement json = GSON.fromJson(reader, JsonElement.class);
            BaseOreCreeper.CODEC.parse(JsonOps.INSTANCE, json)
                    .resultOrPartial(error -> OreCreeper.LOG.error("Failed to parse type {}: {}", id, error))
                    .ifPresent(base -> LOADED_TYPES.put(identifier, base));
        } catch (Exception e) {
            OreCreeper.LOG.error("Failed to parse json for {}", id, e);
        }
    }
}
