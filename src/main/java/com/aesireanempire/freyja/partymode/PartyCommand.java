package com.aesireanempire.freyja.partymode;

import com.aesireanempire.freyja.partymode.commands.Command;
import com.aesireanempire.freyja.partymode.commands.CommandFactory;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;

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
            Command command = CommandFactory.getCommand(args[0]);

            System.arraycopy(args, 1, args, 0, args.length - 1);

            command.process(var1, args);
        }
        else
        {
            throw new WrongUsageException(getCommandUsage(var1));
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
