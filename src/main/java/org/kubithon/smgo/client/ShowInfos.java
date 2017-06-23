package org.kubithon.smgo.client;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;
import org.kubithon.smgo.client.effect.EffectInfos;
import org.kubithon.smgo.client.utils.Json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry.Impl;

public class ShowInfos extends Impl<ShowInfos> {
    /**
     * This show's name.
     */
    @SerializedName("name")
    private String name;

    /**
     * This show's timeline. A mapping time -> effects happening.
     */
    @SerializedName("timeline")
    private Map<Integer, List<EffectInfos>> timeline;

    public ShowInfos(String name, Map<Integer, List<EffectInfos>> timeline) {
        this.name = name;
        this.timeline = timeline;
    }

    public ShowInfos() {}

    public Map<Integer, List<EffectInfos>> getTimeline() {
        return this.timeline;
    }

    @Override
    public String toString() {
        return "ShowInfos [name=" + this.name + ", timeline=" + this.timeline + "]";
    }

    public static ShowInfos read(ResourceLocation resourceIn) {
        IResource iResource = null;
        InputStream stream = null;
        JsonObject jsonObject = null;
        try {
            iResource = Minecraft.getMinecraft().getResourceManager().getResource(resourceIn);
            stream = new BufferedInputStream(iResource.getInputStream());
            StringWriter writer = new StringWriter();
            IOUtils.copy(stream, writer, "UTF-8");
            String theString = writer.toString();
            jsonObject = (JsonObject) new JsonParser().parse(theString);

            // return Json.GSON.fromJson(theString, ShowInfos.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ShowInfos infos = new ShowInfos();

        infos.name = jsonObject.get("name").getAsString();
        infos.timeline = new HashMap<>();

        JsonObject obj = jsonObject.get("timeline").getAsJsonObject();
        ArrayList list;

        for (Entry<String, JsonElement> entry : obj.entrySet()) {
            infos.timeline.put(Integer.valueOf(entry.getKey()), list = new ArrayList<>());
            JsonArray array = entry.getValue().getAsJsonArray();
            for (JsonElement el : array)
                list.add(Json.GSON.fromJson(el.toString(), EffectInfos.class));
        }

        return infos;
    }
}
