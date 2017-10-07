package org.kubithon.smgo.client.effect.sword;

import static net.minecraft.client.renderer.GlStateManager.glVertexPointer;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL11.glEnableClientState;

import org.kubithon.smgo.client.effect.Effect;
import org.kubithon.smgo.client.utils.Color;
import org.kubithon.smgo.client.utils.RenderUtils;
import org.kubithon.smgo.client.utils.VertexBufferObject;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.VertexBufferUploader;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class Sword extends Effect<SwordParameters> {

    private static boolean            init;
    private static VertexBufferObject vbo;

    public Sword(SwordParameters parameters) {
        super(parameters);
    }

    @Override
    public void render(Tessellator tessellator, VertexBuffer vertexbuffer, float partialTicks) {

        if (!init) {
            VertexBuffer buffer = new VertexBuffer(DefaultVertexFormats.POSITION.getIntegerSize() * 1024);
            vbo = new VertexBufferObject(DefaultVertexFormats.POSITION);
            VertexBufferUploader vertexBufferUploader = new VertexBufferUploader();
            vertexBufferUploader.setVertexBuffer(vbo);

            buffer.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION);

            buffer.pos(partialTicks, partialTicks, partialTicks).endVertex();

            buffer.finishDrawing();
            vertexBufferUploader.draw(buffer);

            init = true;
        }

        GlStateManager.disableTexture2D();
        RenderUtils.color(Color.WHITE);
        GlStateManager.glLineWidth(1.5f);
        {
            vbo.bindBuffer();
            glVertexPointer(3, GL11.GL_FLOAT, 3 * Float.BYTES, 0);
            glEnableClientState(GL_VERTEX_ARRAY);
            vbo.drawArrays(GL11.GL_LINES);
            vbo.unbindBuffer();
        }
        GlStateManager.enableTexture2D();
    }
}
