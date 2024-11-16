package com.gbth.gbthcore.data.recipe;

import com.gbth.gbthcore.GBTHCore;
import com.gbth.gbthcore.integration.gtceu.multiblocks.GBTHCoreMultiblocks;
import com.gbth.gbthcore.registry.GBTHCoreItems;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.UnificationEntry;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import com.gregtechceu.gtceu.data.recipe.generated.ToolRecipeHandler;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.gear;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.plate;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Potin;

public class CraftingTableRecipes {

    public static void register(Consumer<FinishedRecipe> provider) {
        for(ItemEntry<? extends Item> battery : ToolRecipeHandler.batteryItems.get(LV)) {
            VanillaRecipeHelper.addShapedEnergyTransferRecipe(provider, true, false, true,
                    "prospector_" + battery.getId().getPath(),
                    Ingredient.of(battery), GBTHCoreItems.PROSPECTOR_LV.asStack(),
                    "EPS", "CDC", "PBP",
                    'E', GTItems.EMITTER_LV.asStack(),
                    'P', new UnificationEntry(plate, GTMaterials.Steel),
                    'S', GTItems.SENSOR_LV.asStack(),
                    'D', new UnificationEntry(plate, GTMaterials.Glass),
                    'C', CustomTags.LV_CIRCUITS,
                    'B', battery.asStack());
        }

        for(ItemEntry<? extends Item> battery : ToolRecipeHandler.batteryItems.get(HV)) {
            VanillaRecipeHelper.addShapedEnergyTransferRecipe(provider, true, false, true,
                    "prospector_" + battery.getId().getPath(),
                    Ingredient.of(battery), GBTHCoreItems.PROSPECTOR_HV.asStack(),
                    "EPS", "CDC", "PBP",
                    'E', GTItems.EMITTER_HV.asStack(),
                    'P', new UnificationEntry(plate, GTMaterials.StainlessSteel),
                    'S', GTItems.SENSOR_HV.asStack(),
                    'D', GTItems.COVER_SCREEN.asStack(),
                    'C', CustomTags.HV_CIRCUITS,
                    'B', battery.asStack());
        }

        for (ItemEntry<? extends Item> battery : ToolRecipeHandler.batteryItems.get(LuV)) {
            VanillaRecipeHelper.addShapedEnergyTransferRecipe(provider, true, false, true,
                    "prospector_" + battery.getId().getPath(),
                    Ingredient.of(battery), GBTHCoreItems.PROSPECTOR_LuV.asStack(),
                    "EPS", "CDC", "PBP",
                    'E', GTItems.EMITTER_LuV.asStack(),
                    'P', new UnificationEntry(plate, GTMaterials.RhodiumPlatedPalladium),
                    'S', GTItems.SENSOR_LuV.asStack(),
                    'D', GTItems.COVER_SCREEN.asStack(),
                    'C', CustomTags.LuV_CIRCUITS,
                    'B', battery.asStack());
        }

        VanillaRecipeHelper.addShapedRecipe(provider, GBTHCore.id("steam_alloy_smelter"),
                GBTHCoreMultiblocks.STEAM_ALLOY_SMELTER.asStack(),
                "SPS", "SAS", "SPS",
                'S', GTBlocks.CASING_STEEL_SOLID,
                'P', new UnificationEntry(gear, Potin),
                'A', GTMachines.STEAM_ALLOY_SMELTER.right().asStack());

        VanillaRecipeHelper.addShapedRecipe(provider, GBTHCore.id("steam_centrifuge"),
                GBTHCoreMultiblocks.STEAM_CENTRIFUGE.asStack(),
                "SPS", "SAS", "SPS",
                'S', GTBlocks.CASING_STEEL_SOLID,
                'P', new UnificationEntry(gear, Potin),
                'A', GTMachines.CENTRIFUGE[LV].asStack());

        VanillaRecipeHelper.addShapedRecipe(provider, GBTHCore.id("steam_extractor"),
                GBTHCoreMultiblocks.STEAM_EXTRACTOR.asStack(),
                "SPS", "SAS", "SPS",
                'S', GTBlocks.CASING_STEEL_SOLID,
                'P', new UnificationEntry(gear, Potin),
                'A', GTMachines.STEAM_EXTRACTOR.right().asStack());
    }
}
