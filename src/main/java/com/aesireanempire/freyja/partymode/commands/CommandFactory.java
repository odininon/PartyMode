package com.aesireanempire.freyja.partymode.commands;

/**
 * Created by freyja
 */
public class CommandFactory {
    public static Command getCommand(String command) {
        if ("list".equalsIgnoreCase(command)) {
            return new ListCommand();
        }

        if ("accept".equalsIgnoreCase(command)) {
            return new AcceptCommand();
        }

        if ("leave".equalsIgnoreCase(command)) {
            return new LeaveCommand();
        }

        if ("invite".equalsIgnoreCase(command)) {
            return new InviteCommand();
        }

        return new NullCommand();

    }
}
