package org.kubithon.smgo.client.utils;

import static org.lwjgl.opengl.GL11.GL_COLOR_ARRAY;
import static org.lwjgl.opengl.GL11.GL_COMPILE;
import static org.lwjgl.opengl.GL11.GL_NORMAL_ARRAY;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_COORD_ARRAY;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL11.glCallList;
import static org.lwjgl.opengl.GL11.glColorPointer;
import static org.lwjgl.opengl.GL11.glDeleteLists;
import static org.lwjgl.opengl.GL11.glDisableClientState;
import static org.lwjgl.opengl.GL11.glEnableClientState;
import static org.lwjgl.opengl.GL11.glEndList;
import static org.lwjgl.opengl.GL11.glGenLists;
import static org.lwjgl.opengl.GL11.glNewList;
import static org.lwjgl.opengl.GL11.glNormalPointer;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoordPointer;
import static org.lwjgl.opengl.GL11.glVertexPointer;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

import java.util.List;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.VertexBufferUploader;
import net.minecraft.client.renderer.WorldVertexBufferUploader;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.client.renderer.vertex.VertexFormatElement.EnumUsage;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Renderable implements org.lwjgl.util.Renderable {

    // VBO
    private VertexBufferObject        vbo;
    private VertexBufferUploader      vertexBufferUploader;
    private WorldVertexBufferUploader directUploader;
    private VertexFormat              vertexformat;
    private int                       drawMode;

    // Display list
    private int displayList;

    public void upload(VertexBuffer buffer) {
        if (OpenGlHelper.useVbo()) {
            this.vertexformat = buffer.getVertexFormat();
            this.vbo = new VertexBufferObject(this.vertexformat);
            this.vertexBufferUploader = new VertexBufferUploader();
            this.vertexBufferUploader.setVertexBuffer(this.vbo);
            this.drawMode = buffer.getDrawMode();
        } else {
            this.directUploader = new WorldVertexBufferUploader();
            this.displayList = glGenLists(1);
        }

        if (OpenGlHelper.useVbo()) {
            this.vertexBufferUploader.draw(buffer);
        } else {
            glNewList(this.displayList, GL_COMPILE);
            glPushMatrix();
            this.directUploader.draw(buffer);
            glPopMatrix();
            glEndList();
        }
    }

    @Override
    public void render() {
        if (OpenGlHelper.useVbo()) {
            this.vbo.bindBuffer();
            int stride = this.vertexformat.getNextOffset();
            List<VertexFormatElement> list = this.vertexformat.getElements();

            for (int i = 0; i < list.size(); ++i) {
                preDraw(list.get(i).getUsage(), this.vertexformat, i, stride);
            }

            this.vbo.drawArrays(this.drawMode);

            for (int i = 0; i < list.size(); ++i) {
                postDraw(list.get(i).getUsage(), this.vertexformat, i, stride);
            }
            this.vbo.unbindBuffer();
        } else {
            glCallList(this.displayList);
        }
    }

    public void delete() {
        if (OpenGlHelper.useVbo()) {
            this.vbo.deleteGlBuffers();
        } else {
            glDeleteLists(this.displayList, 1);
        }
    }

    public static void preDraw(EnumUsage attrType, VertexFormat format, int element, int stride) {
        VertexFormatElement attr = format.getElement(element);
        int count = attr.getElementCount();
        int constant = attr.getType().getGlConstant();
        switch (attrType) {
        case POSITION:
            glVertexPointer(count, constant, stride, format.getOffset(element));
            glEnableClientState(GL_VERTEX_ARRAY);
            break;
        case NORMAL:
            if (count != 3) {
                throw new IllegalArgumentException("Normal attribute should have the size 3: " + attr);
            }
            glNormalPointer(constant, stride, format.getOffset(element));
            glEnableClientState(GL_NORMAL_ARRAY);
            break;
        case COLOR:
            glColorPointer(count, constant, stride, format.getOffset(element));
            glEnableClientState(GL_COLOR_ARRAY);
            break;
        case UV:
            OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit + attr.getIndex());
            glTexCoordPointer(count, constant, stride, format.getOffset(element));
            glEnableClientState(GL_TEXTURE_COORD_ARRAY);
            OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
            break;
        case PADDING:
            break;
        case GENERIC:
            glEnableVertexAttribArray(attr.getIndex());
            glVertexAttribPointer(attr.getIndex(), count, constant, false, stride, format.getOffset(element));
        default:
            FMLLog.severe("Unimplemented vanilla attribute upload: %s", attrType.getDisplayName());
        }
    }

    public static void postDraw(EnumUsage attrType, VertexFormat format, int element, int stride) {
        VertexFormatElement attr = format.getElement(element);
        switch (attrType) {
        case POSITION:
            glDisableClientState(GL_VERTEX_ARRAY);
            break;
        case NORMAL:
            glDisableClientState(GL_NORMAL_ARRAY);
            break;
        case COLOR:
            glDisableClientState(GL_COLOR_ARRAY);
            // is this really needed? NON mdr
            // GlStateManager.resetColor();
            break;
        case UV:
            OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit + attr.getIndex());
            glDisableClientState(GL_TEXTURE_COORD_ARRAY);
            OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
            break;
        case PADDING:
            break;
        case GENERIC:
            glDisableVertexAttribArray(attr.getIndex());
        default:
            FMLLog.severe("Unimplemented vanilla attribute upload: %s", attrType.getDisplayName());
        }
    }
}
