package com.gbth.gbthcore.gtceu.multiblocks;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.UITemplate;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.IUIMachine;
import com.gregtechceu.gtceu.common.machine.multiblock.primitive.PrimitiveWorkableMachine;
import com.gregtechceu.gtceu.config.ConfigHolder;
import com.lowdragmc.lowdraglib.gui.modular.ModularUI;
import com.lowdragmc.lowdraglib.gui.texture.GuiTextureGroup;
import com.lowdragmc.lowdraglib.gui.widget.LabelWidget;
import com.lowdragmc.lowdraglib.gui.widget.ProgressWidget;
import com.lowdragmc.lowdraglib.gui.widget.SlotWidget;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.Direction.AxisDirection;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BloomeryMachine extends PrimitiveWorkableMachine implements IUIMachine {
    public BloomeryMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }

    @Override
    public ModularUI createUI(Player player) {
        return new ModularUI(176, 166, this, player)
                .background(GuiTextures.PRIMITIVE_BACKGROUND)
                .widget(new LabelWidget(5, 5, getBlockState().getBlock().getDescriptionId()))
                .widget(new SlotWidget(importItems.storage, 0, 52, 27, true, true)
                        .setBackgroundTexture(new GuiTextureGroup(GuiTextures.PRIMITIVE_SLOT, GuiTextures.PRIMITIVE_DUST_OVERLAY)))
                .widget(new SlotWidget(importItems.storage, 1, 33, 27, true, true)
                        .setBackgroundTexture(new GuiTextureGroup(GuiTextures.PRIMITIVE_SLOT, GuiTextures.PRIMITIVE_DUST_OVERLAY)))
                .widget(new SlotWidget(importItems.storage, 2, 42, 46, true, true)
                        .setBackgroundTexture(new GuiTextureGroup(GuiTextures.PRIMITIVE_SLOT, GuiTextures.PRIMITIVE_FURNACE_OVERLAY)))
                .widget(new ProgressWidget(recipeLogic::getProgressPercent, 77, 39, 20, 15, GuiTextures.PRIMITIVE_BLAST_FURNACE_PROGRESS_BAR))
                .widget(new SlotWidget(exportItems.storage, 0, 104, 38, true, false)
                        .setBackgroundTexture(new GuiTextureGroup(GuiTextures.PRIMITIVE_SLOT, GuiTextures.PRIMITIVE_INGOT_OVERLAY)))
                .widget(UITemplate.bindPlayerInventory(player.getInventory(), GuiTextures.PRIMITIVE_SLOT, 7, 84, true));
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void clientTick() {
        super.clientTick();
        if(this.recipeLogic.isWorking()) {
            BlockPos pos = this.getPos();
            Direction dir = this.getFrontFacing().getOpposite();
            float xPos = (float) dir.getStepX() * 0.76f + (float) pos.getX() + 0.5f;
            float yPos = (float) dir.getStepY() * 0.76f + (float) pos.getY() + 0.25f;
            float zPos = (float) dir.getStepZ() * 0.76f + (float) pos.getZ() + 0.5f;
            float ySpeed = (float) dir.getStepY() * 0.1f + 0.2f + 0.1f * GTValues.RNG.nextFloat();
            this.getLevel().addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, xPos, yPos, zPos, 0.0, ySpeed, 0.0);

        }
    }

    @Override
    public void animateTick(RandomSource random) {
        if(this.isActive()) {
            BlockPos pos = this.getPos();
            float x = (float) pos.getX() + 0.5f;
            float z = (float) pos.getZ() + 0.5f;
            Direction dir = this.getFrontFacing();
            float horizontalOffset = GTValues.RNG.nextFloat() * 0.6f - 0.3f;
            float y = (float) pos.getY() + GTValues.RNG.nextFloat() * 0.375f + 0.3f;
            if(dir.getAxis() == Axis.X) {
                if(dir.getAxisDirection() == AxisDirection.POSITIVE) {
                    x += 0.52f;
                } else {
                    x -= 0.52f;
                }
                z += horizontalOffset;
            } else if(dir.getAxis() == Axis.Z) {
                if(dir.getAxisDirection() == AxisDirection.POSITIVE) {
                    z += 0.52f;
                } else {
                    z -= 0.52f;
                }
                x += horizontalOffset;
            }

            if(ConfigHolder.INSTANCE.machines.machineSounds && GTValues.RNG.nextDouble() < 0.1) {
                this.getLevel().playLocalSound(x, y, z, SoundEvents.FURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1.0F, 1.0F, false);
            }

            this.getLevel().addParticle(ParticleTypes.LARGE_SMOKE, x, y, z, 0, 0, 0);
            this.getLevel().addParticle(ParticleTypes.FLAME, x, y, z, 0, 0, 0);
        }
    }
}
