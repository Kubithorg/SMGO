package org.kubithon.smgo.common.registry;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.kubithon.smgo.common.Smgo;
import org.kubithon.smgo.common.exceptions.ShowLoadingException;
import org.kubithon.smgo.common.show.ShowInfos;
import org.kubithon.smgo.common.utils.JsonReader;
import org.kubithon.smgo.common.utils.SmgoConfig;

import net.minecraft.util.ResourceLocation;

public class ShowsRegistry {
    private static Map<ResourceLocation, ShowRegInfos> registry = new HashMap<>();

    public static void register(String modid, String res, File jsonLoc) {
        register(new ResourceLocation(modid, res), jsonLoc);
    }

    public static void register(ResourceLocation res, File jsonLoc) {
        try {
            registry.put(res, new ShowRegInfos(jsonLoc));
        } catch (ShowLoadingException e) {
            e.addMessage("Error while loading \"" + jsonLoc.getPath() + "\"");
            e.logMessages();
            if (SmgoConfig.debug)
                e.printStackTrace();
        }
    }

    public static void reload() {
        Iterator<Entry<ResourceLocation, ShowRegInfos>> it = registry.entrySet().iterator();
        Entry<ResourceLocation, ShowRegInfos> entry;
        while (it.hasNext()) {
            entry = it.next();
            try {
                entry.getValue().load();
            } catch (ShowLoadingException e) {
                Smgo.logger.error("Error while loading \"" + entry.getValue().jsonLocation.getPath() + "\"");
                Smgo.logger.error(e.getMessage());
                if (SmgoConfig.debug)
                    e.printStackTrace();
                it.remove();
            }
        }
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

        public ShowRegInfos(File jsonLoc) throws ShowLoadingException {
            this.jsonLocation = jsonLoc;
            this.load();
        }

        public void load() throws ShowLoadingException {
            this.infos = new JsonReader(this.jsonLocation).readShowInfos();
        }
    }
}
