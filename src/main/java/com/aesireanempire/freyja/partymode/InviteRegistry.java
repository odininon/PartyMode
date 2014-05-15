package com.aesireanempire.freyja.partymode;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

import java.util.HashMap;

/**
 * Created by freyja
 */
public class InviteRegistry
{
    private static InviteRegistry INSTANCE = new InviteRegistry();

    private HashMap<String, Party> invitations = new HashMap<String, Party>();

    public static InviteRegistry getInstance()
    {
        return INSTANCE;
    }

    public static boolean addInvite(Party party, EntityPlayer player)
    {
        if (!getInstance().invitations.containsKey(player.getDisplayName()))
        {
            getInstance().invitations.put(player.getDisplayName(), party);
            player.addChatComponentMessage(new ChatComponentText("You have been invited to " + party.getPartyMembers().get(0).getDisplayName() + "'s party."));
            player.addChatComponentMessage(new ChatComponentText("To accept type /party accept"));
            return true;
        }

        return false;
    }

    public static Party getPlayerInvite(EntityPlayer player)
    {
        return getInstance().invitations.get(player.getDisplayName());
    }
}
