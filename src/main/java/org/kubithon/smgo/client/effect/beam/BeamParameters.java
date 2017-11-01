package org.kubithon.smgo.client.effect.beam;

import org.kubithon.smgo.client.effect.EffectParameters;
import org.kubithon.smgo.client.math.IExpression;
import org.kubithon.smgo.client.utils.Color;

import com.google.gson.JsonObject;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BeamParameters extends EffectParameters {
    private Color color;

    private IExpression endX;
    private IExpression endY;
    private IExpression endZ;

    public Color getColor() {
        return this.color;
    }

    protected BeamParameters(JsonObject jsonObject) {
        super(jsonObject);
        this.color = new Color(Integer.parseUnsignedInt(jsonObject.get("color").getAsString(), 16));
        this.endX = readExpression(jsonObject.get("endX"));
        this.endY = readExpression(jsonObject.get("endY"));
        this.endZ = readExpression(jsonObject.get("endZ"));
    }

    public IExpression getEndX() {
        return this.endX;
    }

    public IExpression getEndY() {
        return this.endY;
    }

    public IExpression getEndZ() {
        return this.endZ;
    }

    public static BeamParameters read(JsonObject jsonObject) {
        return new BeamParameters(jsonObject);
    }
}
