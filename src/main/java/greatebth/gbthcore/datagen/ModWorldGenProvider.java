package greatebth.gbthcore.datagen;

import greatebth.gbthcore.GBTHCore;
import greatebth.gbthcore.worldgen.ModBiomModifiers;
import greatebth.gbthcore.worldgen.ModConfiguredFeature;
import greatebth.gbthcore.worldgen.ModPlacedFeature;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModWorldGenProvider extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeature::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeature::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomModifiers::bootstrap);
    public ModWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER ,Set.of(GBTHCore.MOD_ID));
    }
}
