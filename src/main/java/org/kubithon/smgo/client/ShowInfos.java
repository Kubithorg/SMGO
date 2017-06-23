package org.kubithon.smgo.client;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.kubithon.smgo.client.effect.EffectInfos;
import org.kubithon.smgo.client.utils.Json;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;

public class ShowInfos {
    /**
     * This show's name.
     */
    @SerializedName("name")
    private final String name;

    /**
     * This show's timeline. A mapping time -> effects happening.
     */
    @SerializedName("timeline")
    private final Map<Integer, List<EffectInfos>> timeline;

    public ShowInfos(String name, Map<Integer, List<EffectInfos>> timeline) {
        this.name = name;
        this.timeline = timeline;
    }

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
        try {
            iResource = Minecraft.getMinecraft().getResourceManager().getResource(resourceIn);
            stream = new BufferedInputStream(iResource.getInputStream());
            StringWriter writer = new StringWriter();
            IOUtils.copy(stream, writer, "UTF-8");
            String theString = writer.toString();

            return Json.GSON.fromJson(theString, ShowInfos.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}