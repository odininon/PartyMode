package com.aesireanempire.freyja.partymode;

import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PartyCommandTest {
    @Mock
    EntityPlayer player;

    @Mock
    EntityPlayer player2;

    @Mock
    World world;

    PartyCommand command;

    InviteRegistry inviteRegistry;

    PartyRegistry partyRegistry;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        command = new PartyCommand();
        PartyMode.setInviteRegistry(new InviteRegistry());
        inviteRegistry = PartyMode.getInviteRegistry();

        PartyMode.setPartyRegistry(new PartyRegistry());
        partyRegistry = PartyMode.getPartyRegistry();
    }

    @Test
    public void testListCommand() throws Exception {
        PartyRegistry partyRegistry = PartyMode.getPartyRegistry();
        partyRegistry.registerParty(new Party(player));

        when(player.getDisplayName()).thenReturn("Bob");

        command.processCommand(player, new String[]{"list"});

        verify(player).addChatMessage(new ChatComponentText("Party Members: "));
        verify(player).addChatMessage(new ChatComponentText("- Bob"));
    }

    @Test
    public void testInviteCommandSuccess() throws Exception {
        partyRegistry.registerParty(new Party(player));
        partyRegistry.registerParty(new Party(player2));

        when(player.getDisplayName()).thenReturn("Bob");
        when(player2.getDisplayName()).thenReturn("Roger");

        when(player.getEntityWorld()).thenReturn(world);
        when(world.getPlayerEntityByName(anyString())).thenReturn(player2);

        command.processCommand(player, new String[]{"invite", "Rodger"});

        verify(player2).addChatComponentMessage(new ChatComponentText("You have been invited to Bob's party."));
        verify(player).addChatComponentMessage(new ChatComponentText("Invite sent to Rodger."));
    }

    @Test
    public void testInviteCommandFail() throws Exception {
        Party party = new Party(player);
        partyRegistry.registerParty(party);
        partyRegistry.registerParty(new Party(player2));

        when(player.getDisplayName()).thenReturn("Bob");
        when(player2.getDisplayName()).thenReturn("Roger");

        inviteRegistry.addInvite(new Invite(player, party, player2));

        when(player.getEntityWorld()).thenReturn(world);
        when(world.getPlayerEntityByName(anyString())).thenReturn(player2);

        command.processCommand(player, new String[]{"invite", "Rodger"});

        verify(player).addChatComponentMessage(new ChatComponentText("Rodger currently has pending invitations."));
    }

    @Test
    public void testAcceptCommandWithInvitations() throws Exception {
        Party party = new Party(player);
        partyRegistry.registerParty(party);
        partyRegistry.registerParty(new Party(player2));

        when(player.getDisplayName()).thenReturn("Bob");
        when(player2.getDisplayName()).thenReturn("Roger");

        inviteRegistry.addInvite(new Invite(player, party, player2));

        command.processCommand(player2, new String[]{"accept"});

        verify(player).addChatComponentMessage(new ChatComponentText("Roger has joined your party."));

        assertNull(inviteRegistry.getPlayerInvite(player2));
    }

    @Test
    public void testAcceptCommandWithNoInvitations() throws Exception {
        Party party = new Party(player);
        partyRegistry.registerParty(party);
        partyRegistry.registerParty(new Party(player2));

        when(player.getDisplayName()).thenReturn("Bob");
        when(player2.getDisplayName()).thenReturn("Roger");

        command.processCommand(player2, new String[]{"accept"});

        verify(player2).addChatComponentMessage(new ChatComponentText("You have no pending initiations."));
    }

    @Test(expected = WrongUsageException.class)
    public void testThrowsExceptionIfFaultyCommand() throws Exception {
        command.processCommand(player, new String[]{"testgaeehrae"});
    }

    @Test(expected = WrongUsageException.class)
    public void testThrowsExecptionWithNoArugments() throws Exception {
        command.processCommand(player, new String[]{});
    }

    @Test
    public void testAllowUserToLeaveParty() throws Exception {
        Party party = new Party(player);
        party.addMember(player2);

        when(player.getDisplayName()).thenReturn("Bob");
        when(player2.getDisplayName()).thenReturn("Roger");

        partyRegistry.registerParty(party);

        assertEquals(2, party.getPartySize());

        command.processCommand(player, new String[]{"leave"});

        assertEquals(1, party.getPartySize());

        verify(player).addChatComponentMessage(new ChatComponentText("You have left the party."));
        verify(player2).addChatComponentMessage(new ChatComponentText("Bob has left your party."));
    }
}
