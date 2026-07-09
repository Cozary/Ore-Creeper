package com.cozary.ore_creeper.platform;

import com.cozary.ore_creeper.OreCreeper;
import com.cozary.ore_creeper.platform.services.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public Path getResourcePath(String... path) {
        Optional<ModContainer> container = FabricLoader.getInstance().getModContainer(OreCreeper.MOD_ID);
        if (container.isPresent()) {
            String relativePath = String.join("/", path);
            for (Path root : container.get().getRootPaths()) {
                Path targetPath = relativePath.isEmpty() ? root : root.resolve(relativePath);
                if (relativePath.isEmpty()) {
                    if (Files.exists(root.resolve("data")) || Files.exists(root.resolve("pack.mcmeta"))) {
                        return root;
                    }
                } else if (Files.exists(targetPath)) {
                    return targetPath;
                }
            }
            // Fallback to the first root path if no specific resource root found
            Path root = container.get().getRootPaths().get(0);
            return relativePath.isEmpty() ? root : root.resolve(relativePath);
        }
        return null;
    }

    @Override
    public Path getConfigDir() {
        return FabricLoader.getInstance().getConfigDir();
    }
}
