package com.gbth.gbthcore;

import com.gbth.gbthcore.integration.gtceu.multiblocks.GBTHCoreMultiblocks;
import com.gbth.gbthcore.integration.heracles.tasks.GatherFluidTask;
import earth.terrarium.heracles.common.handlers.progress.QuestProgressHandler;
import earth.terrarium.heracles.common.handlers.progress.QuestsProgress;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags.Items;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING;

@Mod.EventBusSubscriber(modid = GBTHCore.MODID, bus = Bus.FORGE)
public class GBTHCoreEvents {

    @SubscribeEvent
    public static void onBlockClicked(PlayerInteractEvent.RightClickBlock event) {
        BlockState state;
        if(event.getLevel().getBlockState(event.getPos()).getBlock() == Blocks.CAMPFIRE && event.getEntity().isCrouching()) {
            if(event.getItemStack().is(Items.RAW_MATERIALS)) {
                state = GBTHCoreMultiblocks.BLOOMERY.defaultBlockState().setValue(HORIZONTAL_FACING, event.getEntity().getDirection().getOpposite());
            } else if(event.getItemStack().is(Blocks.COBBLED_DEEPSLATE.asItem())) {
                state = GBTHCoreMultiblocks.ROCK_BLASTER.defaultBlockState().setValue(HORIZONTAL_FACING, event.getEntity().getDirection().getOpposite());
            } else return;
        } else return;
        event.getLevel().setBlock(event.getPos(), state, Block.UPDATE_CLIENTS);
        event.getEntity().getMainHandItem().setCount(event.getEntity().getMainHandItem().getCount() - 1);
        event.getLevel().addParticle(ParticleTypes.EXPLOSION, event.getPos().getX(), event.getPos().getY() + 0.5, event.getPos().getZ(), 0, 0, 0);
        event.getLevel().playSound(null, event.getPos(), SoundEvents.PLAYER_LEVELUP, SoundSource.BLOCKS);
        event.setCanceled(true);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onTankClicked(PlayerInteractEvent.RightClickBlock event) {
        if(event.getEntity() instanceof ServerPlayer serverPlayer) {
            serverPlayer.getServer().execute(() -> {
                QuestsProgress progress = QuestProgressHandler.getProgress(serverPlayer.getServer(), serverPlayer.getUUID());
                progress.testAndProgressTaskType(serverPlayer, event.getEntity().getMainHandItem(), GatherFluidTask.FLUID_TASK_TYPE);
            });
        }
    }
}
