package com.gbth.gbthcore;

import static com.gbth.gbthcore.registry.GBTHCoreRegistries.REGISTRATE;

public class GBTHCoreLang {

    public static void register() {
        REGISTRATE.addRawLang("gbthcore.bloomery", "Bloomery");
        REGISTRATE.addRawLang("gbthcore.multiblock.large_boiler.fuel_usage", "§7Fuel Burn Rate: §f%dx");
        REGISTRATE.addRawLang("gbthcore.multiblock.large_boiler.production_efficiency", "§7Production Efficiency: §f%dx");
    }
}
