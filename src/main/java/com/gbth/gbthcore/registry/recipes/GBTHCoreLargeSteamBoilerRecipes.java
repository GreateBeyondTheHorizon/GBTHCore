package com.gbth.gbthcore.registry.recipes;

import com.gregtechceu.gtceu.utils.GTUtil;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;

import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.LARGE_BOILER_RECIPES;

public class GBTHCoreLargeSteamBoilerRecipes {

    public static void init(Consumer<FinishedRecipe> consumer) {
        LARGE_BOILER_RECIPES.getLookup().removeAllRecipes();
        Set<Item> items = new HashSet<>();
        for(Entry<Item, Integer> fuelValue : FurnaceBlockEntity.getFuel().entrySet()) {
            items.add(fuelValue.getKey());
            LARGE_BOILER_RECIPES.recipeBuilder(BuiltInRegistries.ITEM.getKey(fuelValue.getKey()))
                    .inputItems(fuelValue.getKey())
                    .duration(fuelValue.getValue() * 12)
                    .save(consumer);
        }
        BuiltInRegistries.ITEM.forEach(item -> {
            int burnTime = GTUtil.getItemBurnTime(item);
            if(burnTime > 0 && !items.contains(item)) {
                LARGE_BOILER_RECIPES.recipeBuilder(BuiltInRegistries.ITEM.getKey(item))
                        .inputItems(item)
                        .duration(burnTime * 12)
                        .save(consumer);
            }
        });
    }
}
