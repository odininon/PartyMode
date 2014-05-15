package com.aesireanempire.freyja.partymode;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class PartyTest
{
    @Mock
    EntityPlayer player;

    @Mock
    EntityPlayer player2;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testContainsMember()
    {
        Party party = new Party(player);

        assertTrue(party.containsMember(player));
    }

    @Test
    public void testAddMember()
    {
        Party party = new Party(player);
        party.addMember(player2);

        verify(player).addChatComponentMessage(new ChatComponentText(anyString()));
    }

    @Test
    public void testGetSize()
    {
        Party party = new Party(player);
        assertEquals(party.getPartySize(), 1);
    }

    @Test
    public void testGetsMembers()
    {
        Party party = new Party(player);
        ArrayList<EntityPlayer> partyMembers = party.getPartyMembers();

        assertTrue(partyMembers.contains(player));
    }

    @Test
    public void testRemovesMember()
    {
        Party party = new Party(player);
        party.removeMember(player);
        ArrayList<EntityPlayer> partyMembers = party.getPartyMembers();

        assertFalse(partyMembers.contains(player));
    }
}
