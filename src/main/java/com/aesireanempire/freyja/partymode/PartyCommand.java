package com.aesireanempire.freyja.partymode;

import com.aesireanempire.freyja.partymode.commands.Command;
import com.aesireanempire.freyja.partymode.commands.CommandFactory;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by freyja
 */
public class PartyCommand implements ICommand {

    @Override
    public String getName() {
        return "party";
    }

    @Override
    public String getUsage(ICommandSender iCommandSender) {
        return "party {list|invite}";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("party", "p");
    }

    @Override
    public void execute(MinecraftServer minecraftServer, ICommandSender iCommandSender, String[] args) throws CommandException {
        if (args.length > 0) {
            Command command = CommandFactory.getCommand(args[0]);

            System.arraycopy(args, 1, args, 0, args.length - 1);

            command.process(iCommandSender, args);
        } else {
            throw new WrongUsageException(getUsage(iCommandSender));
        }
    }

    @Override
    public boolean checkPermission(MinecraftServer minecraftServer, ICommandSender iCommandSender) {
        return true;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer minecraftServer, ICommandSender iCommandSender, String[] strings, @Nullable BlockPos blockPos) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] var1, int var2) {
        return false;
    }


    @Override
    public int compareTo(ICommand o) {
        return 0;
    }
}
