package com.aesireanempire.freyja.partymode;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

import java.util.HashMap;

/**
 * Created by freyja
 */
public class InviteRegistry
{
    private HashMap<String, Party> invitations = new HashMap<String, Party>();

    public Party getPlayerInvite(EntityPlayer player)
    {
        return invitations.get(player.getDisplayName());
    }

    public boolean addInvite(Invite invite)
    {
        if (!invitations.containsKey(invite.getInvitee().getDisplayName()))
        {
            invitations.put(invite.getInvitee().getDisplayName(), invite.getParty());
            invite.getInvitee().addChatComponentMessage(new ChatComponentText("You have been invited to " + invite.getCreator().getDisplayName() + "'s party."));
            invite.getInvitee().addChatComponentMessage(new ChatComponentText("To accept type /party accept"));
            return true;
        }
        return false;
    }
}
