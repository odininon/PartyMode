package com.aesireanempire.freyja.partymode.commands;

import com.aesireanempire.freyja.partymode.Party;
import com.aesireanempire.freyja.partymode.PartyMode;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

/**
 * Created by freyja
 */
public class LeaveCommand implements Command
{
    @Override public void process(ICommandSender sender, String[] args)
    {
        Party playerParty = PartyMode.getPartyRegistry().getPlayerParty((EntityPlayer) sender);

        if (playerParty != null)
        {
            playerParty.removeMember((EntityPlayer) sender);
            ((EntityPlayer) sender).addChatComponentMessage(new ChatComponentText("You have left the party."));

            PartyMode.getPartyRegistry().registerParty(new Party(((EntityPlayer) sender)));
        }
    }
}
