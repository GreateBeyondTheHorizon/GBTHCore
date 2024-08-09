package com.gbth.gbthcore.registry;

import com.gbth.gbthcore.integration.gtceu.common.item.AdvancedProspectorScannerBehavior;
import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.gui.misc.ProspectorMode;
import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.api.item.component.ElectricStats;
import com.gregtechceu.gtceu.config.ConfigHolder;
import com.tterrag.registrate.util.entry.ItemEntry;

import static com.gbth.gbthcore.registry.GBTHCoreRegistries.REGISTRATE;
import static com.gregtechceu.gtceu.common.data.GTItems.attach;

public class GBTHCoreItems {

    public static void register() {}

    public static ItemEntry<ComponentItem> PROSPECTOR_LV = REGISTRATE
            .item("prospector_lv", ComponentItem::create)
            .lang("Ore Prospector (LV)")
            .model((c, p) -> p.withExistingParent("prospector_lv", "item/generated")
                    .texture("layer0", GTCEu.id("item/prospector.lv")))
            .properties(p -> p.stacksTo(1))
            .onRegister(attach(ElectricStats.createElectricItem(100_100L, GTValues.LV),
                    new AdvancedProspectorScannerBehavior(5, GTValues.V[GTValues.LV] / 16L, ProspectorMode.ORE)))
            .register();

    public static ItemEntry<ComponentItem> PROSPECTOR_HV = REGISTRATE
            .item("prospector_hv", ComponentItem::create)
            .lang("Advanced Prospector (HV)")
            .model((c, p) -> p.withExistingParent("prospector_hv", "item/generated")
                    .texture("layer0", GTCEu.id("item/prospector.hv")))
            .properties(p -> p.stacksTo(1))
            .onRegister(attach(ElectricStats.createElectricItem(1_600_100L, GTValues.HV),
                    new AdvancedProspectorScannerBehavior(8, GTValues.V[GTValues.HV] / 16L, ProspectorMode.ORE,
                            ProspectorMode.FLUID,
                            ConfigHolder.INSTANCE.machines.doBedrockOres ? ProspectorMode.BEDROCK_ORE : null)))
            .register();

    public static ItemEntry<ComponentItem> PROSPECTOR_LUV = REGISTRATE
            .item("prospector_luv", ComponentItem::create)
            .lang("Super Prospector (LuV)")
            .model((c, p) -> p.withExistingParent("prospector_luv", "item/generated")
                    .texture("layer0", GTCEu.id("item/prospector.luv")))
            .properties(p -> p.stacksTo(1))
            .onRegister(attach(ElectricStats.createElectricItem(1_000_000_100L, GTValues.LuV),
                    new AdvancedProspectorScannerBehavior(11, GTValues.V[GTValues.LuV] / 16L, ProspectorMode.ORE,
                            ProspectorMode.FLUID,
                            ConfigHolder.INSTANCE.machines.doBedrockOres ? ProspectorMode.BEDROCK_ORE : null)))
            .register();

}
