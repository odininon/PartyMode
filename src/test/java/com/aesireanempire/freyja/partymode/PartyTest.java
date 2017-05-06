package com.aesireanempire.freyja.partymode;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;

public class PartyTest {
    @Mock
    EntityPlayer player;

    @Mock
    EntityPlayer player2;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testContainsMember() {
        Party party = new Party(player);

        assertTrue(party.containsMember(player));
    }

    @Test
    public void testAddMember() {
        Party party = new Party(player);
        party.addMember(player2);

        verify(player).sendMessage(new TextComponentString(anyString()));
    }

    @Test
    public void testGetSize() {
        Party party = new Party(player);
        assertEquals(party.getPartySize(), 1);
    }

    @Test
    public void testGetsMembers() {
        Party party = new Party(player);
        List<EntityPlayer> partyMembers = party.getPartyMembers();

        assertTrue(partyMembers.contains(player));
    }

    @Test
    public void testRemovesMember() {
        Party party = new Party(player);
        party.removeMember(player);
        List<EntityPlayer> partyMembers = party.getPartyMembers();

        assertFalse(partyMembers.contains(player));
    }
}
