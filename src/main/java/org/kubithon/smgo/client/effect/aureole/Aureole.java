package org.kubithon.smgo.client.effect.aureole;

import static net.minecraft.util.math.MathHelper.cos;
import static net.minecraft.util.math.MathHelper.sin;

import org.kubithon.smgo.client.effect.Effect;
import org.kubithon.smgo.client.utils.RenderUtils;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class Aureole extends Effect<AureoleParameters> {

	public Aureole(AureoleParameters parameters) {
		super(parameters);
	}

	@Override
	public void render(Tessellator tessellator, VertexBuffer vertexbuffer, float partialTicks) {
	    float slide = (float) (2 * Math.PI / (parameters.getAmountOfSlides() * 2));
	    float r = parameters.getRadius();
	    GlStateManager.rotate((float) (Math.PI * this.age / 20), 0, 1, 0);
        GlStateManager.disableTexture2D();
        RenderUtils.color(parameters.getColor());
        GlStateManager.glLineWidth(0.5f);

        vertexbuffer.begin(GL11.GL_TRIANGLES, DefaultVertexFormats.POSITION);
        
        float u = 0.f;
        
        for (int i = 0; i < parameters.getAmountOfSlides(); i++) {
            vertexbuffer.pos(r* cos(u), r* sin(u), 0).endVertex();
            vertexbuffer.pos(r* cos(u + slide), r* sin(u + slide), 0).endVertex();
            vertexbuffer.pos(0, 0, 0).endVertex();
            u += 2 * slide;
        }
        
        tessellator.draw();
        GlStateManager.enableTexture2D();
	}
}
