package com.aesireanempire.freyja.partymode.commands;

import net.minecraft.command.ICommandSender;

/**
 * Created by freyja
 */
public interface Command {
    void process(ICommandSender sender, String[] args);
}
