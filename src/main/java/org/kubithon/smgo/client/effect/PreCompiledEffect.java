package org.kubithon.smgo.client.effect;

import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class PreCompiledEffect<P extends EffectParameters> extends Effect<P> {

    private int     displayList;
    private boolean compiled;

    public PreCompiledEffect(P parameters) {
        super(parameters);
    }

    @Override
    public void render(Tessellator tessellator, VertexBuffer vertexbuffer, float partialTicks) {
        if (!this.compiled)
            this.compile(tessellator, vertexbuffer);

        GlStateManager.pushMatrix();
        this.preRender();
        GlStateManager.callList(this.displayList);
        this.postRender();
        GlStateManager.popMatrix();
    }

    protected void compile(Tessellator tessellator, VertexBuffer vertexbuffer) {
        this.displayList = GLAllocation.generateDisplayLists(1);
        GlStateManager.glNewList(this.displayList, 4864);

        this.setup(tessellator, vertexbuffer);

        GlStateManager.glEndList();
        this.compiled = true;
    }

    protected abstract void setup(Tessellator tessellator, VertexBuffer vertexbuffer);

    protected void preRender() {}

    protected void postRender() {}

}
