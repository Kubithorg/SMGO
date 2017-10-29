package org.kubithon.smgo.proxy;

import org.kubithon.smgo.client.registry.ShowsRegistry;
import org.kubithon.smgo.common.Smgo;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class ClientProxy extends CommonProxy {
    public static SoundEvent soundEvent;

    private void registerShows() {
        ShowsRegistry.register(Smgo.MODID, "beam", "show/beam.json");
        ShowsRegistry.register(Smgo.MODID, "beambunch", "show/beambunch.json");
        ShowsRegistry.register(Smgo.MODID, "torus", "show/torus.json");
        ShowsRegistry.register(Smgo.MODID, "surface", "show/surface.json");
        ShowsRegistry.register(Smgo.MODID, "torus2", "show/torus2.json");
        ShowsRegistry.register(Smgo.MODID, "sphere", "show/sphere.json");
        ShowsRegistry.register(Smgo.MODID, "spherebeam", "show/spherebeam.json");
        ShowsRegistry.register(Smgo.MODID, "aureole", "show/aureole.json");
        ShowsRegistry.register(Smgo.MODID, "quadstorm", "show/quadstorm.json");
        ShowsRegistry.register(Smgo.MODID, "click", "show/click.json");

        ResourceLocation location = new ResourceLocation(Smgo.MODID, "clicktrack");
        soundEvent = new SoundEvent(location);
        SoundEvent.REGISTRY.register(2038, location, soundEvent);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        this.registerShows();
    }
}
