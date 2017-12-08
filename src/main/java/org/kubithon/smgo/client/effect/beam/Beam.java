package org.kubithon.smgo.client.effect.beam;

import org.kubithon.smgo.client.effect.Effect;
import org.kubithon.smgo.client.show.Show;
import org.kubithon.smgo.client.utils.RenderUtils;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Beam extends Effect<BeamParameters> {

    protected float endX;
    protected float endY;
    protected float endZ;

    public Beam(BeamParameters parameters) {
        super(parameters);
    }

    @Override
    public void tick(Show show, double tickDuration) {
        super.tick(show, tickDuration);

        this.passParams(this.parameters.getEndX());
        this.passParams(this.parameters.getEndY());
        this.passParams(this.parameters.getEndZ());

        this.endX = this.parameters.getEndX().getValue();
        this.endY = this.parameters.getEndY().getValue();
        this.endZ = this.parameters.getEndZ().getValue();
    }

    @Override
    public void render(Tessellator tessellator, VertexBuffer vertexbuffer, float partialTicks) {
        GlStateManager.disableTexture2D();
        GlStateManager.glLineWidth(this.parameters.lineWidth);
        RenderUtils.color(this.parameters.getColor());
        vertexbuffer.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION);
        vertexbuffer.pos(this.x, this.y, this.z).endVertex();
        vertexbuffer.pos(this.endX, this.endY, this.endZ).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
    }
}
