package com.gbth.gbthcore.gtceu.multiblocks;

import com.gbth.gbthcore.GBTHCoreRecipeTypes;
import com.gbth.gbthcore.registry.GBTHCoreRegistries;
import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;

import static com.gregtechceu.gtceu.common.data.GTBlocks.CASING_PRIMITIVE_BRICKS;

public class GBTHCoreMultiblocks {

    public static final MultiblockMachineDefinition BLOOMERY = GBTHCoreRegistries.REGISTRATE.multiblock("bloomery", BloomeryMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GBTHCoreRecipeTypes.BLOOMERY_RECIPES)
            .appearanceBlock(CASING_PRIMITIVE_BRICKS)
            .pattern(def -> FactoryBlockPattern.start()
                    .aisle("MMM", "#M#", "#M#", "#M#")
                    .aisle("MMM", "M#M", "M#M", "M#M")
                    .aisle("MMM", "#C#", "#M#", "#M#")
                    .where("M", Predicates.blocks(Blocks.MUD_BRICKS))
                    .where("#", Predicates.air())
                    .where("C", Predicates.controller(Predicates.blocks(def.get())))
                    .build())
            .workableCasingRenderer(new ResourceLocation("block/mud_bricks"), GTCEu.id("block/multiblock/primitive_blast_furnace"), false)
            .register();

    public static void register() {}

}
