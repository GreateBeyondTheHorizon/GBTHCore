package com.gbth.gbthcore;

import com.gbth.gbthcore.common.data.GBTHCoreCreativeTabs;
import com.gbth.gbthcore.data.GBTHCoreDatagen;
import com.gbth.gbthcore.integration.heracles.icon.FluidQuestIcon;
import com.gbth.gbthcore.integration.heracles.tasks.GatherFluidTask;
import com.gbth.gbthcore.integration.heracles.widget.FluidTaskWidget;
import com.gbth.gbthcore.registry.GBTHCoreRegistries;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.mojang.logging.LogUtils;
import earth.terrarium.heracles.api.quests.QuestIcons;
import earth.terrarium.heracles.api.tasks.QuestTaskDisplayFormatter;
import earth.terrarium.heracles.api.tasks.QuestTasks;
import earth.terrarium.heracles.api.tasks.client.QuestTaskWidgets;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;
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

        QuestTasks.register(GatherFluidTask.FLUID_TASK_TYPE);

        if(FMLLoader.getDist().isClient()) {
            QuestTaskWidgets.registerSimple(GatherFluidTask.FLUID_TASK_TYPE, FluidTaskWidget::new);
            QuestIcons.register(FluidQuestIcon.FLUID_ICON_TYPE);
            QuestTaskDisplayFormatter.register(GatherFluidTask.FLUID_TASK_TYPE, (progress, task) -> String.format("%d/%d", task.storage().read(progress.progress()), task.requiredAmount()));
        }
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(GBTHCore.MODID, path);
    }
}
