package fr.ironcraft.mcshow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;


public class ShowsManager {
    private List<Show> shows = new ArrayList<Show>();
    private List<Show> showsToAdd = new ArrayList<Show>();

    public void startShow(Show show) {
        showsToAdd.add(show);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void render(RenderWorldLastEvent event) {
        for (Iterator<Show> iterator = shows.iterator(); iterator.hasNext();) {
            Show show = iterator.next();

            show.render(event.getPartialTicks());
        }
    }

    @SubscribeEvent
    public void tick(WorldTickEvent event) {
        if (event.phase == Phase.START) {
            return;
        }
        
        for (Iterator<Show> iterator = shows.iterator(); iterator.hasNext();) {
            Show show = iterator.next();
            show.tick();

            if (show.isDone()) {
                iterator.remove();
            }
        }

        for (Iterator<Show> iterator = showsToAdd.iterator(); iterator.hasNext();) {
            shows.add(iterator.next());
        }

        showsToAdd.clear();
    }
}
