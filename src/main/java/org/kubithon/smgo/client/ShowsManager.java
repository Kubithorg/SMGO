package org.kubithon.smgo.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;

public class ShowsManager {
    private List<Show> shows = Collections.synchronizedList(new ArrayList<Show>());

    public void startShow(Show show) {
        this.shows.add(show);
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
    public void tick(WorldTickEvent event) {
        if (event.phase == Phase.START)
            return;
        synchronized (this.shows) {
            for (Iterator<Show> iterator = this.shows.iterator(); iterator.hasNext();) {
                Show show = iterator.next();
                show.tick();

                if (show.isDone())
                    iterator.remove();
            }
        }
    }
}
