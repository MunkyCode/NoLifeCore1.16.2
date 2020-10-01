package com.github.munkycode.nolifecore.init;

import com.github.munkycode.nolifecore.NoLifeCore;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;



public final class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, NoLifeCore.MODID);

    public static final RegistryObject<Block> EXAMPLE_ORE = BLOCKS.register("example_ore", ()-> new Block(AbstractBlock.Properties.create(Material.ROCK)));


}
