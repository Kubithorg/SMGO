package org.kubithon.smgo.client.effect;

import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;

public abstract class PreCompiledEffect<P extends EffectParameters> extends Effect<P> {

    private static int     displayList;
    private static boolean compiled;

    public PreCompiledEffect(P parameters) {
        super(parameters);
    }

    @Override
    public void render(Tessellator tessellator, VertexBuffer vertexbuffer, float partialTicks) {
        if (!PreCompiledEffect.compiled)
            this.compile(tessellator, vertexbuffer);

        GlStateManager.pushMatrix();
        this.preRender();
        GlStateManager.callList(PreCompiledEffect.displayList);
        this.postRender();
        GlStateManager.popMatrix();
    }

    protected void compile(Tessellator tessellator, VertexBuffer vertexbuffer) {
        PreCompiledEffect.displayList = GLAllocation.generateDisplayLists(1);
        GlStateManager.glNewList(PreCompiledEffect.displayList, 4864);

        this.setup(tessellator, vertexbuffer);

        GlStateManager.glEndList();
        PreCompiledEffect.compiled = true;
    }

    protected abstract void setup(Tessellator tessellator, VertexBuffer vertexbuffer);

    protected void preRender() {}

    protected void postRender() {}

}
