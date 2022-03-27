package com.taahyt.pingu.messages.login;

import com.taahyt.pingu.messages.AbstractMessage;
import com.taahyt.pingu.util.packet.PacketBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import lombok.SneakyThrows;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;

public class ClientboundDisconnectMessage extends AbstractMessage {

    public TextComponent chat;

    public ClientboundDisconnectMessage() {
        super(0x00);
    }

    @Override
    public void deserialize(ChannelHandlerContext channel, PacketBuffer buf) {

    }

    @SneakyThrows
    @Override
    public ByteBuf serialize(ChannelHandlerContext channel) {
        PacketBuffer buffer = new PacketBuffer();
        System.out.println("hi");
        buffer.writeVarInt(this.getPacketId());
        buffer.writeString(GsonComponentSerializer.gson().serialize(chat));
        return buffer;
    }
}
