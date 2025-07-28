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
import net.minecraft.network.chat.Component;
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
            .tooltips(Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", Component.translatable("gbthcore.bloomery")))
            .pattern(def -> FactoryBlockPattern.start()
                    .aisle("SMS", "#M#", "#M#")
                    .aisle("MMM", "M#M", "M#M")
                    .aisle("SMS", "#C#", "#M#")
                    .where("S", blocks(Blocks.MUD_BRICK_SLAB))
                    .where("M", blocks(Blocks.MUD_BRICKS))
                    .where("#", Predicates.air())
                    .where("C", Predicates.controller(blocks(def.get())))
                    .build())
            .workableCasingModel(new ResourceLocation("block/mud_bricks"), GTCEu.id("block/multiblock/primitive_blast_furnace"))
            .register();

    public static final MultiblockMachineDefinition ROCK_BLASTER = REGISTRATE.multiblock("rock_blaster", RockBlasterMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GBTHCoreRecipeTypes.ROCK_BLASTER_RECIPES)
            .appearanceBlock(CASING_PRIMITIVE_BRICKS)
            .tooltips(Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", Component.translatable("gbthcore.rock_blaster")))
            .pattern(def -> FactoryBlockPattern.start()
                    .aisle("##M##", "##M##", "#####")
                    .aisle("#MDM#", "#MDM#", "##S##")
                    .aisle("MDDDM", "MD#DM", "#S#S#")
                    .aisle("#MDM#", "#MDM#", "##S##")
                    .aisle("##M##", "##C##", "#####")
                    .where("#", Predicates.air())
                    .where("D", blocks(Blocks.COBBLED_DEEPSLATE))
                    .where("M", blocks(Blocks.MUD_BRICKS))
                    .where("C", Predicates.controller(blocks(def.get())))
                    .where("S", blocks(Blocks.MUD_BRICK_SLAB))
                    .build())
            .workableCasingModel(new ResourceLocation("block/mud_bricks"), GTCEu.id("block/multiblock/primitive_blast_furnace"))
            .register();

    public static final MultiblockMachineDefinition STEAM_CENTRIFUGE = REGISTRATE.multiblock("steam_centrifuge", SteamParallelMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(GTRecipeTypes.CENTRIFUGE_RECIPES)
            .appearanceBlock(CASING_STEEL_SOLID)
            .tooltips(Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", Component.translatable("gtceu.centrifuge")))
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
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_solid_steel"), GTCEu.id("block/machines/centrifuge"))
            .register();

    public static final MultiblockMachineDefinition STEAM_ALLOY_SMELTER = REGISTRATE.multiblock("steam_alloy_smelter", SteamParallelMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(GTRecipeTypes.ALLOY_SMELTER_RECIPES)
            .appearanceBlock(CASING_STEEL_SOLID)
            .tooltips(Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", Component.translatable("gtceu.alloy_smelter")))
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
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_solid_steel"), GTCEu.id("block/machines/alloy_smelter"))
            .register();

    public static final MultiblockMachineDefinition STEAM_EXTRACTOR = REGISTRATE.multiblock("steam_extractor", SteamParallelMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(GTRecipeTypes.EXTRACTOR_RECIPES)
            .appearanceBlock(CASING_STEEL_SOLID)
            .tooltips(Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", Component.translatable("gtceu.extractor")))
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
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_solid_steel"), GTCEu.id("block/machines/extractor"))
            .register();

    public static void register() {}
}
