package com.taahyt.pingu.messages.status;

import com.taahyt.pingu.messages.AbstractMessage;
import com.taahyt.pingu.util.packet.PacketBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class ClientboundRequestMessage extends AbstractMessage {

    public ClientboundRequestMessage() {
        super(0x00);
    }

    @Override
    public void deserialize(ChannelHandlerContext channel, PacketBuffer buf) {

    }

    @Override
    public ByteBuf serialize(ChannelHandlerContext channel) {
        System.out.println("Serializing Response");

        PacketBuffer buffer = new PacketBuffer();
        buffer.writeVarInt(0x00);

        JSONObject response = new JSONObject();

        JSONObject version = new JSONObject();
        version.put("name", "Pingu");
        version.put("protocol", 757);
        response.put("version", version);

        JSONObject players = new JSONObject();
        players.put("max", 20);
        players.put("online", 0);
        players.put("sample", new JSONArray());
        response.put("players", players);

        TextComponent component = Component.text("Pingu Framework").color(TextColor.color(0xff0000));
        JSONObject description = new JSONObject(new JSONTokener(GsonComponentSerializer.gson().serialize(component)));
        response.put("description", description);

        buffer.writeString(response.toString());
        return buffer;
    }
}
