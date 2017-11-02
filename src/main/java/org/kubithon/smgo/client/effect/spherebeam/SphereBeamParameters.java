package org.kubithon.smgo.client.effect.spherebeam;

import org.kubithon.smgo.client.effect.EffectParameters;
import org.kubithon.smgo.client.utils.Color;

import com.google.gson.JsonObject;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SphereBeamParameters extends EffectParameters {
    int   xBeam, yBeam;
    float smallRadius, bigRadius;
    Color color;

    protected SphereBeamParameters(JsonObject jsonObject) {
        super(jsonObject);
        this.xBeam = jsonObject.get("xBeam").getAsInt();
        this.yBeam = jsonObject.get("yBeam").getAsInt();
        this.smallRadius = jsonObject.get("smallRadius").getAsFloat();
        this.bigRadius = jsonObject.get("bigRadius").getAsFloat();
        this.color = new Color(Integer.parseUnsignedInt(jsonObject.get("color").getAsString(), 16));
    }

    public static SphereBeamParameters read(JsonObject jsonObject) {
        return new SphereBeamParameters(jsonObject);
    }

}
