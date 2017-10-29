package org.kubithon.smgo.common.command;

import org.kubithon.smgo.client.registry.ShowsRegistry;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class CommandReloadShows extends CommandBase {
    private static final String NAME  = "reloadshows";
    private static final String USAGE = "/reloadshows";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return USAGE;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        ShowsRegistry.reload();
    }

}
