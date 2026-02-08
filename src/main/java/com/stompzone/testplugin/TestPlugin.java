package com.stompzone.testplugin;

import java.util.logging.Level;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.stompzone.testplugin.bridge.Py4JHolder;
import com.stompzone.testplugin.chat.PlayerChatListener;

import javax.annotation.Nonnull;

public class TestPlugin extends JavaPlugin {

    public TestPlugin(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    public void start() {
        getLogger().atInfo().log("StompZone TestPlugin chat bridge loaded.");

        PlayerChatListener.register(
            getEventRegistry(),
            getLogger()
        );
    }

    @Override
    protected void shutdown() {
        super.shutdown();
        Py4JHolder.stop();
    }
}
