package org.kubithon.smgo.client.utils;

import java.nio.FloatBuffer;

import org.joml.Matrix4fc;
import org.lwjgl.BufferUtils;

import net.minecraft.client.renderer.GlStateManager;

public class RenderUtils {
    public static void color(Color color) {
        GlStateManager.color(color.getRedFloat(), color.getGreenFloat(), color.getBlueFloat(), color.getAlphaFloat());
    }
    
    public static void multMatrix(Matrix4fc matrix) {
        TempVars tempVars = TempVars.get();
        tempVars.floatBuffer16.clear();
        matrix.get(tempVars.floatBuffer16);
        GlStateManager.multMatrix(tempVars.floatBuffer16);
        tempVars.release();
    }
}
