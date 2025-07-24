package com.gbth.gbthcore.mixin;

import com.gregtechceu.gtceu.common.item.ProspectorScannerBehavior;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ProspectorScannerBehavior.class)
public interface ProspectorScannerBehaviorAccessor {

    @Accessor(remap = false) public int getRadius();

    @Accessor(remap = false) public long getCost();
}
