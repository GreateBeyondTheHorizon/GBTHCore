package com.gbth.gbthcore.mixin;

import com.gbth.gbthcore.GBTHConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.WaterFluid;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(WaterFluid.class)
public abstract class WaterFluidMixin extends FlowingFluid {
	@Override
	public boolean canConvertToSource(FluidState state, Level level, BlockPos pos) {
		if (GBTHConfig.INSTANCE.restrictWaterSpread) {
			for (String biome : GBTHConfig.INSTANCE.allowedWaterSpreadBiomes) {
				if (level.getBiome(pos).is(new ResourceLocation(biome))) {
					return true;
				}
			}
			return false;
		}
		return true;
	}
}
