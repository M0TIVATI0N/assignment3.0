package edu.aitu.oop3.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgresDB implements IDB {
    // 1. Статическое поле для единственного экземпляра
    private static PostgresDB instance;

    private final String URL = "jdbc:postgresql://aws-1-ap-northeast-1.pooler.supabase.com:5432/postgres?sslmode=require";
    private final String USER = "postgres.dghildsdypaedbehejrk";
    private final String PASSWORD;

    // 2. Приватный конструктор
    private PostgresDB() {
        this.PASSWORD = loadPassword();
    }

    // 3. Публичный статический метод для получения инстанса
    public static synchronized PostgresDB getInstance() {
        if (instance == null) {
            instance = new PostgresDB();
        }
        return instance;
    }

    private String loadPassword() {
        Properties props = new Properties();
        // Ищет файл в корне проекта
        try (InputStream input = new FileInputStream("config.properties")) {
            props.load(input);
            String value = props.getProperty("DB_PASSWORD");
            if (value == null || value.isBlank()) {
                throw new RuntimeException("DB_PASSWORD not found in config.properties");
            }
            return value;
        } catch (IOException e) {
            throw new RuntimeException("Could not find config.properties in project root!", e);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}