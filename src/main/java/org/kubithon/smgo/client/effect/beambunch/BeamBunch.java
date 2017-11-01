package org.kubithon.smgo.client.effect.beambunch;

import static net.minecraft.client.renderer.vertex.DefaultVertexFormats.POSITION;
import static net.minecraft.util.math.MathHelper.cos;
import static net.minecraft.util.math.MathHelper.sin;
import static org.lwjgl.opengl.GL11.GL_LINES;

import org.kubithon.smgo.client.effect.Effect;
import org.kubithon.smgo.client.utils.RenderUtils;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BeamBunch extends Effect<BeamBunchParameters> {
    public BeamBunch(BeamBunchParameters parameters) {
        super(parameters);
    }

    @Override
    public void render(Tessellator tessellator, VertexBuffer vertexbuffer, float partialTicks) {
        GlStateManager.pushAttrib();
        GlStateManager.disableTexture2D();
        GlStateManager.glLineWidth(this.parameters.getLineWidth());
        RenderUtils.color(this.parameters.getColor());
        vertexbuffer.begin(GL_LINES, POSITION);

        float angle, endX, endY, endZ;

        for (int i = 0; i < this.parameters.getAmount(); i++) {
            angle = (float) (i * (Math.PI / this.parameters.getAmount())
                    + Math.toRadians(this.parameters.getSpeed() * this.age));
            endX = this.x + cos(angle) * this.parameters.getBeamsLength();
            endY = this.y + sin(this.parameters.getAngle()) * this.parameters.getBeamsLength();
            endZ = this.z + sin(angle) * this.parameters.getBeamsLength();

            vertexbuffer.pos(this.x, this.y, this.z).endVertex();
            vertexbuffer.pos(endX, endY, endZ).endVertex();
        }

        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.popAttrib();
    }
}
