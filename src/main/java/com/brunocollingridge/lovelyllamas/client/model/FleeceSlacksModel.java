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
public class FleeceSlacksModel<T extends LivingEntity> extends BipedModel<T> {
    private static final Map<Float, FleeceSlacksModel<?>> MODEL_CACHE = new HashMap<>();
    private final ModelRenderer rightpantaloon;
    private final ModelRenderer leftpantaloon;
    private final ModelRenderer crotch;

    public FleeceSlacksModel(float modelSize) {
        super(modelSize, 0.0F, 64, 32);
        rightpantaloon = new ModelRenderer(this);
        rightpantaloon.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedRightLeg.addChild(rightpantaloon);
        rightpantaloon.setTextureOffset(0, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, 0.35F, false);

        leftpantaloon = new ModelRenderer(this);
        leftpantaloon.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedLeftLeg.addChild(leftpantaloon);
        leftpantaloon.setTextureOffset(0, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, 0.35F, false);

        crotch = new ModelRenderer(this);
        crotch.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedBody.addChild(crotch);
        crotch.setTextureOffset(0, 14).addBox(-4.0F, 11.4F, -2.0F, 8.0F, 3.0F, 4.0F, 0.35F, false);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.rightpantaloon.copyModelAngles(this.bipedRightLeg);
        this.leftpantaloon.copyModelAngles(this.bipedLeftLeg);
        this.crotch.copyModelAngles(this.bipedBody);
        this.rightpantaloon.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.leftpantaloon.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.crotch.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
    }

    @SuppressWarnings("unchecked")
    public static <A extends BipedModel<?>> A get(float modelSize) {
        return (A) MODEL_CACHE.computeIfAbsent(modelSize, FleeceSlacksModel::new);
    }
}