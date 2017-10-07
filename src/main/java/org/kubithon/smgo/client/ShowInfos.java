package org.kubithon.smgo.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.kubithon.smgo.client.effect.EffectInfos;
import org.kubithon.smgo.client.utils.Timing;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry.Impl;

public class ShowInfos extends Impl<ShowInfos> {
    /**
     * This show's timeline. A mapping time -> effects happening.
     */
    private TIntObjectMap<List<EffectInfos>> timeline;

    /**
     * This show's last tick (when no effect is longer displayed).
     */
    private transient int lastTick;

    protected ShowInfos(JsonObject jsonObject) {
        this.timeline = new TIntObjectHashMap<>();

        JsonObject obj = jsonObject.get("timeline").getAsJsonObject();
        ArrayList<EffectInfos> list;
        int key;
        EffectInfos infos;

        for (Entry<String, JsonElement> entry : obj.entrySet()) {
            key = Timing.parseTime(entry.getKey());

            this.timeline.put(key, list = new ArrayList<>());
            JsonArray array = entry.getValue().getAsJsonArray();
            for (JsonElement el : array) {
                infos = EffectInfos.read(el.getAsJsonObject());
                if (lastTick < key + infos.getParameters().getMaxAge()) {
                    lastTick = key + infos.getParameters().getMaxAge();
                }
                list.add(infos);
            }
        }

    }

    public TIntObjectMap<List<EffectInfos>> getTimeline() {
        return this.timeline;
    }

    @Override
    public String toString() {
        return "ShowInfos [timeline=" + this.timeline + "]";
    }

    public static ShowInfos read(JsonObject jsonObject) {
        return new ShowInfos(jsonObject);
    }

    public int getLastTick() {
        return this.lastTick;
    }
}
