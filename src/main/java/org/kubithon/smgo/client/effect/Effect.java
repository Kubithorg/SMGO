package org.kubithon.smgo.client.effect;

import java.beans.Expression;

import org.kubithon.smgo.client.Show;
import org.kubithon.smgo.client.math.IExpression;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Represents an actual effect in the world.
 *
 * @param <P>
 *            The parameters type.
 */
@SideOnly(Side.CLIENT)
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
    protected float age;

    public Effect(P parameters) {
        this.parameters = parameters;
    }

    /**
     * Gets the parameters of this effect.
     *
     * @return The parameters of this effect
     */
    public P getParameters() {
        return this.parameters;
    }

    /**
     * Updates this effect.
     */
    public void tick(Show show, double tickDuration) {
        if ((this.age += tickDuration) >= this.parameters.getMaxAge())
            this.setShouldBeRemoved(true);

        this.passParams(this.parameters.getX());
        this.passParams(this.parameters.getY());
        this.passParams(this.parameters.getZ());

        this.x = this.parameters.getX().getValue();
        this.y = this.parameters.getY().getValue();
        this.z = this.parameters.getZ().getValue();
    }

    /**
     * Passes the usual parameters to the given {@link Expression}. You can
     * override this, but don't forget to call {@code super.passParams(expr)} or
     * you shall get unpredictable results.
     *
     * @param expr
     *            The {@link Expression} to pass parameters to
     * @return The {@link Expression} with newly set parameters
     * @see Expression#with(String, float)
     */
    protected void passParams(IExpression expr) {
        expr.setVariable("age", this.age);
        expr.setVariable("maxAge", this.getParameters().getMaxAge());
        expr.setVariable("ageRatio", this.age / this.getParameters().getMaxAge());
        expr.setVariable("t", this.age / this.getParameters().getMaxAge());
    }

    /**
     * @return {@code true} whenever this effect should be removed from the
     *         world.
     */
    public boolean shouldBeRemoved() {
        return this.shouldBeRemoved;
    }

    /**
     * Set to {@code true} whenever this effect should be removed from the
     * world.
     *
     * @param shouldBeRemoved
     *            {@code true} if this effect should be removed, {@code false}
     *            otherwise
     */
    public void setShouldBeRemoved(boolean shouldBeRemoved) {
        this.shouldBeRemoved = shouldBeRemoved;
    }

    /**
     * Renders.
     */
    public abstract void render(Tessellator tessellator, VertexBuffer vertexbuffer, float partialTicks);

    /**
     * Used to free the graphical resources.
     */
    public void delete() {}
}
