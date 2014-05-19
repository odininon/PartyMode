package com.aesireanempire.freyja.partymode.commands;

import com.aesireanempire.freyja.partymode.Party;
import com.aesireanempire.freyja.partymode.PartyMode;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

/**
 * Created by freyja
 */
public class ListCommand implements Command
{
    @Override public void process(ICommandSender sender, String[] args)
    {
        sender.addChatMessage(new ChatComponentText("Party Members: "));
        Party party = PartyMode.getPartyRegistry().getPlayerParty((EntityPlayer) sender);
        {
            for (EntityPlayer player : party.getPartyMembers())
            {
                sender.addChatMessage(new ChatComponentText("- " + player.getDisplayName()));
            }
        }
    }
}
