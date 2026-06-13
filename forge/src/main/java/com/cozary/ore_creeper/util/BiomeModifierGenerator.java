package com.cozary.ore_creeper.util;

import com.cozary.ore_creeper.OreCreeper;
import com.cozary.ore_creeper.data.BaseOreCreeper;
import com.cozary.ore_creeper.data.BaseOreCreeperLoader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.resources.Identifier;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class BiomeModifierGenerator {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static Path generate() {

        Path configDir = FMLPaths.CONFIGDIR.get();
        Path packDir = configDir.resolve("ore_creeper/generated_pack");

        File modifiersDir = packDir.resolve("data/ore_creeper/forge/biome_modifier").toFile();
        if (!modifiersDir.exists()) {
            modifiersDir.mkdirs();
        }

        createPackMcmeta(packDir.toFile());

        for (Map.Entry<Identifier, BaseOreCreeper> entry : BaseOreCreeperLoader.LOADED_TYPES.entrySet()) {
            Identifier id = entry.getKey();
            BaseOreCreeper base = entry.getValue();

            JsonObject addJson = new JsonObject();
            addJson.addProperty("type", "forge:add_spawns");

            if (base.spawnInBiomes().isPresent()) {
                addJson.addProperty("biomes", "#" + base.spawnInBiomes().get().location());
            } else if (base.isNether()) {
                addJson.addProperty("biomes", "#minecraft:is_nether");
            } else {
                addJson.addProperty("biomes", "#minecraft:is_overworld");
            }

            JsonObject spawner = new JsonObject();
            spawner.addProperty("type", "ore_creeper:" + id.getPath());
            spawner.addProperty("weight", base.spawnWeight());
            spawner.addProperty("minCount", base.minGroupSize());
            spawner.addProperty("maxCount", base.maxGroupSize());

            JsonArray spawners = new JsonArray();
            spawners.add(spawner);
            addJson.add("spawners", spawners);

            File addFile = new File(modifiersDir, id.getPath() + ".json");
            try (FileWriter writer = new FileWriter(addFile)) {
                GSON.toJson(addJson, writer);
            } catch (IOException e) {
                OreCreeper.LOG.error("Failed to write add_spawns biome modifier for {}", id, e);
            }

            base.removeFromBiomes().ifPresent(tagKey -> {
                JsonObject removeJson = new JsonObject();
                removeJson.addProperty("type", "forge:remove_spawns");
                removeJson.addProperty("biomes", "#" + tagKey.location());
                removeJson.addProperty("entity_types", "ore_creeper:" + id.getPath());

                File removeFile = new File(modifiersDir, id.getPath() + "_removal.json");
                try (FileWriter writer = new FileWriter(removeFile)) {
                    GSON.toJson(removeJson, writer);
                } catch (IOException e) {
                    OreCreeper.LOG.error("Failed to write remove_spawns biome modifier for {}", id, e);
                }
            });
        }

        OreCreeper.LOG.info("Generated dynamic biome modifiers pack at {}", packDir.toAbsolutePath());
        return packDir;
    }

    private static void createPackMcmeta(File packDir) {
        JsonObject pack = new JsonObject();
        JsonObject meta = new JsonObject();
        meta.addProperty("pack_format", 48);
        meta.addProperty("description", "Ore Creeper Dynamic Biome Modifiers");
        pack.add("pack", meta);

        try (FileWriter writer = new FileWriter(new File(packDir, "pack.mcmeta"))) {
            GSON.toJson(pack, writer);
        } catch (IOException e) {
            OreCreeper.LOG.error("Failed to write pack.mcmeta", e);
        }
    }
}
