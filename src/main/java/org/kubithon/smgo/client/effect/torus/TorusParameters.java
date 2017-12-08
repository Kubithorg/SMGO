package org.kubithon.smgo.client.effect.torus;

import org.kubithon.smgo.client.effect.PreCompiledParameters;
import org.kubithon.smgo.client.math.IExpression;
import org.kubithon.smgo.client.utils.Color;
import org.kubithon.smgo.common.exceptions.ShowLoadingException;

import com.google.gson.JsonObject;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TorusParameters extends PreCompiledParameters {
    /**
     * The radius "R" of the torus. Represents the biggest circle.
     */
    private float bigCircleRadius = 10;

    /**
     * The radius "r" of the torus. Represents the smallest circle.
     */
    private IExpression smallCircleRadius = new IExpression.Constant(1.0F);

    private int amountOfRings = 150;
    private int amountOfSides = 32;

    /**
     * The color of the torus.
     */
    private Color color = Color.HOTPINK;

    protected TorusParameters(JsonObject jsonObject) throws ShowLoadingException {
        super(jsonObject);
        if (jsonObject.has("bigCircleRadius"))
            this.bigCircleRadius = jsonObject.get("bigCircleRadius").getAsFloat();
        if (jsonObject.has("smallCircleRadius"))
            this.smallCircleRadius = readExpression(jsonObject.get("smallCircleRadius"));
        if (jsonObject.has("amountOfRings"))
            this.amountOfRings = jsonObject.get("amountOfRings").getAsInt();
        if (jsonObject.has("amountOfSides"))
            this.amountOfSides = jsonObject.get("amountOfSides").getAsInt();
        if (jsonObject.has("color"))
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

    public static TorusParameters read(JsonObject jsonObject) throws ShowLoadingException {
        return new TorusParameters(jsonObject);
    }
}
