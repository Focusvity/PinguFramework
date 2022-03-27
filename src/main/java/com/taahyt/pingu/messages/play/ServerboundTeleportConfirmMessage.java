package com.taahyt.pingu.messages.play;

import com.taahyt.pingu.PinguFramework;
import com.taahyt.pingu.messages.AbstractMessage;
import com.taahyt.pingu.player.Player;
import com.taahyt.pingu.util.packet.PacketBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.net.InetSocketAddress;

public class ServerboundTeleportConfirmMessage extends AbstractMessage {

    public int teleportId;

    public ServerboundTeleportConfirmMessage() {
        super(0x00);
    }

    @Override
    public void deserialize(ChannelHandlerContext channel, PacketBuffer buf) {
        System.out.println("DESERIALIZING TELEPORT CONFIRM");
        this.teleportId = buf.readVarInt();
        Player player = PinguFramework.getServer().getPlayer((InetSocketAddress) channel.channel().remoteAddress());
        if (player != null) {
            ClientboundPlayerPositionMessage clientboundPlayerPositionMessage = new ClientboundPlayerPositionMessage(player.getLocation(),
                this.teleportId);
            channel.writeAndFlush(clientboundPlayerPositionMessage.serialize(channel));
        }

    }

    @Override
    public ByteBuf serialize(ChannelHandlerContext channel) {
        return null;
    }
}
