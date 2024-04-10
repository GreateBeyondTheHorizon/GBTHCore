package com.gbth.gbthcore;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(modid = GBTHCore.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ServerConfig
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> ALLOWED_WATER_FLOW_BIOME_STRINGS = BUILDER
            .comment("A list of biomes that water is able to create source blocks in.")
            .defineListAllowEmpty("allowed_water_flow_biomes", List.of(
                    "minecraft:river",
                    "minecraft:frozen_river",
                    "minecraft:ocean",
                    "minecraft:deep_ocean",
                    "minecraft:frozen_ocean",
                    "minecraft:deep_frozen_ocean",
                    "minecraft:cold_ocean",
                    "minecraft:deep_cold_ocean",
                    "minecraft:lukewarm_ocean",
                    "minecraft:deep_lukewarm_ocean",
                    "minecraft:warm_ocean"
            ), ServerConfig::validateBiomeName);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static Set<ResourceKey<Biome>> allowedWaterFlowBiomes;

    private static boolean validateBiomeName(final Object obj) {
        return obj instanceof final String biomeName && ForgeRegistries.BIOMES.containsKey(new ResourceLocation(biomeName));
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        allowedWaterFlowBiomes = ALLOWED_WATER_FLOW_BIOME_STRINGS.get().stream()
                .map(biomeName -> ResourceKey.create(Registries.BIOME, new ResourceLocation(biomeName)))
                .collect(Collectors.toSet());
    }
}
