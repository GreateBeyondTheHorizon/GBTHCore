package com.gbth.gbthcore.integration.heracles.icon;

import com.gbth.gbthcore.GBTHCore;
import com.gbth.gbthcore.integration.heracles.utils.GBTHCoreWidgetUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamresourceful.resourcefullib.client.scissor.ScissorBoxStack;
import earth.terrarium.heracles.api.quests.QuestIcon;
import earth.terrarium.heracles.api.quests.QuestIconType;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;

public record FluidQuestIcon(Fluid fluid) implements QuestIcon<FluidQuestIcon> {

    public static final QuestIconType<FluidQuestIcon> FLUID_ICON_TYPE = new FluidIconType();

    @Override
    public boolean render(GuiGraphics graphics, ScissorBoxStack scissor, int x, int y, int width, int height) {
        return GBTHCoreWidgetUtils.drawFluid(graphics, fluid, x, y, width);
    }

    @Override
    public QuestIconType<FluidQuestIcon> type() {
        return FLUID_ICON_TYPE;
    }

    private static class FluidIconType implements QuestIconType<FluidQuestIcon> {

        @Override
        public ResourceLocation id() {
            return GBTHCore.id("fluid");
        }

        @Override
        public Codec<FluidQuestIcon> codec() {
            return RecordCodecBuilder.create(i -> i.group(
                    ForgeRegistries.FLUIDS.getCodec().fieldOf("fluid").forGetter(FluidQuestIcon::fluid)
            ).apply(i, FluidQuestIcon::new));
        }
    }
}
