package net.tadditions.mod.client.renderers;

import net.tadditions.mod.client.model.DematModel;
import net.tadditions.mod.client.model.ImpulseKeyModel;
import net.tadditions.mod.items.CoreKeyItem;
import net.tadditions.mod.items.SubsysItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class DematRenderer extends GeoItemRenderer<SubsysItem> {
    public DematRenderer() {
        super(new DematModel());
    }

}
