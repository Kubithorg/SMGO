package org.kubithon.smgo.client.effect.beam;

import org.kubithon.smgo.client.Show;
import org.kubithon.smgo.client.effect.Effect;
import org.kubithon.smgo.client.utils.Color;
import org.kubithon.smgo.client.utils.RenderUtils;
import org.lwjgl.opengl.GL11;

import com.google.gson.annotations.SerializedName;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;


public class Beam extends Effect<BeamParameters> {
    private float endX;
    private float endY;
    private float endZ;

    public Beam(BeamParameters parameters) {
        super(parameters);
    }

    @Override
    public void tick(Show show) {
        super.tick(show);

        endX = eval(parameters.getEndX());
        endY = eval(parameters.getEndY());
        endZ = eval(parameters.getEndZ());
    }

    @Override
    public void render(Tessellator tessellator, VertexBuffer vertexbuffer, float partialTicks) {
        GlStateManager.disableTexture2D();
        RenderUtils.color(parameters.getColor());
        vertexbuffer.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION);
        vertexbuffer.pos(x, y, z).endVertex();
        vertexbuffer.pos(endX, endY, endZ).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
    }
}
