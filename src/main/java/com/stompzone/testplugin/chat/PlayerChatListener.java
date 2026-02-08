package com.stompzone.testplugin.chat;

import java.util.concurrent.CompletableFuture;
import com.hypixel.hytale.event.EventRegistry;
import com.hypixel.hytale.event.IEventRegistry;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.event.events.player.PlayerChatEvent;
import com.stompzone.testplugin.bridge.Py4JHolder;
import com.stompzone.testplugin.chat.ChatBroadcaster;

public final class PlayerChatListener {

    private PlayerChatListener() {}

    public static void register(EventRegistry events, HytaleLogger logger) {
        events.registerAsyncGlobal(
                PlayerChatEvent.class,
                (CompletableFuture<PlayerChatEvent> future) -> future.thenApply(event -> {
                    if (event.isCancelled()) {
                        return event;
                    }

                    String content = event.getContent();
                    if (content == null || content.isBlank()) {
                        return event;
                    }

                    // Prevent Discord echo loop
                    if (content.startsWith(ChatBroadcaster.DISCORD_TAG)) {
                        return event;
                    }

                    String username = event.getSender().getUsername();
                    var python = Py4JHolder.python();
                    if (python != null) {
                        python.onHytaleChat(username, content);
                    }

                    return event;
                })
        );
    }
}