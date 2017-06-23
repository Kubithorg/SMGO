package org.kubithon.smgo.proxy;

import org.kubithon.smgo.client.ShowInfos;
import org.kubithon.smgo.common.Smgo;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerShows() {
        ShowInfos showInfos = ShowInfos.read(new ResourceLocation(Smgo.MODID, "show/show_test.json"));
        GameRegistry.register(showInfos.setRegistryName(Smgo.MODID, "show_test"));
    }
}
