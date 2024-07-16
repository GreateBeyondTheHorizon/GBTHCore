package com.gbth.gbthcore.mixin;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.blockentity.AltarBlockEntity;
import com.aetherteam.aether.item.AetherItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AetherBlocks.class)
public class AetherBlocksMixin {

    @Shadow @Final public static RegistryObject<Block> AMBROSIUM_BLOCK;

    @Inject(method = "registerFuels", at = @At("RETURN"), remap = false)
    private static void gbthcore$registerFuels(CallbackInfo ci) {
        AltarBlockEntity.removeItemEnchantingTime(AetherItems.AMBROSIUM_SHARD.get());
        AltarBlockEntity.removeItemEnchantingTime(AMBROSIUM_BLOCK.get());

        AltarBlockEntity.addItemEnchantingTime(gbthcore$getItem("gtceu", "ambrosium_gem"), 12 * 20);
        AltarBlockEntity.addItemEnchantingTime(gbthcore$getItem("gtceu", "ambrosium_block"), 12 * 20 * 9);
    }

    @Unique
    private static Item gbthcore$getItem(String namespace, String path) {
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation(namespace, path));
    }
}
