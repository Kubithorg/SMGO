package org.kubithon.smgo.client.effect.quadstorm;

import static org.kubithon.smgo.client.math.Maths.randomize;
import static org.kubithon.smgo.client.math.Maths.randomizeQuad;

import org.kubithon.smgo.client.effect.EffectWithRenderable;
import org.kubithon.smgo.client.utils.Color;
import org.kubithon.smgo.client.utils.RenderUtils;
import org.kubithon.smgo.client.utils.TempVars;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class QuadStorm extends EffectWithRenderable<QuadStormParameters> {

    public QuadStorm(QuadStormParameters parameters) {
        super(parameters);
    }

    @Override
    public void render(Tessellator tessellator, VertexBuffer vertexbuffer, float partialTicks) {
        super.checkInit(vertexbuffer);
        GlStateManager.rotate((float) (Math.PI * this.age / 20), 0, 1, 0);
        GlStateManager.disableTexture2D();
        RenderUtils.color(Color.WHITE);
        GlStateManager.glLineWidth(1.5f);
        {
            this.renderable.render();
        }
        GlStateManager.enableTexture2D();
    }

    @Override
    protected void buildRenderable(VertexBuffer vertexbuffer) {
        TempVars tempVars = TempVars.get();
        vertexbuffer.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION);

        // tempVars.quadra = quad vertices
        // tempVars.vect1 = origin

        for (int k = 0; k < this.parameters.getAmount(); k++) {
            randomize(this.parameters.getRadius(), tempVars.vect1);
            randomizeQuad(tempVars.quadra, tempVars.vect1);

            int j;
            for (int i = 0; i < tempVars.quadra.length; i++) {
                vertexbuffer.pos(tempVars.quadra[i].x, tempVars.quadra[i].y, tempVars.quadra[i].z).endVertex();

                j = (i + 1) % tempVars.quadra.length;

                vertexbuffer.pos(tempVars.quadra[j].x, tempVars.quadra[j].y, tempVars.quadra[j].z).endVertex();
            }
        }
        vertexbuffer.finishDrawing();
        this.renderable.upload(vertexbuffer);

        tempVars.release();
    }
}
