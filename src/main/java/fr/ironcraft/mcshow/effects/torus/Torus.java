package fr.ironcraft.mcshow.effects.torus;

import static fr.ironcraft.mcshow.utils.Maths.TWO_PI;
import static net.minecraft.util.math.MathHelper.cos;
import static net.minecraft.util.math.MathHelper.sin;

import org.lwjgl.opengl.GL11;

import fr.ironcraft.mcshow.effects.Effect;
import fr.ironcraft.mcshow.utils.Expression;
import fr.ironcraft.mcshow.utils.Maths;
import fr.ironcraft.mcshow.utils.RenderUtils;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;


public class Torus extends Effect<TorusParameters> {
    public Torus(TorusParameters parameters) {
        super(parameters);
    }

    @Override
    public void render(Tessellator tessellator, VertexBuffer vertexbuffer, float partialTicks) {
        GlStateManager.disableTexture2D();
        RenderUtils.color(parameters.getColor());
        GlStateManager.glLineWidth(0.5f);
        vertexbuffer.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION);

        //        putTorus(parameters.getSmallCircleRadius(), parameters.getBigCircleRadius(), parameters.getAmountOfSides(), parameters.getAmountOfRings(), vertexbuffer);
        putTorus(parameters.getSmallCircleRadius(), parameters.getBigCircleRadius(), parameters.getAmountOfSides(), parameters.getAmountOfRings(), vertexbuffer);

        tessellator.draw();
        GlStateManager.enableTexture2D();
    }

    void putTorus(Expression smallCircleRadius, float R, int nsides, int rings, VertexBuffer buffer) {
        float r, nextR;
        float ringDelta = TWO_PI / rings;
        float sideDelta = TWO_PI / nsides;
        float theta = 0.0f, cosTheta = 1.0f, sinTheta = 0.0f;
        for (int i = rings - 1; i >= 0; i--) {
            float theta1 = theta + ringDelta;
            float cosTheta1 = cos(theta1);
            float sinTheta1 = sin(theta1);
            float phi = 0.0f;
            
            r = eval(smallCircleRadius.with("angle", theta));
            nextR = eval(smallCircleRadius.with("angle", theta1));
            
            for (int j = nsides; j >= 0; j--) {
                phi += sideDelta;
                float cosPhi = cos(phi);
                float sinPhi = sin(phi);
                float dist = R + nextR * cosPhi;
                buffer.pos(x + cosTheta1 * dist, y + nextR * sinPhi, z - sinTheta1 * dist)
                      .endVertex();
                dist = R + r * cosPhi;
                buffer.pos(x + cosTheta * dist, y + r * sinPhi, z - sinTheta * dist)
                      .endVertex();
            }
            theta = theta1;
            cosTheta = cosTheta1;
            sinTheta = sinTheta1;
        }
    }
}
