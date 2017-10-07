package org.kubithon.smgo.client.effect.ring;

import org.kubithon.smgo.client.effect.EffectWithRenderable;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;

public class Ring extends EffectWithRenderable<RingParameters> {

    public Ring(RingParameters parameters) {
        super(parameters);
    }

    @Override
    protected void buildRenderable(VertexBuffer vertexbuffer) {
        
    }

    @Override
    public void render(Tessellator tessellator, VertexBuffer vertexbuffer, float partialTicks) {
        
    }

}
