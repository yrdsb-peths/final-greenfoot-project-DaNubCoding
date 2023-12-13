import java.io.File;
import java.util.Date;

public class Log  
{
    private static boolean DEBUG;
    
    static {
        // When testing locally, the file will exist meaning it will log, otherwise the file is git-ignored
        File f = new File("DEBUG");
        DEBUG = f.exists();
    }
    
    public static void start() {
        if (!DEBUG) return;
        Date datetime = new Date();
        System.out.println("\n" + formatTime() + " Started and debugging");
    }
    
    public static void debug(String output) {
        if (!DEBUG) return;
        Date datetime = new Date();
        System.out.println(formatTime() + " " + output);
    }
    
    private static String formatTime() {
        Date date = new Date();
        return "[" + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + "]";
    }
}
