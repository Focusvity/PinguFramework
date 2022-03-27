package com.taahyt.pingu.messages.play;

import com.taahyt.pingu.PinguFramework;
import com.taahyt.pingu.messages.AbstractMessage;
import com.taahyt.pingu.player.Player;
import com.taahyt.pingu.util.packet.PacketBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.net.InetSocketAddress;

public class ServerboundPlayerPosAndRotMessage extends AbstractMessage {

    public ServerboundPlayerPosAndRotMessage() {
        super(0x12);
    }

    @Override
    public void deserialize(ChannelHandlerContext channel, PacketBuffer buf) {
        System.out.println("DESERIALIZING PLAYER POS AND ROT");
        double x = buf.readDouble();
        double y = buf.readDouble();
        double z = buf.readDouble();
        float yaw = buf.readFloat();
        float pitch = buf.readFloat();
        boolean onGround = buf.readBoolean();
        Player player = PinguFramework.getServer().getPlayer((InetSocketAddress) channel.channel().remoteAddress());
        if (player != null) {
            player.setLocation(player.getLocation().update(x, y, z, pitch, yaw));
            player.setOnGround(onGround);
        }
    }

    @Override
    public ByteBuf serialize(ChannelHandlerContext channel) {
        return null;
    }
}
