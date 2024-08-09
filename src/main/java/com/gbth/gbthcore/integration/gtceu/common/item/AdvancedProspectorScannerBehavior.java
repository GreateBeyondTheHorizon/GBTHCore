package com.gbth.gbthcore.integration.gtceu.common.item;

import com.gbth.gbthcore.mixin.ProspectorScannerBehaviorAccessor;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.misc.ProspectorMode;
import com.gregtechceu.gtceu.api.gui.widget.ProspectingMapWidget;
import com.gregtechceu.gtceu.common.item.ProspectorScannerBehavior;
import com.lowdragmc.lowdraglib.gui.factory.HeldItemUIFactory.HeldItemHolder;
import com.lowdragmc.lowdraglib.gui.modular.ModularUI;
import com.lowdragmc.lowdraglib.gui.texture.GuiTextureGroup;
import com.lowdragmc.lowdraglib.gui.widget.SwitchWidget;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;

public class AdvancedProspectorScannerBehavior extends ProspectorScannerBehavior {
    public AdvancedProspectorScannerBehavior(int radius, long cost, ProspectorMode<?>... modes) {
        super(radius, cost, modes);
    }

    @Override
    public ModularUI createUI(HeldItemHolder holder, Player entityPlayer) {
        int width = 332;
        int height = 200;
        long cost = ((ProspectorScannerBehaviorAccessor) (this)).getCost();
        if(cost == GTValues.V[GTValues.HV] / 16L) {
            width = 400;
            height = 300;
        } else if(cost == GTValues.V[GTValues.LuV] / 16L) {
            width = 500;
            height = 400;
        }
        var mode = getMode(entityPlayer.getItemInHand(InteractionHand.MAIN_HAND));
        var map = new ProspectingMapWidget(4, 4, width - 8, height - 8, ((ProspectorScannerBehaviorAccessor) (this)).getRadius(), mode, 1);
        return new ModularUI(width, height, holder, entityPlayer)
                .background(GuiTextures.BACKGROUND)
                .widget(map)
                .widget(new SwitchWidget(-20, 4, 18, 18, (cd, pressed) -> map.setDarkMode(pressed))
                        .setSupplier(map::isDarkMode)
                        .setTexture(
                                new GuiTextureGroup(GuiTextures.BUTTON, GuiTextures.PROGRESS_BAR_SOLAR_STEAM.get(true).copy().getSubTexture(0, 0.5, 1, 0.5).scale(0.8f)),
                                new GuiTextureGroup(GuiTextures.BUTTON, GuiTextures.PROGRESS_BAR_SOLAR_STEAM.get(true).copy().getSubTexture(0, 0, 1, 0.5).scale(0.8f))));
    }
}
