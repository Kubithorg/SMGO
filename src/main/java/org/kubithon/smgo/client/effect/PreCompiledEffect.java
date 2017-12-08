package org.kubithon.smgo.client.effect;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class PreCompiledEffect<P extends PreCompiledParameters> extends Effect<P> {

    private int     displayList;
    private boolean compiled;

    public PreCompiledEffect(P parameters) {
        super(parameters);
    }

    @Override
    public void render(Tessellator tessellator, BufferBuilder vertexbuffer, float partialTicks) {
        if (!this.compiled)
            this.compile(tessellator, vertexbuffer);

        GlStateManager.pushMatrix();
        this.preRender();
        GlStateManager.callList(this.displayList);
        this.postRender();
        GlStateManager.popMatrix();
    }

    protected void compile(Tessellator tessellator, BufferBuilder vertexbuffer) {
        this.displayList = GLAllocation.generateDisplayLists(1);
        GlStateManager.glNewList(this.displayList, 4864);

        this.setup(tessellator, vertexbuffer);

        GlStateManager.glEndList();
        this.compiled = true;
    }

    protected abstract void setup(Tessellator tessellator, BufferBuilder vertexbuffer);

    protected void preRender() {
        if (this.parameters.rotSpeedX != 0)
            GlStateManager.rotate(this.age * this.parameters.rotSpeedX, 1, 0, 0);
        if (this.parameters.rotSpeedY != 0)
            GlStateManager.rotate(this.age * this.parameters.rotSpeedY, 0, 1, 0);
        if (this.parameters.rotSpeedZ != 0)
            GlStateManager.rotate(this.age * this.parameters.rotSpeedZ, 0, 0, 1);
    }

    protected void postRender() {}

}
