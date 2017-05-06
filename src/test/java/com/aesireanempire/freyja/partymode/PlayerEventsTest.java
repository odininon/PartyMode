package com.aesireanempire.freyja.partymode;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class PlayerEventsTest {

    @Mock
    private EntityPlayer player;

    private PartyRegistry partyRegistry;
    private PlayerEvents playerEvents;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        partyRegistry = new PartyRegistry();
        playerEvents = new PlayerEvents(partyRegistry);

        when(player.getDisplayNameString()).thenReturn("Bob");
    }

    @Test
    public void onPlayerJoined() throws Exception {
        PlayerEvent.PlayerLoggedInEvent playerLoggedInEvent = new PlayerEvent.PlayerLoggedInEvent(player);

        playerEvents.onPlayerJoined(playerLoggedInEvent);

        assertEquals(1, partyRegistry.getParties().size());
    }

    @Test
    public void onPlayerDisconnect() throws Exception {
        PlayerEvent.PlayerLoggedOutEvent playerLoggedOutEvent = new PlayerEvent.PlayerLoggedOutEvent(player);

        partyRegistry.registerParty(new Party(player));
        playerEvents.onPlayerDisconnect(playerLoggedOutEvent);

        assertEquals(0, partyRegistry.getParties().size());
    }

}