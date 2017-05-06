package com.aesireanempire.freyja.partymode;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

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
