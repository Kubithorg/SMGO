package fr.ironcraft.mcshow.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.ironcraft.mcshow.effects.EffectInfos;


public class Json {
    public static final Gson GSON = (new GsonBuilder()).registerTypeAdapter(Color.class, new Color.JsonAdapter())
                                                       .registerTypeAdapter(EffectInfos.class, new EffectInfos.Deserializer())
                                                       .registerTypeAdapter(Expression.class, new Expression.JsonAdapter())
                                                       .create();
}
