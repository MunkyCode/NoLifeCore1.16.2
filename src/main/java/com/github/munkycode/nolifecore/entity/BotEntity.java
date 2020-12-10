package com.github.munkycode.nolifecore.entity;

import com.github.munkycode.nolifecore.entity.ai.goal.BotBreakBlock;
import com.github.munkycode.nolifecore.entity.ai.goal.FindWoodGoal;
import com.github.munkycode.nolifecore.entity.ai.goal.RandomWalkingGoal2;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.entity.ai.attributes.Attributes;

import javax.annotation.Nullable;

public class BotEntity extends MonsterEntity {

    public BlockPos breakBlock;
    public boolean foundBlock;

    public final BotInventory inventory = new BotInventory(this);

    public BotEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        this.setCanPickUpLoot(true);
        breakBlock = null;
        foundBlock = false;
    }


    public static AttributeModifierMap.MutableAttribute setCustomAttributes(){
       return MonsterEntity.func_234295_eP_().createMutableAttribute(Attributes.FOLLOW_RANGE, 35.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, (double)0.5F);
        // return MobEntity.registerAttributes().createMutableAttribute(Attributes.MAX_HEALTH, 20.0f)
       //         .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.23f)
         //       .createMutableAttribute(Attributes.FOLLOW_RANGE, 35.0d);
    }

   @Override
    protected void registerGoals() {
        //super.registerGoals();
        //this.goalSelector.addGoal(1, new LookRandomlyGoal(this));
        //this.goalSelector.addGoal(1, new RandomWalkingGoal2(this, 1.0D));
        //this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D, 0.0F));

        this.goalSelector.addGoal(0, new FindWoodGoal(this, 1));
        this.goalSelector.addGoal(0, new BotBreakBlock(this));
        this.goalSelector.addGoal(9, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(9, new RandomWalkingGoal2(this, 1.0));
    }

    /**
     * Creates and drops the provided item. Depending on the dropAround, it will drop teh item around the player, instead
     * of dropping the item from where the player is pointing at. Likewise, if traceItem is true, the dropped item entity
     * will have the thrower set as the player.
     */
    @Nullable
    public ItemEntity dropItem(ItemStack droppedItem, boolean dropAround, boolean traceItem) {
        if (droppedItem.isEmpty()) {
            return null;
        } else {
            if (this.world.isRemote) {
                this.swingArm(Hand.MAIN_HAND);
            }

            double d0 = this.getPosYEye() - (double)0.3F;
            ItemEntity itementity = new ItemEntity(this.world, this.getPosX(), d0, this.getPosZ(), droppedItem);
            itementity.setPickupDelay(40);
            if (traceItem) {
                itementity.setThrowerId(this.getUniqueID());
            }

            if (dropAround) {
                float f = this.rand.nextFloat() * 0.5F;
                float f1 = this.rand.nextFloat() * ((float)Math.PI * 2F);
                itementity.setMotion((double)(-MathHelper.sin(f1) * f), (double)0.2F, (double)(MathHelper.cos(f1) * f));
            } else {
                float f7 = 0.3F;
                float f8 = MathHelper.sin(this.rotationPitch * ((float)Math.PI / 180F));
                float f2 = MathHelper.cos(this.rotationPitch * ((float)Math.PI / 180F));
                float f3 = MathHelper.sin(this.rotationYaw * ((float)Math.PI / 180F));
                float f4 = MathHelper.cos(this.rotationYaw * ((float)Math.PI / 180F));
                float f5 = this.rand.nextFloat() * ((float)Math.PI * 2F);
                float f6 = 0.02F * this.rand.nextFloat();
                itementity.setMotion((double)(-f3 * f2 * 0.3F) + Math.cos((double)f5) * (double)f6, (double)(-f8 * 0.3F + 0.1F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F), (double)(f4 * f2 * 0.3F) + Math.sin((double)f5) * (double)f6);
            }

            return itementity;
        }
    }
    @Override
    public boolean canPickUpItem(ItemStack itemstackIn) {
        return true;
    }

    public void livingTick() {
        this.world.getProfiler().startSection("looting");
        for(ItemEntity itementity : this.world.getEntitiesWithinAABB(ItemEntity.class, this.getBoundingBox().grow(1.0D, 0.0D, 1.0D))) {
            if (!itementity.removed && !itementity.getItem().isEmpty() && !itementity.cannotPickup()) {
                boolean temp = this.inventory.addItemStackToInventory(itementity.getItem());
                System.out.println(temp?"treu":"fales");
            }
        }


        this.world.getProfiler().endSection();
        super.livingTick();
    }
}
