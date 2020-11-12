package com.github.munkycode.nolifecore.client.entity.model;


import com.github.munkycode.nolifecore.entity.BotEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class BotModel<T extends BotEntity> extends PlayerModel<T> {
    private final ModelRenderer head;
    //private final ModelRenderer headwear;
    private final ModelRenderer body;
    private final ModelRenderer right_arm;
    private final ModelRenderer left_arm;
    private final ModelRenderer right_leg;
    private final ModelRenderer left_leg;

    public BotModel() {
        super(0.0f, false);
        textureWidth = 64;
        textureHeight = 64;

        head = new ModelRenderer(this, 0, 0);
        head.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

        //headwear = new ModelRenderer(this, 32, 0);
        //headwear.setRotationPoint(0.0F, 0.0F, 0.0F);
        //headwear.setTextureOffset(32, 0).addBox(-4.0F, -7.75F, -4.0F, 8.0F, 8.0F, 8.0F, 0.25F, false);

        body = new ModelRenderer(this, 16, 16);
        this.body.addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0f, false);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);

        right_arm = new ModelRenderer(this, 40, 16);
        this.right_arm.addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0f, false);
        this.right_arm.setRotationPoint(-5.0F, 2.0F, 0.0F);

        left_arm = new ModelRenderer(this, 32, 48);
        left_arm.addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0f, false);
        this.left_arm.setRotationPoint(5.0F, 2.0F, 0.0F);

        right_leg = new ModelRenderer(this, 0, 16);
        this.right_leg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0f, false);
        this.right_leg.setRotationPoint(-1.9F, 12.0F, 0.0F);


        left_leg = new ModelRenderer(this, 16, 48);
        this.left_leg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0f, false);
        this.left_leg.setRotationPoint(1.9F, 12.0F, 0.0F);

    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        boolean flag = entityIn.getTicksElytraFlying() > 4;
        boolean flag1 = entityIn.isActualySwimming();
        this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        if (flag) {
            this.head.rotateAngleX = (-(float)Math.PI / 4F);
        } else if (this.swimAnimation > 0.0F) {
            if (flag1) {
                this.head.rotateAngleX = this.rotLerpRad(this.swimAnimation, this.head.rotateAngleX, (-(float)Math.PI / 4F));
            } else {
                this.head.rotateAngleX = this.rotLerpRad(this.swimAnimation, this.head.rotateAngleX, headPitch * ((float)Math.PI / 180F));
            }
        } else {
            this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        }

        this.body.rotateAngleY = 0.0F;
        this.right_arm.rotationPointZ = 0.0F;
        this.right_arm.rotationPointX = -5.0F;
        this.left_arm.rotationPointZ = 0.0F;
        this.left_arm.rotationPointX = 5.0F;
        float f = 1.0F;
        if (flag) {
            f = (float)entityIn.getMotion().lengthSquared();
            f = f / 0.2F;
            f = f * f * f;
        }

        if (f < 1.0F) {
            f = 1.0F;
        }

        this.right_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
        this.left_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
        this.right_arm.rotateAngleZ = 0.0F;
        this.left_arm.rotateAngleZ = 0.0F;
        this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
        this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / f;
        this.right_leg.rotateAngleY = 0.0F;
        this.left_leg.rotateAngleY = 0.0F;
        this.right_leg.rotateAngleZ = 0.0F;
        this.left_leg.rotateAngleZ = 0.0F;
        if (this.isSitting) {
            this.right_arm.rotateAngleX += (-(float) Math.PI / 5F);
            this.left_arm.rotateAngleX += (-(float) Math.PI / 5F);
            this.right_leg.rotateAngleX = -1.4137167F;
            this.right_leg.rotateAngleY = ((float) Math.PI / 10F);
            this.right_leg.rotateAngleZ = 0.07853982F;
            this.left_leg.rotateAngleX = -1.4137167F;
            this.left_leg.rotateAngleY = (-(float) Math.PI / 10F);
            this.left_leg.rotateAngleZ = -0.07853982F;
        }
    }

    //@Override
    //public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){}

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        //headwear.render(matrixStack, buffer, packedLight, packedOverlay);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        right_arm.render(matrixStack, buffer, packedLight, packedOverlay);
        left_arm.render(matrixStack, buffer, packedLight, packedOverlay);
        right_leg.render(matrixStack, buffer, packedLight, packedOverlay);
        left_leg.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
