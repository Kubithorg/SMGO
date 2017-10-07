package org.kubithon.smgo.client.effect.beam;

import org.kubithon.smgo.client.Show;
import org.kubithon.smgo.client.effect.Effect;
import org.kubithon.smgo.client.utils.RenderUtils;
import org.lwjgl.opengl.GL11;

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
    public void tick(Show show, double tickDuration) {
        super.tick(show, tickDuration);

        this.endX = this.parameters.getEndX().getValue();
        this.endY = this.parameters.getEndY().getValue();
        this.endZ = this.parameters.getEndZ().getValue();
    }

    @Override
    public void render(Tessellator tessellator, VertexBuffer vertexbuffer, float partialTicks) {
        GlStateManager.disableTexture2D();
        RenderUtils.color(this.parameters.getColor());
        vertexbuffer.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION);
        vertexbuffer.pos(this.x, this.y, this.z).endVertex();
        vertexbuffer.pos(this.endX, this.endY, this.endZ).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
    }
}
