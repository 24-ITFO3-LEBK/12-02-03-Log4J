import java.sql.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBReader {
    private static final Logger logger = LogManager.getLogger(DBReader.class);

    public static void main( String args[] )  {
        Connection c = null;
        Statement stmt = null;
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ramon\\IdeaProjects\\untitled1\\src\\lib\\meine DB.db");
                c.setAutoCommit(false);
                logger.info(System.currentTimeMillis() + " Datenbankverbindung erfolgt");
            } catch (ClassNotFoundException  | SQLException e) {
                logger.error(System.currentTimeMillis() + " Datenbankverbindung nicht erfolgt");
                throw new RuntimeException(e);
            }

        try {
                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM meineDaten");

                if (rs.isBeforeFirst()) {
                    while (rs.next()) {
                        int id = rs.getInt("ID");
                        int name = rs.getInt("Wert");
                        String age = rs.getString("Beschreibung");


                        System.out.println("ID = " + id);
                        System.out.println("Wert = " + name);
                        System.out.println("Beschreibung = " + age);
                    }
                    logger.info(System.currentTimeMillis() + " Die Daten wurden ausgegeben");
                } else {
                    logger.error(System.currentTimeMillis() + " Es wurden keine Daten gegeben");
                }
                    rs.close();
                    stmt.close();
                    c.close();
            }catch (SQLException e){
                logger.error(System.currentTimeMillis() + " Der SQL Befehl ist fehlgeschlagen");

            }


    }
}