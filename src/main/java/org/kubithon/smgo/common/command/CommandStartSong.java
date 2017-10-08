package org.kubithon.smgo.common.command;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import org.kubithon.smgo.client.audio.SoundHandler;

import com.google.common.collect.Lists;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class CommandStartSong extends CommandBase
{
    private static final String NAME  = "startsong";
    private static final String USAGE = "/startsong x y z songName";

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
        if (args.length >= 4) {
            int x, y, z;
            try {
                x = Integer.valueOf(args[0]);
                y = Integer.valueOf(args[1]);
                z = Integer.valueOf(args[2]);
            } catch (NumberFormatException e) {
                throw new WrongUsageException(USAGE + "\nx, y or z is not a number.");
            }

            if (args[3].equals("test"))
                // Oui c'est dégueu, c'est du test donc osef.
                SoundHandler.playSoundFromStream(x, y, z, sender.getCommandSenderEntity().dimension, 10, "test",
                        "https://i.leviathan-studio.com/amateis/Halsey_Drive.wav");

        }
        else {
            throw new WrongUsageException(USAGE + "\nWrong number of arguments.");
        }
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos pos) {
        List<String> list = Lists.<String> newArrayList();
        switch (args.length) {
            case 1:
                list.add(String.valueOf((int) sender.getPositionVector().xCoord));
                return list;
            case 2:
                list.add(String.valueOf((int) sender.getPositionVector().yCoord));
                return list;
            case 3:
                list.add(String.valueOf((int) sender.getPositionVector().zCoord));
                return list;
            case 4:
                list.add("test");
                return list;
        }
        return Collections.<String> emptyList();
    }

}
