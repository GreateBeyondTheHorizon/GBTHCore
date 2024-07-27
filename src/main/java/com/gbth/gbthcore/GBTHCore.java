package com.gbth.gbthcore;

import com.gbth.gbthcore.data.GBTHCoreDatagen;
import com.gbth.gbthcore.registry.GBTHCoreRegistries;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(GBTHCore.MODID)
public class GBTHCore
{
    public static final String MODID = "gbthcore";
    private static final Logger LOGGER = LogUtils.getLogger();
    public GBTHCore()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        GBTHCoreRegistries.REGISTRATE.registerRegistrate();
        GBTHCoreLang.register();

        modEventBus.addGenericListener(MachineDefinition.class, GBTHCoreRegistries::registerMachines);
        modEventBus.addGenericListener(GTRecipeType.class, GBTHCoreRegistries::registerRecipeTypes);

        GBTHConfig.init();

        GBTHCoreDatagen.init();
    }
}
