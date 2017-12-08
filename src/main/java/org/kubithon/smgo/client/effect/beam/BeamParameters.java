package org.kubithon.smgo.client.effect.beam;

import org.kubithon.smgo.client.effect.EffectParameters;
import org.kubithon.smgo.client.math.IExpression;
import org.kubithon.smgo.client.utils.Color;
import org.kubithon.smgo.common.exceptions.ShowLoadingException;

import com.google.gson.JsonObject;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BeamParameters extends EffectParameters {
    private Color color = Color.CADETBLUE;

    private IExpression endX;
    private IExpression endY;
    private IExpression endZ;

    public float lineWidth = 1;

    public Color getColor() {
        return this.color;
    }

    protected BeamParameters(JsonObject jsonObject) throws ShowLoadingException {
        super(jsonObject);
        if (jsonObject.has("color"))
            this.color = new Color(Integer.parseUnsignedInt(jsonObject.get("color").getAsString(), 16));
        if (!jsonObject.has("endX"))
            throw new ShowLoadingException("Missing element \"endX\" in the parameters of some effect.");
        this.endX = readExpression(jsonObject.get("endX"));
        if (!jsonObject.has("endY"))
            throw new ShowLoadingException("Missing element \"endY\" in the parameters of some effect.");
        this.endY = readExpression(jsonObject.get("endY"));
        if (!jsonObject.has("endZ"))
            throw new ShowLoadingException("Missing element \"endZ\" in the parameters of some effect.");
        this.endZ = readExpression(jsonObject.get("endZ"));
        if (jsonObject.has("lineWidth"))
            this.lineWidth = jsonObject.get("lineWidth").getAsFloat();
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

    public static BeamParameters read(JsonObject jsonObject) throws ShowLoadingException {
        return new BeamParameters(jsonObject);
    }
}
