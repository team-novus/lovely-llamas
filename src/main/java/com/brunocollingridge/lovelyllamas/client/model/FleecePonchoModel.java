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
public class FleecePonchoModel<T extends LivingEntity> extends BipedModel<T> {
    private static final Map<Float, FleecePonchoModel<?>> MODEL_CACHE = new HashMap<>();
    private final ModelRenderer poncho;

    /*
    Have to split model into both arms and body - arms dont animate rn
     */
    public FleecePonchoModel(float modelSize) {
        super(modelSize, 0.0F, 64, 32);
        poncho = new ModelRenderer(this);
        this.bipedBody.addChild(poncho);
        poncho.setRotationPoint(0.0F, 0.0F, 0.0F);
        poncho.setTextureOffset(24, 0).addBox(-8.0F, 0.0F, -2.0F, 16.0F, 12.0F, 4.0F, 0.75F, false);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.poncho.copyModelAngles(this.bipedBody);
        this.poncho.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
    }

    @Nonnull
    @Override
    protected Iterable<ModelRenderer> getBodyParts() {
        return ImmutableList.of(this.poncho);
    }

    @SuppressWarnings("unchecked")
    public static <A extends BipedModel<?>> A get(float modelSize) {
        return (A) MODEL_CACHE.computeIfAbsent(modelSize, FleecePonchoModel::new);
    }
}