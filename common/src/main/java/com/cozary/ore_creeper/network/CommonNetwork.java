package com.cozary.ore_creeper.network;

import com.cozary.ore_creeper.data.BaseOreCreeperManager;

public class CommonNetwork {
    public static void handleBaseSync(OreCreeperSyncPayload payload, Object context) {

        BaseOreCreeperManager.setClientTypes(payload.bases());
    }
}
