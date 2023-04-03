package com.markobozic.parkinglots.config;

import org.flywaydb.core.Flyway;

public class FlywayConfig {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/parking_lots";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";


    private FlywayConfig() {
    }

    public static void migrate() {
        Flyway flyway = Flyway.configure()
                .defaultSchema("public")
                .dataSource(DB_URL, DB_USER, DB_PASSWORD)
                .locations("classpath:db.migration")
                .baselineOnMigrate(true)
                .load();

        flyway.migrate();
    }
}
