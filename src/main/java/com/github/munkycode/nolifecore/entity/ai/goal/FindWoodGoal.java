package com.github.munkycode.nolifecore.entity.ai.goal;

import com.github.munkycode.nolifecore.entity.BotEntity;
import net.minecraft.block.BambooBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;

public class FindWoodGoal extends Goal {

    private final BotEntity bot;
    private final double speed;
    private double x;
    private double y;
    private double z;
    private boolean foundTree;
    private BlockPos tree;
    private BlockPos lastTree;
    private boolean justMinedBlock;

    public FindWoodGoal(BotEntity bot, double speed) {
        this.bot = bot;
        this.speed = speed;
        foundTree = false;
        justMinedBlock = false;
    }

    @Override
    public boolean shouldExecute() {
        if(this.bot.inventory.itemCount(Items.OAK_LOG) > Integer.MAX_VALUE - 2) {
            //System.out.println(this.bot.inventory.itemCount(Items.OAK_LOG));
            this.bot.foundBlock = false;
            return false;
        }
        if(foundTree) return true;
        BlockPos blockpos = null;
        double distTo = Float.MAX_VALUE;
        double dist = 10.0d;
        if(justMinedBlock){
            if(bot.world.getBlockState(lastTree.add(0,1,0)).getBlock() == Blocks.OAK_LOG){
                blockpos = lastTree.add(0,1,0);
            }
            else{
                blockpos = null;
                justMinedBlock = false;
                lastTree = null;
            }

        }
        else {
            for (BlockPos blockpos1 : BlockPos.getAllInBoxMutable(MathHelper.floor(this.bot.getPosX() - dist), MathHelper.floor(this.bot.getPosY() - dist), MathHelper.floor(this.bot.getPosZ() - dist), MathHelper.floor(this.bot.getPosX() + dist), MathHelper.floor(this.bot.getPosY() + dist), MathHelper.floor(this.bot.getPosZ() + dist))) {
                if (bot.world.getBlockState(blockpos1).getBlock() == Blocks.OAK_LOG) {
                    blockpos = blockpos1;
                    break;
                    //System.out.println("foundLog");
//                       if(blockpos1.distanceSq(bot.getPosition()) < distTo){
//                           System.out.println("log closer");
//                           blockpos = blockpos1;
//                           distTo = blockpos1.distanceSq(bot.getPosition());
//                       }
                }
            }
        }

        if (blockpos != null) {
            tree = blockpos;
            this.x = blockpos.getX();
            this.y = blockpos.getY();
            this.z = blockpos.getZ();
            foundTree = true;
            this.bot.foundBlock = true;
            this.bot.breakBlock = tree;
            return true;
        }
       else{
           return false;
       }
    }

    public void startExecuting() {
        this.bot.getNavigator().tryMoveToXYZ(this.x, this.y, this.z, this.speed);

    }

    public boolean shouldContinueExecuting() {
        if(this.tree == null) return false;
        else if(this.tree.withinDistance(this.bot.getPositionVec(), 2.0D)) {
            this.bot.getNavigator().clearPath();
            return false;
        }
        return !this.bot.getNavigator().noPath() && !this.bot.isBeingRidden();
    }

    @Override
    public void resetTask() {
        bot.getNavigator().clearPath();
        if(bot.world.getBlockState(tree).getBlock() != Blocks.OAK_LOG){
            lastTree = tree;
            tree = null;
            foundTree = false;
            justMinedBlock = true;
            this.bot.foundBlock = false;
        }
        super.resetTask();
    }
}
