package com.taahyt.pingu.messages.play;

import com.taahyt.pingu.PinguFramework;
import com.taahyt.pingu.chunk.ChunkData;
import com.taahyt.pingu.messages.AbstractMessage;
import com.taahyt.pingu.server.math.Location;
import com.taahyt.pingu.util.packet.PacketBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class ClientboundChunkDataMessage extends AbstractMessage {

    public ClientboundChunkDataMessage() {
        super(0x22);
    }

    @Override
    public void deserialize(ChannelHandlerContext channel, PacketBuffer buf) {
    }

    @Override
    public ByteBuf serialize(ChannelHandlerContext channel) {
        PacketBuffer buffer = new PacketBuffer();
        buffer.writeVarInt(getPacketId());

        // TODO: Properly implement ChunkData - requires block entity
        ChunkData data = new ChunkData();
        buffer.writeNbt(data.getHeightMaps());
        buffer.writeVarInt(0); // size
        buffer.writeByte(0); // data - https://wiki.vg/Chunk_Format#Data_structure
        buffer.writeVarInt(data.getBlockEntities().size());

        data.getBlockEntities().forEach((index, block) -> {
            Location location = new Location(PinguFramework.getServer().getWorld("overworld"), 0, 0, 0);
            buffer.writeByte((byte) (((int) location.x() & 15) << 4 | ((int) location.z() & 15)));
            buffer.writeShort((short) location.y());
            buffer.writeVarInt(block.getId());
            // nbt stuff
        });

        // TODO: Implement light

        return buffer;
    }
}
