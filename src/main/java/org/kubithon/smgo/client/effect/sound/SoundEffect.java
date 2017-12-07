package org.kubithon.smgo.client.effect.sound;

import java.io.File;

import org.kubithon.smgo.client.audio.SoundManager;
import org.kubithon.smgo.client.effect.Effect;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;

public class SoundEffect extends Effect<SoundParameters> {

    private boolean playing;
    private String  hash;

    public SoundEffect(SoundParameters parameters) {
        super(parameters);
    }

    @Override
    public void render(Tessellator tessellator, VertexBuffer vertexbuffer, float partialTicks) {
        if (this.playing && this.age < this.parameters.stopAfter)
            return;
        if (!this.playing) {
            this.playing = true;
            this.hash = SoundManager.getInstance().start(
                    new File("shows" + File.separator + "musics" + File.separator + this.parameters.fileName),
                    (int) ((this.parameters.startAt + this.age) * 50));
            return;
        }
        SoundManager.getInstance().stop(this.hash);
    }

    @Override
    public void delete() {
        SoundManager.getInstance().stop(this.hash);
    }

}
