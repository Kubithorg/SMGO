package org.kubithon.smgo.proxy;

import org.kubithon.smgo.common.Smgo;
import org.kubithon.smgo.common.network.StartShowMessage;
import org.kubithon.smgo.common.network.StartShowMessage.StartShowHandler;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        Smgo.NETWORK.registerMessage(StartShowHandler.class, StartShowMessage.class, 0, Side.CLIENT);
    }

    public void init(FMLInitializationEvent event) {}
}
