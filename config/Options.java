package app.config;

import org.apache.log4j.PropertyConfigurator;

import static spark.Spark.*;

public class Options {

    private final static int port = 1335;
    private final static int maxThreads = 8;
    private final static int minThreads = 2;
    private final static int timeOutMillis = 30000;
    private final static String contentType = "application/json";

    public String getContentType() { return contentType; }

    public void initialize() {
        String log4jConfPath = "src/main/java/app/config/log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
        port(port);
        /*
         * @description :: {NOTE} :: for now thread is disabled. in memory setup is too expensive while playing in my computer
         */
        threadPool(maxThreads, minThreads, timeOutMillis);
    }

}