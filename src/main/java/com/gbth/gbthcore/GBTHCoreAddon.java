package com.gbth.gbthcore;

import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;

@GTAddon
public class GBTHCoreAddon implements IGTAddon {


    @Override
    public GTRegistrate getRegistrate() {
        return GBTHCoreRegistries.REGISTRATE;
    }

    @Override
    public void initializeAddon() {}

    @Override
    public String addonModId() {
        return GBTHCore.MODID;
    }
}
