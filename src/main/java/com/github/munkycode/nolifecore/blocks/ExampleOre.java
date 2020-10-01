package com.github.munkycode.nolifecore.blocks;


import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class ExampleOre extends Block {

    public ExampleOre(){
        super(AbstractBlock.Properties.create(Material.ROCK)
                .hardnessAndResistance(3.0f, 4.0f)
                .sound(SoundType.STONE)
                .harvestLevel(2)
                .harvestTool(ToolType.PICKAXE));
    }
}
