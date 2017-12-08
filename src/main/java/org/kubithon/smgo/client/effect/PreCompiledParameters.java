package org.kubithon.smgo.client.effect;

import org.kubithon.smgo.common.exceptions.ShowLoadingException;

import com.google.gson.JsonObject;

public class PreCompiledParameters extends EffectParameters {

    public float rotSpeedX = 0, rotSpeedY = 0, rotSpeedZ = 0;

    protected PreCompiledParameters(JsonObject jsonObject) throws ShowLoadingException {
        super(jsonObject);
        if (jsonObject.has("rotSpeedX"))
            this.rotSpeedX = jsonObject.get("rotSpeedX").getAsFloat();
        if (jsonObject.has("rotSpeedY"))
            this.rotSpeedY = jsonObject.get("rotSpeedY").getAsFloat();
        if (jsonObject.has("rotSpeedZ"))
            this.rotSpeedZ = jsonObject.get("rotSpeedZ").getAsFloat();
    }

    public static PreCompiledParameters read(JsonObject jsonObject) throws ShowLoadingException {
        return new PreCompiledParameters(jsonObject);
    }

}
