package com.gbth.gbthcore;

import com.gbth.gbthcore.common.data.GBTHCoreCreativeTabs;
import com.gbth.gbthcore.data.GBTHCoreDatagen;
import com.gbth.gbthcore.registry.GBTHCoreRegistries;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(GBTHCore.MODID)
public class GBTHCore
{
    public static final String MODID = "gbthcore";
    public static final Logger LOGGER = LogUtils.getLogger();
    public GBTHCore() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addGenericListener(MachineDefinition.class, GBTHCoreRegistries::registerMachines);
        modEventBus.addGenericListener(GTRecipeType.class, GBTHCoreRegistries::registerRecipeTypes);

        GBTHCoreRegistries.REGISTRATE.registerRegistrate();

        GBTHCoreCreativeTabs.register();

        GBTHConfig.init();

        GBTHCoreDatagen.init();
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(GBTHCore.MODID, path);
    }
}
