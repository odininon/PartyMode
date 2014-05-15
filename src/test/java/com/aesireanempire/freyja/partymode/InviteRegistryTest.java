package com.aesireanempire.freyja.partymode;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class InviteRegistryTest
{
    @Mock
    EntityPlayer player;

    @Mock
    EntityPlayer player2;

    @Mock
    Party party;

    InviteRegistry registry;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
        registry = new InviteRegistry();
    }

    @Test
    public void testAddInvite()
    {

    }

    @Test
    public void testGetPlayerInviteWithNoInvites()
    {

        Party playerInvite = registry.getPlayerInvite(player);

        assertNull(playerInvite);
    }

    @Test
    public void testGetPlayerInviteWithInvite()
    {
        registry.addInvite(new Invite(player, party, player2));
        Party playerInvite = registry.getPlayerInvite(player);

        assertEquals(playerInvite, party);
    }

    @Test
    public void testNotifiesPlayerOfBeingInvited()
    {
        when(player.getDisplayName()).thenReturn("Awesome");

        registry.addInvite(new Invite(player, party, player2));

        verify(player2).addChatComponentMessage(new ChatComponentText("You have been invited to Awesome's party."));
        verify(player2).addChatComponentMessage(new ChatComponentText("To accept type /party accept"));
    }

    @Test
    public void testReturnsTrueIfInvited()
    {
        boolean invite = registry.addInvite(new Invite(player, party, player2));

        assertTrue(invite);
    }

    @Test
    public void testReturnsFalseIfAlreadyInvited()
    {
        registry.addInvite(new Invite(player, party, player2));

        boolean invite = registry.addInvite(new Invite(player, party, player2));

        assertFalse(invite);
    }
}
