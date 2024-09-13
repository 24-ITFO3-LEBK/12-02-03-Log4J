package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Connection c;
        Statement statement = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            statement = c.createStatement();
        } catch ( Exception e ) {
            logger.error( e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        logger.info("Opened database successfully");

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM meineDaten");
            if (!resultSet.next()) {
                logger.warn("Could not fetch any data from datebase");
            } else {
                System.out.printf("----------------------------------%n");
                System.out.printf("| %-3s | %-5s | %-16s |%n", "ID", "WERT", "BESCHREIBUNG");
                System.out.printf("----------------------------------%n");

                do {
                    int id = resultSet.getInt("ID");
                    int wert = resultSet.getInt("WERT");
                    String beschreibung = resultSet.getString("BESCHREIBUNG");
                    System.out.printf("| %-3s | %-5s | %-16s |%n", id, wert, beschreibung);
                } while (resultSet.next());
                logger.info("Data was fetched successfully");
            }
        } catch (Exception e) {
            logger.error("Error while fetching data from datebase");
        }
    }
}