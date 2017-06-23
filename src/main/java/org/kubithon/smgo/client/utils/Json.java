package org.kubithon.smgo.client.utils;

import org.kubithon.smgo.client.effect.EffectInfos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class Json {
    public static final Gson GSON = (new GsonBuilder()).registerTypeAdapter(Color.class, new Color.JsonAdapter())
                                                       .registerTypeAdapter(EffectInfos.class, new EffectInfos.Deserializer())
                                                       .registerTypeAdapter(Expression.class, new Expression.JsonAdapter())
                                                       .create();
}
