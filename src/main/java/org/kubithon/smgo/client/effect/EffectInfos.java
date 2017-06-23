package org.kubithon.smgo.client.effect;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;

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
    @SerializedName("type")
    private String type;

    /**
     * The parameters to be applied on an {@link Effect} built from these infos.
     */
    @SerializedName("parameters")
    private EffectParameters parameters;

    private EffectInfos() {

    }

    public EffectInfos(String type, EffectParameters parameters) {
        this.type = type;
        this.parameters = parameters;
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

    public static class Deserializer implements JsonDeserializer<EffectInfos> {
        @Override
        public EffectInfos deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            EffectInfos infos = new EffectInfos();

            infos.type = jsonObject.get("type").getAsString();

            EffectType<? extends EffectParameters> effectType = EffectType.getTypeByIdentifier(infos.type);

            if (effectType == null)
                throw new IllegalArgumentException("Effect type '" + infos.type + "' not found.");

            infos.parameters = context.deserialize(jsonObject.get("parameters"), effectType.getParametersClass());

            return infos;
        }
    }

    @Override
    public String toString() {
        return "EffectInfos [type=" + this.type + ", parameters=" + this.parameters + "]";
    }
}
