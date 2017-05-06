package com.aesireanempire.freyja.partymode;

import net.minecraft.entity.player.EntityPlayer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class PartyRegistryTest {
    @Mock
    EntityPlayer player;

    @Mock
    EntityPlayer player2;

    @Mock
    Party party;

    PartyRegistry registry;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        registry = new PartyRegistry();
    }

    @Test
    public void testRegisterParty() throws Exception {
        registry.registerParty(party);

        assertTrue(registry.getParties().contains(party));
    }

    @Test
    public void testRemovePlayerFromParties() throws Exception {
        Party party1 = new Party(player);
        registry.registerParty(party1);

        Party party2 = new Party(player2);
        registry.registerParty(party2);
        registry.removePlayerFromParties(player);

        assertFalse(registry.getParties().contains(party1));
    }

    @Test
    public void testGetPlayerPartyWithParty() throws Exception {
        Party party1 = new Party(player);
        registry.registerParty(party1);

        Party playerParty = registry.getPlayerParty(player);

        assertEquals(party1, playerParty);
    }

    @Test
    public void testGetPlayerPartyWithOutParty() throws Exception {
        registry.registerParty(party);

        Party playerParty = registry.getPlayerParty(player);

        assertNull(playerParty);
    }

    @Test
    public void testMovePlayerToParty() throws Exception {
        Party party1 = new Party(player);
        Party party2 = new Party(player2);

        registry.registerParty(party1);
        registry.registerParty(party2);

        registry.movePlayerToParty(player, party2);

        assertEquals(registry.getPlayerParty(player), party2);
    }
}
