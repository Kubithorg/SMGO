package org.kubithon.smgo.client.effect;

import org.kubithon.smgo.client.utils.Renderable;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;

public abstract class EffectWithRenderable<P extends EffectParameters> extends Effect<P> {

    protected Renderable renderable;

    public EffectWithRenderable(P parameters) {
        super(parameters);
    }

    @Override
    public void render(Tessellator tessellator, VertexBuffer vertexbuffer, float partialTicks) {
        this.checkInit(vertexbuffer);
    }

    public void checkInit(VertexBuffer vertexbuffer) {
        if (renderable == null) {
            renderable = buildRenderable(vertexbuffer);
        }
    }

    protected abstract Renderable buildRenderable(VertexBuffer vertexbuffer);
}
