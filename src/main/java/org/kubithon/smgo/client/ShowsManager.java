package org.kubithon.smgo.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.kubithon.smgo.common.Smgo;
import org.kubithon.smgo.proxy.ClientProxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

public class ShowsManager {
    private List<Show> shows = Collections.synchronizedList(new ArrayList<Show>());

    public void startShow(Show show) {
        this.shows.add(show);

        Minecraft.getMinecraft().player.playSound(ClientProxy.soundEvent, 1.0f, 1.0f);
//        Minecraft.getMinecraft().getSoundHandler().playSound(new PositionedSoundRecord(new ResourceLocation("smgo:clicktrack", SoundCategory.MUSIC, 1.0f, 1.0f, false, 0, ISound.AttenuationType.LINEAR, (float)packetIn.getX(), (float)packetIn.getY(), (float)packetIn.getZ()));
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void render(RenderWorldLastEvent event) {
        synchronized (this.shows) {
            for (Iterator<Show> iterator = this.shows.iterator(); iterator.hasNext();) {
                Show show = iterator.next();

                show.render(event.getPartialTicks());
            }
        }
    }

    @SubscribeEvent
    public void tick(ClientTickEvent event) {
        if (event.phase == Phase.START) {
            return;
        }
        synchronized (this.shows) {
            for (Iterator<Show> iterator = this.shows.iterator(); iterator.hasNext();) {
                Show show = iterator.next();
                show.tick();

                if (show.isDone()) {
                    iterator.remove();
                }
            }
        }
    }
}
