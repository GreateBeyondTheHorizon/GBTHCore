package greatebth.gbthcore.datagen;

import greatebth.gbthcore.GBTHCore;
import greatebth.gbthcore.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output,  ExistingFileHelper exFileHelper) {
        super(output, GBTHCore.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.PEBBLE_BLOCK);
       // blockWithItem(ModBlocks.SAPPHIRE_BLOCK);
       // blockWithItem(ModBlocks.SAPPHIRE_ORE);
    }




    private void blockWithItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }


}
