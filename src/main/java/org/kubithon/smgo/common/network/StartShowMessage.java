package org.kubithon.smgo.common.network;

import org.kubithon.smgo.client.Show;
import org.kubithon.smgo.client.ShowInfos;
import org.kubithon.smgo.client.registry.ShowsRegistry;
import org.kubithon.smgo.common.Smgo;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class StartShowMessage implements IMessage {

    public ResourceLocation res;
    public float            x, y, z, startTime;

    public StartShowMessage() {}

    public StartShowMessage(ResourceLocation res, float x, float y, float z, float startTime) {
        this.res = res;
        this.x = x;
        this.y = y;
        this.z = z;
        this.startTime = startTime;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.res = new ResourceLocation(ByteBufUtils.readUTF8String(buf));
        this.x = buf.readFloat();
        this.y = buf.readFloat();
        this.z = buf.readFloat();
        this.startTime = buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, this.res.toString());
        buf.writeFloat(this.x);
        buf.writeFloat(this.y);
        buf.writeFloat(this.z);
        buf.writeFloat(this.startTime);
    }

    public static class StartShowHandler implements IMessageHandler<StartShowMessage, IMessage> {

        @Override
        public IMessage onMessage(StartShowMessage message, MessageContext ctx) {
            ShowInfos showInfos = ShowsRegistry.get(message.res);
            if (showInfos == null)
                Smgo.logger.warn("Network Error : Show '" + message.res + "' not found.");
            else
                Smgo.showsManager.startShow(new Show(showInfos, message.x, message.y, message.z, message.startTime));
            return null;
        }
    }

}
