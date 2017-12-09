package org.kubithon.smgo.proxy;

import java.io.File;

import org.kubithon.smgo.common.Smgo;
import org.kubithon.smgo.common.command.CommandReloadShows;
import org.kubithon.smgo.common.command.CommandStartShow;
import org.kubithon.smgo.common.exceptions.ShowLoadingException;
import org.kubithon.smgo.common.network.StartShowMessage;
import org.kubithon.smgo.common.network.StartShowMessage.StartShowHandler;
import org.kubithon.smgo.common.registry.ShowsRegistry;
import org.kubithon.smgo.common.show.ShowInfos;
import org.kubithon.smgo.common.utils.SmgoConfig;

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
        File showDir = SmgoConfig.getFileFromString(SmgoConfig.showsPath);
        if (!showDir.exists())
            showDir.mkdir();
        for (File file : showDir.listFiles())
            if (file.isFile())
                ShowsRegistry.register(Smgo.MODID, file.getName().substring(0, file.getName().lastIndexOf(".")), file);
    }

    public ShowInfos readShowInfos(JsonObject json) throws ShowLoadingException {
        return ShowInfos.read(json);
    }
}
