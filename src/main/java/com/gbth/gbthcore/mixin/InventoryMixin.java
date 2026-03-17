package com.gbth.gbthcore.mixin;

import com.bawnorton.mixinsquared.TargetHandler;
import com.gbth.gbthcore.integration.heracles.tasks.GatherFluidTask;
import earth.terrarium.heracles.common.handlers.progress.QuestProgressHandler;
import earth.terrarium.heracles.common.handlers.progress.QuestsProgress;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Inventory.class, priority = 1001)
public class InventoryMixin {

    @Shadow @Final public Player player;

    @TargetHandler(mixin = "earth.terrarium.heracles.mixins.common.InventoryMixin", name = "heracles$testProgress")
    @Inject(method = "@MixinSquared:Handler", at = @At("TAIL"))
    private void gbthcore$testProgress(ItemStack stack, CallbackInfo ci) {
        if(this.player instanceof ServerPlayer serverPlayer) {
            QuestsProgress progress = QuestProgressHandler.getProgress(serverPlayer.getServer(), serverPlayer.getUUID());
            progress.testAndProgressTaskType(serverPlayer, stack, GatherFluidTask.FLUID_TASK_TYPE);
        }
    }
}
