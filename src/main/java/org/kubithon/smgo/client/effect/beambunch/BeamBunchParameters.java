package org.kubithon.smgo.client.effect.beambunch;

import org.kubithon.smgo.client.effect.EffectParameters;
import org.kubithon.smgo.client.utils.Color;

import com.google.gson.JsonObject;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BeamBunchParameters extends EffectParameters {
    /**
     * Amount of beams in this bunch.
     */
    private int amount;

    /**
     * Angle between an horizotal plane and the bunch. In degrees.
     */
    private float angle;

    /**
     * Rotation speed : angle in degrees per tick.
     */
    private float speed;

    /**
     * The color of the beams.
     */
    private Color color;

    /**
     * Length of a beam, in blocks.
     */
    private float beamsLength;

    /**
     * Line width of a beam.
     */
    private float lineWidth;

    protected BeamBunchParameters(JsonObject jsonObject) {
        super(jsonObject);
        this.amount = jsonObject.get("amount").getAsInt();
        this.angle = jsonObject.get("angle").getAsFloat();
        this.speed = jsonObject.get("speed").getAsFloat();
        this.color = new Color(Integer.parseUnsignedInt(jsonObject.get("color").getAsString(), 16));
        this.beamsLength = jsonObject.get("beamsLength").getAsFloat();
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

    public static BeamBunchParameters read(JsonObject jsonObject) {
        return new BeamBunchParameters(jsonObject);
    }
}
