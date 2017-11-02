package org.kubithon.smgo.client.effect.aureole;

import org.kubithon.smgo.client.effect.EffectParameters;
import org.kubithon.smgo.client.utils.Color;

import com.google.gson.JsonObject;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class AureoleParameters extends EffectParameters {

    /**
     * The radius of the aureole.
     */
    private float radius = 1f;

    /**
     * The amount of different slides. The circle representing the aureole will
     * be separated in 2*n slides.
     */
    private int amountOfSlides = 6;

    /**
     * This aureole color.
     */
    private Color color = Color.GOLD.withAlpha(0.8f);

    protected AureoleParameters(JsonObject jsonObject) {
        super(jsonObject);

        if (jsonObject.has("radius"))
            this.radius = jsonObject.get("radius").getAsFloat();

        if (jsonObject.has("color"))
            this.color = new Color(Integer.parseUnsignedInt(jsonObject.get("color").getAsString(), 16));

        if (jsonObject.has("slides"))
            this.amountOfSlides = jsonObject.get("slides").getAsInt();
    }

    public float getRadius() {
        return this.radius;
    }

    public Color getColor() {
        return this.color;
    }

    public int getAmountOfSlides() {
        return this.amountOfSlides;
    }

    public static AureoleParameters read(JsonObject jsonObject) {
        return new AureoleParameters(jsonObject);
    }
}
