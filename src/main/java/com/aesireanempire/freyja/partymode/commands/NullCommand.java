package com.aesireanempire.freyja.partymode.commands;

import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;

/**
 * Created by freyja
 */
public class NullCommand implements Command {
    @Override
    public void process(ICommandSender sender, String[] args) {
        throw new WrongUsageException("");
    }
}
