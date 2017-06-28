package org.kubithon.smgo.client.effect.torus;

import static net.minecraft.util.math.MathHelper.cos;
import static net.minecraft.util.math.MathHelper.sin;

import org.kubithon.smgo.client.effect.Effect;
import org.kubithon.smgo.client.math.IExpression;
import org.kubithon.smgo.client.utils.RenderUtils;
import org.lwjgl.opengl.GL11;

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
        RenderUtils.color(this.parameters.getColor());
        GlStateManager.glLineWidth(0.5f);
        vertexbuffer.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION);

        // putTorus(parameters.getSmallCircleRadius(),
        // parameters.getBigCircleRadius(), parameters.getAmountOfSides(),
        // parameters.getAmountOfRings(), vertexbuffer);
        this.putTorus(this.parameters.getSmallCircleRadius(), this.parameters.getBigCircleRadius(),
                this.parameters.getAmountOfSides(), this.parameters.getAmountOfRings(), vertexbuffer);

        tessellator.draw();
        GlStateManager.enableTexture2D();
    }

    void putTorus(IExpression smallCircleRadius, float R, int nsides, int rings, VertexBuffer buffer) {
        float r, nextR;
        float ringDelta = (float) (2 * Math.PI / rings);
        float sideDelta = (float) (2 * Math.PI / nsides);
        float theta = 0.0f, cosTheta = 1.0f, sinTheta = 0.0f;
        smallCircleRadius.setVariable("age", this.age);
        for (int i = rings - 1; i >= 0; i--) {
            float theta1 = theta + ringDelta;
            float cosTheta1 = cos(theta1);
            float sinTheta1 = sin(theta1);
            float phi = 0.0f;

            smallCircleRadius.setVariable("angle", theta);
            r = smallCircleRadius.getValue();
            smallCircleRadius.setVariable("angle", theta1);
            nextR = smallCircleRadius.getValue();

            for (int j = nsides; j >= 0; j--) {
                phi += sideDelta;
                float cosPhi = cos(phi);
                float sinPhi = sin(phi);
                float dist = R + nextR * cosPhi;
                buffer.pos(this.x + cosTheta1 * dist, this.y + nextR * sinPhi, this.z - sinTheta1 * dist).endVertex();
                dist = R + r * cosPhi;
                buffer.pos(this.x + cosTheta * dist, this.y + r * sinPhi, this.z - sinTheta * dist).endVertex();
            }
            theta = theta1;
            cosTheta = cosTheta1;
            sinTheta = sinTheta1;
        }
    }
}
