package com.stompzone.relay;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;

import javax.annotation.Nonnull;

public class BroadcastPlugin extends JavaPlugin {

    public BroadcastPlugin(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        super.setup();
        this.getCommandRegistry().registerCommand(
            new StompcastCommand("stompcast", "Broadcasts a message to everyone in your world", false)
    );
}


}
