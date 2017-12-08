package org.kubithon.smgo.proxy;

import org.kubithon.smgo.client.show.ClientShowInfos;
import org.kubithon.smgo.client.show.ClientShowsManager;
import org.kubithon.smgo.common.exceptions.ShowLoadingException;
import org.kubithon.smgo.common.show.ShowInfos;

import com.google.gson.JsonObject;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
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
    }

    @Override
    public ShowInfos readShowInfos(JsonObject json) throws ShowLoadingException {
        return ClientShowInfos.read(json);
    }
}
