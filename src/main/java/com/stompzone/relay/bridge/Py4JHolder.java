package com.stompzone.relay.bridge;

import com.hypixel.hytale.logger.HytaleLogger;
import com.stompzone.relay.chat.ChatBroadcaster;
import java.util.logging.Level;
import py4j.GatewayServer;

public final class Py4JHolder {

    private static GatewayServer gateway;
    private static PythonBridge python;
    private static HytaleLogger logger;

    private Py4JHolder() {}

    /**
     * Starts the Py4J gateway server so Python can call into the plugin.
     *
     * @param pluginLogger Logger from the plugin (PluginBase#getLogger()).
     */
    public static void start(HytaleLogger pluginLogger) {
        logger = pluginLogger;
        start();
    }

    /** Starts the Py4J gateway server without logging. Prefer {@link #start(HytaleLogger)}. */
    public static void start() {
        gateway = new GatewayServer(new EntryPoint());
        gateway.start();

        if (logger != null) {
            logger.at(Level.INFO).log("Py4J gateway started.");
        }
    }

    public static void stop() {
        if (gateway != null) {
            gateway.shutdown();
            gateway = null;
            python = null;

            if (logger != null) {
                logger.at(Level.INFO).log("Py4J gateway stopped.");
            }
        }
    }

    public static PythonBridge python() {
        return python;
    }

    public static final class EntryPoint {

        public void registerPython(PythonBridge bridge) {
            python = bridge;
        }

        /** Discord -> Hytale */
        public void sendDiscordMessage(String message) {
            ChatBroadcaster.broadcastFromDiscord(message);
        }
    }
}
