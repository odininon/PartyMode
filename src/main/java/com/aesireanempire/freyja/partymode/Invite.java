package com.aesireanempire.freyja.partymode;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by freyja
 */
public class Invite {
    private final EntityPlayer creator;
    private final EntityPlayer invitee;
    private final Party party;

    public Invite(EntityPlayer creator, Party party, EntityPlayer invitee) {
        this.creator = creator;
        this.party = party;
        this.invitee = invitee;
    }

    public EntityPlayer getCreator() {
        return creator;
    }

    public Party getParty() {
        return party;
    }

    public EntityPlayer getInvitee() {
        return invitee;
    }
}
