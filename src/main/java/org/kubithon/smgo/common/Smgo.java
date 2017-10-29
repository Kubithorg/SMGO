package org.kubithon.smgo.common;

import org.apache.logging.log4j.Logger;
import org.kubithon.smgo.client.ShowsManager;
import org.kubithon.smgo.common.command.CommandReloadShows;
import org.kubithon.smgo.common.command.CommandStartShow;
import org.kubithon.smgo.proxy.CommonProxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod.EventBusSubscriber
@Mod(modid = Smgo.MODID)
public class Smgo {
    public static final String MODID = "smgo";

    @Instance(MODID)
    public static Smgo   instance;
    public static Logger logger;

    @SidedProxy(clientSide = "org.kubithon.smgo.proxy.ClientProxy", serverSide = "org.kubithon.smgo.proxy.CommonProxy")
    public static CommonProxy proxy;

    public static ShowsManager showsManager = new ShowsManager();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        MinecraftForge.EVENT_BUS.register(showsManager);
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void serverStart(FMLServerStartingEvent e) {
        e.registerServerCommand(new CommandStartShow());
        e.registerServerCommand(new CommandReloadShows());
    }

}
