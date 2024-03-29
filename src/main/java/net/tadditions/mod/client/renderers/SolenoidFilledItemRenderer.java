package net.tadditions.mod.client.renderers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.tadditions.mod.client.model.SolenoidFilledItemModel;
import net.tadditions.mod.client.model.SolenoidFilledModel;
import net.tadditions.mod.items.AnimatedBlockItem;
import net.tadditions.mod.tileentity.SolenoidFilledTile;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

import javax.annotation.Nullable;

public class SolenoidFilledItemRenderer extends GeoItemRenderer<AnimatedBlockItem> {
    public SolenoidFilledItemRenderer() {
        super(new SolenoidFilledItemModel());
    }

    @Override
    public RenderType getRenderType(AnimatedBlockItem animatable, float partialTicks, MatrixStack stack, @Nullable IRenderTypeBuffer renderTypeBuffer, @Nullable IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.getEntityTranslucent(textureLocation);
    }
}
