package com.gbth.gbthcore.gtceu.multiblocks;

import com.gbth.gbthcore.GBTHCoreRecipeTypes;
import com.gbth.gbthcore.registry.GBTHCoreRegistries;
import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.lowdragmc.lowdraglib.utils.BlockInfo;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;

import static com.gregtechceu.gtceu.common.data.GTBlocks.CASING_PRIMITIVE_BRICKS;
import static net.minecraft.world.level.block.CampfireBlock.FACING;
import static net.minecraft.world.level.block.CampfireBlock.LIT;

public class GBTHCoreMultiblocks {

    public static final MultiblockMachineDefinition BLOOMERY = GBTHCoreRegistries.REGISTRATE.multiblock("bloomery", BloomeryMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GBTHCoreRecipeTypes.BLOOMERY_RECIPES)
            .appearanceBlock(CASING_PRIMITIVE_BRICKS)
            .pattern(def -> FactoryBlockPattern.start()
                    .aisle("MMM", "#M#", "#M#", "#M#")
                    .aisle("MMM", "MAM", "M#M", "M#M")
                    .aisle("MMM", "#C#", "#M#", "#M#")
                    .where("M", Predicates.blocks(Blocks.MUD_BRICKS))
                    .where("#", Predicates.air())
                    .where("C", Predicates.controller(Predicates.blocks(def.get())))
                    .where("A", Predicates.custom(s -> s.getBlockState().getBlock() instanceof CampfireBlock,
                            () -> new BlockInfo[]{new BlockInfo(Blocks.CAMPFIRE.defaultBlockState().setValue(LIT, false)), new BlockInfo(Blocks.CAMPFIRE.defaultBlockState().setValue(LIT, false).setValue(FACING, Direction.NORTH))}))
                    .build())
            .workableCasingRenderer(new ResourceLocation("block/mud_bricks"), GTCEu.id("block/multiblock/primitive_blast_furnace"), false)
            .register();

    public static void register() {}

}
