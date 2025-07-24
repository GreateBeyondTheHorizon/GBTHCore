package com.gbth.gbthcore.data.recipe;

import com.gbth.gbthcore.GBTHCore;
import com.gbth.gbthcore.integration.gtceu.multiblocks.GBTHCoreMultiblocks;
import com.gbth.gbthcore.registry.GBTHCoreItems;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialEntry;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import com.tterrag.registrate.util.entry.ItemEntry;
import it.unimi.dsi.fastutil.ints.Int2ReferenceArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ReferenceMap;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;
import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.gear;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.plate;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Potin;

public class CraftingTableRecipes {

    private static final Int2ReferenceMap<List<ItemEntry<? extends Item>>> batteryItems = new Int2ReferenceArrayMap<>();

    private static void setupItems() {
        batteryItems.put(GTValues.ULV, List.of(GTItems.BATTERY_ULV_TANTALUM));
        batteryItems.put(GTValues.LV,
                List.of(GTItems.BATTERY_LV_LITHIUM, GTItems.BATTERY_LV_CADMIUM, GTItems.BATTERY_LV_SODIUM));
        batteryItems.put(GTValues.MV,
                List.of(GTItems.BATTERY_MV_LITHIUM, GTItems.BATTERY_MV_CADMIUM, GTItems.BATTERY_MV_SODIUM));
        batteryItems.put(GTValues.HV, List.of(GTItems.BATTERY_HV_LITHIUM, GTItems.BATTERY_HV_CADMIUM,
                GTItems.BATTERY_HV_SODIUM, GTItems.ENERGIUM_CRYSTAL));
        batteryItems.put(GTValues.EV, List.of(GTItems.BATTERY_EV_VANADIUM, GTItems.LAPOTRON_CRYSTAL));
        batteryItems.put(GTValues.IV, List.of(GTItems.BATTERY_IV_VANADIUM, GTItems.ENERGY_LAPOTRONIC_ORB));
        batteryItems.put(GTValues.LuV,
                List.of(GTItems.BATTERY_LuV_VANADIUM, GTItems.ENERGY_LAPOTRONIC_ORB_CLUSTER));
        batteryItems.put(GTValues.ZPM, List.of(GTItems.BATTERY_ZPM_NAQUADRIA, GTItems.ENERGY_MODULE));
        batteryItems.put(GTValues.UV, List.of(GTItems.BATTERY_UV_NAQUADRIA, GTItems.ENERGY_CLUSTER));
    }

    public static void register(Consumer<FinishedRecipe> provider) {
        setupItems();
        for(ItemEntry<? extends Item> battery : batteryItems.get(LV)) {
            VanillaRecipeHelper.addShapedEnergyTransferRecipe(provider, true, false, true,
                    "prospector_" + battery.getId().getPath(),
                    Ingredient.of(battery), GBTHCoreItems.PROSPECTOR_LV.asStack(),
                    "EPS", "CDC", "PBP",
                    'E', GTItems.EMITTER_LV.asStack(),
                    'P', new MaterialEntry(plate, GTMaterials.Steel),
                    'S', GTItems.SENSOR_LV.asStack(),
                    'D', new MaterialEntry(plate, GTMaterials.Glass),
                    'C', CustomTags.LV_CIRCUITS,
                    'B', battery.asStack());
        }

        for(ItemEntry<? extends Item> battery : batteryItems.get(HV)) {
            VanillaRecipeHelper.addShapedEnergyTransferRecipe(provider, true, false, true,
                    "prospector_" + battery.getId().getPath(),
                    Ingredient.of(battery), GBTHCoreItems.PROSPECTOR_HV.asStack(),
                    "EPS", "CDC", "PBP",
                    'E', GTItems.EMITTER_HV.asStack(),
                    'P', new MaterialEntry(plate, GTMaterials.StainlessSteel),
                    'S', GTItems.SENSOR_HV.asStack(),
                    'D', GTItems.COVER_SCREEN.asStack(),
                    'C', CustomTags.HV_CIRCUITS,
                    'B', battery.asStack());
        }

        for (ItemEntry<? extends Item> battery : batteryItems.get(LuV)) {
            VanillaRecipeHelper.addShapedEnergyTransferRecipe(provider, true, false, true,
                    "prospector_" + battery.getId().getPath(),
                    Ingredient.of(battery), GBTHCoreItems.PROSPECTOR_LuV.asStack(),
                    "EPS", "CDC", "PBP",
                    'E', GTItems.EMITTER_LuV.asStack(),
                    'P', new MaterialEntry(plate, GTMaterials.RhodiumPlatedPalladium),
                    'S', GTItems.SENSOR_LuV.asStack(),
                    'D', GTItems.COVER_SCREEN.asStack(),
                    'C', CustomTags.LuV_CIRCUITS,
                    'B', battery.asStack());
        }

        VanillaRecipeHelper.addShapedRecipe(provider, GBTHCore.id("steam_alloy_smelter"),
                GBTHCoreMultiblocks.STEAM_ALLOY_SMELTER.asStack(),
                "SPS", "SAS", "SPS",
                'S', GTBlocks.CASING_STEEL_SOLID,
                'P', new MaterialEntry(gear, Potin),
                'A', GTMachines.STEAM_ALLOY_SMELTER.right().asStack());

        VanillaRecipeHelper.addShapedRecipe(provider, GBTHCore.id("steam_centrifuge"),
                GBTHCoreMultiblocks.STEAM_CENTRIFUGE.asStack(),
                "SPS", "SAS", "SPS",
                'S', GTBlocks.CASING_STEEL_SOLID,
                'P', new MaterialEntry(gear, Potin),
                'A', GTMachines.CENTRIFUGE[LV].asStack());

        VanillaRecipeHelper.addShapedRecipe(provider, GBTHCore.id("steam_extractor"),
                GBTHCoreMultiblocks.STEAM_EXTRACTOR.asStack(),
                "SPS", "SAS", "SPS",
                'S', GTBlocks.CASING_STEEL_SOLID,
                'P', new MaterialEntry(gear, Potin),
                'A', GTMachines.STEAM_EXTRACTOR.right().asStack());
    }
}
