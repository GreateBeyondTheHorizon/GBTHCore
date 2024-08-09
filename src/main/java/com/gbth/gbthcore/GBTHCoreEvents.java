package com.gbth.gbthcore;

import com.gbth.gbthcore.integration.gtceu.multiblocks.GBTHCoreMultiblocks;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING;

@Mod.EventBusSubscriber(modid = GBTHCore.MODID, bus = Bus.FORGE)
public class GBTHCoreEvents {

    @SubscribeEvent
    public static void onBlockClicked(PlayerInteractEvent.RightClickBlock event) {
        if(event.getLevel().getBlockState(event.getPos()).getBlock() == Blocks.CAMPFIRE) {
            if(event.getItemStack().is(Blocks.MUD_BRICKS.asItem()) && event.getEntity().isCrouching()) {
                BlockState state = GBTHCoreMultiblocks.BLOOMERY.defaultBlockState().setValue(HORIZONTAL_FACING, event.getEntity().getDirection().getOpposite());
                event.getLevel().setBlock(event.getPos(), state, 2);
                event.getEntity().getMainHandItem().setCount(event.getEntity().getMainHandItem().getCount() - 1);
                event.getLevel().addParticle(ParticleTypes.EXPLOSION, event.getPos().getX(), event.getPos().getY() + 0.5, event.getPos().getZ(), 0, 0, 0);
                event.getLevel().playSound(null, event.getPos(), SoundEvents.PLAYER_LEVELUP, SoundSource.BLOCKS);
                event.setCanceled(true);
            }
        }
    }
}
