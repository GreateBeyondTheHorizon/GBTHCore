package com.gbth.gbthcore.mixin;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractFurnaceBlockEntity.class)
public abstract class AbstractFurnaceBlockEntityMixin extends BaseContainerBlockEntity implements WorldlyContainer, RecipeHolder, StackedContentsCompatible {

    protected AbstractFurnaceBlockEntityMixin(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }

    @Inject(method = "canPlaceItem", at = @At("HEAD"), cancellable = true)
    private void gbthcore$canPlaceItem(CallbackInfoReturnable<Boolean> cir) {
        if (gbthcore$isDisabled()) {
            cir.setReturnValue(false);
        }
    }

    @Override
    public boolean canOpen(Player pPlayer) {
        if (gbthcore$isDisabled()) {
            Component warningText = Component.literal("This block has no functionality!")
                    .withStyle(ChatFormatting.RED);
            pPlayer.displayClientMessage(warningText, true);
            return false;
        }
        return super.canOpen(pPlayer);
    }

    @Unique
    private boolean gbthcore$isDisabled() {
        Block block = this.getBlockState().getBlock();
        return block == Blocks.FURNACE || block == Blocks.BLAST_FURNACE || block == Blocks.SMOKER;
    }
}
