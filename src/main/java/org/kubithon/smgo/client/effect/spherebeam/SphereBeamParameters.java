package org.kubithon.smgo.client.effect.spherebeam;

import org.kubithon.smgo.client.effect.EffectParameters;
import org.kubithon.smgo.client.utils.Color;
import org.kubithon.smgo.common.exceptions.ShowLoadingException;

import com.google.gson.JsonObject;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SphereBeamParameters extends EffectParameters {
    int   xBeam       = 8, yBeam = 8;
    float smallRadius = 1, bigRadius = 10;
    Color color       = Color.MIDNIGHTBLUE;

    protected SphereBeamParameters(JsonObject jsonObject) throws ShowLoadingException {
        super(jsonObject);
        if (jsonObject.has("xBeam"))
            this.xBeam = jsonObject.get("xBeam").getAsInt();
        if (jsonObject.has("yBeam"))
            this.yBeam = jsonObject.get("yBeam").getAsInt();
        if (jsonObject.has("smallRadius"))
            this.smallRadius = jsonObject.get("smallRadius").getAsFloat();
        if (jsonObject.has("bigRadius"))
            this.bigRadius = jsonObject.get("bigRadius").getAsFloat();
        if (jsonObject.has("color"))
            this.color = new Color(Integer.parseUnsignedInt(jsonObject.get("color").getAsString(), 16));
    }

    public static SphereBeamParameters read(JsonObject jsonObject) throws ShowLoadingException {
        return new SphereBeamParameters(jsonObject);
    }

}
