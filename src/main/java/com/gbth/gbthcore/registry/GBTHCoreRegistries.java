package com.gbth.gbthcore.registry;

import com.gbth.gbthcore.GBTHCore;
import com.gbth.gbthcore.GBTHCoreRecipeTypes;
import com.gbth.gbthcore.gtceu.multiblocks.GBTHCoreMultiblocks;
import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.event.PostMaterialEvent;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = GBTHCore.MODID, bus = Bus.MOD)
public class GBTHCoreRegistries {

    public static final GTRegistrate REGISTRATE = GTRegistrate.create(GBTHCore.MODID);

    public static void registerMachines(GTCEuAPI.RegisterEvent<ResourceLocation, MachineDefinition> ignoredEvent) {
        GBTHCoreMultiblocks.register();
    }

    public static void registerRecipeTypes(GTCEuAPI.RegisterEvent<ResourceLocation, GTRecipeType> ignoredEvent) {
        GBTHCoreRecipeTypes.register();
    }

    @SubscribeEvent
    public static void registerMaterials(PostMaterialEvent event) {
        GBTHCoreMaterials.register();
    }
}
