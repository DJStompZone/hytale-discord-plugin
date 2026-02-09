package com.stompzone.relay.bridge;

import com.hypixel.hytale.logger.HytaleLogger;
import com.stompzone.relay.chat.ChatBroadcaster;
import java.util.logging.Level;
import py4j.GatewayServer;

public final class Py4JHolder {

    private static final int DEFAULT_PORT = 25333;
    private static final EntryPoint ENTRY_POINT = new EntryPoint();

    private static GatewayServer gateway;
    private static HytaleLogger logger;
    private static PythonBridge python;

    private Py4JHolder() {}

    /**
     * Starts the Py4J GatewayServer with a safe, minimal entry point.
     *
     * @param pluginLogger Logger from the plugin (PluginBase#getLogger()).
     * @param port Port to listen on (default is 25333).
     */
    public static synchronized void start(HytaleLogger pluginLogger, int port) {
        logger = pluginLogger;

        if (gateway != null) {
            stop();
        }

        gateway = new GatewayServer(ENTRY_POINT, port);
        gateway.start();

        if (logger != null) {
            logger.at(Level.INFO).log("Py4J GatewayServer started on port %s", port);
        }
    }

    /** Convenience overload using the default port (25333). */
    public static synchronized void start(HytaleLogger pluginLogger) {
        start(pluginLogger, DEFAULT_PORT);
    }

    public static synchronized void stop() {
        if (gateway != null) {
            gateway.shutdown();
            gateway = null;
            python = null;

            if (logger != null) {
                logger.at(Level.INFO).log("Py4J GatewayServer stopped.");
            }
        }
    }

    /** Returns the currently registered Python bridge (may be null if not registered). */
    public static PythonBridge python() {
        return python;
    }

    /**
     * The only object exposed to Python as `entry_point`.
     * Keep this tiny on purpose.
     */
    public static final class EntryPoint {

        /** Python -> Java: register a bridge object for callbacks (Hytale -> Python). */
        public void registerPython(PythonBridge bridge) {
            python = bridge;
        }

        /** Python -> Java: Discord -> Hytale. */
        public void sendDiscordMessage(String message) {
            ChatBroadcaster.broadcastFromDiscord(message);
        }
    }
}
