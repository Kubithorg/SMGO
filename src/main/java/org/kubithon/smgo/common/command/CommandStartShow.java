package org.kubithon.smgo.common.command;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import org.kubithon.smgo.client.Show;
import org.kubithon.smgo.client.ShowInfos;
import org.kubithon.smgo.common.Smgo;

import com.google.common.collect.Lists;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class CommandStartShow extends CommandBase {
    private static final String NAME = "startshow";
    private static final String USAGE = "/startshow x y z showName";

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
            }
            catch (NumberFormatException e) {
                throw new WrongUsageException(USAGE + "\nx, y or z is not a number.");
            }
            ShowInfos showInfos = GameRegistry.findRegistry(ShowInfos.class)
                                              .getValue(new ResourceLocation(args[3]));

            if (showInfos == null) {
                sender.sendMessage(new TextComponentString("Show '" + args[3] + "' not found."));
            }
            else {
                Smgo.showsManager.startShow(new Show(showInfos, x + 0.5, y + 0.5, z + 0.5));
            }
        }
        else
            throw new WrongUsageException(USAGE + "\nWrong number of arguments.");
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
                return getListOfStringsMatchingLastWord(args, GameRegistry.findRegistry(ShowInfos.class)
                                                                          .getKeys());
        }
        return Collections.<String> emptyList();
    }

}
