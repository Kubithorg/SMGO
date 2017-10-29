package org.kubithon.smgo.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.kubithon.smgo.proxy.ClientProxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ShowsManager {
    private static final float MIN_UPDATE_TIME = 0.05F;

    private List<Show> shows    = Collections.synchronizedList(new ArrayList<Show>());
    private double     lastTime = -1;

    public void startShow(Show show) {
        this.shows.add(show);

        Minecraft.getMinecraft().player.playSound(ClientProxy.soundEvent, 1.0f, 1.0f);
        // Minecraft.getMinecraft().getSoundHandler().playSound(new
        // PositionedSoundRecord(new ResourceLocation("smgo:clicktrack",
        // SoundCategory.MUSIC, 1.0f, 1.0f, false, 0,
        // ISound.AttenuationType.LINEAR, (float)packetIn.getX(),
        // (float)packetIn.getY(), (float)packetIn.getZ()));
        show.tick(0);
    }

//    public void playMusic(SoundEvent location) {
//        this.currentMusic = PositionedSoundRecord.getMusicRecord(requestedMusicType.getMusicLocation());
//        this.mc.getSoundHandler().playSound(this.currentMusic);
//        this.timeUntilNextMusic = Integer.MAX_VALUE;
//    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void render(RenderWorldLastEvent event) {
        double time = System.currentTimeMillis();
        double tickDuration = (time - this.lastTime) / 50;
        if (this.lastTime < 0) {
            tickDuration = 0;
        }
        synchronized (this.shows) {
            if (tickDuration > MIN_UPDATE_TIME || tickDuration == 0) {
                for (Iterator<Show> iterator = this.shows.iterator(); iterator.hasNext();) {
                    Show show = iterator.next();

                    show.tick(tickDuration);

                    if (show.isDone()) {
                        iterator.remove();
                    }
                }
                this.lastTime = time;
            }

            for (Iterator<Show> iterator = this.shows.iterator(); iterator.hasNext();) {
                Show show = iterator.next();

                show.render(event.getPartialTicks());
            }
        }

    }

    // @SubscribeEvent
    // public void tick(WorldTickEvent event) {
    // if (event.phase == Phase.START)
    // return;
    // synchronized (this.shows) {
    // for (Iterator<Show> iterator = this.shows.iterator();
    // iterator.hasNext();) {
    // Show show = iterator.next();
    // show.tick();
    //
    // if (show.isDone())
    // iterator.remove();
    // }
    // }
    // }
}
