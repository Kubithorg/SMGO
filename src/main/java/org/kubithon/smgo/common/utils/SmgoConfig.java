package org.kubithon.smgo.common.utils;

import java.io.File;

import org.kubithon.smgo.common.Smgo;

import net.minecraftforge.common.config.Config;

@Config(modid = Smgo.MODID)
public class SmgoConfig {

    @Config.Comment("Enable the debug mode, error will be fully printed in the logs.")
    public static boolean debug = false;

    @Config.Comment("Relative path from the game directory to the shows directory.")
    public static String showsPath = "shows";

    @Config.Comment("Relative path from the game directory to the musics directory.")
    public static String musicsPath = "shows/musics";

    public static File getFileFromString(String path) {
        String[] parts = path.split("/");
        String realPath = "";
        for (String part : parts)
            realPath = realPath + part + File.separator;
        realPath = realPath.substring(0, realPath.length() - 1);
        return new File(realPath);
    }
}
