package com.gbth.gbthcore.integration.jei;

import com.gbth.gbthcore.registry.GBTHCoreItems;
import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.integration.jei.orevein.GTBedrockFluidInfoCategory;
import com.gregtechceu.gtceu.integration.jei.orevein.GTOreVeinInfoCategory;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

@JeiPlugin
public class GBTHCoreJEIPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(GTCEu.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.getIngredientManager().addIngredientsAtRuntime(VanillaTypes.ITEM_STACK,
                List.of(GBTHCoreItems.PROSPECTOR_LV.asStack(), GBTHCoreItems.PROSPECTOR_HV.asStack(), GBTHCoreItems.PROSPECTOR_LuV.asStack()));

        registration.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK,
                List.of(GTItems.PROSPECTOR_LV.asStack(), GTItems.PROSPECTOR_HV.asStack(), GTItems.PROSPECTOR_LuV.asStack()));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(GBTHCoreItems.PROSPECTOR_LV.asStack(), RecipeType.create(GTCEu.MOD_ID, "ore_vein_diagram", GTOreVeinInfoCategory.class));
        registration.addRecipeCatalyst(GBTHCoreItems.PROSPECTOR_HV.asStack(), RecipeType.create(GTCEu.MOD_ID, "ore_vein_diagram", GTOreVeinInfoCategory.class));
        registration.addRecipeCatalyst(GBTHCoreItems.PROSPECTOR_LuV.asStack(), RecipeType.create(GTCEu.MOD_ID, "ore_vein_diagram", GTOreVeinInfoCategory.class));

        registration.addRecipeCatalyst(GBTHCoreItems.PROSPECTOR_LV.asStack(), RecipeType.create(GTCEu.MOD_ID, "bedrock_fluid_diagram", GTBedrockFluidInfoCategory.class));
        registration.addRecipeCatalyst(GBTHCoreItems.PROSPECTOR_HV.asStack(), RecipeType.create(GTCEu.MOD_ID, "bedrock_fluid_diagram", GTBedrockFluidInfoCategory.class));
        registration.addRecipeCatalyst(GBTHCoreItems.PROSPECTOR_LuV.asStack(), RecipeType.create(GTCEu.MOD_ID, "bedrock_fluid_diagram", GTBedrockFluidInfoCategory.class));
    }
}
