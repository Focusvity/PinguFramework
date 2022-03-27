package com.taahyt.pingu.messages;

import com.taahyt.pingu.messages.handshaking.*;
import com.taahyt.pingu.messages.login.ServerboundLoginStartMessage;
import com.taahyt.pingu.messages.play.*;
import com.taahyt.pingu.messages.status.ClientboundPingMessage;
import com.taahyt.pingu.messages.status.ClientboundRequestMessage;
import com.taahyt.pingu.messages.status.ServerboundPingMessage;
import com.taahyt.pingu.messages.status.ServerboundRequestMessage;
import com.taahyt.pingu.util.Status;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

public enum Messages {
    HANDSHAKE(0x00, ServerboundHandshakeMessage::new, Status.HANDSHAKING),

    REQUEST(0x00, ServerboundRequestMessage::new, Status.STATUS),
    RESPONSE(0x00, ClientboundRequestMessage::new, Status.STATUS),

    PING(0x01, ServerboundPingMessage::new, Status.STATUS),
    PONG(0x01, ClientboundPingMessage::new, Status.STATUS),

    LOGIN_START(0x00, ServerboundLoginStartMessage::new, Status.LOGIN),

    TELEPORT_CONFIRM(0x00, ServerboundTeleportConfirmMessage::new, Status.PLAY),
    CHAT_MESSAGE(0x03, ServerboundChatMessage::new, Status.PLAY),
    CLIENT_STATUS(0x04, ServerboundClientStatusMessage::new, Status.PLAY),
    CLIENT_SETTINGS(0x05, ServerboundClientSettingsMessage::new, Status.PLAY),
    PLAYER_POSITION(0x11, ServerboundPlayerPositionMessage::new, Status.PLAY),
    //PLAYER_POS_AND_ROT(0x12, ServerboundPlayerPosAndRotMessage::new, Status.PLAY),
    PLAYER_ROTATION(0x13, ServerboundPlayerRotationMessage::new, Status.PLAY),
    PLAYER_MOVEMENT(0x14, ServerboundPlayerMovementMessage::new, Status.PLAY);

    @Getter
    private final int packetId;
    @Getter
    private final AbstractMessage message;
    @Getter
    private final Status status;

    Messages(int packetId, MessageSupplier supplier, Status status) {
        this.packetId = packetId;
        this.message = supplier.create();
        this.status = status;
    }

    public static Optional<AbstractMessage> getById(Status status, int packetId) {
        return Arrays.stream(values()).filter(packet -> packet.getPacketId() == packetId && packet.getStatus() == status)
            .map(Messages::getMessage).findFirst();
    }

    private interface MessageSupplier {
        AbstractMessage create();
    }
}
