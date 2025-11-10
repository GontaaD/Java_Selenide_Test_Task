package org.trello;

import io.github.cdimascio.dotenv.Dotenv;

public class SecretsManager {
    private static final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

    public static String get(String key) {

        String env = System.getenv(key);
        if (env != null && !env.isBlank()) {
            return env;
        }

        String localenv = dotenv.get(key);
        if ((localenv != null) && !localenv.isBlank()) {
            return localenv;
        }
        throw new RuntimeException("Environment variable " + key + " is missing");

    }
}

