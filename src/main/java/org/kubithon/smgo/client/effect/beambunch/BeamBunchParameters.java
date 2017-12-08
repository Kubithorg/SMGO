package org.kubithon.smgo.client.effect.beambunch;

import org.kubithon.smgo.client.effect.EffectParameters;
import org.kubithon.smgo.client.utils.Color;
import org.kubithon.smgo.common.exceptions.ShowLoadingException;

import com.google.gson.JsonObject;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BeamBunchParameters extends EffectParameters {
    /**
     * Amount of beams in this bunch.
     */
    private int amount = 36;

    /**
     * Angle between an horizotal plane and the bunch. In degrees.
     */
    private float angle = 27.31647F;

    /**
     * Rotation speed : angle in degrees per tick.
     */
    private float speed = 1.0F;

    /**
     * The color of the beams.
     */
    private Color color = Color.BLUEVIOLET;

    /**
     * Length of a beam, in blocks.
     */
    private float beamsLength = 2.5F;

    /**
     * Line width of a beam.
     */
    private float lineWidth = 0.2F;

    protected BeamBunchParameters(JsonObject jsonObject) throws ShowLoadingException {
        super(jsonObject);
        if (jsonObject.has("amount"))
            this.amount = jsonObject.get("amount").getAsInt();
        if (jsonObject.has("angle"))
            this.angle = jsonObject.get("angle").getAsFloat();
        if (jsonObject.has("speed"))
            this.speed = jsonObject.get("speed").getAsFloat();
        if (jsonObject.has("color"))
            this.color = new Color(Integer.parseUnsignedInt(jsonObject.get("color").getAsString(), 16));
        if (jsonObject.has("beamsLength"))
            this.beamsLength = jsonObject.get("beamsLength").getAsFloat();
        if (jsonObject.has("lineWidth"))
            this.lineWidth = jsonObject.get("lineWidth").getAsFloat();
    }

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

    public static BeamBunchParameters read(JsonObject jsonObject) throws ShowLoadingException {
        return new BeamBunchParameters(jsonObject);
    }
}
