package com.gbth.gbthcore.data.recipe;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class GBTHCoreRecipes {

    public static void register(Consumer<FinishedRecipe> provider) {
        CraftingTableRecipes.register(provider);
    }
}
