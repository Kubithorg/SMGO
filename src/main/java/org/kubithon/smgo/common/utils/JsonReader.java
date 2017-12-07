package org.kubithon.smgo.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.apache.commons.io.Charsets;
import org.kubithon.smgo.common.Smgo;
import org.kubithon.smgo.common.show.ShowInfos;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonReader {

    /** The JsonObject that is the root of the file */
    private JsonObject root;

    public JsonReader(File file) {
        JsonParser jsonParser = new JsonParser();
        BufferedReader reader = null;
        StringBuilder strBuilder = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charsets.UTF_8));
            String s;
            while ((s = reader.readLine()) != null)
                strBuilder.append(s);
            Object object = jsonParser.parse(strBuilder.toString());
            this.root = (JsonObject) object;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ShowInfos readShowInfos() {
        return Smgo.proxy.readShowInfos(this.root);
    }
}
