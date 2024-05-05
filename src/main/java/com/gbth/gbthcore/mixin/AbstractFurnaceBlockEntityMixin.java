package com.gbth.gbthcore.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractFurnaceBlockEntity.class)
public abstract class AbstractFurnaceBlockEntityMixin extends BaseContainerBlockEntity implements WorldlyContainer, RecipeHolder, StackedContentsCompatible {

    protected AbstractFurnaceBlockEntityMixin(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }

    @Inject(method = "canPlaceItem", at = @At("HEAD"), cancellable = true)
    private void gbthcore$canPlaceItem(int pIndex, ItemStack pStack, CallbackInfoReturnable<Boolean> cir) {
        if(this.getBlockState().getBlock() == Blocks.FURNACE ||
            this.getBlockState().getBlock() == Blocks.BLAST_FURNACE ||
            this.getBlockState().getBlock() == Blocks.SMOKER) {
            cir.setReturnValue(false);
        }
    }
}
