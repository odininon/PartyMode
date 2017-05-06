package com.aesireanempire.freyja.partymode.commands;

import com.aesireanempire.freyja.partymode.Invite;
import com.aesireanempire.freyja.partymode.Party;
import com.aesireanempire.freyja.partymode.PartyMode;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;

/**
 * Created by freyja
 */
public class InviteCommand implements Command {
    @Override
    public void process(ICommandSender sender, String[] args) throws WrongUsageException {
        if (args.length == 0) {
            throw new WrongUsageException("Must include player name");
        }

        String playerName = args[0];
        Party playerParty = PartyMode.getPartyRegistry().getPlayerParty((EntityPlayer) sender);
        EntityPlayer player = sender.getEntityWorld().getPlayerEntityByName(playerName);
        if (PartyMode.getInviteRegistry().addInvite(new Invite((EntityPlayer) sender, playerParty, player))) {
            ((EntityPlayer) sender).sendMessage(new TextComponentString("Invite sent to " + playerName + "."));
        } else {
            ((EntityPlayer) sender).sendMessage(new TextComponentString(playerName + " currently has pending invitations."));
        }
    }
}
