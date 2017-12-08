package org.kubithon.smgo.client.effect.quadstorm;

import org.kubithon.smgo.client.effect.PreCompiledParameters;
import org.kubithon.smgo.common.exceptions.ShowLoadingException;

import com.google.gson.JsonObject;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class QuadStormParameters extends PreCompiledParameters {

    /**
     * The maximum distance from this effect's origin the squad will be
     * generated.
     */
    private float radius = 5.0f;

    /**
     * The amount of quads.
     */
    private int amount = 10;

    protected QuadStormParameters(JsonObject jsonObject) throws ShowLoadingException {
        super(jsonObject);

        if (jsonObject.has("radius"))
            this.radius = jsonObject.get("radius").getAsFloat();

        if (jsonObject.has("amount"))
            this.amount = jsonObject.get("amout").getAsInt();
    }

    public float getRadius() {
        return this.radius;
    }

    public int getAmount() {
        return this.amount;
    }

    public static QuadStormParameters read(JsonObject jsonObject) throws ShowLoadingException {
        return new QuadStormParameters(jsonObject);
    }
}
