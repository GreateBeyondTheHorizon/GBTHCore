package com.gbth.gbthcore.mixin;

import com.gregtechceu.gtceu.common.machine.multiblock.steam.LargeBoilerMachine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LargeBoilerMachine.class)
public interface LargeBoilerMachineAccessor {

    @Accessor void setCurrentTemperature(int temperature);

    @Accessor long getSteamGenerated();
    @Accessor void setSteamGenerated(long steamGenerated);


    @Accessor void setHasNoWater(boolean hasNoWater);
}
