package com.gbth.gbthcore.gtceu.multiblocks;

import com.gbth.gbthcore.registry.GBTHCoreRecipeTypes;
import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.api.pattern.TraceabilityPredicate;
import com.gregtechceu.gtceu.client.renderer.machine.LargeBoilerRenderer;
import com.gregtechceu.gtceu.common.block.BoilerFireboxType;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.config.ConfigHolder;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Supplier;

import static com.gbth.gbthcore.registry.GBTHCoreRegistries.REGISTRATE;
import static com.gregtechceu.gtceu.api.pattern.Predicates.blocks;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.LARGE_BOILER_RECIPES;

public class GBTHCoreMultiblocks {

    public static final MultiblockMachineDefinition BLOOMERY = REGISTRATE.multiblock("bloomery", BloomeryMachine::new)
            .langValue("Bloomery")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GBTHCoreRecipeTypes.BLOOMERY_RECIPES)
            .appearanceBlock(CASING_PRIMITIVE_BRICKS)
            .pattern(def -> FactoryBlockPattern.start()
                    .aisle("SMS", "#M#", "#M#")
                    .aisle("MMM", "M#M", "M#M")
                    .aisle("SMS", "#C#", "#M#")
                    .where("S", Predicates.blocks(Blocks.MUD_BRICK_SLAB))
                    .where("M", Predicates.blocks(Blocks.MUD_BRICKS))
                    .where("#", Predicates.air())
                    .where("C", Predicates.controller(Predicates.blocks(def.get())))
                    .build())
            .workableCasingRenderer(new ResourceLocation("block/mud_bricks"), GTCEu.id("block/multiblock/primitive_blast_furnace"), false)
            .register();

    public static final MultiblockMachineDefinition BRONZE_BOILER = registerLargeBoiler("bronze", CASING_BRONZE_BRICKS, CASING_BRONZE_PIPE, FIREBOX_BRONZE,
            GTCEu.id("block/casings/solid/machine_casing_bronze_plated_bricks"), BoilerFireboxType.BRONZE_FIREBOX, 30 * 2 + 250, 2, 2, 3);
    public static final MultiblockMachineDefinition STEEL_BOILER = registerLargeBoiler("steel", CASING_STEEL_SOLID, CASING_STEEL_PIPE, FIREBOX_STEEL,
            GTCEu.id("block/casings/solid/machine_casing_solid_steel"), BoilerFireboxType.STEEL_FIREBOX, 30 * 4 + 550, 2, 4, 6);

    public static MultiblockMachineDefinition registerLargeBoiler(String name, Supplier<? extends Block> casing,
                                                                  Supplier<? extends Block> pipe,
                                                                  Supplier<? extends Block> fireBox,
                                                                  ResourceLocation texture, BoilerFireboxType firebox,
                                                                  int maxTemperature, int heatSpeed,
                                                                  int fuelUsage, int productionEfficiency) {
        return REGISTRATE
                .multiblock("%s_large_boiler".formatted(name), holder -> new AdvancedLargeBoilerMachine(holder, maxTemperature, heatSpeed, fuelUsage, productionEfficiency))
                .langValue("Large %s Boiler".formatted(FormattingUtil.toEnglishName(name)))
                .rotationState(RotationState.ALL)
                .recipeType(LARGE_BOILER_RECIPES)
                .recipeModifier(AdvancedLargeBoilerMachine::recipeModifier, true)
                .appearanceBlock(casing)
                .partAppearance((controller, part, side) -> controller.self().getPos().below().getY() == part.self().getPos().getY() ?
                        fireBox.get().defaultBlockState() : casing.get().defaultBlockState())
                .pattern((definition) -> {
                    TraceabilityPredicate fireboxPred = blocks(ALL_FIREBOXES.get(firebox).get()).setMinGlobalLimited(3)
                            .or(Predicates.abilities(PartAbility.IMPORT_FLUIDS).setMinGlobalLimited(1)
                                    .setPreviewCount(1))
                            .or(Predicates.abilities(PartAbility.IMPORT_ITEMS).setMaxGlobalLimited(1)
                                    .setPreviewCount(1))
                            .or(Predicates.abilities(PartAbility.MUFFLER).setExactLimit(1));

                    if (ConfigHolder.INSTANCE.machines.enableMaintenance) {
                        fireboxPred = fireboxPred.or(Predicates.abilities(PartAbility.MAINTENANCE).setExactLimit(1));
                    }

                    return FactoryBlockPattern.start()
                            .aisle("XXX", "CCC", "CCC", "CCC")
                            .aisle("XXX", "CPC", "CPC", "CCC")
                            .aisle("XXX", "CSC", "CCC", "CCC")
                            .where('S', Predicates.controller(blocks(definition.getBlock())))
                            .where('P', blocks(pipe.get()))
                            .where('X', fireboxPred)
                            .where('C', blocks(casing.get()).setMinGlobalLimited(20)
                                    .or(Predicates.abilities(PartAbility.EXPORT_FLUIDS).setMinGlobalLimited(1)
                                            .setPreviewCount(1)))
                            .build();
                })
                .recoveryItems(() -> new ItemLike[] {GTItems.MATERIAL_ITEMS.get(TagPrefix.dustTiny, GTMaterials.Ash).get()})
                .renderer(() -> new LargeBoilerRenderer(texture, firebox,
                        GTCEu.id("block/multiblock/generator/large_%s_boiler".formatted(name))))
                .tooltips(
                        Component.translatable("gtceu.multiblock.large_boiler.max_temperature",
                                (int) (maxTemperature + 274.15), 150 * productionEfficiency / 5),
                        Component.translatable("gbthcore.multiblock.large_boiler.fuel_usage",
                                fuelUsage),
                        Component.translatable("gbthcore.multiblock.large_boiler.production_efficiency",
                                productionEfficiency),
                        Component.translatable("gtceu.multiblock.large_boiler.heat_time_tooltip",
                                maxTemperature / heatSpeed / 20 * 10),
                        Component.translatable("gtceu.multiblock.large_boiler.explosion_tooltip")
                                .withStyle(ChatFormatting.DARK_RED))
                .register();
    }

    public static void register() {}
}
