package com.aesireanempire.freyja.partymode;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

/**
 * Created by freyja
 */
public class PlayerEvents
{

    @SubscribeEvent
    public void onPlayerJoined(PlayerEvent.PlayerLoggedInEvent event)
    {
        PartyMode.getPartyRegistry().registerParty(new Party(event.player));
    }

    @SubscribeEvent
    public void onPlayerDisconnect(PlayerEvent.PlayerLoggedOutEvent event)
    {
        PartyMode.getPartyRegistry().removePlayerFromParties(event.player);
    }
}
