package org.kubithon.smgo.client.effect.spherebeam;

import org.kubithon.smgo.client.effect.PreCompiledEffect;
import org.kubithon.smgo.client.utils.RenderUtils;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SphereBeam extends PreCompiledEffect<SphereBeamParameters> {

    public SphereBeam(SphereBeamParameters parameters) {
        super(parameters);
    }

    @Override
    public void setup(Tessellator tessellator, BufferBuilder vertexbuffer) {
        float x, y, z, xb, yb, zb, r1 = this.parameters.smallRadius, r2 = this.parameters.bigRadius;
        GlStateManager.disableTexture2D();
        RenderUtils.color(this.parameters.color);
        GlStateManager.glLineWidth(0.5f);

        vertexbuffer.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION);

        for (float u = 0; u < 2 * Math.PI; u += 2 * Math.PI / this.parameters.yBeam) {
            xb = (float) Math.cos(u);
            yb = (float) Math.sin(u);
            zb = xb;
            for (float v = 0; v < Math.PI; v += 2 * Math.PI / this.parameters.xBeam) {
                x = (float) (Math.sin(v) * xb);
                y = yb;
                z = (float) (Math.cos(v) * zb);
                vertexbuffer.pos(r1 * x, r1 * y, r1 * z).endVertex();
                vertexbuffer.pos(r2 * x, r2 * y, r2 * z).endVertex();
            }
        }
        tessellator.draw();
        GlStateManager.enableTexture2D();
    }

}
