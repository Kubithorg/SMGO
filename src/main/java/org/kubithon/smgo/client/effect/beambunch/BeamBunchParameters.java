package org.kubithon.smgo.client.effect.beambunch;

import org.kubithon.smgo.client.effect.EffectParameters;
import org.kubithon.smgo.client.utils.Color;

import com.google.gson.annotations.SerializedName;

public class BeamBunchParameters extends EffectParameters {
    /**
     * Amount of beams in this bunch.
     */
    @SerializedName("amount")
    private int amount;

    /**
     * Angle between an horizotal plane and the bunch. In degrees.
     */
    @SerializedName("angle")
    private float angle;

    /**
     * Rotation speed : angle in degrees per tick.
     */
    @SerializedName("speed")
    private float speed;

    /**
     * The color of the beams.
     */
    @SerializedName("color")
    private Color color;

    /**
     * Length of a beam, in blocks.
     */
    @SerializedName("beamsLength")
    private float beamsLength;

    /**
     * Line width of a beam.
     */
    @SerializedName("lineWidth")
    private float lineWidth;

    public float getSpeed() {
        return this.speed;
    }

    public int getAmount() {
        return this.amount;
    }

    public float getAngle() {
        return this.angle;
    }

    public Color getColor() {
        return this.color;
    }

    public float getBeamsLength() {
        return this.beamsLength;
    }

    public float getLineWidth() {
        return this.lineWidth;
    }
}
