package com.aesireanempire.freyja.partymode;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by freyja
 */
public class Party {
    private final ArrayList<EntityPlayer> partMembers = new ArrayList<EntityPlayer>();

    public Party(EntityPlayer... players) {
        Collections.addAll(this.partMembers, players);
    }

    public boolean containsMember(EntityPlayer player) {
        return partMembers.contains(player);
    }

    public int getPartySize() {
        return partMembers.size();
    }

    public ArrayList<EntityPlayer> getPartyMembers() {
        return this.partMembers;
    }

    public void addMember(EntityPlayer player) {
        notifyMembers(player, "%s has joined your party.");
        partMembers.add(player);
    }

    private void notifyMembers(EntityPlayer player, String message) {
        for (EntityPlayer play : partMembers) {
            if (!play.equals(player)) {
                play.sendMessage(new TextComponentString(String.format(message, player.getDisplayNameString())));
            }
        }
    }

    public void removeMember(EntityPlayer player) {
        partMembers.remove(player);
        notifyMembers(player, "%s has left your party.");
    }
}
