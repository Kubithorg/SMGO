package org.kubithon.smgo.client.effect.spherebeam;

import org.kubithon.smgo.client.effect.Effect;
import org.kubithon.smgo.client.effect.EffectParameters;
import org.kubithon.smgo.client.utils.Color;
import org.kubithon.smgo.client.utils.RenderUtils;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SphereBeam extends Effect<EffectParameters> {

    public SphereBeam(EffectParameters parameters) {
        super(parameters);
    }

    @Override
    public void render(Tessellator tessellator, VertexBuffer vertexbuffer, float partialTicks) {
        GlStateManager.rotate((float) (Math.PI * this.age / 20), 0, 1, 0);
        float x, y, z, xb, yb, zb, r1 = 1, r2 = 15;
        GlStateManager.disableTexture2D();
        RenderUtils.color(Color.BLUEVIOLET);
        GlStateManager.glLineWidth(0.5f);

        vertexbuffer.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION);

        for (float u = 0; u < 2 * Math.PI; u += Math.PI / 12) {
            xb = (float) Math.cos(u);
            yb = (float) Math.sin(u);
            zb = xb;
            for (float v = 0; v < Math.PI; v += Math.PI / 12) {
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
