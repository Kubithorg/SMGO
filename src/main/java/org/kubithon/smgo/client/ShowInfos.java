package org.kubithon.smgo.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.kubithon.smgo.client.effect.EffectInfos;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraftforge.fml.common.registry.IForgeRegistryEntry.Impl;

public class ShowInfos extends Impl<ShowInfos> {
    /**
     * This show's name.
     */
    private String name;

    /**
     * This show's timeline. A mapping time -> effects happening.
     */
    private Map<Integer, List<EffectInfos>> timeline;

    protected ShowInfos(JsonObject jsonObject) {
        this.name = jsonObject.get("name").getAsString();
        this.timeline = new HashMap<>();

        JsonObject obj = jsonObject.get("timeline").getAsJsonObject();
        ArrayList list;

        for (Entry<String, JsonElement> entry : obj.entrySet()) {
            this.timeline.put(Integer.valueOf(entry.getKey()), list = new ArrayList<>());
            JsonArray array = entry.getValue().getAsJsonArray();
            for (JsonElement el : array)
                list.add(EffectInfos.read(el.getAsJsonObject()));
        }
    }

    public Map<Integer, List<EffectInfos>> getTimeline() {
        return this.timeline;
    }

    @Override
    public String toString() {
        return "ShowInfos [name=" + this.name + ", timeline=" + this.timeline + "]";
    }

    public static ShowInfos read(JsonObject jsonObject) {
        return new ShowInfos(jsonObject);
    }
}
