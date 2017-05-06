package com.aesireanempire.freyja.partymode;

import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
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

    @Mock
    MinecraftServer minecraftServer;

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

        when(player.getDisplayNameString()).thenReturn("Bob");

        command.execute(minecraftServer, player, new String[]{"list"});

        verify(player).sendMessage(new TextComponentString("Party Members: "));
        verify(player).sendMessage(new TextComponentString("- Bob"));
    }

    @Test
    public void testInviteCommandSuccess() throws Exception {
        partyRegistry.registerParty(new Party(player));
        partyRegistry.registerParty(new Party(player2));

        when(player.getDisplayNameString()).thenReturn("Bob");
        when(player2.getDisplayNameString()).thenReturn("Roger");

        when(player.getEntityWorld()).thenReturn(world);
        when(world.getPlayerEntityByName(anyString())).thenReturn(player2);

        command.execute(minecraftServer, player, new String[]{"invite", "Rodger"});

        verify(player2).sendMessage(new TextComponentString("You have been invited to Bob's party."));
        verify(player).sendMessage(new TextComponentString("Invite sent to Rodger."));
    }

    @Test
    public void testInviteCommandFail() throws Exception {
        Party party = new Party(player);
        partyRegistry.registerParty(party);
        partyRegistry.registerParty(new Party(player2));

        when(player.getDisplayNameString()).thenReturn("Bob");
        when(player2.getDisplayNameString()).thenReturn("Roger");

        inviteRegistry.addInvite(new Invite(player, party, player2));

        when(player.getEntityWorld()).thenReturn(world);
        when(world.getPlayerEntityByName(anyString())).thenReturn(player2);

        command.execute(minecraftServer, player, new String[]{"invite", "Rodger"});

        verify(player).sendMessage(new TextComponentString("Rodger currently has pending invitations."));
    }

    @Test
    public void testAcceptCommandWithInvitations() throws Exception {
        Party party = new Party(player);
        partyRegistry.registerParty(party);
        partyRegistry.registerParty(new Party(player2));

        when(player.getDisplayNameString()).thenReturn("Bob");
        when(player2.getDisplayNameString()).thenReturn("Roger");

        inviteRegistry.addInvite(new Invite(player, party, player2));

        command.execute(minecraftServer, player2, new String[]{"accept"});

        verify(player).sendMessage(new TextComponentString("Roger has joined your party."));

        assertNull(inviteRegistry.getPlayerInvite(player2));
    }

    @Test
    public void testAcceptCommandWithNoInvitations() throws Exception {
        Party party = new Party(player);
        partyRegistry.registerParty(party);
        partyRegistry.registerParty(new Party(player2));

        when(player.getDisplayNameString()).thenReturn("Bob");
        when(player2.getDisplayNameString()).thenReturn("Roger");

        command.execute(minecraftServer, player2, new String[]{"accept"});

        verify(player2).sendMessage(new TextComponentString("You have no pending initiations."));
    }

    @Test(expected = WrongUsageException.class)
    public void testThrowsExceptionIfFaultyCommand() throws Exception {
        command.execute(minecraftServer, player, new String[]{"testgaeehrae"});
    }

    @Test(expected = WrongUsageException.class)
    public void testThrowsExecptionWithNoArugments() throws Exception {
        command.execute(minecraftServer, player, new String[]{});
    }

    @Test
    public void testAllowUserToLeaveParty() throws Exception {
        Party party = new Party(player);
        party.addMember(player2);

        when(player.getDisplayNameString()).thenReturn("Bob");
        when(player2.getDisplayNameString()).thenReturn("Roger");

        partyRegistry.registerParty(party);

        assertEquals(2, party.getPartySize());

        command.execute(minecraftServer, player, new String[]{"leave"});

        assertEquals(1, party.getPartySize());

        verify(player).sendMessage(new TextComponentString("You have left the party."));
        verify(player2).sendMessage(new TextComponentString("Bob has left your party."));
    }
}
