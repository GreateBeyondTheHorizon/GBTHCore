package com.gbth.gbthcore.integration.heracles.settings;

import com.gbth.gbthcore.integration.heracles.tasks.GatherFluidTask;
import com.gbth.gbthcore.registry.GBTHCoreRegistries;
import earth.terrarium.heracles.api.client.settings.CustomizableQuestElementSettings;
import earth.terrarium.heracles.api.client.settings.SettingInitializer;
import earth.terrarium.heracles.api.client.settings.base.IntSetting;
import net.minecraft.Optionull;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.Nullable;

public class FluidTaskSettings implements SettingInitializer<GatherFluidTask>, CustomizableQuestElementSettings<GatherFluidTask> {

    public static final FluidTaskSettings INSTANCE = new FluidTaskSettings();

    @Override
    public CreationData create(@Nullable GatherFluidTask object) {
        CreationData settings = CustomizableQuestElementSettings.super.create(object);
        settings.put("fluid", GBTHCoreRegistries.FLUID, getDefaultFluid(object));
        settings.put("amount", IntSetting.ONE, getDefaultCount(object));
        return settings;
    }

    @Override
    public GatherFluidTask create(String id, GatherFluidTask object, Data data) {
        return create(object, data, (title, icon) -> new GatherFluidTask(
                id,
                title,
                icon,
                data.get("fluid", GBTHCoreRegistries.FLUID).orElse(getDefaultFluid(object)),
                data.get("amount", IntSetting.ONE).orElse(getDefaultCount(object))
        ));
    }

    private static Fluid getDefaultFluid(GatherFluidTask object) {
        return Optionull.mapOrDefault(object, GatherFluidTask::fluid, Fluids.EMPTY);
    }

    private static int getDefaultCount(GatherFluidTask object) {
        return Optionull.mapOrDefault(object, GatherFluidTask::requiredAmount, 1);
    }
}
