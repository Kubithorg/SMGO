package org.kubithon.smgo.client;

import static net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher.staticPlayerX;
import static net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher.staticPlayerY;
import static net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher.staticPlayerZ;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.kubithon.smgo.client.effect.Effect;
import org.kubithon.smgo.client.effect.EffectInfos;
import org.lwjgl.opengl.GL11;

import gnu.trove.map.TIntObjectMap;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;

public class Show {
    private List<Effect<?>> effects;
    private ShowInfos       showInfos;
    private int             time;
    private boolean         isPaused;
    private double          x, y, z;

    public Show(ShowInfos infos, double x, double y, double z) {
        this.showInfos = infos;
        this.effects = new ArrayList<>();
        this.isPaused = false;
        this.time = 0;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void reset() {
        this.effects.clear();
    }

    private TIntObjectMap<List<EffectInfos>> timeline() {
        return this.showInfos.getTimeline();
    }

    public void tick() {
        if (!this.isPaused) {
            for (Iterator<Effect<?>> iterator = this.effects.iterator(); iterator.hasNext();) {
                Effect<?> effect = iterator.next();

                if (effect.shouldBeRemoved())
                    iterator.remove();
                else
                    effect.tick(this);
            }

            if (this.timeline().containsKey(this.time))
                for (Iterator<EffectInfos> iterator = this.timeline().get(this.time).iterator(); iterator.hasNext();) {
                    EffectInfos effectInfos = iterator.next();

                    this.addEffect(effectInfos.buildEffect());
                }
            
            System.out.println("time = " + time);
            this.time++;
        }
    }

    public void addEffect(Effect<?> effect) {
        if (effect != null)
            this.effects.add(effect);
    }

    public void render(float partialTicks) {
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer vertexbuffer = tessellator.getBuffer();

        GlStateManager.pushMatrix();
        GlStateManager.translate(this.x - staticPlayerX, this.y - staticPlayerY, this.z - staticPlayerZ);

        RenderHelper.disableStandardItemLighting();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.enableBlend();
        GlStateManager.disableCull();

        for (Iterator<Effect<?>> iterator = this.effects.iterator(); iterator.hasNext();) {
            Effect<?> effect = iterator.next();

            effect.render(tessellator, vertexbuffer, partialTicks);
        }

        RenderHelper.enableStandardItemLighting();
        GlStateManager.popMatrix();
    }

    public boolean isDone() {
        return false;
    }
}
