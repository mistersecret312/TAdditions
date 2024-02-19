package net.tadditions.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.concurrent.TickDelayedTask;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import net.tadditions.mod.cap.MCapabilities;
import net.tadditions.mod.helper.IConsoleHelp;
import net.tadditions.mod.items.ModItems;
import net.tardis.mod.cap.Capabilities;
import net.tardis.mod.controls.HandbrakeControl;
import net.tardis.mod.controls.SonicPortControl;
import net.tardis.mod.controls.ThrottleControl;
import net.tardis.mod.helper.WorldHelper;
import net.tardis.mod.items.TItems;
import net.tardis.mod.misc.SpaceTimeCoord;
import net.tardis.mod.schematics.Schematic;
import net.tardis.mod.subsystem.StabilizerSubsystem;
import net.tardis.mod.tileentities.ConsoleTile;
import org.spongepowered.asm.mixin.Mixin;

import javax.swing.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Mixin(SonicPortControl.class)
public class SPMixin {
    public boolean onRightClicked(ConsoleTile console, PlayerEntity player) {
        if(!console.getWorld().isRemote()) {
            if(player.getHeldItemMainhand().getItem() == TItems.SONIC.get() && console.getSonicItem().isEmpty() || player.getHeldItemMainhand().getItem() == ModItems.BOOS_UPGRADE.get() && console.getSonicItem().isEmpty()) {
                if(player.getHeldItemMainhand().getItem() == ModItems.BOOS_UPGRADE.get()){
                    player.getHeldItemMainhand().getCapability(MCapabilities.OPENER_CAPABILITY).ifPresent(cap -> {
                        if(!cap.getHandler().getStackInSlot(0).isItemEqual(ItemStack.EMPTY)){
                            cap.getHandler().getStackInSlot(0).getCapability(MCapabilities.CRYSTAL_CAPABILITY).ifPresent(cap1 -> {
                                if(cap1.getType() == 0) {
                                    if (!((IConsoleHelp) console).getAvailable().contains(cap1.getDimData()) && !cap1.getUsed()) {
                                        ((IConsoleHelp) console).addAvailable(cap1.getDimData());
                                        player.sendStatusMessage(new TranslationTextComponent("tadditions.dimension_added").appendSibling(new StringTextComponent(WorldHelper.formatDimName(cap1.getDimData())).mergeStyle(TextFormatting.LIGHT_PURPLE)), true);
                                        cap1.setUsed(true);
                                    } else player.sendStatusMessage(new TranslationTextComponent("tadditions.dimension_add_fail"), true);
                                } else if(cap1.getType() == 1){
                                    if(((IConsoleHelp) console).getAvailable().contains(cap1.getDimData()) && !cap1.getUsed() && !cap1.getCoords().equals(BlockPos.ZERO)) {
                                        cap1.setUsed(true);
                                        player.sendStatusMessage(new TranslationTextComponent("tadditions.coords_uploaded"), true);
                                        console.getWorld().getServer().enqueue(new TickDelayedTask(30, () -> {
                                            console.setDestination(new SpaceTimeCoord(cap1.getDimData(), cap1.getCoords()));
                                            console.getControl(ThrottleControl.class).ifPresent(throttle -> throttle.setAmount(1.0F));
                                            console.getControl(HandbrakeControl.class).ifPresent(handbrake -> handbrake.setFree(true));
                                            console.getSubsystem(StabilizerSubsystem.class).ifPresent(sys -> sys.setControlActivated(true));
                                            console.takeoff();
                                        }));
                                    } else if(!cap1.getUsed() && cap1.getCoords().equals(BlockPos.ZERO)) {
                                        player.sendStatusMessage(new TranslationTextComponent("tadditions.coords_downloaded"), true);
                                        cap1.setCoords(console.getDestinationPosition());
                                        cap1.setDimData(console.getDestinationDimension());
                                    }
                                }
                            });
                        }
                    });
                }
                console.setSonicItem(player.getHeldItemMainhand());
                player.setHeldItem(Hand.MAIN_HAND, ItemStack.EMPTY);
                console.getSonicItem().getCapability(Capabilities.SONIC_CAPABILITY).ifPresent(cap -> {
                    cap.setCharge(cap.getMaxCharge());
                    console.setArtron(console.getArtron()-(cap.getMaxCharge()-cap.getCharge()));
                    Set<Schematic> uniqueSchematics = new HashSet<>();
                    uniqueSchematics.addAll(cap.getSchematics());
                    for(Schematic s : uniqueSchematics) {
                        if (s.onConsumedByTARDIS(console, player))
                            cap.getSchematics().remove(s);
                    }
                });
            }
            else if(player.getHeldItemMainhand().isEmpty() && !console.getSonicItem().isEmpty()) {
                InventoryHelper.spawnItemStack(console.getWorld(), player.getPosX(), player.getPosY(), player.getPosZ(), console.getSonicItem());
                console.setSonicItem(ItemStack.EMPTY);
            }
        }
        return true;
    }
}
