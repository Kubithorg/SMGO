package org.kubithon.smgo.client.effect.sound;

import org.kubithon.smgo.client.effect.EffectParameters;
import org.kubithon.smgo.common.exceptions.ShowLoadingException;

import com.google.gson.JsonObject;

public class SoundParameters extends EffectParameters {

    public String fileName;
    public int    startAt = 0, stopAfter;

    protected SoundParameters(JsonObject jsonObject) throws ShowLoadingException {
        super(jsonObject);
        this.stopAfter = this.getMaxAge();
        if (!jsonObject.has("fileName"))
            throw new ShowLoadingException("Missing element \"fileName\" in the parameters of some effect.");
        this.fileName = jsonObject.get("fileName").getAsString();
        if (jsonObject.has("startAt"))
            this.startAt = jsonObject.get("startAt").getAsInt();
        if (jsonObject.has("stopAfter"))
            this.stopAfter = jsonObject.get("stopAfter").getAsInt();
    }

    public static SoundParameters read(JsonObject jsonObject) throws ShowLoadingException {
        return new SoundParameters(jsonObject);
    }

}
