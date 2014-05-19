package com.aesireanempire.freyja.partymode;

import net.minecraft.entity.player.EntityPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by freyja
 */
public class PartyRegistery
{
    private final ArrayList<Party> parties = new ArrayList<Party>();

    public void registerParty(Party party)
    {
        parties.add(party);
    }

    public void removePlayerFromParties(EntityPlayer player)
    {
        for (int i = getParties().size() - 1; i >= 0; i--)
        {
            Party party = getParties().get(i);
            if (party.containsMember(player))
            {
                party.removeMember(player);
            }
        }

        cleanUpEmptyParties();
    }

    private void cleanUpEmptyParties()
    {
        for (int i = getParties().size() - 1; i >= 0; i--)
        {
            Party party = getParties().get(i);
            if (party.getPartySize() == 0)
            {
                removeParty(party);
            }
        }
    }

    private void removeParty(Party party)
    {
        parties.remove(party);
    }

    public List<Party> getParties()
    {
        return parties;
    }

    public Party getPlayerParty(EntityPlayer player)
    {
        for (Party party : parties)
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
