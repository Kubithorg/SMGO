package org.kubithon.smgo.common.show;

import java.util.Map.Entry;

import org.kubithon.smgo.common.exceptions.ShowLoadingException;
import org.kubithon.smgo.common.utils.SmgoConfig;
import org.kubithon.smgo.common.utils.Timing;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ShowInfos {
    /**
     * This show's last tick (when no effect is longer displayed).
     */
    protected int lastTick;

    protected ShowInfos(JsonObject jsonObject) throws ShowLoadingException {
        JsonObject obj;
        try {
            obj = jsonObject.get("timeline").getAsJsonObject();
        } catch (Exception e) {
            if (SmgoConfig.debug)
                e.printStackTrace();
            if (e instanceof ShowLoadingException)
                throw e;
            throw new ShowLoadingException("Missing element \"timeline\" in some show.");
        }
        int key, maxAge;

        for (Entry<String, JsonElement> entry : obj.entrySet()) {
            key = Timing.parseTime(entry.getKey());

            JsonArray array = entry.getValue().getAsJsonArray();
            for (JsonElement el : array) {
                try {
                    maxAge = el.getAsJsonObject().get("parameters").getAsJsonObject().get("maxAge").getAsInt();
                } catch (Exception e) {
                    if (SmgoConfig.debug)
                        e.printStackTrace();
                    throw new ShowLoadingException(
                            "Missing element \"parameters\" or \"parameters->maxAge\" in the effect : "
                                    + el.toString());
                }
                if (this.lastTick < key + maxAge)
                    this.lastTick = key + maxAge;
            }
        }
    }

    public int getLastTick() {
        return this.lastTick;
    }

    public static ShowInfos read(JsonObject jsonObject) throws ShowLoadingException {
        return new ShowInfos(jsonObject);
    }

}
