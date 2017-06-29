package org.kubithon.smgo.client.effect.surface;

import org.kubithon.smgo.client.effect.EffectParameters;
import org.kubithon.smgo.client.math.IExpression;
import org.kubithon.smgo.client.utils.Color;

import com.google.gson.JsonObject;

public class SurfaceParameters extends EffectParameters {
    IExpression surfaceParamX, surfaceParamY, surfaceParamZ;
    float       fromU, toU, partU, fromV, toV, partV;
    Color       color;

    protected SurfaceParameters(JsonObject jsonObject) {
        super(jsonObject);
        this.surfaceParamX = readExpression(jsonObject.get("surfaceParamX"));
        this.surfaceParamY = readExpression(jsonObject.get("surfaceParamY"));
        this.surfaceParamZ = readExpression(jsonObject.get("surfaceParamZ"));
        this.fromU = jsonObject.get("fromU").getAsFloat();
        this.toU = jsonObject.get("toU").getAsFloat();
        this.partU = jsonObject.get("partU").getAsFloat();
        this.fromV = jsonObject.get("fromV").getAsFloat();
        this.toV = jsonObject.get("toV").getAsFloat();
        this.partV = jsonObject.get("partV").getAsFloat();
        this.color = new Color(Integer.parseUnsignedInt(jsonObject.get("color").getAsString(), 16));
    }

    public static SurfaceParameters read(JsonObject jsonObject) {
        return new SurfaceParameters(jsonObject);
    }

}
