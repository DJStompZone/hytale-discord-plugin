package com.stompzone.relay;

import java.util.logging.Level;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.stompzone.relay.bridge.Py4JHolder;
import com.stompzone.relay.chat.PlayerChatListener;

import javax.annotation.Nonnull;

public class HyPhoenix extends JavaPlugin {

    public HyPhoenix(@Nonnull JavaPluginInit init) {
        super(init);
    }

//    @Override
//    public void start() {
//        super.start();
//        Py4JHolder.start(this, this.getLogger(), 25333);
//        getLogger().atInfo().log("StompZone HyPhoenix chat bridge loaded.");
//        PlayerChatListener.register(
//            getEventRegistry(),
//            getLogger()
//        );
//    }

@Override
public void start() {
    super.start();
    Py4JHolder.start(this.getLogger(), 25333);
    getLogger().atInfo().log("StompZone HyPhoenix chat bridge loaded.");
    PlayerChatListener.register(getEventRegistry(), getLogger());
}

//    @Override
//    public void start() {
//        getLogger().atInfo().log("StompZone HyPhoenix chat bridge loaded.");
//
//        PlayerChatListener.register(
//            getEventRegistry(),
//            getLogger()
//        );
//    }

    @Override
    protected void shutdown() {
//        super.shutdown();
        Py4JHolder.stop();
        super.shutdown();
    }
}
