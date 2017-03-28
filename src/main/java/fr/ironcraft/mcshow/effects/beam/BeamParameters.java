package fr.ironcraft.mcshow.effects.beam;

import com.google.gson.annotations.SerializedName;

import fr.ironcraft.mcshow.effects.EffectParameters;
import fr.ironcraft.mcshow.utils.Color;
import fr.ironcraft.mcshow.utils.Expression;


public class BeamParameters extends EffectParameters {
    @SerializedName("color")
    private Color color;

    @SerializedName("endX")
    private Expression endX;

    @SerializedName("endY")
    private Expression endY;

    @SerializedName("endZ")
    private Expression endZ;

    public Color getColor() {
        return color;
    }

    public Expression getEndX() {
        return endX;
    }

    public Expression getEndY() {
        return endY;
    }

    public Expression getEndZ() {
        return endZ;
    }
}
