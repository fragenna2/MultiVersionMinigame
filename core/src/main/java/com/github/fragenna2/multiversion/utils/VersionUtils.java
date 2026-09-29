package com.github.fragenna2.multiversion.utils;

public class VersionUtils {

    public static int getJavaMajorVersion(String serverVersion) {
        if (serverVersion.startsWith("1.")) {
            return Integer.parseInt(serverVersion.substring(2, 3));
        }

        int dot = serverVersion.indexOf(".");
        if (dot != -1) {
            return Integer.parseInt(serverVersion.substring(0, dot));
        }

        return Integer.parseInt(serverVersion);
    }

}
