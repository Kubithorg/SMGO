package org.kubithon.smgo.client.json;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.io.Charsets;
import org.kubithon.smgo.client.ShowInfos;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;

public class JsonReader {

    /** The JsonObject that is the root of the file */
    private JsonObject root;

    public JsonReader(ResourceLocation resourceIn) {
        JsonParser jsonParser = new JsonParser();
        BufferedReader reader = null;
        IResource iResource = null;
        StringBuilder strBuilder = new StringBuilder();
        try {
            iResource = Minecraft.getMinecraft().getResourceManager().getResource(resourceIn);
            reader = new BufferedReader(new InputStreamReader(iResource.getInputStream(), Charsets.UTF_8));
            String s;
            while ((s = reader.readLine()) != null) {
                strBuilder.append(s);
            }
            Object object = jsonParser.parse(strBuilder.toString());
            this.root = (JsonObject) object;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (iResource != null) {
                    iResource.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ShowInfos readShowInfos() {
        return ShowInfos.read(this.root);
    }
}
