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
public class FleeceChulloModel<T extends LivingEntity> extends BipedModel<T> {
    private static final Map<Float, FleeceChulloModel<?>> MODEL_CACHE = new HashMap<>();
    private final ModelRenderer chullo;

    public FleeceChulloModel(float modelSize) {
        super(modelSize, 0.0F, 64, 32);
        chullo = new ModelRenderer(this);
        this.bipedHead.addChild(chullo);
        chullo.setRotationPoint(0.0F, 0.0F, 0.0F);
        chullo.setTextureOffset(32, 16).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.5F, false);
        chullo.setTextureOffset(28, 18).addBox(-1.5F, -12.0F, -1.0F, 3.0F, 3.0F, 3.0F, 0.5F, false);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.chullo.copyModelAngles(this.bipedHead);
        this.chullo.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
    }

    @Nonnull
    @Override
    protected Iterable<ModelRenderer> getHeadParts() {
        return ImmutableList.of(this.chullo);
    }

    @SuppressWarnings("unchecked")
    public static <A extends BipedModel<?>> A get(float modelSize) {
        return (A) MODEL_CACHE.computeIfAbsent(modelSize, FleeceChulloModel::new);
    }
}