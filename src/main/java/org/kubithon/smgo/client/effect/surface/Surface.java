package org.kubithon.smgo.client.effect.surface;

import org.kubithon.smgo.client.effect.Effect;
import org.kubithon.smgo.client.math.IExpression;
import org.kubithon.smgo.client.utils.RenderUtils;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class Surface extends Effect<SurfaceParameters> {

    public Surface(SurfaceParameters parameters) {
        super(parameters);
    }

    @Override
    public void render(Tessellator tessellator, VertexBuffer vertexbuffer, float partialTicks) {
        IExpression paramX = this.parameters.surfaceParamX;
        IExpression paramY = this.parameters.surfaceParamY;
        IExpression paramZ = this.parameters.surfaceParamZ;

        paramX.setVariable("age", this.age);

        GlStateManager.disableTexture2D();
        RenderUtils.color(this.parameters.color);
        GlStateManager.glLineWidth(0.5f);

        for (float u = this.parameters.fromU; u < this.parameters.toU; u += (this.parameters.toU
                - this.parameters.fromU) / this.parameters.partU) {
            paramX.setVariable("u", u);
            paramY.setVariable("u", u);
            paramZ.setVariable("u", u);
            vertexbuffer.begin(GL11.GL_LINE_LOOP, DefaultVertexFormats.POSITION);
            for (float v = this.parameters.fromV; v < this.parameters.toV; v += (this.parameters.toV
                    - this.parameters.fromV) / this.parameters.partV) {
                paramX.setVariable("v", v);
                paramY.setVariable("v", v);
                paramZ.setVariable("v", v);
                vertexbuffer.pos(paramX.getValue(), paramY.getValue(), paramZ.getValue()).endVertex();
            }
            tessellator.draw();
        }
        GlStateManager.enableTexture2D();
    }

}
