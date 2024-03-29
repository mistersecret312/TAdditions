package net.tadditions.mod.client;

import com.google.common.collect.Maps;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.tadditions.mod.QolMod;
import net.tadditions.mod.blocks.ModBlocks;
import net.tadditions.mod.client.model.ToyotaInteriorDoor;
import net.tadditions.mod.client.renderers.*;
import net.tadditions.mod.container.MContainers;
import net.tadditions.mod.helper.IMDoorType;
import net.tadditions.mod.screens.AdvQuantiscopeWeldScreen;
import net.tadditions.mod.screens.misc.AdvQuantiscopePage;
import net.tadditions.mod.screens.misc.AdvQuantiscopeScreenType;
import net.tadditions.mod.tileentity.ModTileEntitys;
import net.tadditions.mod.world.MDimensions;
import net.tadditions.mod.world.MarsSkyProperty;
import net.tadditions.mod.world.TagreaSkyProperty;
import net.tardis.mod.client.TClientRegistry;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

import java.util.EnumMap;

@SuppressWarnings("deprecation")
@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = QolMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MClientRegistry extends TClientRegistry {


    public static final EnumMap<AdvQuantiscopeScreenType, AdvQuantiscopePage> ADVQUANTISCOPE_SCREENS = Maps.newEnumMap(AdvQuantiscopeScreenType.class);


    @SubscribeEvent
    public static void register(FMLClientSetupEvent event) {
        registerTileRenderers();
        registerScreens();
        registerInteriorDoorRenderers();
        DimensionRenderInfo.field_239208_a_.put(MDimensions.MARS_SKY_PROPERTY_KEY, new MarsSkyProperty());
        DimensionRenderInfo.field_239208_a_.put(MDimensions.TAGREA_SKY_PROPERTY_KEY, new TagreaSkyProperty());
        event.enqueueWork(() -> {
            //Block Render Layers
            RenderTypeLookup.setRenderLayer(ModBlocks.electromagnetic_solenoid_container.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.filled_electromagnetic_solenoid_container.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.exterior_toyota_police_box.get(), RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(ModBlocks.sanguine_door.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.sanguine_trapdoor.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.ancient_keyholder.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.ancient_door.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.lightbox.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.zero_point_field_broken.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.zero_point_field_normal.get(), RenderType.getCutout());
        });
    }


    private static void registerTileRenderers() {
        ClientRegistry.bindTileEntityRenderer(ModTileEntitys.EXTERIOR_TOYOTA_POLICE_BOX.get(), ToyotaExteriorRenderer::new);
        ClientRegistry.bindTileEntityRenderer(ModTileEntitys.WPH.get(), WeaponHolderRenderer::new);
        ClientRegistry.bindTileEntityRenderer(ModTileEntitys.ZPFChamber.get(), ZPFChamberRenderer::new);
        ClientRegistry.bindTileEntityRenderer(ModTileEntitys.ZPFCBroken.get(), ZPFChamberBrokenRenderer::new);
        ClientRegistry.bindTileEntityRenderer(ModTileEntitys.SolenoidFilled.get(), SolenoidFilledRenderer::new);
    }

    private static void registerInteriorDoorRenderers() {
        IMDoorType.EnumDoorType.TOYOTA.setInteriorDoorModel(new ToyotaInteriorDoor());
    }

    public static void registerScreens() {
        ScreenManager.registerFactory(MContainers.ADVQUANTISCOPE_WELD.get(), AdvQuantiscopeWeldScreen::new);
    }

}




