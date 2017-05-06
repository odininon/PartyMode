package com.aesireanempire.freyja.partymode.commands;

import com.aesireanempire.freyja.partymode.Party;
import com.aesireanempire.freyja.partymode.PartyMode;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;

/**
 * Created by freyja
 */
public class AcceptCommand implements Command {
    @Override
    public void process(ICommandSender sender, String[] args) {
        Party party = PartyMode.getInviteRegistry().getPlayerInvite((EntityPlayer) sender);

        if (party != null) {

            PartyMode.getPartyRegistry().movePlayerToParty((EntityPlayer) sender, party);
            PartyMode.getInviteRegistry().removePlayerInvite((EntityPlayer) sender);
        } else {
            ((EntityPlayer) sender).sendMessage(new TextComponentString("You have no pending initiations."));
        }
    }
}
