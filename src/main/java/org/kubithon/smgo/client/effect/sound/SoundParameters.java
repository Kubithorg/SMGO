package org.kubithon.smgo.client.effect.sound;

import org.kubithon.smgo.client.effect.EffectParameters;

import com.google.gson.JsonObject;

public class SoundParameters extends EffectParameters {

    public String fileName;
    public int    startAt = 0, stopAfter;

    protected SoundParameters(JsonObject jsonObject) {
        super(jsonObject);
        this.stopAfter = this.getMaxAge();
        this.fileName = jsonObject.get("fileName").getAsString();
        if (jsonObject.has("startAt"))
            this.startAt = jsonObject.get("startAt").getAsInt();
        if (jsonObject.has("stopAfter"))
            this.stopAfter = jsonObject.get("stopAfter").getAsInt();
    }

    public static SoundParameters read(JsonObject jsonObject) {
        return new SoundParameters(jsonObject);
    }

}
