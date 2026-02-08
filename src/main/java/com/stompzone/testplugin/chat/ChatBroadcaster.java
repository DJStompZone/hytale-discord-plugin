package com.stompzone.testplugin.chat;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.universe.Universe;

public final class ChatBroadcaster {

    public static final String DISCORD_TAG = "[Discord]";

    private ChatBroadcaster() {}

    public static void broadcastFromDiscord(String msg) {
        Message message = Message.raw(DISCORD_TAG + " ")
                .color("#7289DA")
                .insert(Message.raw(msg));

        Universe.get().sendMessage(message);
    }
}
