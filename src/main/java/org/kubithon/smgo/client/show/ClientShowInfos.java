package org.kubithon.smgo.client.show;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.kubithon.smgo.client.ShowInfos;
import org.kubithon.smgo.client.effect.EffectInfos;
import org.kubithon.smgo.client.utils.Timing;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientShowInfos extends ShowInfos {

    /**
     * This show's timeline. A mapping time -> effects happening.
     */
    private TIntObjectMap<List<EffectInfos>> timeline;

    protected ClientShowInfos(JsonObject jsonObject) {
        super(jsonObject);

        this.timeline = new TIntObjectHashMap<>();

        JsonObject obj = jsonObject.get("timeline").getAsJsonObject();
        ArrayList<EffectInfos> list;
        int key;
        EffectInfos infos;

        for (Entry<String, JsonElement> entry : obj.entrySet()) {
            key = Timing.parseTime(entry.getKey());

            this.timeline.put(key, list = new ArrayList<>());
            JsonArray array = entry.getValue().getAsJsonArray();
            for (JsonElement el : array) {
                infos = EffectInfos.read(el.getAsJsonObject());
                if (this.lastTick < key + infos.getParameters().getMaxAge())
                    this.lastTick = key + infos.getParameters().getMaxAge();
                list.add(infos);
            }
        }
    }

    public TIntObjectMap<List<EffectInfos>> getTimeline() {
        return this.timeline;
    }

    @Override
    public String toString() {
        return "ShowInfos [timeline=" + this.timeline + "]";
    }

    public static ClientShowInfos read(JsonObject jsonObject) {
        return new ClientShowInfos(jsonObject);
    }

}
