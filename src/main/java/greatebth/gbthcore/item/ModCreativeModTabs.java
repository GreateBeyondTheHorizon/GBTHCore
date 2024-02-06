package greatebth.gbthcore.item;

import greatebth.gbthcore.GBTHCore;
import greatebth.gbthcore.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GBTHCore.MOD_ID);

    public static final RegistryObject<CreativeModeTab> GBTHCore_TAB = CREATIVE_MODE_TABS.register("gbthcore_tab",
            () -> CreativeModeTab.builder()
                    .icon(()-> new ItemStack(ModItems.FIRE_STICK.get()))
                    .title(Component.translatable("creativetab.gbthcore_tab"))
                    .displayItems((parmeters, output)-> {

                        output.accept(ModItems.FIRE_STICK.get());


                    })
                    .build());


    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
