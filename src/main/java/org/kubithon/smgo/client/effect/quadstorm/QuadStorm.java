package org.kubithon.smgo.client.effect.quadstorm;

import static org.kubithon.smgo.client.math.Maths.randomize;
import static org.kubithon.smgo.client.math.Maths.randomizeQuad;

import org.joml.Vector3f;
import org.kubithon.smgo.client.effect.Effect;
import org.kubithon.smgo.client.utils.Color;
import org.kubithon.smgo.client.utils.RenderUtils;
import org.kubithon.smgo.client.utils.UploadedRenderable;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class QuadStorm extends Effect<QuadStormParameters> {

    public QuadStorm(QuadStormParameters parameters) {
        super(parameters);
    }

    private UploadedRenderable renderable;

    @Override
    public void render(Tessellator tessellator, VertexBuffer vertexbuffer, float partialTicks) {
        if (renderable == null) {
            renderable = new UploadedRenderable();

            vertexbuffer.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION);

            // temp vars
            Vector3f[] vertexes = new Vector3f[4];
            Vector3f origin = new Vector3f();
            for (int k = 0; k < parameters.getAmount(); k++) {
                randomize(parameters.getRadius(), origin);
                randomizeQuad(vertexes, origin);

                int j;
                for (int i = 0; i < vertexes.length; i++) {
                    vertexbuffer.pos(vertexes[i].x, vertexes[i].y, vertexes[i].z)
                          .endVertex();

                    j = (i + 1) % vertexes.length;

                    vertexbuffer.pos(vertexes[j].x, vertexes[j].y, vertexes[j].z)
                          .endVertex();
                }
            }
            vertexbuffer.finishDrawing();
            renderable.upload(vertexbuffer);
        }

        GlStateManager.rotate((float) (Math.PI * this.age / 20), 0, 1, 0);
        GlStateManager.disableTexture2D();
        RenderUtils.color(Color.WHITE);
        GlStateManager.glLineWidth(1.5f);
        {
            renderable.draw();
        }
        GlStateManager.enableTexture2D();
    }
}
