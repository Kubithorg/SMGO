package org.kubithon.smgo.client.effect.torus;

import org.kubithon.smgo.client.effect.EffectParameters;
import org.kubithon.smgo.client.math.IExpression;
import org.kubithon.smgo.client.utils.Color;

import com.google.gson.JsonObject;

public class TorusParameters extends EffectParameters {
    /**
     * The radius "R" of the torus. Represents the biggest circle.
     */
    private float bigCircleRadius;

    /**
     * The radius "r" of the torus. Represents the smallest circle.
     */
    private IExpression smallCircleRadius;

    private int amountOfRings;
    private int amountOfSides;

    /**
     * The color of the torus.
     */
    private Color color;

    protected TorusParameters(JsonObject jsonObject) {
        super(jsonObject);
        this.bigCircleRadius = jsonObject.get("bigCircleRadius").getAsFloat();
        this.smallCircleRadius = readExpression(jsonObject.get("smallCircleRadius"));
        this.amountOfRings = jsonObject.get("amountOfRings").getAsInt();
        this.amountOfSides = jsonObject.get("amountOfSides").getAsInt();
        this.color = new Color(Integer.parseUnsignedInt(jsonObject.get("color").getAsString(), 16));
    }

    public float getBigCircleRadius() {
        return this.bigCircleRadius;
    }

    public IExpression getSmallCircleRadius() {
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

    public static TorusParameters read(JsonObject jsonObject) {
        return new TorusParameters(jsonObject);
    }
}
