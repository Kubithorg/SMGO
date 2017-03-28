package fr.ironcraft.mcshow;

import java.util.ArrayList;
import static net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;

import fr.ironcraft.mcshow.effects.Effect;
import fr.ironcraft.mcshow.effects.EffectInfos;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;


public class Show {
    private List<Effect<?>> effects;
    private ShowInfos showInfos;
    private int time;
    private boolean isPaused;
    private double x, y, z;

    public Show(ShowInfos infos, double x, double y, double z) {
        this.showInfos = infos;
        effects = new ArrayList<Effect<?>>();
        isPaused = false;
        time = 0;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void reset() {
        effects.clear();
    }

    private Map<Integer, List<EffectInfos>> timeline() {
        return showInfos.getTimeline();
    }

    public void tick() {
        if (!isPaused) {
            for (Iterator<Effect<?>> iterator = effects.iterator(); iterator.hasNext();) {
                Effect<?> effect = iterator.next();

                if (effect.shouldBeRemoved()) {
                    iterator.remove();
                }
                else {
                    effect.tick(this);
                }
            }

            if (timeline().containsKey(time)) {
                for (Iterator<EffectInfos> iterator = timeline().get(time)
                                                                .iterator(); iterator.hasNext();) {
                    EffectInfos effectInfos = iterator.next();

                    addEffect(effectInfos.buildEffect());
                }
            }

            time++;
        }
    }

    public void addEffect(Effect<?> effect) {
        if (effect != null) {
            effects.add(effect);
        }
    }

    public void render(float partialTicks) {
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer vertexbuffer = tessellator.getBuffer();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x - staticPlayerX, y - staticPlayerY, z - staticPlayerZ);

        RenderHelper.disableStandardItemLighting();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.enableBlend();
        GlStateManager.disableCull();

        for (Iterator<Effect<?>> iterator = effects.iterator(); iterator.hasNext();) {
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
