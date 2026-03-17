package com.gbth.gbthcore.integration.heracles.utils;

import com.mojang.blaze3d.systems.RenderSystem;
import com.teamresourceful.resourcefullib.client.CloseablePoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;

public class GBTHCoreWidgetUtils {

    public static boolean drawFluid(GuiGraphics graphics, Fluid fluid, int x, int y, int size) {
        if(fluid != null && !fluid.isSame(Fluids.EMPTY)) {
            int scale = size / 16;
            try(var pose = new CloseablePoseStack(graphics)) {
                //pose.translate(x, y, 0);
                //pose.scale(scale, scale, 1);
                IClientFluidTypeExtensions extensions = IClientFluidTypeExtensions.of(fluid);
                ResourceLocation stillTexture = extensions.getStillTexture();
                if(stillTexture == null) return false;
                int color = extensions.getTintColor(new FluidStack(fluid, 1));
                float r = ((color >> 16) & 0xFF) / 255f;
                float g = ((color >> 8) & 0xFF) / 255f;
                float b = ((color) & 0xFF) / 255f;
                float a = ((color >> 24) & 0xFF) / 255f;

                RenderSystem.setShaderTexture(0, InventoryMenu.BLOCK_ATLAS);
                RenderSystem.setShaderColor(r, g, b, a);

                TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS)
                        .apply(stillTexture);

                graphics.blit(x, y, 0, size, size, sprite);
                RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
            }
            return true;
        }
        return false;
    }
}
