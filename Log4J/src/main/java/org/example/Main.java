package org.example;

import java.sql.*;

import org.apache.logging.log4j.*;

public class Main {
    static final String DB_URL = "jdbc:SQLite:C:\\Users\\wienhoste\\IdeaProjects\\Logging\\Db\\meine DB.db";
    static final String QUERY = "SELECT * FROM meineDaten";

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        try {
            Connection con = DriverManager.getConnection(DB_URL);
            logger.info("Datenbankverbindung hergestellt");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);
            logger.info("Datenbankabfrage erfolgreich");

            int id;
            int wert;
            String beschreibung;

            if (!rs.next()) {
                logger.warn("Datenbankabfrage ist leer");
            } else {
                do {
                    id = rs.getInt("ID");
                    wert = rs.getInt("Wert");
                    beschreibung = rs.getString("Beschreibung");

                    System.out.println("ID: " + id + "\nWert: " + wert + "\nBeschreibung: " + beschreibung + "\n");
                } while (rs.next());
            }
        } catch (SQLException e) {
            logger.fatal("Datenbankverbindung fehlgeschlagen");
        }
    }
}