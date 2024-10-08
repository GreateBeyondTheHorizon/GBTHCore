package com.gbth.gbthcore.integration.gtceu.multiblocks;

import com.gbth.gbthcore.GBTHCoreRecipeTypes;
import com.gbth.gbthcore.common.data.GBTHCoreCreativeTabs;
import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.machine.multiblock.steam.SteamParallelMultiblockMachine;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;

import static com.gbth.gbthcore.registry.GBTHCoreRegistries.REGISTRATE;
import static com.gregtechceu.gtceu.api.pattern.Predicates.blocks;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;

public class GBTHCoreMultiblocks {

    static {
        REGISTRATE.creativeModeTab(() -> GBTHCoreCreativeTabs.MACHINE);
    }

    public static final MultiblockMachineDefinition BLOOMERY = REGISTRATE.multiblock("bloomery", BloomeryMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GBTHCoreRecipeTypes.BLOOMERY_RECIPES)
            .appearanceBlock(CASING_PRIMITIVE_BRICKS)
            .pattern(def -> FactoryBlockPattern.start()
                    .aisle("SMS", "#M#", "#M#")
                    .aisle("MMM", "M#M", "M#M")
                    .aisle("SMS", "#C#", "#M#")
                    .where("S", blocks(Blocks.MUD_BRICK_SLAB))
                    .where("M", blocks(Blocks.MUD_BRICKS))
                    .where("#", Predicates.air())
                    .where("C", Predicates.controller(blocks(def.get())))
                    .build())
            .workableCasingRenderer(new ResourceLocation("block/mud_bricks"), GTCEu.id("block/multiblock/primitive_blast_furnace"), false)
            .register();

    public static final MultiblockMachineDefinition STEAM_CENTRIFUGE = REGISTRATE.multiblock("steam_centrifuge", SteamParallelMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(GTRecipeTypes.CENTRIFUGE_RECIPES)
            .appearanceBlock(CASING_STEEL_SOLID)
            .recipeModifier(SteamParallelMultiblockMachine::recipeModifier, true)
            .addOutputLimit(ItemRecipeCapability.CAP, 1)
            .pattern(def -> FactoryBlockPattern.start()
                    .aisle("CCC", "XXX", "XXX")
                    .aisle("CCC", "X#X", "XXX")
                    .aisle("CCC", "XSX", "XXX")
                    .where("S", Predicates.controller(blocks(def.getBlock())))
                    .where("#", Predicates.air())
                    .where("X", blocks(CASING_STEEL_SOLID.get()).setMinGlobalLimited(13)
                            .or(Predicates.abilities(PartAbility.STEAM_IMPORT_ITEMS).setPreviewCount(1))
                            .or(Predicates.abilities(PartAbility.STEAM_EXPORT_ITEMS).setPreviewCount(1))
                            .or(Predicates.abilities(PartAbility.STEAM).setExactLimit(1)))
                    .where('C', blocks(FIREBOX_STEEL.get()))
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_solid_steel"), GTCEu.id("block/machines/centrifuge"))
            .register();

    public static final MultiblockMachineDefinition STEAM_ALLOY_SMELTER = REGISTRATE.multiblock("steam_alloy_smelter", SteamParallelMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(GTRecipeTypes.ALLOY_SMELTER_RECIPES)
            .appearanceBlock(CASING_STEEL_SOLID)
            .recipeModifier(SteamParallelMultiblockMachine::recipeModifier, true)
            .addOutputLimit(ItemRecipeCapability.CAP, 1)
            .pattern(def -> FactoryBlockPattern.start()
                    .aisle("CCC", "XXX", "XXX")
                    .aisle("CCC", "X#X", "XXX")
                    .aisle("CCC", "XSX", "XXX")
                    .where("S", Predicates.controller(blocks(def.getBlock())))
                    .where("#", Predicates.air())
                    .where("X", blocks(CASING_STEEL_SOLID.get()).setMinGlobalLimited(13)
                            .or(Predicates.abilities(PartAbility.STEAM_IMPORT_ITEMS).setPreviewCount(1))
                            .or(Predicates.abilities(PartAbility.STEAM_EXPORT_ITEMS).setPreviewCount(1))
                            .or(Predicates.abilities(PartAbility.STEAM).setExactLimit(1)))
                    .where('C', blocks(FIREBOX_STEEL.get()))
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_solid_steel"), GTCEu.id("block/machines/alloy_smelter"))
            .register();

    public static final MultiblockMachineDefinition STEAM_EXTRACTOR = REGISTRATE.multiblock("steam_extractor", SteamParallelMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(GTRecipeTypes.EXTRACTOR_RECIPES)
            .appearanceBlock(CASING_STEEL_SOLID)
            .recipeModifier(SteamParallelMultiblockMachine::recipeModifier, true)
            .addOutputLimit(ItemRecipeCapability.CAP, 1)
            .pattern(def -> FactoryBlockPattern.start()
                    .aisle("CCC", "XXX", "XXX")
                    .aisle("CCC", "X#X", "XXX")
                    .aisle("CCC", "XSX", "XXX")
                    .where("S", Predicates.controller(blocks(def.getBlock())))
                    .where("#", Predicates.air())
                    .where("X", blocks(CASING_STEEL_SOLID.get()).setMinGlobalLimited(13)
                            .or(Predicates.abilities(PartAbility.STEAM_IMPORT_ITEMS).setPreviewCount(1))
                            .or(Predicates.abilities(PartAbility.STEAM_EXPORT_ITEMS).setPreviewCount(1))
                            .or(Predicates.abilities(PartAbility.STEAM).setExactLimit(1)))
                    .where('C', blocks(FIREBOX_STEEL.get()))
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_solid_steel"), GTCEu.id("block/machines/extractor"))
            .register();

    public static void register() {}
}
