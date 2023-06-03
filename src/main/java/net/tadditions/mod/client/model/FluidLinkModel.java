package net.tadditions.mod.client.model;

import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.tadditions.mod.QolMod;
import net.tadditions.mod.items.CoreKeyItem;
import net.tadditions.mod.items.SubsysItem;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import java.util.Iterator;
import java.util.List;

public class FluidLinkModel extends AnimatedGeoModel<SubsysItem> {
    private static final ResourceLocation modelResource = new ResourceLocation(QolMod.MOD_ID, "geo/fluid_link.geo.json");
    private static final ResourceLocation textureResource = new ResourceLocation(QolMod.MOD_ID, "textures/itemanim/fluidlink/fluid_link_frame_1.png");
    private static final ResourceLocation animationResource = new ResourceLocation(QolMod.MOD_ID, "animations/fluid_link_animation.json");
    private static final List<ResourceLocation> animo = Lists.newArrayList(new ResourceLocation(QolMod.MOD_ID, "textures/itemanim/fluidlink/fluid_link_frame_1.png"), new ResourceLocation(QolMod.MOD_ID, "textures/itemanim/fluidlink/fluid_link_frame_2.png"), new ResourceLocation(QolMod.MOD_ID, "textures/itemanim/fluidlink/fluid_link_frame_3.png"),
            new ResourceLocation(QolMod.MOD_ID, "textures/itemanim/fluidlink/fluid_link_frame_4.png"),new ResourceLocation(QolMod.MOD_ID, "textures/itemanim/fluidlink/fluid_link_frame_5.png"),new ResourceLocation(QolMod.MOD_ID, "textures/itemanim/fluidlink/fluid_link_frame_6.png"),
            new ResourceLocation(QolMod.MOD_ID, "textures/itemanim/fluidlink/fluid_link_frame_7.png"),new ResourceLocation(QolMod.MOD_ID, "textures/itemanim/fluidlink/fluid_link_frame_8.png"),new ResourceLocation(QolMod.MOD_ID, "textures/itemanim/fluidlink/fluid_link_frame_9.png"),
            new ResourceLocation(QolMod.MOD_ID, "textures/itemanim/fluidlink/fluid_link_frame_10.png"),new ResourceLocation(QolMod.MOD_ID, "textures/itemanim/fluidlink/fluid_link_frame_11.png"),new ResourceLocation(QolMod.MOD_ID, "textures/itemanim/fluidlink/fluid_link_frame_12.png"),
            new ResourceLocation(QolMod.MOD_ID, "textures/itemanim/fluidlink/fluid_link_frame_13.png"),new ResourceLocation(QolMod.MOD_ID, "textures/itemanim/fluidlink/fluid_link_frame_14.png"),new ResourceLocation(QolMod.MOD_ID, "textures/itemanim/fluidlink/fluid_link_frame_15.png"),
            new ResourceLocation(QolMod.MOD_ID, "textures/itemanim/fluidlink/fluid_link_frame_16.png"),new ResourceLocation(QolMod.MOD_ID, "textures/itemanim/fluidlink/fluid_link_frame_17.png"),new ResourceLocation(QolMod.MOD_ID, "textures/itemanim/fluidlink/fluid_link_frame_18.png"),
            new ResourceLocation(QolMod.MOD_ID, "textures/itemanim/fluidlink/fluid_link_frame_19.png"),new ResourceLocation(QolMod.MOD_ID, "textures/itemanim/fluidlink/fluid_link_frame_20.png"));
    private int tickCount = 0;
    private int currentIndex = 0;

    @Override
    public ResourceLocation getModelLocation(SubsysItem object) {
        return modelResource;
    }

    @Override
    public ResourceLocation getTextureLocation(SubsysItem object) {
        return animateTexture(animo);
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SubsysItem object) {
        return animationResource;
    }

    public ResourceLocation animateTexture(List<ResourceLocation> list){
        tickCount++;
        if (tickCount >= 10) {
            tickCount = 0;
            currentIndex++;
            if (currentIndex >= list.size()) {
                currentIndex = 0;
            }
        }

        return list.get(currentIndex);
    }
}
