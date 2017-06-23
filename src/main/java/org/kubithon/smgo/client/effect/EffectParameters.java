package org.kubithon.smgo.client.effect;

import org.kubithon.smgo.client.utils.Expression;

import com.google.gson.annotations.SerializedName;

public class EffectParameters {
    /**
     * The expression of the function: age -> X position.
     */
    @SerializedName("x")
    private Expression x;

    /**
     * The expression of the function: age -> Y position.
     */
    @SerializedName("y")
    private Expression y;

    /**
     * The expression of the function: age -> Z position.
     */
    @SerializedName("z")
    private Expression z;

    /**
     * The max age of this effect (its lifetime before it gets removed from the
     * world) in ticks.
     */
    @SerializedName("maxAge")
    private int maxAge;

    /**
     * Instantiates a new effect parameters.
     */
    public EffectParameters() {}

    /**
     * Instantiates a new effect parameters.
     *
     * @param x
     *            The expression of the function: age -> X position
     * @param y
     *            The expression of the function: age -> Y position
     * @param z
     *            The expression of the function: age -> Z position
     * @param maxAge
     *            The max age of this effect
     */
    public EffectParameters(Expression x, Expression y, Expression z, int maxAge) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.maxAge = maxAge;
    }

    /**
     * Gets the expression of the function: age -> X position.
     *
     * @return The expression of the function: age -> X position
     */
    public Expression getX() {
        return this.x;
    }

    /**
     * Gets the expression of the function: age -> Y position.
     *
     * @return The expression of the function: age -> Y position
     */
    public Expression getY() {
        return this.y;
    }

    /**
     * Gets the expression of the function: age -> Z position.
     *
     * @return The expression of the function: age -> Z position
     */
    public Expression getZ() {
        return this.z;
    }

    /**
     * Gets the max age of this effect (its lifetime before it gets removed from
     * the world) in ticks.
     *
     * @return The max age of this effect (its lifetime before it gets removed
     *         from the world) in ticks
     */
    public int getMaxAge() {
        return this.maxAge;
    }
}
