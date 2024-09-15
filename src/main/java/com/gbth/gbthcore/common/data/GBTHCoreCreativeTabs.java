package com.gbth.gbthcore.common.data;

import com.gbth.gbthcore.GBTHCore;
import com.gregtechceu.gtceu.common.data.GTCreativeModeTabs.RegistrateDisplayItemsGenerator;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.CreativeModeTab;

import static com.gbth.gbthcore.registry.GBTHCoreRegistries.REGISTRATE;

public class GBTHCoreCreativeTabs {

    public static RegistryEntry<CreativeModeTab> MACHINE;

    public static void register() {}

    static {
        MACHINE = REGISTRATE.defaultCreativeTab("machine", b -> {
            b.displayItems(new RegistrateDisplayItemsGenerator("machine", REGISTRATE))
                    .icon(GTMachines.ELECTRIC_BLAST_FURNACE::asStack)
                    .title(REGISTRATE.addLang("itemGroup", GBTHCore.id("machine"), "GBTHCore Machines"))
                    .build();
        }).register();
    }
}
