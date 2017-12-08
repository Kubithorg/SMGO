package org.kubithon.smgo.common.show;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.kubithon.smgo.common.Smgo;
import org.kubithon.smgo.common.network.StartShowMessage;
import org.kubithon.smgo.common.registry.ShowsRegistry;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class ShowsManager {

    private List<CurrentShowInfos> shows = Collections.synchronizedList(new ArrayList<>());

    public void startShow(ResourceLocation res, double x, double y, double z, double time) {
        this.cleanList();
        this.shows.add(new CurrentShowInfos(res, x, y, z, time));
        Smgo.NETWORK.sendToAll(new StartShowMessage(res, (float) x, (float) y, (float) z, (float) time));
    }

    @SubscribeEvent
    public void atPlayerConnection(PlayerEvent.PlayerLoggedInEvent event) {
        this.cleanList();
        synchronized (this.shows) {
            long curTime = System.currentTimeMillis();
            double showTime = 0;
            for (CurrentShowInfos show : this.shows) {
                showTime = curTime - show.startTime + show.time;
                if (showTime < show.showInfos.getLastTick() * 50)
                    Smgo.NETWORK.sendTo(new StartShowMessage(show.res, show.x, show.y, show.z, (float) (showTime / 50)),
                            (EntityPlayerMP) event.player);
            }
        }
    }

    private void cleanList() {
        synchronized (this.shows) {
            CurrentShowInfos show = null;
            long curTime = System.currentTimeMillis();
            Iterator<CurrentShowInfos> iterator = this.shows.iterator();
            while (iterator.hasNext()) {
                show = iterator.next();
                if (curTime - show.startTime + show.time > show.showInfos.getLastTick() * 50)
                    iterator.remove();
            }
        }
    }

    private class CurrentShowInfos {
        public ShowInfos        showInfos;
        public ResourceLocation res;
        public float            x, y, z, time;
        public long             startTime;

        public CurrentShowInfos(ResourceLocation res, double x, double y, double z, double time) {
            this.showInfos = ShowsRegistry.get(res);
            this.res = res;
            this.x = (float) x;
            this.y = (float) y;
            this.z = (float) z;
            this.time = (float) time * 50;
            this.startTime = System.currentTimeMillis();
        }
    }
}
