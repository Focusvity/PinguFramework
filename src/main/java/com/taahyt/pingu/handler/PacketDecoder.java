package com.taahyt.pingu.handler;

import com.taahyt.pingu.PinguFramework;
import com.taahyt.pingu.messages.AbstractMessage;
import com.taahyt.pingu.messages.Messages;
import com.taahyt.pingu.util.packet.PacketBuffer;
import com.taahyt.pingu.util.Status;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.Optional;

public class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        int i = in.readableBytes();
        if (i != 0) {
            PacketBuffer packetBuffer = new PacketBuffer(in);
            int j = packetBuffer.readVarInt();
            System.out.println("Incoming packet with length " + i + " and ID " + j);

            Status status = PinguFramework.CLIENTS.getOrDefault(ctx, Status.HANDSHAKING);

            Optional<AbstractMessage> optMessage = Messages.getById(status, j);
            optMessage.ifPresent(message -> {
                System.out.println("Incoming packet with length " + i + " and ID " + j);
                System.out.println("USING " + message.getClass().getName() + " PACKET");
                message.deserialize(ctx, packetBuffer);

                ByteBuf serializedMsg = message.serialize(ctx);
                if (serializedMsg != null) {
                    ctx.writeAndFlush(serializedMsg);
                }
            });
        }
    }
}
