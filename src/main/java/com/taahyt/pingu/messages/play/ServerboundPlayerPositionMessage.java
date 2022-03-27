package com.taahyt.pingu.messages.play;

import com.taahyt.pingu.PinguFramework;
import com.taahyt.pingu.messages.AbstractMessage;
import com.taahyt.pingu.player.Player;
import com.taahyt.pingu.util.packet.PacketBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.net.InetSocketAddress;

public class ServerboundPlayerPositionMessage extends AbstractMessage {

    public ServerboundPlayerPositionMessage() {
        super(0x11);
    }

    @Override
    public void deserialize(ChannelHandlerContext channel, PacketBuffer buf) {
        System.out.println("DESERIALIZING PLAYER POS");
        double x = buf.readDouble();
        double y = buf.readDouble();
        double z = buf.readDouble();
        boolean onGround = buf.readBoolean();
        Player player = PinguFramework.getServer().getPlayer((InetSocketAddress) channel.channel().remoteAddress());
        if (player != null) {
            player.setLocation(player.getLocation().update(x, y, z));
            player.setOnGround(onGround);
        }
    }

    @Override
    public ByteBuf serialize(ChannelHandlerContext channel) {
        return null;
    }
}
