package org.kubithon.smgo.client.effect;

import org.kubithon.smgo.client.utils.Renderable;

import net.minecraft.client.renderer.VertexBuffer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
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
