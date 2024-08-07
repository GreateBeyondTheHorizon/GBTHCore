package com.gbth.gbthcore.integration.kubejs;

import com.gbth.gbthcore.integration.gtceu.material.MaterialFlags;
import com.gbth.gbthcore.integration.gtceu.material.PropertyKeys;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingsEvent;

public class GBTHCoreKubeJSPlugin extends KubeJSPlugin {

    @Override
    public void registerBindings(BindingsEvent event) {
        super.registerBindings(event);
        event.add("GBTHCoreMaterialFlags", MaterialFlags.class);
        event.add("GBTHCorePropertyKeys", PropertyKeys.class);
    }
}
