package com.cozary.ore_creeper.platform;

import com.cozary.ore_creeper.OreCreeper;
import com.cozary.ore_creeper.platform.services.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

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
            Path root = container.get().getRootPaths().get(0);
            Path result = root;
            for (String p : path) {
                result = result.resolve(p);
            }
            return result;
        }
        return null;
    }
}
