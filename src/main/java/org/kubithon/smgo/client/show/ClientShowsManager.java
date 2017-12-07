package org.kubithon.smgo.client.show;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.kubithon.smgo.common.registry.ShowsRegistry;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientShowsManager {
    private static final float MIN_UPDATE_TIME = 0.05F;

    private List<Show> shows    = Collections.synchronizedList(new ArrayList<Show>());
    private double     lastTime = -1;

    public void startShow(ResourceLocation res, double x, double y, double z, double time) {
        Show show = new Show((ClientShowInfos) ShowsRegistry.get(res), x, y, z, time);
        this.shows.add(show);
        show.tick(0);
    }

    @SubscribeEvent
    public void atDeconnection(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        synchronized (this.shows) {
            for (Iterator<Show> iterator = this.shows.iterator(); iterator.hasNext();) {
                Show show = iterator.next();
                show.delete();
                iterator.remove();
            }
        }
        this.shows.clear();
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void render(RenderWorldLastEvent event) {
        double time = System.currentTimeMillis();
        double tickDuration = (time - this.lastTime) / 50;
        if (this.lastTime < 0)
            tickDuration = 0;
        synchronized (this.shows) {
            if (tickDuration > MIN_UPDATE_TIME || tickDuration == 0) {
                for (Iterator<Show> iterator = this.shows.iterator(); iterator.hasNext();) {
                    Show show = iterator.next();

                    show.tick(tickDuration);

                    if (show.isDone())
                        iterator.remove();
                }
                this.lastTime = time;
            }

            for (Iterator<Show> iterator = this.shows.iterator(); iterator.hasNext();) {
                Show show = iterator.next();

                show.render(event.getPartialTicks());
            }
        }
    }
}
