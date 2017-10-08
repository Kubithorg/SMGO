package org.kubithon.smgo.client.effect;

import org.kubithon.smgo.client.utils.Renderable;

import net.minecraft.client.renderer.VertexBuffer;

public abstract class EffectWithRenderable<P extends EffectParameters> extends Effect<P> {

    protected Renderable renderable;

    public EffectWithRenderable(P parameters) {
        super(parameters);
    }

    public void checkInit(VertexBuffer vertexbuffer) {
        if (this.renderable == null) {
            this.renderable = new Renderable();
            this.buildRenderable(vertexbuffer);
        }
    }

    protected abstract void buildRenderable(VertexBuffer vertexbuffer);
}
