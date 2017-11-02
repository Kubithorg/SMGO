package org.kubithon.smgo.client;
//TODO Should be moved to org.kubithon.smgo.common.show

import java.util.Map.Entry;

import org.kubithon.smgo.client.utils.Timing;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ShowInfos {
    /**
     * This show's last tick (when no effect is longer displayed).
     */
    protected int lastTick;

    protected ShowInfos(JsonObject jsonObject) {
        JsonObject obj = jsonObject.get("timeline").getAsJsonObject();
        int key, maxAge;

        for (Entry<String, JsonElement> entry : obj.entrySet()) {
            key = Timing.parseTime(entry.getKey());

            JsonArray array = entry.getValue().getAsJsonArray();
            for (JsonElement el : array) {
                maxAge = el.getAsJsonObject().get("parameters").getAsJsonObject().get("maxAge").getAsInt();
                if (this.lastTick < key + maxAge)
                    this.lastTick = key + maxAge;
            }
        }
    }

    public int getLastTick() {
        return this.lastTick;
    }

    public static ShowInfos read(JsonObject jsonObject) {
        return new ShowInfos(jsonObject);
    }

}
