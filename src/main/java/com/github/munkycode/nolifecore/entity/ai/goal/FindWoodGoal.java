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

    public FindWoodGoal(BotEntity bot, double speed) {
        this.bot = bot;
        this.speed = speed;
        foundTree = false;
    }

    @Override
    public boolean shouldExecute() {
        if(this.bot.inventory.hasItem(Items.OAK_LOG)) {
            System.out.println("has Log");
            return false;
        }
        if(foundTree) return true;
        BlockPos blockpos = null;
        double dist = 10.0d;
        for(BlockPos blockpos1 : BlockPos.getAllInBoxMutable(MathHelper.floor(this.bot.getPosX() - dist), MathHelper.floor(this.bot.getPosY() - dist), MathHelper.floor(this.bot.getPosZ() - dist), MathHelper.floor(this.bot.getPosX() + dist), MathHelper.floor(this.bot.getPosY() + dist), MathHelper.floor(this.bot.getPosZ() + dist))) {
           if (bot.world.getBlockState(blockpos1).getBlock() == Blocks.OAK_LOG) {
                blockpos = blockpos1;
                break;
            }
       }

        if (blockpos != null) {
            tree = blockpos;
            this.x = blockpos.getX();
            this.y = blockpos.getY();
            this.z = blockpos.getZ();
            System.out.println("true block pos != null");
            foundTree = true;
            return true;
        }
       else{
            System.out.println("false");
           return false;
       }
    }

    public void startExecuting() {
        this.bot.getNavigator().tryMoveToXYZ(this.x, this.y, this.z, this.speed);

    }

    public boolean shouldContinueExecuting() {
        return !this.bot.getNavigator().noPath() && !this.bot.isBeingRidden();
    }

    @Override
    public void resetTask() {
        bot.getNavigator().clearPath();
        if(bot.world.getBlockState(tree).getBlock() != Blocks.OAK_LOG) foundTree = false;
        super.resetTask();
    }
}
