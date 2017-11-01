package org.kubithon.smgo.client.effect.sword;

import org.kubithon.smgo.client.effect.EffectParameters;

import com.google.gson.JsonObject;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SwordParameters extends EffectParameters {
    protected SwordParameters(JsonObject jsonObject) {
        super(jsonObject);
    }

    public static SwordParameters read(JsonObject jsonObject) {
        return new SwordParameters(jsonObject);
    }
}
