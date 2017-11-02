package org.kubithon.smgo.proxy;

import org.kubithon.smgo.client.ShowInfos;
import org.kubithon.smgo.client.show.ClientShowInfos;
import org.kubithon.smgo.client.show.ClientShowsManager;
import org.kubithon.smgo.common.Smgo;

import com.google.gson.JsonObject;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
    public static SoundEvent  soundEvent;
    public ClientShowsManager clientShowsManager;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        this.clientShowsManager = new ClientShowsManager();
        MinecraftForge.EVENT_BUS.register(this.clientShowsManager);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        this.registerSounds();
    }

    private void registerSounds() {
        ResourceLocation location = new ResourceLocation(Smgo.MODID, "clicktrack");
        soundEvent = new SoundEvent(location);
        SoundEvent.REGISTRY.register(2038, location, soundEvent);
    }

    @Override
    public ShowInfos readShowInfos(JsonObject json) {
        return ClientShowInfos.read(json);
    }
}
