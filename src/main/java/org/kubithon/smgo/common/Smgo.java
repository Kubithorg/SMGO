package org.kubithon.smgo.common;

import org.apache.logging.log4j.Logger;
import org.kubithon.smgo.common.show.ShowsManager;
import org.kubithon.smgo.proxy.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

@Mod.EventBusSubscriber
@Mod(modid = Smgo.MODID)
public class Smgo {
    public static final String MODID = "smgo";

    @Instance(MODID)
    public static Smgo   instance;
    public static Logger logger;

    public static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);

    @SidedProxy(clientSide = "org.kubithon.smgo.proxy.ClientProxy", serverSide = "org.kubithon.smgo.proxy.CommonProxy")
    public static CommonProxy proxy;

    public static ShowsManager showsManager = new ShowsManager();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void serverStart(FMLServerStartingEvent event) {
        proxy.startServer(event);
    }

}
