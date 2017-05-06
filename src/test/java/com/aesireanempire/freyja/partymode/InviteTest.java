package com.aesireanempire.freyja.partymode;

import net.minecraft.entity.player.EntityPlayer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

/**
 * Created by freyja
 */
public class InviteTest {
    @Mock
    EntityPlayer player;

    @Mock
    EntityPlayer player2;

    @Mock
    Party party;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInviteHasCreator() {
        Invite invite = new Invite(player, party, player2);
        assertEquals(invite.getCreator(), player);
    }

    @Test
    public void testInviteHasParty() {
        Invite invite = new Invite(player, party, player2);
        assertEquals(invite.getParty(), party);
    }

    @Test
    public void testInviteHasInvitee() {
        Invite invite = new Invite(player, party, player2);
        assertEquals(invite.getInvitee(), player2);
    }
}
