package com.aesireanempire.freyja.partymode;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by freyja
 */
public class InviteRegistry {
    private final Map<String, Party> invitations = new HashMap<String, Party>();

    public Party getPlayerInvite(EntityPlayer player) {
        return invitations.get(player.getDisplayNameString());
    }

    public boolean addInvite(Invite invite) {
        if (!invitations.containsKey(invite.getInvitee().getDisplayNameString())) {
            invitations.put(invite.getInvitee().getDisplayNameString(), invite.getParty());
            invite.getInvitee().sendMessage(new TextComponentString("You have been invited to " + invite.getCreator().getDisplayNameString() + "'s party."));
            invite.getInvitee().sendMessage(new TextComponentString("To accept type /party accept"));
            return true;
        }
        return false;
    }

    public void removePlayerInvite(EntityPlayer sender) {
        invitations.remove(sender.getDisplayNameString());
    }
}
