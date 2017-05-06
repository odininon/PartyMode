package com.aesireanempire.freyja.partymode.commands;

import com.aesireanempire.freyja.partymode.Party;
import com.aesireanempire.freyja.partymode.PartyMode;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;

/**
 * Created by freyja
 */
public class ListCommand implements Command {
    @Override
    public void process(ICommandSender sender, String[] args) {
        sender.sendMessage(new TextComponentString("Party Members: "));
        Party party = PartyMode.getPartyRegistry().getPlayerParty((EntityPlayer) sender);
        {
            for (EntityPlayer player : party.getPartyMembers()) {
                sender.sendMessage(new TextComponentString("- " + player.getDisplayNameString()));
            }
        }
    }
}
