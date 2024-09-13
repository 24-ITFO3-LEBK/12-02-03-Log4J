package org.school;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

public class LoggerDb {
    public static void main(String[] args){
        Logger log = Logger.getLogger("TestLOgger");
        String sql = "SELECT * FROM meineDaten";




        Connection c = null;
        try {
            Path path = Paths.get("C:\\Users\\trung\\OneDrive\\Desktop\\LEBK\\STDMJAVA\\GitFolder\\javaproject\\src\\main\\resources\\meineDB.db");
            log.info("DB was found");
            c = DriverManager.getConnection("jdbc:sqlite:meineDB.db");
            log.info("Connection establish");
        } catch ( Exception e){
            System.err.println(e.getMessage());
        }

    }


}
