package org.kubithon.smgo.client.effect;

import com.google.gson.JsonObject;

/**
 * Contains enough information to build an {@link Effect} object : effect type
 * identifier and parameters. This class is not to be overridden.
 *
 * @author Wytrem
 */
public final class EffectInfos {
    /**
     * The identifier for the type of an {@link Effect} built from these infos.
     */
    private String type;

    /**
     * The parameters to be applied on an {@link Effect} built from these infos.
     */
    private EffectParameters parameters;

    protected EffectInfos(JsonObject jsonObject) {
        this.type = jsonObject.get("type").getAsString();

        EffectType<? extends EffectParameters> effectType = EffectType.getTypeByIdentifier(this.type);

        if (effectType == null)
            throw new IllegalArgumentException("Effect type '" + this.type + "' not found.");

        try {
            this.parameters = (EffectParameters) effectType.getParametersClass().getMethod("read", JsonObject.class)
                    .invoke(null, jsonObject.get("parameters").getAsJsonObject());
        } catch (Exception e) {
            e.printStackTrace();
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

    public static EffectInfos read(JsonObject jsonObject) {
        return new EffectInfos(jsonObject);
    }

    @Override
    public String toString() {
        return "EffectInfos [type=" + this.type + ", parameters=" + this.parameters + "]";
    }
}
