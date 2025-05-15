package com.cozary.ore_creeper.config;

import com.cozary.ore_creeper.util.ConfigUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class FabricConfigManager {

    private static final Path CONFIG_PATH = Path.of("config", "nameless_trinkets-common.json");
    private static FabricConfig config = new FabricConfig();

    public static void loadConfig() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());

            FabricConfig read = ConfigUtils.readConfig(CONFIG_PATH, FabricConfig.class);
            if (read != null) {
                config = read;
            } else {
                saveConfig();
            }
        } catch (IOException e) {
            System.err.println("Failed to load general config.");
            e.printStackTrace();
        }
    }

    public static void saveConfig() {
        try {
            ConfigUtils.writeConfig(CONFIG_PATH, config);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save general config", e);
        }
    }

    public static FabricConfig getConfig() {
        return config;
    }
}
