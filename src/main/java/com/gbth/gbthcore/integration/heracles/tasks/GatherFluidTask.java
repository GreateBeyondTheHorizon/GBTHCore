package com.gbth.gbthcore.integration.heracles.tasks;

import com.gbth.gbthcore.GBTHCore;
import com.gbth.gbthcore.integration.heracles.icon.FluidQuestIcon;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import earth.terrarium.heracles.api.CustomizableQuestElement;
import earth.terrarium.heracles.api.quests.QuestIcon;
import earth.terrarium.heracles.api.quests.QuestIcons;
import earth.terrarium.heracles.api.tasks.QuestTask;
import earth.terrarium.heracles.api.tasks.QuestTaskType;
import earth.terrarium.heracles.api.tasks.storage.defaults.IntegerTaskStorage;
import net.minecraft.nbt.NumericTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.registries.ForgeRegistries;

public record GatherFluidTask(String id, String title, QuestIcon<?> icon, Fluid fluid, int requiredAmount) implements QuestTask<ItemStack, NumericTag, GatherFluidTask>, CustomizableQuestElement {

    public static final QuestTaskType<GatherFluidTask> FLUID_TASK_TYPE = new FluidType();

    @Override
    public NumericTag test(QuestTaskType<?> type, NumericTag progress, ItemStack input) {
        int amount = 0;
        //TODO: may need to check both fluid caps (blocks/items)
            IFluidHandlerItem fluidCap = input.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).orElse(null);
            if(fluidCap != null) {
                for(int j = 0; j < fluidCap.getTanks(); j++) {
                    if(this.fluid.isSame(fluidCap.getFluidInTank(j).getFluid())) {
                        amount += fluidCap.getFluidInTank(j).getAmount();
                    }
                }
            }
        return storage().set(amount * input.getCount());
    }

    @Override
    public float getProgress(NumericTag progress) {
        return this.storage().readInt(progress) / (float) requiredAmount;
    }

    @Override
    public IntegerTaskStorage storage() {
        return IntegerTaskStorage.INSTANCE;
    }

    @Override
    public QuestTaskType<GatherFluidTask> type() {
        return FLUID_TASK_TYPE;
    }

    @Override
    public QuestIcon<?> icon() {
        return new FluidQuestIcon(this.fluid);
    }

    private static class FluidType implements QuestTaskType<GatherFluidTask> {

        @Override
        public ResourceLocation id() {
            return GBTHCore.id("fluid_task");
        }

        @Override
        public Codec<GatherFluidTask> codec(String id) {
            return RecordCodecBuilder.create(i -> i.group(
                    RecordCodecBuilder.point(id),
                    Codec.STRING.optionalFieldOf("title", "").forGetter(GatherFluidTask::title),
                    QuestIcons.CODEC.optionalFieldOf("icon", new FluidQuestIcon(Fluids.EMPTY)).orElse(new FluidQuestIcon(Fluids.WATER)).forGetter(GatherFluidTask::icon),
                    ForgeRegistries.FLUIDS.getCodec().fieldOf("fluid").forGetter(GatherFluidTask::fluid),
                    Codec.INT.fieldOf("amount").forGetter(GatherFluidTask::requiredAmount)
            ).apply(i, GatherFluidTask::new));
        }
    }
}
