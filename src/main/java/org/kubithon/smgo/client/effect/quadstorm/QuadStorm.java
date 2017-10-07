package org.kubithon.smgo.client.effect.quadstorm;

import static org.kubithon.smgo.client.math.Maths.randomize;
import static org.kubithon.smgo.client.math.Maths.randomizeQuad;

import org.kubithon.smgo.client.effect.Effect;
import org.kubithon.smgo.client.utils.Color;
import org.kubithon.smgo.client.utils.RenderUtils;
import org.kubithon.smgo.client.utils.Renderable;
import org.kubithon.smgo.client.utils.TempVars;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class QuadStorm extends Effect<QuadStormParameters> {

    public QuadStorm(QuadStormParameters parameters) {
        super(parameters);
    }

    private Renderable renderable;

    @Override
    public void render(Tessellator tessellator, VertexBuffer vertexbuffer, float partialTicks) {
        if (renderable == null) {
            renderable = new Renderable();

            TempVars tempVars = TempVars.get();
            vertexbuffer.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION);

            // tempVars.quadra = quad vertices
            // tempVars.vect1 = origin
            
            for (int k = 0; k < parameters.getAmount(); k++) {
                randomize(parameters.getRadius(), tempVars.vect1);
                randomizeQuad(tempVars.quadra, tempVars.vect1);

                int j;
                for (int i = 0; i < tempVars.quadra.length; i++) {
                    vertexbuffer.pos(tempVars.quadra[i].x, tempVars.quadra[i].y, tempVars.quadra[i].z).endVertex();

                    j = (i + 1) % tempVars.quadra.length;

                    vertexbuffer.pos(tempVars.quadra[j].x, tempVars.quadra[j].y, tempVars.quadra[j].z).endVertex();
                }
            }
            vertexbuffer.finishDrawing();
            renderable.upload(vertexbuffer);

            tempVars.release();
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
