package com.aesireanempire.freyja.partymode.commands;

import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;

/**
 * Created by freyja
 */
public interface Command {
    void process(ICommandSender sender, String[] args) throws WrongUsageException;
}
