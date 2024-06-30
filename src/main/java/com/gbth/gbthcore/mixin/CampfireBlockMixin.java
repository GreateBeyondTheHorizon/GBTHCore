package com.gbth.gbthcore.mixin;

import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.world.level.block.CampfireBlock.LIT;

@Mixin(CampfireBlock.class)
public abstract class CampfireBlockMixin extends BaseEntityBlock implements SimpleWaterloggedBlock {
    protected CampfireBlockMixin(Properties pProperties) {
        super(pProperties);
    }

    @Inject(method = "getStateForPlacement", at = @At("RETURN"), cancellable = true)
    private void gbthcore$getStateForPlacement(CallbackInfoReturnable<BlockState> cir) {
        if(this.getDescriptionId().equals("block.minecraft.campfire")) {
            cir.setReturnValue(cir.getReturnValue().setValue(LIT, false));
        }
    }
}
