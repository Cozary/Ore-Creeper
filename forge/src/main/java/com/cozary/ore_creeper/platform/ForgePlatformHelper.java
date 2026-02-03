package com.cozary.ore_creeper.platform;

import com.cozary.ore_creeper.OreCreeper;
import com.cozary.ore_creeper.platform.services.IPlatformHelper;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;

import java.nio.file.Path;

public class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {

        return "Forge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.isProduction();
    }

    @Override
    public Path getResourcePath(String... path) {
        return ModList.get().getModFileById(OreCreeper.MOD_ID).getFile().findResource(path);
    }
}