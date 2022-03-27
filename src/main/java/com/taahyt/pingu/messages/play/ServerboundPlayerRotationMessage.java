package com.taahyt.pingu.messages.play;

import com.taahyt.pingu.PinguFramework;
import com.taahyt.pingu.messages.AbstractMessage;
import com.taahyt.pingu.player.Player;
import com.taahyt.pingu.util.packet.PacketBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.net.InetSocketAddress;

public class ServerboundPlayerRotationMessage extends AbstractMessage {

    public ServerboundPlayerRotationMessage() {
        super(0x13);
    }

    @Override
    public void deserialize(ChannelHandlerContext channel, PacketBuffer buf) {
        System.out.println("DESERIALIZING PLAYER ROTATION");
        float yaw = buf.readFloat();
        float pitch = buf.readFloat();
        boolean onGround = buf.readBoolean();
        Player player = PinguFramework.getServer().getPlayer((InetSocketAddress) channel.channel().remoteAddress());
        if (player != null) {
            player.setLocation(player.getLocation().update(pitch, yaw));
            player.setOnGround(onGround);
        }
    }

    @Override
    public ByteBuf serialize(ChannelHandlerContext channel) {
        return null;
    }
}
