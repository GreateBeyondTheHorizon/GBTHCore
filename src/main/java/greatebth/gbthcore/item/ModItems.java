package greatebth.gbthcore.item;

import greatebth.gbthcore.GBTHCore;
import greatebth.gbthcore.item.custom.FireStick;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, GBTHCore.MOD_ID);

    public static final RegistryObject<Item> FIRE_STICK = ITEMS.register("fire_stick",
            () -> new FireStick(new Item.Properties()));
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
