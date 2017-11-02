package org.kubithon.smgo.client.effect.ring;

import org.kubithon.smgo.client.effect.EffectParameters;

import com.google.gson.JsonObject;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RingParameters extends EffectParameters {
    protected RingParameters(JsonObject jsonObject) {
        super(jsonObject);
    }
}
