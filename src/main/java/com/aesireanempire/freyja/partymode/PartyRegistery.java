package com.aesireanempire.freyja.partymode;

import net.minecraft.entity.player.EntityPlayer;

import java.util.ArrayList;

/**
 * Created by freyja
 */
public class PartyRegistery
{
    private static PartyRegistery Instance = new PartyRegistery();

    private ArrayList<Party> parties = new ArrayList<Party>();

    private PartyRegistery()
    {

    }

    public static PartyRegistery getInstance()
    {
        return Instance;
    }

    public void registerParty(Party party)
    {
        getInstance().parties.add(party);
    }

    public void removePlayerFromParties(EntityPlayer player)
    {
        for (Party party : getParties())
        {
            if (party.containsMember(player))
            {
                party.removeMember(player);
            }
        }

        cleanUpEmptyParties();
    }

    private void cleanUpEmptyParties()
    {
        for (Party party : getParties())
        {
            if (party.getPartySize() == 0)
            {
                removeParty(party);
            }
        }
    }

    private void removeParty(Party party)
    {
        getInstance().parties.remove(party);
    }

    public ArrayList<Party> getParties()
    {
        return parties;
    }

    public Party getPlayerParty(EntityPlayer player)
    {
        for (Party party : getInstance().parties)
        {
            if (party.containsMember(player))
            {
                return party;
            }
        }
        return null;
    }

    public void movePlayerToParty(EntityPlayer player, Party party)
    {
        removePlayerFromParties(player);
        for (Party list : getParties())
        {
            if (list.equals(party))
            {
                list.addMember(player);
                break;
            }
        }
    }
}
