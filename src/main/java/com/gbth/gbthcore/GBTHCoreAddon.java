package com.gbth.gbthcore;

import com.gbth.gbthcore.data.recipe.GBTHCoreRecipes;
import com.gbth.gbthcore.registry.GBTHCoreItems;
import com.gbth.gbthcore.registry.GBTHCoreRegistries;
import com.gbth.gbthcore.registry.GBTHCoreTagPrefixes;
import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

@GTAddon
public class GBTHCoreAddon implements IGTAddon {


    @Override
    public GTRegistrate getRegistrate() {
        return GBTHCoreRegistries.REGISTRATE;
    }

    @Override
    public void initializeAddon() {
        GBTHCoreItems.register();
    }

    @Override
    public String addonModId() {
        return GBTHCore.MODID;
    }

    @Override
    public void registerTagPrefixes() {
        GBTHCoreTagPrefixes.register();
    }

    @Override
    public void addRecipes(Consumer<FinishedRecipe> provider) {
        GBTHCoreRecipes.register(provider);
    }
}
