package org.kubithon.smgo.client.utils;

import static java.lang.Character.isDigit;

//@SideOnly(Side.CLIENT) used in ShowInfos
public class Timing {
    public static int parseTime(String str) {
        // In case of "235t" or just 235
        if (str.contains("t") || isInteger(str))
            return Integer.parseInt(str.replaceAll("t", ""));

        if (str.contains("s") || str.contains("m") || str.contains("h")) {
            int pos = str.length() - 1;

            int seconds = 0;
            int i;
            while (pos > 0)
                switch (str.charAt(pos)) {
                case 's':
                    pos--;
                    i = 1;
                    while (pos >= 0 && isDigit(str.charAt(pos))) {
                        seconds += Character.getNumericValue(str.charAt(pos)) * i;
                        i *= 10;
                        pos--;
                    }
                    break;
                case 'm':
                    pos--;
                    i = 1;
                    while (pos >= 0 && isDigit(str.charAt(pos))) {
                        seconds += Character.getNumericValue(str.charAt(pos)) * i * 60;
                        i *= 10;
                        pos--;
                    }
                    break;
                case 'h':
                    pos--;
                    i = 1;
                    while (pos >= 0 && isDigit(str.charAt(pos))) {
                        seconds += Character.getNumericValue(str.charAt(pos)) * i * 3600;
                        i *= 10;
                        pos--;
                    }
                    break;
                default:
                    break;
                }

            return seconds * 20;
        }

        throw new RuntimeException("Invalid time format: '" + str + "'");
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
