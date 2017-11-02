package org.kubithon.smgo.client.effect;

import org.kubithon.smgo.client.math.ExpressionReader;
import org.kubithon.smgo.client.math.IExpression;
import org.kubithon.smgo.common.utils.Timing;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Every subclass should redefine a {@code static P read(JsonObject)} where
 * {@code P} is the subclass type.
 */
@SideOnly(Side.CLIENT)
public class EffectParameters {
    /**
     * The expression of the function: age -> X position.
     */
    private IExpression x;

    /**
     * The expression of the function: age -> Y position.
     */
    private IExpression y;

    /**
     * The expression of the function: age -> Z position.
     */
    private IExpression z;

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
        JsonElement maxAgeElement = jsonObject.get("maxAge");

        if (maxAgeElement.isJsonPrimitive()) {
            JsonPrimitive maxAgePrimitive = (JsonPrimitive) maxAgeElement;

            if (maxAgePrimitive.isNumber())
                this.maxAge = maxAgePrimitive.getAsInt();
            else
                this.maxAge = Timing.parseTime(maxAgePrimitive.getAsString());
        }
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
    public EffectParameters(IExpression x, IExpression y, IExpression z, int maxAge) {
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
    public IExpression getX() {
        return this.x;
    }

    /**
     * Gets the expression of the function: age -> Y position.
     *
     * @return The expression of the function: age -> Y position
     */
    public IExpression getY() {
        return this.y;
    }

    /**
     * Gets the expression of the function: age -> Z position.
     *
     * @return The expression of the function: age -> Z position
     */
    public IExpression getZ() {
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

    public static IExpression readExpression(JsonElement jsonEl) {
        return ExpressionReader.read(jsonEl.getAsString());
    }
}
