package org.kubithon.smgo.client.effect;

import org.kubithon.smgo.client.Show;
import org.kubithon.smgo.client.utils.Expression;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


/**
 * Represents an actual effect in the world.
 *
 * @param <P> The parameters type.
 */
public abstract class Effect<P extends EffectParameters> {
    /**
     * Set to {@code true} whenever this effect should be removed from the
     * world. It will be destroyed by the effects manager on the next tick.
     */
    protected boolean shouldBeRemoved = false;

    /**
     * The parameters of this effect.
     */
    protected final P parameters;

    /**
     * The current X location of this effect.
     */
    protected float x;

    /**
     * The current Y location of this effect.
     */
    protected float y;

    /**
     * The current Z location of this effect.
     */
    protected float z;

    /**
     * Amount of ticks this effect has been alive.
     */
    protected int age;

    public Effect(P parameters) {
        this.parameters = parameters;
    }

    /**
     * Gets the parameters of this effect.
     *
     * @return The parameters of this effect
     */
    public P getParameters() {
        return parameters;
    }

    /**
     * Updates this effect.
     */
    public void tick(Show show) {
        if (age++ == parameters.getMaxAge()) {
            setShouldBeRemoved(true);
        }

        x = eval(parameters.getX());
        y = eval(parameters.getY());
        z = eval(parameters.getZ());
    }

    /**
     * Passes this effect usual parameters to the given {@link Expression}, and
     * evaluates it.
     * 
     * @param expr The expression to be evaluated in this {@link Effect} context
     * @return The {@link Expression} value
     * @see Effect#passParams(Expression)
     * @see Expression#eval()
     */
    protected float eval(Expression expr) {
        return passParams(expr).eval();
    }

    /**
     * Passes the usual parameters to the given {@link Expression}. You can
     * override this, but don't forget to call {@code super.passParams(expr)} or
     * you shall get unpredictable results.
     * 
     * @param expr The {@link Expression} to pass parameters to
     * @return The {@link Expression} with newly set parameters
     * @see Expression#with(String, float)
     */
    protected Expression passParams(Expression expr) {
        return expr.with("age", age)
                   .with("maxAge", getParameters().getMaxAge())
                   .with("ageRatio", age / (float) getParameters().getMaxAge());
    }

    /**
     * @return {@code true} whenever this effect should be removed from the
     *         world.
     */
    public boolean shouldBeRemoved() {
        return shouldBeRemoved;
    }

    /**
     * Set to {@code true} whenever this effect should be removed from the
     * world.
     * 
     * @param shouldBeRemoved {@code true} if this effect should be removed,
     *        {@code false} otherwise
     */
    public void setShouldBeRemoved(boolean shouldBeRemoved) {
        this.shouldBeRemoved = shouldBeRemoved;
    }

    /**
     * Renders.
     */
    @SideOnly(Side.CLIENT)
    public abstract void render(Tessellator tessellator, VertexBuffer vertexbuffer, float partialTicks);
}
