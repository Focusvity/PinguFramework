package com.taahyt.pingu.messages.play;

import com.taahyt.pingu.messages.AbstractMessage;
import com.taahyt.pingu.util.packet.PacketBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.util.UUID;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;

public class ClientboundChatMessage extends AbstractMessage {

    private final TextComponent message;
    private final UUID sender;

    public ClientboundChatMessage(TextComponent message, UUID sender) {
        super(0x0F);
        this.message = message;
        this.sender = sender;
    }

    @Override
    public void deserialize(ChannelHandlerContext channel, PacketBuffer buf) {
    }

    @Override
    public ByteBuf serialize(ChannelHandlerContext channel) {
        PacketBuffer buffer = new PacketBuffer();
        buffer.writeString(GsonComponentSerializer.gson().serialize(message));
        buffer.writeByte(0);
        buffer.writeUUID(sender);
        return null;
    }
}
