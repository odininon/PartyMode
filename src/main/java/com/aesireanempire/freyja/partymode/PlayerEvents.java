package com.aesireanempire.freyja.partymode;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

/**
 * Created by freyja
 */
public class PlayerEvents {
    private final PartyRegistry partyRegistry;

    public PlayerEvents(PartyRegistry partyRegistry) {
        this.partyRegistry = partyRegistry;
    }

    @SubscribeEvent
    public void onPlayerJoined(PlayerEvent.PlayerLoggedInEvent event) {
        partyRegistry.registerParty(new Party(event.player));
    }

    @SubscribeEvent
    public void onPlayerDisconnect(PlayerEvent.PlayerLoggedOutEvent event) {
        partyRegistry.removePlayerFromParties(event.player);
    }
}
