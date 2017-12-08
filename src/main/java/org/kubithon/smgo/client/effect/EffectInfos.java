package org.kubithon.smgo.client.effect;

import org.kubithon.smgo.common.Smgo;
import org.kubithon.smgo.common.exceptions.ShowLoadingException;
import org.kubithon.smgo.common.utils.SmgoConfig;

import com.google.gson.JsonObject;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Contains enough information to build an {@link Effect} object : effect type
 * identifier and parameters. This class is not to be overridden.
 *
 * @author Wytrem
 */
@SideOnly(Side.CLIENT)
public final class EffectInfos {
    /**
     * The identifier for the type of an {@link Effect} built from these infos.
     */
    private String type;

    /**
     * The parameters to be applied on an {@link Effect} built from these infos.
     */
    private EffectParameters parameters;

    protected EffectInfos(JsonObject jsonObject) throws ShowLoadingException {
        this.type = jsonObject.get("type").getAsString();

        EffectType<? extends EffectParameters> effectType = EffectType.getTypeByIdentifier(this.type);

        if (effectType == null)
            throw new ShowLoadingException("Effect type '" + this.type + "' not found.");

        try {
            this.parameters = (EffectParameters) effectType.getParametersClass().getMethod("read", JsonObject.class)
                    .invoke(null, jsonObject.get("parameters").getAsJsonObject());
        } catch (Exception e) {
            if (SmgoConfig.debug)
                e.printStackTrace();
            if (e.getCause() instanceof ShowLoadingException)
                throw (ShowLoadingException) e.getCause();
            else
                Smgo.logger.error("Error while reading the parameters of an effect.");
        }
    }

    public String getType() {
        return this.type;
    }

    public EffectParameters getParameters() {
        return this.parameters;
    }

    /**
     * @return A new {@link Effect} built on these infos.
     */
    public Effect<?> buildEffect() {
        EffectType<?> effectType = EffectType.getTypeByIdentifier(this.type);

        if (effectType == null)
            throw new IllegalArgumentException("Effect type '" + this.type + "' not found.");

        return effectType.buildEffect(this.parameters);
    }

    public static EffectInfos read(JsonObject jsonObject) throws ShowLoadingException {
        return new EffectInfos(jsonObject);
    }

    @Override
    public String toString() {
        return "EffectInfos [type=" + this.type + ", parameters=" + this.parameters + "]";
    }
}
