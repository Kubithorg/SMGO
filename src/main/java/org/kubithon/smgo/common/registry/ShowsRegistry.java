package org.kubithon.smgo.common.registry;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.kubithon.smgo.common.show.ShowInfos;
import org.kubithon.smgo.common.utils.JsonReader;

import net.minecraft.util.ResourceLocation;

public class ShowsRegistry {
    private static Map<ResourceLocation, ShowRegInfos> registry = new HashMap<>();

    public static void register(String modid, String res, File jsonLoc) {
        register(new ResourceLocation(modid, res), jsonLoc);
    }

    public static void register(ResourceLocation res, File jsonLoc) {
        registry.put(res, new ShowRegInfos(jsonLoc));
    }

    public static void reload() {
        for (Entry<ResourceLocation, ShowRegInfos> entry : registry.entrySet())
            entry.getValue().load();
    }

    public static ShowInfos get(ResourceLocation res) {
        ShowRegInfos regInfos = registry.get(res);
        return regInfos == null ? null : regInfos.infos;
    }

    public static Set<ResourceLocation> getKeys() {
        return registry.keySet();
    }

    private static class ShowRegInfos {
        public ShowInfos infos;
        public File      jsonLocation;

        public ShowRegInfos(File jsonLoc) {
            this.jsonLocation = jsonLoc;
            this.load();
        }

        public void load() {
            this.infos = new JsonReader(this.jsonLocation).readShowInfos();
        }
    }
}
