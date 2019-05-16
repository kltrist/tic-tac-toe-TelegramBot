package gui.cmd;

public class Command {

    public final static String START = "start";
    public final static String START_C = "start -c";
    public final static String SAVE = "save";
    public final static String RESTORE = "restore_game";
    public final static String EXIT = "exit";

    public static String getHelp() {
        StringBuilder sb = new StringBuilder();
        sb.append("start       Start game (between people)\n");
        sb.append("start -c    Start game with computer\n");
        sb.append("save        Save game state\n");
        sb.append("restore_game     Restore game state\n");
        sb.append("exit        Exit\n");
        return sb.toString();
    }
}
