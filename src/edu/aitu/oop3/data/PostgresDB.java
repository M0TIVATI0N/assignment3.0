package edu.aitu.oop3.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgresDB implements IDB {

    private static final String URL =
            "jdbc:postgresql://aws-1-ap-northeast-1.pooler.supabase.com:5432/postgres?sslmode=require";

    private static final String USER =
            "postgres.dghildsdypaedbehejrk";

    private static final String PASSWORD = loadPassword();

    private static String loadPassword() {
        Properties props = new Properties();
        try (InputStream input = new FileInputStream("config.properties")) {
            props.load(input);

            String value = props.getProperty("DB_PASSWORD");
            if (value == null || value.isBlank()) {
                throw new RuntimeException("DB_PASSWORD is not set in config.properties");
            }
            return value;

        } catch (IOException e) {
            throw new RuntimeException(
                    "Cannot load DB_PASSWORD from config.properties", e
            );
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}


//sudo systemctl start docker

//package edu.aitu.oop3.data;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public  class PostgresDB implements IDB {
//    //  private static final String URL =
//    //          "jdbc:postgresql://aws-1-ap-northeast-1.pooler.supabase.com:5432/postgres?sslmode=require";
//    //  postgresql://postgres.dghildsdypaedbehejrk:[YOUR-PASSWORD]@aws-1-ap-northeast-1.pooler.supabase.com:5432/postgres
//    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
//
//    //    private static final String USER = "postgres.dghildsdypaedbehejrk";
//    //    private static final String PASSWORD = "dgrusMgxGwziXGGS"; // ← DATABASE PASSWORD
//    private static final String USER = "user";
//    private static final String PASSWORD = "secretpassword"; // ← DATABASE PASSWORD
//
//    @Override
//    public  Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(URL, USER, PASSWORD);
//    }
//}