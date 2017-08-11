package app;

import app.config.Options;
import app.Routes;

public class Main {

    public static void main(String[] args) {

        Options options = new Options();
        options.initialize();

        Routes routes = new Routes(options);
        routes.initialize();

    }

}
