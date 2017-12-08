package org.kubithon.smgo.client.effect.surface;

import org.kubithon.smgo.client.effect.PreCompiledEffect;
import org.kubithon.smgo.client.math.IExpression;
import org.kubithon.smgo.client.utils.RenderUtils;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Surface extends PreCompiledEffect<SurfaceParameters> {

    public Surface(SurfaceParameters parameters) {
        super(parameters);
    }

    @Override
    protected void setup(Tessellator tessellator, BufferBuilder vertexbuffer) {
        IExpression paramX = this.parameters.surfaceParamX;
        IExpression paramY = this.parameters.surfaceParamY;
        IExpression paramZ = this.parameters.surfaceParamZ;

        GlStateManager.disableTexture2D();
        RenderUtils.color(this.parameters.color);
        GlStateManager.glLineWidth(0.5f);

        for (float u = this.parameters.fromU; u <= this.parameters.toU; u += (this.parameters.toU
                - this.parameters.fromU) / this.parameters.partU)
            this.uSet(u, paramX, paramY, paramZ, tessellator, vertexbuffer);
        this.uSet(this.parameters.toU, paramX, paramY, paramZ, tessellator, vertexbuffer);
        GlStateManager.enableTexture2D();
    }

    private void uSet(float u, IExpression paramX, IExpression paramY, IExpression paramZ, Tessellator tessellator,
            BufferBuilder vertexbuffer) {
        paramX.setVariable("u", u);
        paramY.setVariable("u", u);
        paramZ.setVariable("u", u);
        vertexbuffer.begin(this.parameters.loop ? GL11.GL_LINE_LOOP : GL11.GL_LINE_STRIP,
                DefaultVertexFormats.POSITION);
        for (float v = this.parameters.fromV; v <= this.parameters.toV; v += (this.parameters.toV
                - this.parameters.fromV) / this.parameters.partV)
            this.vSet(v, paramX, paramY, paramZ, vertexbuffer);
        this.vSet(this.parameters.toV, paramX, paramY, paramZ, vertexbuffer);
        tessellator.draw();
    }

    private void vSet(float v, IExpression paramX, IExpression paramY, IExpression paramZ, BufferBuilder vertexbuffer) {
        paramX.setVariable("v", v);
        paramY.setVariable("v", v);
        paramZ.setVariable("v", v);
        vertexbuffer.pos(paramX.getValue(), paramY.getValue(), paramZ.getValue()).endVertex();
    }

}
