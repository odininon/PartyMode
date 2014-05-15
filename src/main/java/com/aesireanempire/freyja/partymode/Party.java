package com.aesireanempire.freyja.partymode;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by freyja
 */
public class Party
{
    private ArrayList<EntityPlayer> partMembers = new ArrayList<EntityPlayer>();

    public Party(EntityPlayer... players)
    {
        Collections.addAll(this.partMembers, players);
    }

    public boolean containsMember(EntityPlayer player)
    {
        return partMembers.contains(player);
    }

    public int getPartySize()
    {
        return partMembers.size();
    }

    public ArrayList<EntityPlayer> getPartyMembers()
    {
        return this.partMembers;
    }

    public void addMember(EntityPlayer player)
    {
        for (EntityPlayer play : partMembers)
        {
            play.addChatComponentMessage(new ChatComponentText(player.getDisplayName() + " has joined your party."));
        }

        partMembers.add(player);
    }

    public void removeMember(EntityPlayer player)
    {
        partMembers.remove(player);
    }
}
