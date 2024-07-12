package com.gbth.gbthcore;

import static com.gbth.gbthcore.registry.GBTHCoreRegistries.REGISTRATE;

public class GBTHCoreLang {

    public static void register() {
        REGISTRATE.addRawLang("gbthcore.bloomery", "Bloomery");
        REGISTRATE.addRawLang("tagprefix.bloom", "%s Bloom");
    }
}
