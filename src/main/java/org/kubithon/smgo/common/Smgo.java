package org.kubithon.smgo.common;

import org.apache.logging.log4j.Logger;
import org.kubithon.smgo.client.ShowInfos;
import org.kubithon.smgo.client.ShowsManager;
import org.kubithon.smgo.common.command.CommandStartShow;
import org.kubithon.smgo.proxy.CommonProxy;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.RegistryBuilder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void createRegistry(RegistryEvent.NewRegistry event) {
        RegistryBuilder<ShowInfos> builder = new RegistryBuilder<ShowInfos>();
        builder.setName(new ResourceLocation(MODID, "showinfos"));
        builder.setType(ShowInfos.class);
        builder.setIDRange(0, 4096);
        builder.create();
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerShows(RegistryEvent.Register<ShowInfos> event) {
        proxy.registerShows();
    }

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
    }

}
