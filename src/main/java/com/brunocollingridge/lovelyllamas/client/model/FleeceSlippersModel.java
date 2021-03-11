package com.brunocollingridge.lovelyllamas.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class FleeceSlippersModel<T extends LivingEntity> extends BipedModel<T> {
    private static final Map<Float, FleeceSlippersModel<?>> MODEL_CACHE = new HashMap<>();
    private final ModelRenderer leftslipper;
    private final ModelRenderer rightslipper;

    public FleeceSlippersModel(float modelSize) {
        super(modelSize, 0.0F, 64, 32);
        leftslipper = new ModelRenderer(this);
        leftslipper.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedLeftLeg.addChild(leftslipper);
        leftslipper.setTextureOffset(0, 24).addBox(-2.0F, 10.0F, -4.0F, 4.0F, 2.0F, 6.0F, 0.5F, true);

        rightslipper = new ModelRenderer(this);
        rightslipper.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedRightLeg.addChild(rightslipper);
        rightslipper.setTextureOffset(0, 24).addBox(-2.0F, 10.0F, -4.0F, 4.0F, 2.0F, 6.0F, 0.5F, false);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.leftslipper.copyModelAngles(this.bipedLeftLeg);
        this.rightslipper.copyModelAngles(this.bipedRightLeg);
        this.leftslipper.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.rightslipper.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
    }

    @SuppressWarnings("unchecked")
    public static <A extends BipedModel<?>> A get(float modelSize) {
        return (A) MODEL_CACHE.computeIfAbsent(modelSize, FleeceSlippersModel::new);
    }
}