package org.kubithon.smgo.client.effect.beam;

import org.kubithon.smgo.client.effect.EffectParameters;
import org.kubithon.smgo.client.utils.Color;
import org.kubithon.smgo.client.utils.Expression;

import com.google.gson.annotations.SerializedName;

public class BeamParameters extends EffectParameters {
    @SerializedName("color")
    private Color color;

    @SerializedName("endX")
    private Expression endX;

    @SerializedName("endY")
    private Expression endY;

    @SerializedName("endZ")
    private Expression endZ;

    public Color getColor() {
        return this.color;
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
}
