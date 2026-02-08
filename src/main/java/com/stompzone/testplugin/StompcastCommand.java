package com.stompzone.testplugin;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;

public class StompcastCommand extends AbstractPlayerCommand {

    public StompcastCommand(@Nonnull String name, @Nonnull String description, boolean requiresConfirmation) {
        super(name, description, requiresConfirmation);
    }

    @Override
    protected void execute(
            @Nonnull CommandContext commandContext,
            @Nonnull Store<EntityStore> store,
            @Nonnull Ref<EntityStore> ref,
            @Nonnull PlayerRef playerRef,
            @Nonnull World world
    ) {
        Message msg = Message.raw("[Stompcast] Hello from plugin land.")
                .bold(true)
                .color("#FFD54A");

        world.sendMessage(msg);

        // Optional: confirm back to the sender only (also via PlayerRef)
        playerRef.sendMessage(Message.raw("Sent. You are now officially a menace."));
    }
}
