package org.kubithon.smgo.proxy;

import org.kubithon.smgo.client.ShowInfos;
import org.kubithon.smgo.client.registry.ShowsRegistry;
import org.kubithon.smgo.common.Smgo;
import org.kubithon.smgo.common.command.CommandReloadShows;
import org.kubithon.smgo.common.command.CommandStartShow;
import org.kubithon.smgo.common.network.StartShowMessage;
import org.kubithon.smgo.common.network.StartShowMessage.StartShowHandler;

import com.google.gson.JsonObject;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        Smgo.logger = event.getModLog();

        MinecraftForge.EVENT_BUS.register(Smgo.showsManager);

        Smgo.NETWORK.registerMessage(StartShowHandler.class, StartShowMessage.class, 0, Side.CLIENT);
    }

    public void init(FMLInitializationEvent event) {
        this.registerShows();
    }

    public void startServer(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandStartShow());
        event.registerServerCommand(new CommandReloadShows());
    }

    protected void registerShows() {
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
    }

    public ShowInfos readShowInfos(JsonObject json) {
        return ShowInfos.read(json);
    }
}
