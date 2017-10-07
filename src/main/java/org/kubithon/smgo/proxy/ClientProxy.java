package org.kubithon.smgo.proxy;

import org.kubithon.smgo.client.ShowInfos;
import org.kubithon.smgo.client.json.JsonReader;
import org.kubithon.smgo.common.Smgo;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerShows() {
        ShowInfos showInfos = new JsonReader(new ResourceLocation(Smgo.MODID, "show/beam.json")).readShowInfos();
        GameRegistry.register(showInfos.setRegistryName(Smgo.MODID, "beam"));
        showInfos = new JsonReader(new ResourceLocation(Smgo.MODID, "show/beambunch.json")).readShowInfos();
        GameRegistry.register(showInfos.setRegistryName(Smgo.MODID, "beambunch"));
        showInfos = new JsonReader(new ResourceLocation(Smgo.MODID, "show/torus.json")).readShowInfos();
        GameRegistry.register(showInfos.setRegistryName(Smgo.MODID, "torus"));
        showInfos = new JsonReader(new ResourceLocation(Smgo.MODID, "show/surface.json")).readShowInfos();
        GameRegistry.register(showInfos.setRegistryName(Smgo.MODID, "surface"));
        showInfos = new JsonReader(new ResourceLocation(Smgo.MODID, "show/torus2.json")).readShowInfos();
        GameRegistry.register(showInfos.setRegistryName(Smgo.MODID, "torus2"));
        showInfos = new JsonReader(new ResourceLocation(Smgo.MODID, "show/sphere.json")).readShowInfos();
        GameRegistry.register(showInfos.setRegistryName(Smgo.MODID, "sphere"));
        showInfos = new JsonReader(new ResourceLocation(Smgo.MODID, "show/spherebeam.json")).readShowInfos();
        GameRegistry.register(showInfos.setRegistryName(Smgo.MODID, "spherebeam"));
        showInfos = new JsonReader(new ResourceLocation(Smgo.MODID, "show/aureole.json")).readShowInfos();
        GameRegistry.register(showInfos.setRegistryName(Smgo.MODID, "aureole"));
        showInfos = new JsonReader(new ResourceLocation(Smgo.MODID, "show/quadstorm.json")).readShowInfos();
        GameRegistry.register(showInfos.setRegistryName(Smgo.MODID, "quadstorm"));
        showInfos = new JsonReader(new ResourceLocation(Smgo.MODID, "show/click.json")).readShowInfos();
        GameRegistry.register(showInfos.setRegistryName(Smgo.MODID, "click"));
    }
}
