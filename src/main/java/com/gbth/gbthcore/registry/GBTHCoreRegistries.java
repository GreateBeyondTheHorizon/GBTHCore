package com.gbth.gbthcore.registry;

import com.gbth.gbthcore.GBTHCore;
import com.gbth.gbthcore.GBTHCoreRecipeTypes;
import com.gbth.gbthcore.integration.gtceu.multiblocks.GBTHCoreMultiblocks;
import com.gbth.gbthcore.integration.heracles.settings.FluidTaskSettings;
import com.gbth.gbthcore.integration.heracles.tasks.GatherFluidTask;
import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.event.PostMaterialEvent;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import earth.terrarium.heracles.api.client.settings.Settings;
import earth.terrarium.heracles.api.client.settings.base.TextSetting;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = GBTHCore.MODID, bus = Bus.MOD)
public class GBTHCoreRegistries {

    public static final GTRegistrate REGISTRATE = GTRegistrate.create(GBTHCore.MODID);
    public static final TextSetting<Fluid> FLUID = new TextSetting<>(
            s -> s.chars().mapToObj(i -> (char) i).allMatch(ResourceLocation::isAllowedInResourceLocation),
            ResourceLocation::isValidResourceLocation,
            s -> ForgeRegistries.FLUIDS.getValue(ResourceLocation.tryParse(s)),
            id -> id == null ? "" : id.toString()
    );

    static {
        Settings.register(GatherFluidTask.FLUID_TASK_TYPE, FluidTaskSettings.INSTANCE);
    }

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
