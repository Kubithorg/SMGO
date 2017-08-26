package org.kubithon.smgo.client.effect.quadstorm;

import org.kubithon.smgo.client.effect.EffectParameters;

import com.google.gson.JsonObject;


public class QuadStormParameters extends EffectParameters {

    /**
     * The maximum distance from this effect's origin the squad will be
     * generated.
     */
    private float radius = 5.0f;

    /**
     * The amount of quads.
     */
    private int amount = 10;

    protected QuadStormParameters(JsonObject jsonObject) {
        super(jsonObject);
        
        if (jsonObject.has("radius")) {
            radius = jsonObject.get("radius").getAsFloat();
        }
        
        if (jsonObject.has("amount")) {
            amount = jsonObject.get("amout").getAsInt();
        }
    }
    
    public float getRadius() {
        return radius;
    }
    
    public int getAmount() {
        return amount;
    }

    public static QuadStormParameters read(JsonObject jsonObject) {
        return new QuadStormParameters(jsonObject);
    }
}
