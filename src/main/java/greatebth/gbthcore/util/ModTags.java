package greatebth.gbthcore.util;

import greatebth.gbthcore.GBTHCore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> PEBBLES = tag("pebbles");
        public static final TagKey<Block> FIRE_STARTER = tag("fire_starter");

        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(GBTHCore.MOD_ID, name));
        }
    }

    public static class Items {

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(GBTHCore.MOD_ID, name));
        }
    }
}
