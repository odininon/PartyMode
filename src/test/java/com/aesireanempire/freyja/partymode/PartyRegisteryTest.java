package com.aesireanempire.freyja.partymode;

import net.minecraft.entity.player.EntityPlayer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class PartyRegisteryTest
{
    @Mock
    EntityPlayer player;

    @Mock
    EntityPlayer player2;

    @Mock
    Party party;

    PartyRegistery registery;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
        registery = new PartyRegistery();
    }

    @Test
    public void testRegisterParty() throws Exception
    {
        registery.registerParty(party);

        assertTrue(registery.getParties().contains(party));
    }

    @Test
    public void testRemovePlayerFromParties() throws Exception
    {
        registery.registerParty(new Party(player));
        registery.registerParty(new Party(player));
        registery.removePlayerFromParties(player);

        assertFalse(registery.getParties().contains(party));
    }

    @Test
    public void testGetPlayerPartyWithParty() throws Exception
    {
        Party party1 = new Party(player);
        registery.registerParty(party1);

        Party playerParty = registery.getPlayerParty(player);

        assertEquals(party1, playerParty);
    }

    @Test
    public void testGetPlayerPartyWithOutParty() throws Exception
    {
        registery.registerParty(party);

        Party playerParty = registery.getPlayerParty(player);

        assertNull(playerParty);
    }

    @Test
    public void testMovePlayerToParty() throws Exception
    {
        Party party2 = new Party(player2);

        registery.registerParty(party);
        registery.registerParty(party2);

        registery.movePlayerToParty(player, party2);

        assertEquals(registery.getPlayerParty(player), party2);
    }
}
