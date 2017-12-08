package org.kubithon.smgo.client.effect.quadstorm;

import static org.kubithon.smgo.client.math.Maths.randomize;
import static org.kubithon.smgo.client.math.Maths.randomizeQuad;

import org.joml.Vector3f;
import org.kubithon.smgo.client.effect.PreCompiledEffect;
import org.kubithon.smgo.client.utils.Color;
import org.kubithon.smgo.client.utils.RenderUtils;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class QuadStorm extends PreCompiledEffect<QuadStormParameters> {

    public QuadStorm(QuadStormParameters parameters) {
        super(parameters);
    }

    @Override
    protected void setup(Tessellator tessellator, BufferBuilder vertexbuffer) {
        Vector3f origin = new Vector3f();
        Vector3f[] quadVertices = { new Vector3f(), new Vector3f(), new Vector3f(), new Vector3f() };

        GlStateManager.disableTexture2D();
        RenderUtils.color(Color.WHITE);
        GlStateManager.glLineWidth(1.5f);

        vertexbuffer.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION);

        for (int k = 0; k < this.parameters.getAmount(); k++) {
            randomize(this.parameters.getRadius(), origin);
            randomizeQuad(quadVertices, origin);

            int j;
            for (int i = 0; i < quadVertices.length; i++) {
                vertexbuffer.pos(quadVertices[i].x, quadVertices[i].y, quadVertices[i].z).endVertex();

                j = (i + 1) % quadVertices.length;

                vertexbuffer.pos(quadVertices[j].x, quadVertices[j].y, quadVertices[j].z).endVertex();
            }
        }
        tessellator.draw();

        GlStateManager.enableTexture2D();
    }

    @Override
    protected void preRender() {
        GlStateManager.rotate((float) (Math.PI * this.age / 5), 0, 1, 0);
    }
}
