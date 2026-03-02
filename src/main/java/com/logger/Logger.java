package mercator.logger;

public class Logger {
    private static java.util.logging.Logger logger = null;

    public Logger() {
        logger = java.util.logging.Logger.getLogger(this.getClass().getName());
    }
    public static void log(String msg ) {
        logger.info(msg);
    }
    public static void warn(String msg ) {
        logger.warning(msg);
    }
}
