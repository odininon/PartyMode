package com.aesireanempire.freyja.partymode;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

import java.util.Arrays;
import java.util.List;

/**
 * Created by freyja
 */
public class PartyCommand implements ICommand
{
    @Override public String getCommandName()
    {
        return "party";
    }

    @Override public String getCommandUsage(ICommandSender var1)
    {
        return "party {list|invite}";
    }

    @Override public List getCommandAliases()
    {
        return Arrays.asList("party", "p");
    }

    @Override public void processCommand(ICommandSender var1, String[] args)
    {
        if (args.length > 0)
        {
            String command = args[0];

            if ("list".equalsIgnoreCase(command))
            {
                processListCommand(var1);
                return;
            }

            if ("invite".equalsIgnoreCase(command))
            {
                if (args.length == 2)
                {
                    processInviteCase(var1, args[1]);
                    return;
                }
            }

            if ("accept".equalsIgnoreCase(command))
            {
                processAcceptCommand(var1);
                return;
            }

            if ("leave".equalsIgnoreCase(command))
            {
                processLeaveCommand(var1);
                return;
            }

            throw new WrongUsageException((getCommandUsage(var1)));
        }
        else
        {
            throw new WrongUsageException(getCommandUsage(var1));
        }
    }

    private void processLeaveCommand(ICommandSender sender)
    {
        Party playerParty = PartyMode.getPartyRegistry().getPlayerParty((EntityPlayer) sender);

        if (playerParty != null)
        {
            playerParty.removeMember((EntityPlayer) sender);
            ((EntityPlayer) sender).addChatComponentMessage(new ChatComponentText("You have left the party."));

            PartyMode.getPartyRegistry().registerParty(new Party(((EntityPlayer) sender)));
        }
    }

    private void processAcceptCommand(ICommandSender sender)
    {
        Party party = PartyMode.getInviteRegistry().getPlayerInvite((EntityPlayer) sender);

        if (party != null)
        {

            PartyMode.getPartyRegistry().movePlayerToParty((EntityPlayer) sender, party);
            PartyMode.getInviteRegistry().removePlayerInvite((EntityPlayer) sender);
        }
        else
        {
            ((EntityPlayer) sender).addChatComponentMessage(new ChatComponentText("You have no pending initiations."));
        }
    }

    private void processInviteCase(ICommandSender sender, String playerName)
    {
        Party playerParty = PartyMode.getPartyRegistry().getPlayerParty((EntityPlayer) sender);
        EntityPlayer player = sender.getEntityWorld().getPlayerEntityByName(playerName);
        if (PartyMode.getInviteRegistry().addInvite(new Invite((EntityPlayer) sender, playerParty, player)))
        {
            ((EntityPlayer) sender).addChatComponentMessage(new ChatComponentText("Invite sent to " + playerName + "."));
        }
        else
        {
            ((EntityPlayer) sender).addChatComponentMessage(new ChatComponentText(playerName + " currently has pending invitations."));
        }
    }

    private void processListCommand(ICommandSender sender)
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

    @Override public boolean canCommandSenderUseCommand(ICommandSender var1)
    {
        return true;
    }

    @Override public List addTabCompletionOptions(ICommandSender var1, String[] var2)
    {
        return null;
    }

    @Override public boolean isUsernameIndex(String[] var1, int var2)
    {
        return false;
    }

    @Override public int compareTo(Object o)
    {
        return 0;
    }
}
