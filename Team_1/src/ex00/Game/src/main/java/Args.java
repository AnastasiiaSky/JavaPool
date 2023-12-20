package Game.src.main.java;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")

public class Args {
    @Parameter(names = {"--enemiesCount", "-e"})
    private static String enemiesCount;
    @Parameter(names = {"--wallsCount", "-w"})
    private static String wallsCount;

    @Parameter(names = {"--size", "-s"})
    private static String size;

    @Parameter(names = {"--profile", "-p"})
    private static String profile;

    public static int getArgumentEnemiesCount() {
        return Integer.parseInt(enemiesCount);
    }

    public static int getArgumentWallsCount() {
        return Integer.parseInt(wallsCount);
    }

    public static int getArgumentSize() {
        return Integer.parseInt(size);
    }

    public static boolean getArgumentProfile() {
        return profile.equals("production");
    }
}

