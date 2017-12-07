package org.kubithon.smgo.client.show;

import static net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher.staticPlayerX;
import static net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher.staticPlayerY;
import static net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher.staticPlayerZ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.kubithon.smgo.client.effect.Effect;
import org.kubithon.smgo.client.effect.EffectInfos;
import org.lwjgl.opengl.GL11;

import gnu.trove.map.TIntObjectMap;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Show {
    private List<Effect<?>> effects;
    private ClientShowInfos showInfos;
    private double          time;
    private boolean         isPaused;
    private double          x, y, z;
    private int             lastTimelineKey;

    public Show(ClientShowInfos infos, double x, double y, double z) {
        this.showInfos = infos;
        this.effects = Collections.synchronizedList(new ArrayList<>());
        this.isPaused = false;
        this.time = 0;
        this.lastTimelineKey = -1;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Show(ClientShowInfos infos, double x, double y, double z, double time) {
        this(infos, x, y, z);
        this.time = time;
    }

    public void reset() {
        this.effects.clear();
    }

    private TIntObjectMap<List<EffectInfos>> timeline() {
        return this.showInfos.getTimeline();
    }

    public void tick(double tickDuration) {
        if (!this.isPaused) {
            synchronized (this.effects) {
                for (Iterator<Effect<?>> iterator = this.effects.iterator(); iterator.hasNext();) {
                    Effect<?> effect = iterator.next();

                    if (effect.shouldBeRemoved()) {
                        effect.delete();
                        iterator.remove();
                    } else
                        effect.tick(this, tickDuration);
                }
            }

            int newLast = -1;

            for (int i : this.timeline().keys())
                if (this.lastTimelineKey < i && i <= this.time) {
                    for (Iterator<EffectInfos> iterator = this.timeline().get(i).iterator(); iterator.hasNext();) {
                        EffectInfos effectInfos = iterator.next();

                        this.addEffect((float) (this.time - i), effectInfos.buildEffect());
                    }
                    if (i > newLast)
                        newLast = i;
                }

            if (newLast >= 0)
                this.lastTimelineKey = newLast;

            this.time += tickDuration;
        }
    }

    public void addEffect(float startAge, Effect<?> effect) {
        if (effect != null) {
            effect.setAge(startAge);
            this.effects.add(effect);
        }
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
        return this.time > this.showInfos.getLastTick();
    }

    public void delete() {
        synchronized (this.effects) {
            for (Iterator<Effect<?>> iterator = this.effects.iterator(); iterator.hasNext();) {
                Effect<?> effect = iterator.next();
                effect.delete();
                iterator.remove();
            }
        }
    }
}
