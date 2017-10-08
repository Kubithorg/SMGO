package org.kubithon.smgo.client.audio;

import java.util.Map.Entry;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(value = Side.CLIENT)
public class SourceAudioEvent
{

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {

        EntityPlayer player = Minecraft.getMinecraft().player;
        World world = Minecraft.getMinecraft().world;

        if (event.phase == TickEvent.Phase.START) {
            if (world != null)
                if (SoundHandler.soundPlaying.size() > 0) {
                    if (world == null || player == null) {
                        SoundHandler.soundPlaying.clear();
                    }
                    else {
                        for (Entry<String, SoundManager> entry : SoundHandler.soundPlaying.entrySet()) {
                            if (entry.getValue().getCurrentSong() == null || entry.getValue().getCurrentSong().volume == null)
                                continue;
                            if (entry.getValue().getCurrentSong().dimension == 1234)
                                entry.getValue().getCurrentSong().volume.setValue(-20F);
                            else if (entry.getValue().getCurrentSong().dimension != world.provider.getDimension())
                                entry.getValue().getCurrentSong().volume.setValue(-80F);
                            else {

                                float dist = (float) Math.abs(Math.sqrt(Math.pow(player.posX - entry.getValue().getCurrentSong().x, 2)
                                        + Math.pow(player.posY - entry.getValue().getCurrentSong().y, 2)
                                        + Math.pow(player.posZ - entry.getValue().getCurrentSong().z, 2)));

                                float d = (float) Math.abs(Math.sqrt(Math.pow(player.posX - entry.getValue().getCurrentSong().x, 2)
                                        + Math.pow(player.posY - entry.getValue().getCurrentSong().y, 2)
                                        + Math.pow(player.posZ - entry.getValue().getCurrentSong().z, 2)));
                                if (d < dist)
                                    dist = d;

                                if (dist > entry.getValue().getCurrentSong().playRadius + 10F)
                                    entry.getValue().getCurrentSong().volume.setValue(-80F);
                                else {
                                    float volume = dist * (50F / entry.getValue().getCurrentSong().playRadius
                                            / (Minecraft.getMinecraft().gameSettings.getSoundLevel(SoundCategory.MASTER)
                                                    * Minecraft.getMinecraft().gameSettings.getSoundLevel(SoundCategory.RECORDS)));
                                    if (volume > 80F)
                                        entry.getValue().getCurrentSong().volume.setValue(-80F);
                                    else
                                        entry.getValue().getCurrentSong().volume.setValue(0F - volume);
                                }
                            }
                        }
                    }
                }
        }
    }
}
