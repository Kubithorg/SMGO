package fr.ironcraft.mcshow.effects.beambunch;

import static fr.ironcraft.mcshow.utils.Maths.PI;
import static fr.ironcraft.mcshow.utils.Maths.toRadians;
import static net.minecraft.client.renderer.vertex.DefaultVertexFormats.POSITION;
import static net.minecraft.util.math.MathHelper.cos;
import static net.minecraft.util.math.MathHelper.sin;
import static org.lwjgl.opengl.GL11.GL_LINES;

import org.lwjgl.opengl.GL11;

import fr.ironcraft.mcshow.effects.Effect;
import fr.ironcraft.mcshow.utils.Maths;
import fr.ironcraft.mcshow.utils.RenderUtils;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.MathHelper;


public class BeamBunch extends Effect<BeamBunchParameters> {
    public BeamBunch(BeamBunchParameters parameters) {
        super(parameters);
    }

    @Override
    public void render(Tessellator tessellator, VertexBuffer vertexbuffer, float partialTicks) {
        GlStateManager.pushAttrib();
        GlStateManager.disableTexture2D();
        GlStateManager.glLineWidth(parameters.getLineWidth());
        RenderUtils.color(parameters.getColor());
        vertexbuffer.begin(GL_LINES, POSITION);

        float angle, endX, endY, endZ;

        for (int i = 0; i < parameters.getAmount(); i++) {
            angle = i * (PI / parameters.getAmount()) + toRadians(parameters.getSpeed() * age);
            endX = x + cos(angle) * parameters.getBeamsLength();
            endY = y + sin(parameters.getAngle()) * parameters.getBeamsLength();
            endZ = z + sin(angle) * parameters.getBeamsLength();

            vertexbuffer.pos(x, y, z).endVertex();
            vertexbuffer.pos(endX, endY, endZ).endVertex();
        }

        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.popAttrib();
    }
}
