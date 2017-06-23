package org.kubithon.smgo.client.effect.torus;

import org.kubithon.smgo.client.effect.EffectParameters;
import org.kubithon.smgo.client.utils.Color;
import org.kubithon.smgo.client.utils.Expression;

import com.google.gson.annotations.SerializedName;

public class TorusParameters extends EffectParameters {
    /**
     * The radius "R" of the torus. Represents the biggest circle.
     */
    @SerializedName("bigCircleRadius")
    private float bigCircleRadius;

    /**
     * The radius "r" of the torus. Represents the smallest circle.
     */
    @SerializedName("smallCircleRadius")
    private Expression smallCircleRadius;

    private int amountOfRings;
    private int amountOfSides;

    /**
     * The color of the torus.
     */
    @SerializedName("color")
    private Color color;

    public float getBigCircleRadius() {
        return this.bigCircleRadius;
    }

    public Expression getSmallCircleRadius() {
        return this.smallCircleRadius;
    }

    public Color getColor() {
        return this.color;
    }

    public int getAmountOfRings() {
        return this.amountOfRings;
    }

    public int getAmountOfSides() {
        return this.amountOfSides;
    }
}
