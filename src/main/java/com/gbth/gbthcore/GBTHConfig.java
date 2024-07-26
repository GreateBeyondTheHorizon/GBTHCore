package com.gbth.gbthcore;

import dev.toma.configuration.Configuration;
import dev.toma.configuration.config.Configurable;
import dev.toma.configuration.config.format.ConfigFormats;
import dev.toma.configuration.config.Config;

@Config(id = GBTHCore.MODID)
public class GBTHConfig {
    public static GBTHConfig INSTANCE;
    private static final Object LOCK = new Object();

    public static void init() {
        synchronized (LOCK) {
            if (INSTANCE == null) {
                INSTANCE = Configuration.registerConfig(GBTHConfig.class, ConfigFormats.yaml()).getConfigInstance();
            }
        }
    }

    @Configurable
    @Configurable.Comment({ "Restricts the spreading of water (creating new source blocks) to specific biomes defined " +
            "in allowedWaterSpreadBiomes.", "Default: true" })
    public boolean restrictWaterSpread = true;

    @Configurable
    @Configurable.Comment({ "List of biomes in which water can spread to create new source blocks." })
    public String[] allowedWaterSpreadBiomes = new String[] {
            "minecraft:river",
            "minecraft:frozen_river",
            "minecraft:ocean",
            "minecraft:deep_ocean",
            "minecraft:frozen_ocean",
            "minecraft:deep_frozen_ocean",
            "minecraft:cold_ocean",
            "minecraft:deep_cold_ocean",
            "minecraft:lukewarm_ocean",
            "minecraft:deep_lukewarm_ocean",
            "minecraft:warm_ocean",
            "wythers:icy_ocean",
            "wythers:deep_icy_ocean",
            "wythers:badlands_river",
            "wythers:black_river",
            "wythers:desert_river",
            "wythers:deep_desert_river",
            "wythers:gravelly_river",
            "wythers:icy_river",
            "wythers:jungle_river",
            "wythers:lantern_river",
            "wythers:savanna_river",
            "wythers:tropical_forest_river"
    };
}
