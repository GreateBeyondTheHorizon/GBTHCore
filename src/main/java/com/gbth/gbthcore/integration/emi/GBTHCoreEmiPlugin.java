package com.gbth.gbthcore.integration.emi;

import com.gbth.gbthcore.registry.GBTHCoreItems;
import com.gregtechceu.gtceu.integration.emi.orevein.GTBedrockFluidEmiCategory;
import com.gregtechceu.gtceu.integration.emi.orevein.GTOreVeinEmiCategory;
import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.stack.EmiStack;

@EmiEntrypoint
public class GBTHCoreEmiPlugin implements EmiPlugin {

    @Override
    public void register(EmiRegistry emiRegistry) {
        emiRegistry.addWorkstation(GTOreVeinEmiCategory.CATEGORY, EmiStack.of(GBTHCoreItems.PROSPECTOR_LV));
        emiRegistry.addWorkstation(GTOreVeinEmiCategory.CATEGORY, EmiStack.of(GBTHCoreItems.PROSPECTOR_HV));
        emiRegistry.addWorkstation(GTOreVeinEmiCategory.CATEGORY, EmiStack.of(GBTHCoreItems.PROSPECTOR_LUV));

        emiRegistry.addWorkstation(GTBedrockFluidEmiCategory.CATEGORY, EmiStack.of(GBTHCoreItems.PROSPECTOR_LV));
        emiRegistry.addWorkstation(GTBedrockFluidEmiCategory.CATEGORY, EmiStack.of(GBTHCoreItems.PROSPECTOR_HV));
        emiRegistry.addWorkstation(GTBedrockFluidEmiCategory.CATEGORY, EmiStack.of(GBTHCoreItems.PROSPECTOR_LUV));
    }
}
