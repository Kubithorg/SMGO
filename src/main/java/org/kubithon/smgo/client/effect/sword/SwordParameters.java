package org.kubithon.smgo.client.effect.sword;

import org.kubithon.smgo.client.effect.EffectParameters;

import com.google.gson.JsonObject;

public class SwordParameters extends EffectParameters {
    protected SwordParameters(JsonObject jsonObject) {
        super(jsonObject);
    }

    public static SwordParameters read(JsonObject jsonObject) {
        return new SwordParameters(jsonObject);
    }
}
