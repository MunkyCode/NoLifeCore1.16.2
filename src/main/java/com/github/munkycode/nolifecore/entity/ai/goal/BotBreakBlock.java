package com.github.munkycode.nolifecore.entity.ai.goal;

import com.github.munkycode.nolifecore.entity.BotEntity;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class BotBreakBlock extends Goal {
    private final BotEntity bot;
    private int breakingTime;

    public BotBreakBlock(BotEntity bot){
        this.bot = bot;
        breakingTime = 0;
    }

    @Override
    public boolean shouldExecute() {
        if(this.bot.breakBlock == null) return false;
        else if(!bot.foundBlock) return false;
        else return bot.breakBlock.withinDistance(this.bot.getPositionVec(), 7.0D);
    }

    public void startExecuting(){
        this.breakingTime = 0;
    }

    public void tick(){
        World world = this.bot.world;
        BlockPos blockpos1 = this.bot.breakBlock;
        Random random = this.bot.getRNG();
        if(blockpos1 != null && this.bot.foundBlock) {
            if (this.breakingTime > 0 && !world.isRemote) {
                ((ServerWorld) world).spawnParticle(new ItemParticleData(ParticleTypes.ITEM, new ItemStack(Items.EGG)), (double) blockpos1.getX() + 0.5D, (double) blockpos1.getY() + 0.7D, (double) blockpos1.getZ() + 0.5D, 3, ((double) random.nextFloat() - 0.5D) * 0.08D, ((double) random.nextFloat() - 0.5D) * 0.08D, ((double) random.nextFloat() - 0.5D) * 0.08D, (double) 0.15F);
            }
            if(this.breakingTime % 6 == 0){
                world.playSound(null, blockpos1, SoundEvents.BLOCK_WOOD_HIT, SoundCategory.BLOCKS, 0.5F, 0.9F + random.nextFloat() * 0.2F);
            }
            if(this.breakingTime > 60){
                Item i = world.getBlockState(blockpos1).getBlock().asItem();
                world.destroyBlock(blockpos1, true, null, 1);
                world.playSound(null, blockpos1, SoundEvents.BLOCK_WOOD_BREAK, SoundCategory.BLOCKS, 0.5f, 0.9f + random.nextFloat() * 0.2f);
                for(ItemEntity item :world.getEntitiesWithinAABB(ItemEntity.class, this.bot.getBoundingBox().grow(3,3,3))){
                    //TODO: Need to abstract out that Items.OAK_LOG for more general use
                    if (!item.removed && !item.getItem().isEmpty() && !item.cannotPickup() && item.getItem().getItem() == i) {
                        boolean temp = this.bot.inventory.addItemStackToInventory(item.getItem());
                        //System.out.println(temp?"treu":"fales");
                    }
                }

            }
            this.breakingTime++;
        }
    }


}
