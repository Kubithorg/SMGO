package fr.ironcraft.mcshow.utils;

import net.minecraft.client.renderer.GlStateManager;


public class RenderUtils {
    public static void color(Color color) {
        GlStateManager.color(color.getRedFloat(), color.getGreenFloat(), color.getBlueFloat(), color.getAlphaFloat());
    }
}
