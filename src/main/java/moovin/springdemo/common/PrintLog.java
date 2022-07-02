package moovin.springdemo.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PrintLog {
    private final static Logger logger = LogManager.getLogger(PrintLog.class);

    public static void Print(String message) {
        try {
            System.out.println(message);
            logger.info(message);
        } catch (Exception ex) {
            logger.fatal("Error " + ex);
        }
    }

    public static void PrintError(String message) {
        try {
            System.out.println(message);
            logger.error("Error " + message);
        } catch (Exception ex) {
            logger.fatal("Error Print " + ex);
        }
    }
}
