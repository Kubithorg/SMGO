package org.kubithon.smgo.client.effect.beam;

import org.kubithon.smgo.client.effect.EffectParameters;
import org.kubithon.smgo.client.utils.Color;
import org.kubithon.smgo.client.utils.Expression;

import com.google.gson.JsonObject;

public class BeamParameters extends EffectParameters {
    private Color color;

    private Expression endX;
    private Expression endY;
    private Expression endZ;

    public Color getColor() {
        return this.color;
    }

    protected BeamParameters(JsonObject jsonObject) {
        super(jsonObject);
        this.color = new Color(Integer.parseUnsignedInt(jsonObject.get("color").getAsString(), 16));
        this.endX = readExpression(jsonObject.get("endX"));
        this.endY = readExpression(jsonObject.get("endY"));
        this.endZ = readExpression(jsonObject.get("endZ"));
    }

    public Expression getEndX() {
        return this.endX;
    }

    public Expression getEndY() {
        return this.endY;
    }

    public Expression getEndZ() {
        return this.endZ;
    }

    public static BeamParameters read(JsonObject jsonObject) {
        return new BeamParameters(jsonObject);
    }
}
