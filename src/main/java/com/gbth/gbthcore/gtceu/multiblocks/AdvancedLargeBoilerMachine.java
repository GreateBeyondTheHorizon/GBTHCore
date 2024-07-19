package com.gbth.gbthcore.gtceu.multiblocks;

import com.gbth.gbthcore.mixin.LargeBoilerMachineAccessor;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.IRecipeHandler;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.machine.multiblock.steam.LargeBoilerMachine;
import com.lowdragmc.lowdraglib.side.fluid.FluidHelper;
import lombok.Getter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Plane;
import net.minecraft.util.Mth;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdvancedLargeBoilerMachine extends LargeBoilerMachine {

    @Getter
    private final int fuelUsage, productionEfficiency;
    private long STEAM_PER_WATER = 150;

    public AdvancedLargeBoilerMachine(IMachineBlockEntity holder, int maxTemperature, int heatSpeed, int fuelUsage, int productionEfficiency, Object... args) {
        super(holder, maxTemperature, heatSpeed, args);
        this.fuelUsage = fuelUsage;
        this.productionEfficiency = productionEfficiency;
    }

    @Override
    protected void updateCurrentTemperature() {
        if(this.recipeLogic.isWorking()) {
            if(this.getOffsetTimer() % 10 == 0 && this.getCurrentTemperature() < this.getMaxTemperature()) {
                this.setCurrentTemperature(Mth.clamp(this.getCurrentTemperature() + this.heatSpeed, 0, this.getMaxTemperature()));
            }
        } else if(this.getCurrentTemperature() > 0) {
            this.setCurrentTemperature(this.getCurrentTemperature() - this.getCoolDownRate());
        }

        if(this.getCurrentTemperature() >= 100 && this.getOffsetTimer() % TICKS_PER_STEAM_GENERATION == 0) {
            long maxDrain = this.getCurrentTemperature() * this.getThrottle() * FluidHelper.getBucket() * productionEfficiency / (STEAM_PER_WATER * 100000);
            List<FluidIngredient> drainWater = List.of(FluidIngredient.of(maxDrain * fuelUsage, Fluids.WATER));
            List<IRecipeHandler<?>> inputTanks = new ArrayList<>();
            if(this.getCapabilitiesProxy().contains(IO.IN, FluidRecipeCapability.CAP)) {
                inputTanks.addAll((Objects.requireNonNull(this.getCapabilitiesProxy().get(IO.IN, FluidRecipeCapability.CAP))));
            }
            if(this.getCapabilitiesProxy().contains(IO.BOTH, FluidRecipeCapability.CAP)) {
                inputTanks.addAll(Objects.requireNonNull(this.getCapabilitiesProxy().get(IO.BOTH, FluidRecipeCapability.CAP)));
            }

            for(IRecipeHandler<?> inputTank : inputTanks) {
                drainWater = (List<FluidIngredient>) inputTank.handleRecipe(IO.IN, null, drainWater, null, false);
                if(drainWater == null) break;
            }

            long drained = drainWater != null && drainWater.isEmpty() ? maxDrain / fuelUsage - drainWater.get(0).getAmount() : maxDrain / fuelUsage;
            boolean hasDrainedWater = drained > 0;
            setSteamGenerated(drained * STEAM_PER_WATER);

            if(hasDrainedWater) {
                List<FluidIngredient> fillSteam = List.of(FluidIngredient.of(GTMaterials.Steam.getFluid(this.getSteamGenerated())));
                List<IRecipeHandler<?>> outputTanks = new ArrayList<>();
                if(this.getCapabilitiesProxy().contains(IO.OUT, FluidRecipeCapability.CAP)) {
                    outputTanks.addAll(Objects.requireNonNull(this.getCapabilitiesProxy().get(IO.OUT, FluidRecipeCapability.CAP)));
                }
                if(this.getCapabilitiesProxy().contains(IO.BOTH, FluidRecipeCapability.CAP)) {
                    outputTanks.addAll(Objects.requireNonNull(this.getCapabilitiesProxy().get(IO.BOTH, FluidRecipeCapability.CAP)));
                }

                for(IRecipeHandler<?> outputTank : outputTanks) {
                    fillSteam = (List<FluidIngredient>) outputTank.handleRecipe(IO.OUT, null, fillSteam, null, false);
                    if(fillSteam == null) break;
                }
            }

            if(this.isHasNoWater() && hasDrainedWater) {
                doExplosion(2f);
                BlockPos center = getPos().below().relative(getFrontFacing().getOpposite());
                if(GTValues.RNG.nextInt(100) > 80) {
                    doExplosion(center, 2f);
                }
                for(Direction x : Plane.HORIZONTAL) {
                    for(Direction y : Plane.HORIZONTAL) {
                        if(GTValues.RNG.nextInt(100) > 80) {
                            doExplosion(center.relative(x).relative(y), 2f);
                        }
                    }
                }
            } else {
                this.setHasNoWater(!hasDrainedWater);
            }
        } else {
            if(getCurrentTemperature() < 100) {
                setSteamGenerated(0);
            }
            this.setHasNoWater(false);
        }
        updateSteamSubscription();
    }

    @Nullable
    public static GTRecipe recipeModifier(MetaMachine machine, @NotNull GTRecipe recipe) {
        if(machine instanceof AdvancedLargeBoilerMachine boiler) {
            GTRecipe copied = recipe.copy();
            if(boiler.getThrottle() < 100) {
                copied.duration = recipe.duration * 100 / boiler.getThrottle();
            }
            copied.duration = copied.duration / boiler.fuelUsage;
            return copied;
        } else {
            return null;
        }
    }

    private void setCurrentTemperature(int temperature) {
        ((LargeBoilerMachineAccessor) this).setCurrentTemperature(temperature);
    }

    private long getSteamGenerated() {
        return ((LargeBoilerMachineAccessor) this).getSteamGenerated();
    }

    private void setSteamGenerated(long temperature) {
        ((LargeBoilerMachineAccessor) this).setSteamGenerated(temperature);
    }

    private void setHasNoWater(boolean water) {
        ((LargeBoilerMachineAccessor) this).setHasNoWater(water);
    }
}
