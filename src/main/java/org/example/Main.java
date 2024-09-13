package org.example;

import java.sql.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main( String args[] ) {

        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\User\\IdeaProjects\\db log\\src\\main\\java\\org\\example\\meineDB.db");
            c.setAutoCommit(false);
            logger.error("Datenbank Verbunden");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM meineDaten;" );

            if(!rs.next())
            {
                logger.error("warning results are empty");
            } else if (rs.next()) {
                logger.error("Query ausgeführt");
            }
            while ( rs.next() ) {
            System.out.println(rs);
            }


            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            logger.fatal("Verbindung konnte nicht aufgebaut werden");
            System.exit(0);
        }
        logger.error("Operation done successfully");
    }
}