package com.gbth.gbthcore.integration.heracles.widget;

import com.gbth.gbthcore.integration.heracles.tasks.GatherFluidTask;
import com.teamresourceful.resourcefullib.client.scissor.ScissorBoxStack;
import earth.terrarium.heracles.api.client.DisplayWidget;
import earth.terrarium.heracles.api.client.WidgetUtils;
import earth.terrarium.heracles.api.client.theme.QuestScreenTheme;
import earth.terrarium.heracles.common.handlers.progress.TaskProgress;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.nbt.NumericTag;
import net.minecraft.network.chat.Component;

public class FluidTaskWidget implements DisplayWidget {

    private final GatherFluidTask task;
    private final TaskProgress<NumericTag> progress;
    private static final String LANG_TITLE_KEY = "task.gbthcore.fluid.title";
    private static final String LANG_DESC_KEY = "task.gbthcore.fluid.desc";

    public FluidTaskWidget(GatherFluidTask task, TaskProgress<NumericTag> progress) {
        this.task = task;
        this.progress = progress;
    }

    @Override
    public void render(GuiGraphics graphics, ScissorBoxStack scissor, int x, int y, int width, int mouseX, int mouseY, boolean hovered, float partialTicks) {
        Font font = Minecraft.getInstance().font;
        WidgetUtils.drawBackground(graphics, x, y, width, getHeight(width));
        int iconSize = 32;
        this.task.icon().render(graphics, scissor, x + 5, y + 5, iconSize, iconSize);
        graphics.drawString(font, task.titleOr(Component.translatable(LANG_TITLE_KEY, Component.translatable(task.fluid().getFluidType().getDescriptionId()))), x + iconSize + 16, y + 6, QuestScreenTheme.getTaskTitle(), false);
        graphics.drawString(font, Component.translatable(LANG_DESC_KEY, formatFluidAmount(task.requiredAmount()), Component.translatable(task.fluid().getFluidType().getDescriptionId())), x + iconSize + 16, y + 8 + font.lineHeight, QuestScreenTheme.getTaskDescription(), false);
        WidgetUtils.drawProgressText(graphics, x, y, width, this.task, this.progress);
        int height = getHeight(width);
        WidgetUtils.drawProgressBar(graphics, x + iconSize + 16, y + height - font.lineHeight - 6, x + width - 5, y + height - 6, this.task, this.progress);
    }

    @Override
    public int getHeight(int width) {
        return 42;
    }

    private String formatFluidAmount(int amount) {
        int buckets = amount / 1000;
        int remaining = amount - (buckets * 1000);
        if(buckets < 1) {
            if(remaining > 0) {
                return remaining + "mB";
            } else return buckets + "B";
        }
        return buckets + "B " + remaining + "mB";
    }
}
