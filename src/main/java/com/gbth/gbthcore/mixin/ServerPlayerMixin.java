package com.gbth.gbthcore.mixin;

import com.bawnorton.mixinsquared.TargetHandler;
import com.gbth.gbthcore.integration.heracles.tasks.GatherFluidTask;
import earth.terrarium.heracles.common.handlers.progress.QuestProgressHandler;
import earth.terrarium.heracles.common.handlers.progress.QuestsProgress;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ServerPlayer.class, priority = 1001)
public class ServerPlayerMixin {

    @TargetHandler(mixin = "earth.terrarium.heracles.mixins.common.ServerPlayerMixin", name = "heracles$onItemPickup")
    @Inject(method = "@MixinSquared:Handler", at = @At("TAIL"))
    private void gbthcore$testProgress(ItemEntity itemEntity, CallbackInfo ci, CallbackInfo ci1) {
        ServerPlayer serverPlayer = (ServerPlayer) (Object) this;
        QuestsProgress progress = QuestProgressHandler.getProgress(serverPlayer.getServer(), serverPlayer.getUUID());
        progress.testAndProgressTaskType(serverPlayer, itemEntity.getItem(), GatherFluidTask.FLUID_TASK_TYPE);
    }
}
