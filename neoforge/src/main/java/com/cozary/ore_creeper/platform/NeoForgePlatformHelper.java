package com.cozary.ore_creeper.platform;

import com.cozary.ore_creeper.OreCreeper;
import com.cozary.ore_creeper.platform.services.IPlatformHelper;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforgespi.language.IModInfo;

import java.nio.file.Files;
import java.nio.file.Path;

public class NeoForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {

        return "NeoForge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.getCurrent().isProduction();
    }

    @Override
    public Path getResourcePath(String... path) {
        IModInfo info = ModList.get().getModFileById(OreCreeper.MOD_ID).getMods().get(0);
        var contents = info.getOwningFile().getFile().getContents();
        String relativePath = String.join("/", path);

        for (Path root : contents.getContentRoots()) {
            Path targetPath = relativePath.isEmpty() ? root : root.resolve(relativePath);
            if (Files.exists(targetPath)) {
                return targetPath;
            }
        }
        return null;
    }
}
