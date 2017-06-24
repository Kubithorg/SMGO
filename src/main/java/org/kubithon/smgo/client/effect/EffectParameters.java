package org.kubithon.smgo.client.effect;

import org.kubithon.smgo.client.utils.Expression;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class EffectParameters {
    /**
     * The expression of the function: age -> X position.
     */
    private Expression x;

    /**
     * The expression of the function: age -> Y position.
     */
    private Expression y;

    /**
     * The expression of the function: age -> Z position.
     */
    private Expression z;

    /**
     * The max age of this effect (its lifetime before it gets removed from the
     * world) in ticks.
     */
    private int maxAge;

    /**
     * Instantiates a new effect parameters.
     */
    protected EffectParameters(JsonObject jsonObject) {
        this.x = readExpression(jsonObject.get("x"));
        this.y = readExpression(jsonObject.get("y"));
        this.z = readExpression(jsonObject.get("z"));
        this.maxAge = jsonObject.get("maxAge").getAsInt();
    }

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

    public static EffectParameters read(JsonObject jsonObject) {
        return new EffectParameters(jsonObject);
    }

    public static Expression readExpression(JsonElement jsonEl) {
        return new Expression(jsonEl.getAsString());
    }
}
