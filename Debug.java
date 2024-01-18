import java.io.File;
import java.util.Date;

/**
 * Convenience class for logging information with a datetime prefix.
 */
public class Debug  
{
    private static boolean DEBUG;
    
    static {
        // When testing locally, the file will exist meaning it will log, otherwise the file is git-ignored
        File f = new File("DEBUG");
        DEBUG = f.exists();
    }
    
    /**
     * Indicate whether the program is in debug mode in the console.
     */
    public static void start() {
        if (!DEBUG) return;
        Date datetime = new Date();
        System.out.println("\n" + formatTime() + " Debugging");
    }
    
    /**
     * Log anything in the console.
     */
    public static void log(Object output) {
        if (!DEBUG) return;
        Date datetime = new Date();
        System.out.println(formatTime() + " " + output);
    }
    
    private static String formatTime() {
        Date date = new Date();
        return "[" + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + "." + System.currentTimeMillis() % 1000 + "]";
    }
}
