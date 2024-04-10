package com.gbth.gbthcore.mixin;

import com.gbth.gbthcore.ServerConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.WaterFluid;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(WaterFluid.class)
public abstract class WaterFluidMixin extends FlowingFluid {
	@Override
	public boolean canConvertToSource(FluidState state, Level level, BlockPos pos) {
		for (ResourceKey<Biome> biome : ServerConfig.allowedWaterFlowBiomes) {
			if (level.getBiome(pos).is(biome)) {
				return true;
			}
		}
		return false;
	}
}
