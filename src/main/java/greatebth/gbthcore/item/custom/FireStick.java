package greatebth.gbthcore.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class FireStick extends FlintAndSteelItem {
    public FireStick(Properties pProperties) {
        super(pProperties);

        pProperties.durability(1);
    }


    @Override
    public boolean isEnchantable(ItemStack pStack) {
        return false;
    }

    @Override
    public boolean isRepairable(ItemStack stack) {
        return false;
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Player player = pContext.getPlayer();
        Level level = pContext.getLevel();
        BlockPos blockPos = pContext.getClickedPos();
        BlockState blockState = level.getBlockState(blockPos);

        if(blockState.getBlock() == Blocks.OBSIDIAN){
            return InteractionResult.FAIL;
        }

        return super.useOn(pContext);
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return super.isDamageable(stack);
    }


}
