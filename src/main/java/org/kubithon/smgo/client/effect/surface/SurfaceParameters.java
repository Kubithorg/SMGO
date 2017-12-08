package org.kubithon.smgo.client.effect.surface;

import org.kubithon.smgo.client.effect.EffectParameters;
import org.kubithon.smgo.client.math.IExpression;
import org.kubithon.smgo.client.utils.Color;
import org.kubithon.smgo.common.exceptions.ShowLoadingException;

import com.google.gson.JsonObject;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SurfaceParameters extends EffectParameters {
    IExpression surfaceParamX, surfaceParamY, surfaceParamZ;
    float       fromU, toU, partU, fromV, toV, partV;
    Color       color = Color.BROWN;

    protected SurfaceParameters(JsonObject jsonObject) throws ShowLoadingException {
        super(jsonObject);
        if (!jsonObject.has("surfaceParamX"))
            throw new ShowLoadingException("Missing element \"surfaceParamX\" in the parameters of some effect.");
        this.surfaceParamX = readExpression(jsonObject.get("surfaceParamX"));
        if (!jsonObject.has("surfaceParamY"))
            throw new ShowLoadingException("Missing element \"surfaceParamY\" in the parameters of some effect.");
        this.surfaceParamY = readExpression(jsonObject.get("surfaceParamY"));
        if (!jsonObject.has("surfaceParamZ"))
            throw new ShowLoadingException("Missing element \"surfaceParamZ\" in the parameters of some effect.");
        this.surfaceParamZ = readExpression(jsonObject.get("surfaceParamZ"));
        if (!jsonObject.has("fromU") || !jsonObject.has("toU") || !jsonObject.has("partU"))
            throw new ShowLoadingException(
                    "Missing element \"fromU\" or \"toU\" or \"partU\" in the parameters of some effect.");
        this.fromU = jsonObject.get("fromU").getAsFloat();
        this.toU = jsonObject.get("toU").getAsFloat();
        this.partU = jsonObject.get("partU").getAsFloat();
        if (!jsonObject.has("fromV") || !jsonObject.has("toV") || !jsonObject.has("partV"))
            throw new ShowLoadingException(
                    "Missing element \"fromV\" or \"toV\" or \"partV\" in the parameters of some effect.");
        this.fromV = jsonObject.get("fromV").getAsFloat();
        this.toV = jsonObject.get("toV").getAsFloat();
        this.partV = jsonObject.get("partV").getAsFloat();
        if (jsonObject.has("color"))
            this.color = new Color(Integer.parseUnsignedInt(jsonObject.get("color").getAsString(), 16));
    }

    public static SurfaceParameters read(JsonObject jsonObject) throws ShowLoadingException {
        return new SurfaceParameters(jsonObject);
    }

}
